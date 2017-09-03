package cn.IceTesla.easyorder.Network;

import android.content.Context;
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

import cn.IceTesla.easyorder.Data.Model.dishes;

import static org.apache.http.protocol.HTTP.UTF_8;


public class Operation {
    private Context context;

    public Operation(Context context) {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    public String loginIn(String id, String password)//login in
    {
        String url = "LoginInServlet";
        String result = null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("password", password));
        try {
            HttpEntity entity = new UrlEncodedFormEntity(params, UTF_8);
            HttpPost httpPost = conn.getHttpPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            } else {
                result = "1002";
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
    }//定义一个list，该list的数据类型是NameValuePair,用于Java向url发送Post请求。


    public String getMenu()//读取菜单
    {
        String result = null;
        Connect conn = new Connect(context);
        String url = "MenuServlet";
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = conn.getHttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                result = "2002";
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("网络异常");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("网络异常");
        }
        return result;

    }

    public String placeOrder(String Tid,ArrayList<dishes> list)//�µ�
    {

        String url = "OrderTblServlet";
        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        int dishNum = list.size();
        for(int i = 0;i<list.size();i++){
            String did  = "did"+String.valueOf(i);
            String number = "number"+String.valueOf(i);
            String content = "content"+String.valueOf(i);

            params.add(new BasicNameValuePair(did, list.get(i).getId()));
            params.add(new BasicNameValuePair(number, list.get(i).getName()));
            params.add(new BasicNameValuePair(content, list.get(i).getContent()));

        }
        params.add(new BasicNameValuePair("Tid", Tid));
        params.add(new BasicNameValuePair("dishNum", String.valueOf(dishNum)));




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
                result="2002";
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
    public String getOrderList(){
        String url = "getOrderServlet";
        String result = null;
        Connect conn = new Connect(context);
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
            System.out.println("�����쳣");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("�����쳣");
        }
        return result;
    }

    public String changeOrder(String Oid, String Tid, String Sid, String PNum, java.util.Date time, String Status){

        String url = "changeOrderServlet";
        String result =  null;
        Connect conn = new Connect(context);
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
                result="3002";
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
    public String deleteOrder(String Oid){
        String url = "deleteOrderServlet";
        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Oid", Oid));

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
                result="4002";
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

    public String payMoney(String url,String id)
    {

        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", id));

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
                result="9002";
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

    public String orderTable(String personNum,String tableId,String userId,String orderTime)
    {
        String url = "StartTableServlet";
        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tableId", tableId));
        params.add(new BasicNameValuePair("userId", userId));
        params.add(new BasicNameValuePair("orderTime", orderTime));
        params.add(new BasicNameValuePair("personNum", personNum));
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
                result="5002";
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
    public String changeTable(String Oid , String tableId)
    {
        String url = "ChangeTableServlet";
        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("orderId", Oid));
        params.add(new BasicNameValuePair("tableId", tableId));
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
                result="6002";
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
    public String checkTable(){
        String url = "checkTable";
        String result = null;
        Connect conn = new Connect(context);
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
                result = "7002";
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("�����쳣");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("�����쳣");
        }
        return result;
    }
    public String mergeTable(String table1,String table2){
        String url = "UnionTableServlet";
        String result =  null;
        Connect conn = new Connect(context);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("table1", table1));
        params.add(new BasicNameValuePair("table2", table2));
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
                result="9002";
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




