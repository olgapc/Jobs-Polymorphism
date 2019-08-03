package com.itproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itproject.gsheet.GSheetController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/global")
public class GlobalRestController {
	
	@Autowired
	private GSheetController controller;
	
	// Import GoogleSheet in Database
	// Call from the web browser http://localhost:8080/global/import
	@PostMapping(value = "/import", produces = "application/json;charset=UTF-8")
	public String importGSheet() {

		return "Data Imported";
	}
	
	// Object to Json converter
	private String toJson(Object object){
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
    
}