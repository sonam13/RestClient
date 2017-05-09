package com.tcs.restClient.Controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.tcs.restClient.Request.Request;
import com.tcs.restClient.Service.RestClientService;
@Controller
public class RestClientController {
	@Autowired RestClientService restClientService; 
	@RequestMapping(value = "/restClient", method = RequestMethod.POST)
	public @ResponseBody Object getResponse(@RequestBody Request request) throws UniformInterfaceException, ClientHandlerException, JSONException {
 		return restClientService.getResponse(request);
	}
}
