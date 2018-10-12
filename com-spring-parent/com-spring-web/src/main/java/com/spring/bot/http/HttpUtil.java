package com.spring.bot.http;


import org.apache.commons.collections.map.HashedMap;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018-09-29.
 */
public class HttpUtil {

    public static void main(String[] args) throws Exception{

       /* HttpClients.createDefault();
        HttpClients.createMinimal();*/
        post();

    }

    /***
     * http post demo
     * @throws Exception
     */
    public static void post () throws Exception {
        String url = "http://localhost:8091/web/book/post";
        Map<String, String> map = new HashedMap();
        map.put("name", "gxx");
        map.put("age", "18");

        ArrayList e = new ArrayList();
       // CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpClient httpClient = HttpClients.createMinimal();
        CloseableHttpResponse response = null;
        HttpPost post = new HttpPost(url);

        for (String key : map.keySet()) {
            e.add(new BasicNameValuePair(key, map.get(key)));
        }

        post.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));  // 设置字符格式
        post.addHeader("key", "123456789");

        response = httpClient.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();

        String result = EntityUtils.toString(response.getEntity(), "UTF-8");

        System.out.println("---- " + statusCode + " **** " + result);

        response.close();
        httpClient.close();
    }

    /***
     * http get demo
     * @throws Exception
     */
    public static void get () throws Exception{
        String url = "http://localhost:8091/web/book/get?name=zs";
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("key", "oikeiej00982j2l20");

        response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(" -- get -- " + statusCode + " #### " + result);

        response.close();
        client.close();
    }


}
