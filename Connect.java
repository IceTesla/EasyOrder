package com.example.easyorder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.client.methods.HttpPost;
public class Connect {
	private static final String UrlVar = "";
	public HttpURLConnection getConnection(String urlpath)
	{
		String finalurl = UrlVar + urlpath;
		HttpURLConnection connection = null;
		try{
			URL url =new  URL(finalurl);
	        connection=(HttpURLConnection)url.openConnection();
			connection.setDoInput(true);  //允许输入流
            connection.setDoOutput(true); //允许输出流
            connection.setUseCaches(false);  //不允许使用缓存
            connection.setRequestMethod("POST");  //请求方式
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return connection;

	}
	public HttpPost getHttpPost(String uripath) 
	{	
		HttpPost httpPost=new HttpPost(UrlVar+uripath);	
	
		System.out.println(UrlVar+uripath);
		return httpPost;
	}//http通信前的初始化

}
