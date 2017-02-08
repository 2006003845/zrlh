package jtapp.updateapksamples;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;

public class NetworkTool {
	
	/**
	* 获取网址内容
	* @param url
	* @return
	* @throws Exception
	*/
	public static String getContent(String url,Activity activity) throws Exception
	{
	    
		StringBuilder sb = new StringBuilder();
	    
	    HttpClient client = new DefaultHttpClient();
	    if(isCMWAP(activity))
	    {
	    	String host=Proxy.getDefaultHost();//此处Proxy源自android.net  
	    	int port = Proxy.getPort(activity);//同上  
	    	HttpHost httpHost = new HttpHost(host, port);   
	    	//设置代理  
	    	client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY,httpHost);  
	    }
	   
	   
	    HttpParams httpParams = client.getParams();	
	    //设置网络超时参数
	    HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
	    HttpConnectionParams.setSoTimeout(httpParams, 5000);
	    
	  
	    HttpGet get = new HttpGet(url);
	    
//	    get.setHeader("User-Agent", "");
	    HttpResponse response = client.execute(get);
	    int code = response.getStatusLine().getStatusCode();
	    HttpEntity entity = response.getEntity();
	    
	    if (entity != null) 
	    {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);
	        
	        String line = null;
	        while ((line = reader.readLine())!= null){
	            sb.append(line + "\n");
	        }
	        reader.close();
	    }
	    return sb.toString();
	} 
	
	public static boolean isCMWAP(Activity activity)  
	{  
  
       String currentAPN = "";  
       ConnectivityManager conManager = (ConnectivityManager) activity.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);  
       NetworkInfo info = conManager.getActiveNetworkInfo();  
       currentAPN = info.getExtraInfo();  
  
       if (currentAPN == null || currentAPN == "")  
       {  
           return false;  
       }  
       else  
       {  
           if (currentAPN.equals("cmwap"))  
           {  
               return true;  
           }  
           else  
           {  
               return false;  
           }  
  
       }
	}
}
