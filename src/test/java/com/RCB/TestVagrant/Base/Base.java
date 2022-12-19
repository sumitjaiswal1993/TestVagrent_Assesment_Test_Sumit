package com.RCB.TestVagrant.Base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Base {
	public RequestSpecification req;
	public ResponseSpecification resspec;
	
	
	public ResponseSpecification responseSpecification() {
		
	    resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    return resspec;
	}
	
	public RequestSpecification requestSpecification()  {		
		PrintStream log;
		try {
			if(req==null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			  req = new RequestSpecBuilder().setBaseUri("url")
					  .addFilter(RequestLoggingFilter.logRequestTo(log))
					  .addFilter(ResponseLoggingFilter.logResponseTo(log))
					 .setContentType(ContentType.JSON).build();
			  return req;}
			return req;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return req;
		}
		
	}


}
