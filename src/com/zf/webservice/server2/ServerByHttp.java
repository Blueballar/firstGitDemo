package com.zf.webservice.server2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ServerByHttp {
	public static void main(String[] args) {

		try {
			doRequest2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void doRequest1(){
		
		try {
			URL url = new URL("http://127.0.0.1:8000/weathersearch");
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			String requestString = requestString("����");
			System.out.println("requestString:"+requestString);
			urlConnection.getOutputStream().write(requestString.getBytes());
			
			//��ȡ��Ӧ���ݣ�
			InputStream inputStream = urlConnection.getInputStream();
			ByteArrayOutputStream bis = new ByteArrayOutputStream();
			byte bs[] = new byte[1024];
			int length = -1;
			while((length=inputStream.read(bs))>0){
				bis.write(bs, 0, length);
			}
			String result = bis.toString("utf-8");
			System.out.print(result);
			bis.close();
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void doRequest2() throws IOException{
		long startTime = System.currentTimeMillis();
		//��ȡ����
		URL url = new URL("http://127.0.0.1:8000/weathersearch");
		//������
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		//��������ʽ
		httpURLConnection.setRequestMethod("POST");
		//����Content-Type
		httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		//�����������Ӧ
		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true);
		
		String requestString = requestString("����");
		System.out.println(requestString);
		
		//����SOAPЭ��
		httpURLConnection.getOutputStream().write(requestString.getBytes());
		
		//������Ӧ����
		InputStream inputStream = httpURLConnection.getInputStream();
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		int len = -1;
		byte[] b = new byte[1024];
		//��inputStreamд��byteArrayOutputStream
		while((len = inputStream.read(b, 0, 1024))!= -1){
			byteArrayOutputStream.write(b, 0, len);
		}
		//��ȡ��Ӧ����
		String responseString = byteArrayOutputStream.toString("utf-8");
		System.out.println(responseString);
		inputStream.close();
		byteArrayOutputStream.close();
		long endTime = System.currentTimeMillis();
		System.out.println("����ʱ�䣺 "+ (endTime - startTime));
	}
//	<?xml version="1.0" ?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><ns2:queryWeather xmlns:ns2="http://server.webservice.zf.com/"><arg0>西安</arg0></ns2:queryWeather></S:Body></S:Envelope>
	private static String requestString(String cityName){
		String xmlString = "<?xml version='1.0' ?>"+
		"<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
        "<S:Body>"+
        "<ns2:queryWeather xmlns:ns2=\"http://server.webservice.zf.com/\">"+
        "<arg0>"+ cityName +"</arg0>"+
        "</ns2:queryWeather>"+
        "</S:Body>"+
        "</S:Envelope>";
		return xmlString;
	}
}
