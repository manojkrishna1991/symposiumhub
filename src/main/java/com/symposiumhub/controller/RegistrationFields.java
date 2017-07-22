package com.symposiumhub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.Mongo.AddField;
import com.symposiumhub.Mongo.DynamicFormDataDroplet;
import com.symposiumhub.Mongo.SymposiumDynamicFormHandler;
import com.symposiumhub.Mongo.SymposiumFieldInfo;
import com.symposiumhub.Mongo.Values;
import com.symposiumhub.datasource.EventRepositoryComponent;

/**
 * 
 * @author manoj this controller facilitates the modifying the fields people
 *         register for a symposium
 */
@Controller
public class RegistrationFields {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EventRepositoryComponent eventRepository;

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/registrationfields/{eventId}", method = RequestMethod.GET)
	public ModelAndView registrationFieldsDashboard(@PathVariable String eventId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("registrationfieldsdashboard");
		model.addObject("eventId", eventId);
		model.addObject("event", eventRepository.findEventById(Integer.valueOf(eventId)));
		Query searchUserQuery = new Query(Criteria.where("symposimuId").is(eventId));
		List<SymposiumDynamicFormHandler> find = mongoTemplate.find(
				searchUserQuery, SymposiumDynamicFormHandler.class);
		model.addObject("symposium", find);
		return model;

	}
	
	
	

	

	/**
	 * 
	 * @param requestsymposiumID
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/registrationfields", method = RequestMethod.POST)
	public ModelAndView SaveregistrationFields(String fieldName,
			String fieldType, String eventId,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/registrationfields/"+eventId);

		SymposiumFieldInfo info = new SymposiumFieldInfo();
		
		
		info.setId(UUID.randomUUID().toString());
		info.setName(fieldName);
		info.setType(fieldType);
		info.setValidation("required");
		SymposiumDynamicFormHandler dynaoForm = new SymposiumDynamicFormHandler();
		dynaoForm.setSymposimuId(eventId);
		if(fieldType.equalsIgnoreCase("checkbox") || fieldType.equalsIgnoreCase("radio")){
			dynaoForm.setName(fieldName);
		}
		ArrayList<SymposiumFieldInfo> array = new ArrayList<>();
		array.add(info);
		dynaoForm.setSymposiumFieldInfo(array);
		mongoTemplate.save(dynaoForm);

		Query searchUserQuery = new Query(Criteria.where("symposimuId").is(eventId));
		List<SymposiumDynamicFormHandler> find = mongoTemplate.find(
				searchUserQuery, SymposiumDynamicFormHandler.class);
		model.addObject("symposium", find);
		return model;

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/dynamicRegistrationFormHandler", method = RequestMethod.GET)
	public ModelAndView SaveRegistrationData(
			Map<String, String> allRequestParams, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("dynamicRegistrationDroplet");

		DynamicFormDataDroplet dynamicFormDataDroplet = new DynamicFormDataDroplet();
		dynamicFormDataDroplet.setSymposiumID("1");
		List<Values> values = new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		
		for (Entry<String, String[]> entry : params.entrySet())
		{
			String key = entry.getKey() ;
			String value = StringUtils.join(entry.getValue(), ',');
			Values valueObject = new Values();
			valueObject.setId(UUID.randomUUID().toString());
			valueObject.setName(key);
			valueObject.setValue(value);
			values.add(valueObject);
			
		    System.out.println(entry.getKey() + "/" );
		}
		
		

		dynamicFormDataDroplet.setValues(values);
		mongoTemplate.save(dynamicFormDataDroplet);

		Query searchUserQuery = new Query(Criteria.where("symposiumID").is("1"));
		List<DynamicFormDataDroplet> find = mongoTemplate.find(searchUserQuery,
				DynamicFormDataDroplet.class);
		Query searchUserQuery1 = new Query(Criteria.where("symposimuId")
				.is("1"));
		List<SymposiumDynamicFormHandler> find1 = mongoTemplate.find(
				searchUserQuery1, SymposiumDynamicFormHandler.class);
		model.addObject("DynamicFormForm", find);
		model.addObject("symposium", find1);
		return model;

	}

	/**
	 * 
	 * @param requestsymposiumID
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addField", method = RequestMethod.POST)
	public @ResponseBody String AddField(@RequestBody AddField addField, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		SymposiumDynamicFormHandler findById = mongoTemplate.findById(addField.getFormId(), SymposiumDynamicFormHandler.class);
				
		SymposiumFieldInfo info = new SymposiumFieldInfo();
		info.setId(UUID.randomUUID().toString());
		info.setName(addField.getName());
		info.setType(addField.getFieldType());
		info.setValidation("required");
		findById.getSymposiumFieldInfo().add(info);
		
		
		mongoTemplate.save(findById);

		

		
		return "success";

	}
	
	
	/**
	 * 
	 * @param requestsymposiumID
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/editoption", method = RequestMethod.POST)
	public ModelAndView editField(SymposiumFieldInfo symposiumFieldInfo,String eventId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/registrationfields/"+eventId);
		
		mongoTemplate.updateMulti(
			    new Query(Criteria.where("symposiumFieldInfo.id").is(symposiumFieldInfo.getId())),
			    new Update().set("symposiumFieldInfo.$.name", symposiumFieldInfo.getName()).set("symposiumFieldInfo.$.type", symposiumFieldInfo.getType()),
			    SymposiumDynamicFormHandler.class
			);
		
		mongoTemplate.save(symposiumFieldInfo);

		

		
		return model;

	}
	
	@RequestMapping(value = "/deleteoption", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteoption(String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		mongoTemplate.remove( new Query(Criteria.where("symposiumFieldInfo.id").is(id)), SymposiumDynamicFormHandler.class);
	
		return "success";

	}
}
