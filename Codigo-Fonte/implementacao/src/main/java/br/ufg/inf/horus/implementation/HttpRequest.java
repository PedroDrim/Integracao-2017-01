package br.ufg.inf.horus.implementation;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created by viniciuscmac on 4/24/17.
 */
public class HttpRequest {

    //Método que faz a conexão com a url do argumento e recebe a resposta, retornando-a.
    public String request(String url,String body){
        String resp="";
        try {

            StringBuilder soap = new StringBuilder(body);
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            StringEntity strEntity = new StringEntity(soap.toString(),"UTF-8");
            strEntity.setContentType("text/xml");
            HttpPost post = new HttpPost(url);
            post.setEntity(strEntity);

            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            resp = EntityUtils.toString(respEntity);
            if (respEntity == null) {
                System.out.println("No Response");
            }
        }
        catch (Exception e) {
            System.out.println("Other exception = " + e.toString());
        }

        return resp;

    }

}
