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
	 * ֱ�Ӳ��������ṩ�ķ���service��ʱ�� 587
	 */
	private void call1(){
		long beginTime = System.currentTimeMillis();
		/**
		 * ͨ��������ͼ�ҵ�portType
		 */
		WeatherServiceImplService service = new WeatherServiceImplService();
		//����webservice����
		WeatherServiceImpl impl = service.getWeatherServiceImplPort();
		System.out.println("������"+impl.queryWeather("����"));
		long endTime = System.currentTimeMillis();
		System.out.println("����ʱ��"+(beginTime-endTime));
	}
	/**
	 * ����ʱ�䣺583
	 */
	private void call2(){
		long beginTime = System.currentTimeMillis();
		try {
			URL url = new URL("http://127.0.0.1:8001/weathersearch");
			QName qName = new QName("http://server.webservice.zf.com/","WeatherServiceImplService");
			//����������ͼ
			Service service = Service.create(url, qName);
			///����ͼ�����л�ȡportType����
			WeatherServiceImpl impl = service.getPort(WeatherServiceImpl.class);
			System.out.println("������"+impl.queryWeather("����"));
			long endTime = System.currentTimeMillis();
			System.out.println("����ʱ��"+(beginTime-endTime));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
