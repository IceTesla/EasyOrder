package com.example.easyorder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class Operation {
	@SuppressWarnings("deprecation")
	public String loginIn(String url,String id,String password)//login in
	{
		String result =  null;
		Connect conn = new Connect();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("password", password));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params,HTTP.UTF_8);
			HttpPost httpPost = conn.getHttpPost(url);
			System.out.println(httpPost.toString());
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) 
			{
				result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");			
			}
			else
			{
				result="1002";
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}//定义一个list，该list的数据类型是NameValuePair,用于Java向url发送Post请求。
			
	
			
	
	

		
		
			 

		
		
