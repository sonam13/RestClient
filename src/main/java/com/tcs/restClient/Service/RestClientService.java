package com.tcs.restClient.Service;

import org.json.JSONException;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.tcs.restClient.Request.Request;

public interface RestClientService {

	public Object getResponse(Request request) throws UniformInterfaceException, ClientHandlerException, JSONException;
}
