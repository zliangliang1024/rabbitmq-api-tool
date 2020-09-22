package com.jishusou.tool.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RabbitMqApiTool {

    public static void main(String[] args) throws IOException {
        HttpHost host = new HttpHost("localhost", 15672);
//        HttpPut httpPut = new HttpPut("/api/users/zll");
//        JSONObject object = new JSONObject();
//        object.put("password","123456");
//        object.put("tags","");

//        HttpPut httpPut = new HttpPut("/api/vhosts/test");
//        JSONObject object = new JSONObject();
//        object.put("password","123456");
//        object.put("tags","");

//        HttpPut httpPut = new HttpPut("/api/permissions/test/zll");
//        JSONObject object = new JSONObject();
//        object.put("configure","");
//        object.put("write","");
//        object.put("read","");

        HttpPut httpPut = new HttpPut("/api/queues/test/testQ");
        JSONObject object = new JSONObject();
        object.put("auto_delete",false);
        object.put("durable",false);
//        object.put("argument","");

        StringEntity param = new StringEntity(object.toJSONString());
        httpPut.setEntity(param);
        //认证
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope("localhost", 15672), new UsernamePasswordCredentials("guest", "guest"));
        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpResponse response = client.execute(host, httpPut);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
    }

}
