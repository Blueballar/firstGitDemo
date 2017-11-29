package com.zf.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.zf.webservice.server.WeatherServiceImpl;
import com.zf.webservice.server.WeatherServiceImplService;

public class Client {
	public static void main(String[] args) {
		new Client().call1();
	}
	/**
	 * 直接操作命令提供的服务：service，时间 587
	 */
	private void call1(){
		long beginTime = System.currentTimeMillis();
		/**
		 * 通过服务视图找到portType
		 */
		WeatherServiceImplService service = new WeatherServiceImplService();
		//调用webservice方法
		WeatherServiceImpl impl = service.getWeatherServiceImplPort();
		System.out.println("天气："+impl.queryWeather("西安"));
		long endTime = System.currentTimeMillis();
		System.out.println("运行时间"+(beginTime-endTime));
	}
	/**
	 * 运行时间：583
	 */
	private void call2(){
		long beginTime = System.currentTimeMillis();
		try {
			URL url = new URL("http://127.0.0.1:8001/weathersearch");
			QName qName = new QName("http://server.webservice.zf.com/","WeatherServiceImplService");
			//创建服务视图
			Service service = Service.create(url, qName);
			///从视图服务中获取portType对象
			WeatherServiceImpl impl = service.getPort(WeatherServiceImpl.class);
			System.out.println("天气："+impl.queryWeather("西安"));
			long endTime = System.currentTimeMillis();
			System.out.println("运行时间"+(beginTime-endTime));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
