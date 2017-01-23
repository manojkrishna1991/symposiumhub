package com.spring.security.social.login.example.controller;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFields;
import com.spring.security.social.login.example.service.SymposiumServiceInterface;

/**
 * This Spring controller class implements a CSV file download functionality.
 * 
 * @author manoj
 *
 */
@Controller
public class CSVFileDownloadController {

	@Resource(name="sympService")
	private SymposiumServiceInterface  sympService;
	 
	@RequestMapping(value = "/downloadCSV/{symposiumId}")
	public void downloadCSV(@PathVariable String symposiumId,HttpServletResponse response) throws IOException {

		String csvFileName = "symposiumlist.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		List<RegisterForASymposium> listsymposiumregistration=sympService.getSymposiumRegistrations(symposiumId) ;

		// uses the Super CSV API to generate CSV data from the model data
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
				CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id","fullName", "phoneNumber", "email", "collegeName",
				"collegeId", "registerDate"};
		
		csvWriter.writeHeader(header);
		List<RegisterForASymposium> idModidifed=new ArrayList<RegisterForASymposium>();
		int i = 0;
		for (RegisterForASymposium sFields : listsymposiumregistration) {
			i = i + 1;
			sFields.setId(i);
			
			
		}

		for (RegisterForASymposium  sFields  : listsymposiumregistration) {
			csvWriter.write(sFields, header);
		}

		csvWriter.close();
	}
}
