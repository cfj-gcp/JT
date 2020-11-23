package com.jt.service;

import com.jt.pojo.user;
import com.jt.util.objectMapperUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class userServiceImpl  implements  userService{
    @Override
    public List<user> testHttpClient() {
       List<user>  userlist= new ArrayList<>();
              //由jt-web服务器去连接jt-sso的服务器
        String  url="http://sso.jt.com/user/testHttpClient";
        HttpClient  httpClient= HttpClients.createDefault();
        HttpGet  httpGet=new HttpGet(url);
        try {
          HttpResponse httpResponse= httpClient.execute(httpGet);
          if(httpResponse.getStatusLine().getStatusCode()==200) {
              HttpEntity httpEntity = httpResponse.getEntity();
              String result = EntityUtils.toString(httpEntity, "utf-8");
              userlist = objectMapperUtil.toObject(result, userlist.getClass());
          }
        } catch (IOException e) {
            e.printStackTrace();
            throw   new RuntimeException(e);
        }
        return userlist;
    }
}











