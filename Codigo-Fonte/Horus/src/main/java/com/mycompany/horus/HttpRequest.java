package com.mycompany.horus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by viniciuscmac on 4/24/17.
 */
public class HttpRequest {

    public static void main(String[] args) throws Exception {


    }
    public String request(String url,String body){
        String resp="";
        try {
            // Get target URL
            StringBuilder soap = new StringBuilder(body);
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            StringEntity strEntity = new StringEntity(soap.toString(),"UTF-8");
            strEntity.setContentType("text/xml");
            HttpPost post = new HttpPost(url);
            post.setEntity(strEntity);

            // Execute request
            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            resp = EntityUtils.toString(respEntity);
            if (respEntity != null) {
                System.out.println("Response:");
                System.out.println(resp);
                //Changing response to Xml file
                stringToDom(resp);

            } else {
                System.out.println("No Response");
            }
        }
        catch (Exception e) {
            System.out.println("Other exception = " + e.toString());
        }

        return resp;

    }

    public static void stringToDom(String xmlSource)
            throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter("teste.xml");
        fw.write(xmlSource);
        fw.close();
    }
}
