package com.zf.webservice;

public class Snippet {
	private static String requestString(String cityName){
		String xmlString = "<?xml version='1.0' ?>"+
		"<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
	    "<S:Body>"+
	    "<ns2:queryWeather xmlns:ns2=\"http://server.weather.ws_jaxws_server/\">"+
	    "<arg0>"+ cityName +"</arg0>"+
	    "</ns2:queryWeather>"+
	    "</S:Body>"+
	    "</S:Envelope>";
		return xmlString;
	}
}

