package cn.IceTesla.easyorder.Network;

import android.util.Log;

import java.io.IOException;
	import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
	import java.util.List;

	import org.apache.http.HttpEntity;
	import org.apache.http.HttpResponse;
	import org.apache.http.HttpStatus;
	import org.apache.http.NameValuePair;
	import org.apache.http.client.ClientProtocolException;
	import org.apache.http.client.HttpClient;
	import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

	import org.apache.http.impl.client.DefaultHttpClient;
	import org.apache.http.message.BasicNameValuePair;
	import org.apache.http.protocol.HTTP;
	import org.apache.http.util.EntityUtils;

import static org.apache.http.protocol.HTTP.UTF_8;


public class Operation {
		@SuppressWarnings("deprecation")
		public String loginIn(String id,String password)//login in
		{
			String result =  null;
			String url = "LoginInServlet";
			Connect conn = new Connect();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", id));
			params.add(new BasicNameValuePair("password", password));
			try {
				HttpEntity entity = new UrlEncodedFormEntity(params,UTF_8);
				HttpPost httpPost = conn.getHttpPost(url);
				System.out.println(httpPost.toString());
				httpPost.setEntity(entity);
				HttpClient client = new DefaultHttpClient();
				HttpResponse httpResponse = client.execute(httpPost);
				int temp = httpResponse.getStatusLine().getStatusCode();
				System.out.println(temp);
				if (temp==HttpStatus.SC_OK) 
				{
					result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");			
				}
				else
				{
					result="1003";
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
		public String getMenu()//��ȡ�˵�
		{
			String result = null;
			Connect conn = new Connect();
			String url = "MenuServlet";
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = conn.getHttpGet(url);
			try {
				HttpResponse response = client.execute(httpGet);
				if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) 
				{
					result=EntityUtils.toString(response.getEntity(), "utf-8");
				}
				else
				{
					result = "2002";
				}
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("1","网络异常");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("1","网络异常");
			}
			return result;
			
		}
		
		
		public String placeOrder(String Oid,String Tid,String Sid,String PNum,Date time,String Status)//�µ�
		{
			String url = "OrderTblServlet";
			String result =  null;
			Connect conn = new Connect();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Oid", Oid));
			params.add(new BasicNameValuePair("Tid", Tid));
			params.add(new BasicNameValuePair("Sid", Sid));
			params.add(new BasicNameValuePair("PNum", PNum));
			params.add(new BasicNameValuePair("Time", time.toString()));
			params.add(new BasicNameValuePair("Status",Status));
			try {
				HttpEntity entity = new UrlEncodedFormEntity(params,UTF_8);
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
				
				System.out.println("error1");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error2");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error3");
			}
			return result;
		}
		
		
	}
				

