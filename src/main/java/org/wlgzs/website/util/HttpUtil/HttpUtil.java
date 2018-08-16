package org.wlgzs.website.util.HttpUtil;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.wlgzs.website.enums.Method;
import org.wlgzs.website.util.Result.Result;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zjg
 * @date 2018/8/10 13:22
 * @Description
 */
@Slf4j
public class HttpUtil {

    private String url;
    private Method method;//请求方式，只能是get或post

    public HttpUtil(String url, Method method) {
        this.url = url;
        this.method = method;
    }

    /**
     *
     *
     */
    public void sendLogin(Map<String, Object> param) {
        log.info("发送请求,请求链接为：" + url + " ,请求方式为：" + method);
        CloseableHttpClient client = HttpClients.createDefault();
        String jsonParam = JSON.toJSONString(param);
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity entityParam = new StringEntity(jsonParam, "UTF-8");
        post.setEntity(entityParam);
        HttpEntity entity = doPost(client, post);
        //转换编码方式
        String result = null;
        try {
            result = EntityUtils.toString(entity,"utf8");
            log.info("result: "+result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发送get请求
     *
     * @Return 返回请求结果
     */
    public String send(String cookieName, String cookiesValue) {
        log.info("发送请求,请求链接为：" + url + " ,请求方式为：" + method);
        String result = null; //请求结果
        CloseableHttpClient client = HttpClients.createDefault();
        //封装cookie作为请求参数
        LinkedList<NameValuePair> linkedList = new LinkedList<NameValuePair>();
        BasicNameValuePair cookieNameParam = new BasicNameValuePair("cookieName", cookieName);
        BasicNameValuePair cookieValueParam = new BasicNameValuePair("cookieValue", cookiesValue);
        linkedList.add(cookieNameParam);
        linkedList.add(cookieValueParam);

        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setParameters(linkedList);
            HttpGet get = new HttpGet(uriBuilder.toString());
            result = doGet(client,get);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("建立请求异常");
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                log.info("httpclient关闭失败");
            }
        }
        return result;
    }

    /**
     * 发送接收get请求
     */
    private String doGet(CloseableHttpClient client, HttpGet get) {
        try (CloseableHttpResponse response = client.execute(get);) {
            //转换编码方式
            return EntityUtils.toString(response.getEntity(),"utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送接收post请求
     */
    private HttpEntity doPost(CloseableHttpClient client, HttpPost post) {
        try (CloseableHttpResponse response = client.execute(post);) {
            log.info("输出响应结果：" + response.getEntity().getContent());
            return response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
