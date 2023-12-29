package com.symposiumhub.controller;

import io.github.pixee.security.Filenames;
import java.awt.print.Book;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.dto.SymposiumDto;
import com.symposiumhub.model.HelloMessage;
import com.symposiumhub.model.Profile;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumRegistrationFields;
import com.symposiumhub.service.ActiveUserStore;
import com.symposiumhub.service.LoggedUser;
import com.symposiumhub.service.SymposiumServiceInterface;
import com.symposiumhub.util.FileUtils;

/**
 * This Spring controller class implements a CSV file download functionality.
 * 
 * @author manoj
 *
 */
@Controller
public class CSVFileDownloadController {

	@Resource(name = "sympService")
	private SymposiumServiceInterface sympService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Resource
	private SessionRegistry sessionRegistry;

	@Value("${imagepath}")
	private String imagePath;
	@Resource
	private GreetingController greeting;
	@Resource
	private ActiveUserStore activeUserStore;

	@Resource
	private ProfileComponent profile;

	private static final Log logger = LogFactory.getLog(CSVFileDownloadController.class);

	@RequestMapping(value = "/downloadCSV/{symposiumId}")
	public void downloadCSV(@PathVariable String symposiumId, HttpServletResponse response) throws IOException {

		String csvFileName = "symposiumlist.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);

		List<RegisterForASymposium> listsymposiumregistration = sympService.getSymposiumRegistrations(symposiumId);

		// uses the Super CSV API to generate CSV data from the model data
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id", "fullName", "phoneNumber", "email", "collegeName", "collegeId", "registerDate" };

		csvWriter.writeHeader(header);
		List<RegisterForASymposium> idModidifed = new ArrayList<RegisterForASymposium>();
		int i = 0;
		for (RegisterForASymposium sFields : listsymposiumregistration) {
			i = i + 1;
			sFields.setId(i);

		}

		for (RegisterForASymposium sFields : listsymposiumregistration) {
			csvWriter.write(sFields, header);
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = user.getUsername();

		logger.info(username);

		List<Profile> profile2 = this.profile.getProfile(user.getUserId());

		if (!profile2.isEmpty()) {
			model.addAttribute("profile", profile2.get(0));
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("profile");
		return modelAndView;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView profileSave(Profile profile, Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = user.getUsername();

		logger.info(username);

		List<Profile> profile2 = this.profile.getProfile(user.getUserId());

		if (profile2.isEmpty()) {
			this.profile.add(profile);
		}

		if (!profile2.isEmpty()) {

			if (profile.getPhotoFile() != null) {
				MultipartFile file = profile.getPhotoFile();
				String filename = Filenames.toSimpleFileName(file.getOriginalFilename());
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

				profile.setPhoto(Linkpath);
				
				if (!file.isEmpty()) {
					try {
						byte[] bytes = file.getBytes();
						File finalImagepath = new File(finalpath);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalImagepath));
						stream.write(bytes);
						stream.close();
						boolean isCompressed = FileUtils.CompressImageFile(file, thumbpath);
						if (!isCompressed) {
							profile.setPhoto(Linkpath);

						}

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			profile.setAboutMe(StringUtils.isEmpty(profile.getAboutMe()) ? profile2.get(0).getAboutMe():profile.getAboutMe());
			profile.setGender(StringUtils.isEmpty(profile.getGender()) ? profile2.get(0).getGender():profile.getGender());
			profile.setPhoto(StringUtils.isEmpty(profile.getPhoto()) ? profile2.get(0).getPhoto():profile.getPhoto());
			profile.setPlace(StringUtils.isEmpty(profile.getPlace()) ? profile2.get(0).getPlace():profile.getPlace());
			profile.setUserId(StringUtils.isEmpty(profile.getUserId()) ? profile2.get(0).getUserId():profile.getUserId());
			

			this.profile.update(profile);
			model.addAttribute("profile", profile2.get(0));
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		String messagetext="profile sucessfully updated";
		
		modelAndView.setViewName("redirect:/profile?message=success&messagetext="+messagetext+"");
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewprofile/{profileId}", method = RequestMethod.GET)
	public ModelAndView viewprofile(@PathVariable Integer profileId,Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = user.getUsername();

		logger.info(username);

		List<Profile> profile2 = this.profile.getProfileByProfileId(profileId);

		if (!profile2.isEmpty()) {
			model.addAttribute("profile", profile2.get(0));
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("viewprofile");
		return modelAndView;
	}

}
