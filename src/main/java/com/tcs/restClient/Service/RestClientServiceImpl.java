package com.tcs.restClient.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.tcs.restClient.Request.Request;
@Service
public class RestClientServiceImpl implements RestClientService{

	@Override
	public Object getResponse(Request request) throws UniformInterfaceException, ClientHandlerException, JSONException{
		
		Gson gson =  new Gson();
		Object json=null;
		JSONObject serviceInputParameters = null;
		Object response = null;
		WebResource.Builder resourceBulder;
		Client c = Client.create();
		WebResource resource = c.resource(request.getServiceUrl());
		if(request.getServiceInputParameters()!=null){
		json =  gson.fromJson(request.getServiceInputParameters(),Object.class);
		}
		 if(request.getServiceQueryParameters()!=null ){
			 MultivaluedMap inputParams = new MultivaluedMapImpl();
				for(int i=0;i<serviceInputParameters.names().length();i++){
					inputParams.add(serviceInputParameters.names().getString(i),serviceInputParameters.get(serviceInputParameters.names().getString(i)));
				}
				resource = resource.queryParams(inputParams);
		 }
		 System.out.println(request.getServiceProduces());
			 switch(request.getServiceProduces()){
			 case "MediaType.APPLICATION_JSON":
				 resourceBulder =resource.accept(MediaType.APPLICATION_JSON);
				 break;
			 case "MediaType.TEXT_PLAIN":
				 resourceBulder =resource.accept(MediaType.TEXT_PLAIN);
				 break;
			 case "MediaType.APPLICATION_FORM_URLENCODED":
				 resourceBulder =resource.accept(MediaType.APPLICATION_FORM_URLENCODED);
				 break;
			 case "MediaType.APPLICATION_XHTML_XML":
				 resourceBulder =resource.accept(MediaType.APPLICATION_XHTML_XML);
				 break;
			 case "MediaType.APPLICATION_XML":
				 resourceBulder =resource.accept(MediaType.APPLICATION_XML);
				 break;
			 default:
				 resourceBulder =resource.getRequestBuilder();
				 break;
			 }
			 switch(request.getServiceConsumes()){
			 case "MediaType.APPLICATION_JSON":
				 resourceBulder =resourceBulder.type(MediaType.APPLICATION_JSON);
				 break;
			 case "MediaType.TEXT_PLAIN":
				 resourceBulder =resourceBulder.type(MediaType.TEXT_PLAIN);
				 break;
			 case "MediaType.APPLICATION_FORM_URLENCODED":
				 resourceBulder =resourceBulder.type(MediaType.APPLICATION_FORM_URLENCODED);
				 break;
			 case "MediaType.APPLICATION_XHTML_XML":
				 resourceBulder =resourceBulder.type(MediaType.APPLICATION_XHTML_XML);
				 break;
			 case "MediaType.APPLICATION_XML":
				 resourceBulder =resourceBulder.type(MediaType.APPLICATION_XML);
				 break;
			default:
				 resourceBulder =resourceBulder;
				 break;
			 }
			 if(request.getServiceInputParameters()!=null){
			 if(request.getServiceProduces().contains("TEXT")){
				 if(request.getServiceMethod().equals("POST"))
					 response = resourceBulder.post(String.class,json);
				 else if(request.getServiceMethod().equals("PUT"))
					 response = resourceBulder.put(String.class,json);
				 else if(request.getServiceMethod().equals("DELETE"))
					 response = resourceBulder.delete(String.class,json);
			 }
			 else{
				 if(request.getServiceMethod().equals("POST"))
					 response = resourceBulder.post(Object.class,json);
				 else if(request.getServiceMethod().equals("PUT"))
					 response = resourceBulder.put(Object.class,json);
				 else if(request.getServiceMethod().equals("DELETE"))
					 response = resourceBulder.delete(Object.class,json);
			 }
			 }
			 else{
				 if(request.getServiceProduces().contains("TEXT")){
					 switch(request.getServiceMethod()){
					 case "POST":
						 response =resourceBulder.post(String.class);
						 break;
					 case "PUT":
						 response =resourceBulder.put(String.class);
						 break;
					 case "DELETE":
						 response =resourceBulder.delete(String.class);
						 break;
					 case "GET":
						 response =resourceBulder.get(String.class);
						 break;
					default:
						 break;
					 }
					 }
					 else{
							 switch(request.getServiceMethod()){
							 case "POST":
								 response =resourceBulder.post(Object.class);
								 break;
							 case "PUT":
								 response =resourceBulder.put(Object.class);
								 break;
							 case "DELETE":
								 response =resourceBulder.delete(Object.class);
								 break;
							 case "GET":
								 response =resourceBulder.get(Object.class);
								 break;
							default:
								 break;
							 } 
					 }
			 }
		return response;
}
}
