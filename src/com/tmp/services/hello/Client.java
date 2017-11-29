package com.tmp.services.hello;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.zf.webservice.server.WeatherServiceImpl;
 
public class Client {
	public static void main(String[] args) {
		try {
			ws_call();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ws_call() throws MalformedURLException{
		URL wsdlUrl = new URL("http://localhost:8999/services/hello?wsdl");
		WeatherServiceService service = new WeatherServiceService(wsdlUrl);
		HelloWS helloWS = service.getHelloWSPort();
		List<Weather> ws = helloWS.queryWeather("Î÷°²", "ÉÂÎ÷");
		System.out.println(ws.get(0).getWeather());
	}
	public static void cxf_call(){
		/*JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(WeatherServiceImpl.class);
		WeatherService queryWeather = factoryBean.create(WeatherService.class);*/
	}
}
