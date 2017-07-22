package com.symposiumhub.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.database.dao.ReviewDao;
import com.symposiumhub.datasource.CollegeComponent;
import com.symposiumhub.datasource.NotificationComponent;
import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.model.College;
import com.symposiumhub.model.Notification;
import com.symposiumhub.model.Profile;
import com.symposiumhub.model.Review;
import com.symposiumhub.util.FileUtils;

@Controller
public class ReviewController {

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private CollegeComponent college;

	@Value("${imagepath}")
	private String imagePath;

	@Autowired
	private NotificationComponent notificationComponent;

	@Autowired
	private ProfileComponent profile;

	private static final Log logger = LogFactory.getLog(ReviewController.class);

	@RequestMapping(value = "/page/{collegename}", method = RequestMethod.GET)
	public ModelAndView profile(@PathVariable Integer collegename, Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = user.getUsername();

		List<College> collegeById = college.getCollegeById(collegename);

		if (!collegeById.isEmpty()) {
			model.addAttribute("college", collegeById.get(0));

			String compressedPath = collegeById.get(0).getCompressedPath();
			String compressedPathOne = collegeById.get(0).getCompressedPathOne();
			List<String> compressedPathList = null;
			
			if(!StringUtils.isEmpty(compressedPath)){
				compressedPathList = new ArrayList<String>();
				compressedPathList.add(compressedPath);
			}
			
			if (!StringUtils.isEmpty(compressedPathOne)) {
				if(compressedPathList==null){
					compressedPathList = new ArrayList<String>();
				}
					compressedPathList.add(compressedPathOne);
			}
			model.addAttribute("images", compressedPathList);

			List<Review> reviewsByCollgeId = reviewDao.getReviewsByCollgeId(collegename);

			model.addAttribute("reviews", reviewsByCollgeId);

		}

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("page");
		return modelAndView;
	}

	@RequestMapping(value = { "/review/{start}" }, method = RequestMethod.GET)
	public ModelAndView review(@PathVariable Integer start, Integer end, Model model, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("page", start);

		start = start - 1;

		List<College> college2 = college.getCollegeLimit(start * 100);

		model.addAttribute("college", college2);

		modelAndView.setViewName("review");

		return modelAndView;
	}

	@RequestMapping(value = { "/review" }, method = RequestMethod.GET)
	public ModelAndView review(Model model, HttpServletRequest request) {

		return review(1, 0, model, request);
	}

	@RequestMapping(value = "/writereview", method = RequestMethod.GET)
	public ModelAndView writeReview(Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView modelAndView = new ModelAndView();

		List<College> college2 = college.getCollege();

		model.addAttribute("college", college2);

		modelAndView.setViewName("write-review");

		model.addAttribute("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/writereview", method = RequestMethod.POST)
	public ModelAndView writeReviewSave(Review review, Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView modelAndView = new ModelAndView();

		reviewDao.saveReview(review);

		college.updateRating(reviewDao.getAverageReviews(review.getCollegeId()), review.getCollegeId());

		model.addAttribute("user", user);

		String messagetext = "you review is posted";

		Notification notification = new Notification();
		notification.setNotification(
				user.getUsername() + "posted a review <a href=\"/page/" + review.getCollegeId() + "\"</a>");
		notification.setType("REVIEW");

		List<Profile> profile2 = this.profile.getProfile(user.getUserId());

		if (!profile2.isEmpty()) {
			notification.setFromprofileid(profile2.get(0).getId());
		}

		notificationComponent.add(notification);

		modelAndView.setViewName(
				"redirect:/page/" + review.getCollegeId() + "?message=success&messagetext=" + messagetext + "");
		return modelAndView;
	}

	@RequestMapping(value = { "/addPhoto/{id}" }, method = RequestMethod.GET)
	public ModelAndView uploadphotos(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView();
		model.setViewName("review-add-photo");

		List<College> collegeById = college.getCollegeById(id);

		if (!collegeById.isEmpty()) {
			model.addObject("college", collegeById.get(0));
		}

		return model;
	}

	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
	public ModelAndView imageUpload(@RequestParam("file") List<MultipartFile> files,
			@RequestParam("collegeid") Integer collegeId, Model model, HttpServletRequest request) throws IOException {

		List<College> collegeById = college.getCollegeById(collegeId);

		List<String> imageList = new ArrayList<>();
		List<String> imageListCompressedPath = new ArrayList<>();

		int index = 0;

		for (MultipartFile file : files) {

			String filename = file.getOriginalFilename();
			if (StringUtils.isEmpty(filename)) {
				continue;
			}
			String id = UUID.randomUUID().toString();
			String Imagedirectorypath = imagePath;
			File Imagedirectory = new File(imagePath);
			if (!Imagedirectory.exists()) {
				if (Imagedirectory.mkdir()) {
					logger.debug("Directory created successfully");
				} else {
					logger.debug("Failed to create directory!");
				}
			}
			FileUtils.MakeDirectory(Imagedirectorypath);
			// count number of files

			int filesCount = FileUtils.getCount(Imagedirectorypath, null);
			int thumbCount = FileUtils.getCount(Imagedirectorypath, FileUtils.THUMB);

			// FileNames
			String name = FileUtils.getFilePath(filename, filesCount, null);
			String thumbName = FileUtils.getFilePath(filename, thumbCount, "_thumb");

			// File path to save

			String finalpath = (Imagedirectorypath + "/" + name);
			String thumbpath = FileUtils.getFullPath(Imagedirectorypath, thumbName);

			// link path to store in db
			String Linkpath = "/" + "Images" + "/" + name;
			String thumbLinkPath = "/" + "Images" + "/" + thumbName;

			// set the image url in sympsoium
			imageList.add(Linkpath);
			imageListCompressedPath.add(thumbLinkPath);

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					File finalImagepath = new File(finalpath);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalImagepath));
					stream.write(bytes);
					stream.close();
					boolean isCompressed = FileUtils.CompressImageFile(file, thumbpath);
					if (!isCompressed) {
						imageListCompressedPath.add(thumbLinkPath);

					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			if (index == 0) {
				collegeById.get(0).setCompressedPath(thumbLinkPath);
			}

			if (index == 1) {
				collegeById.get(0).setCompressedPathOne(thumbLinkPath);
			}

			index++;

		}
		college.update(collegeById.get(0));
		ModelAndView models = new ModelAndView("redirect:/page/" + collegeId);
		return models;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public void review(String code, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://accounts.google.com/o/oauth2/token");
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("code", code));
		pairs.add(new BasicNameValuePair("client_id",
				"569882321678-21d4jae0fg82b04hfkf4jebgt4nb8p2l.apps.googleusercontent.com"));
		pairs.add(new BasicNameValuePair("client_secret", "eqz5_WDV8idjy1s7HA4FG92V"));
		pairs.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/contact"));
		pairs.add(new BasicNameValuePair("grant_type", "authorization_code")); // Leave
																				// this
																				// line
																				// how
																				// it
																				// is
		post.setEntity(new UrlEncodedFormEntity(pairs));
		org.apache.http.HttpResponse responses = client.execute(post);
		String responseBody = EntityUtils.toString(responses.getEntity());

		response.getWriter().println(responseBody);
	}

}
