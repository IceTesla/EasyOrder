package cn.IceTesla.easyorder.Network;

import android.content.Context;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import static cn.IceTesla.easyorder.Activity.LoginActivity.cacheGet;

public class Connect {
    private String UrlVar;

    public Connect(Context context) {
        String temp = cacheGet("IP", context);
        UrlVar = "http://" + temp + ":8080/ServerBeta3/";
    }

    public HttpURLConnection getConnection(String urlpath) {

        String finalurl = UrlVar + urlpath;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(finalurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return connection;

    }

    public HttpPost getHttpPost(String uripath) {
        HttpPost httpPost = new HttpPost(UrlVar + uripath);

        System.out.println(UrlVar + uripath);
        return httpPost;
    }

    public HttpGet getHttpGet(String urlpath) {
        HttpGet httpGet = new HttpGet(UrlVar + urlpath);
        return httpGet;
    }

}
