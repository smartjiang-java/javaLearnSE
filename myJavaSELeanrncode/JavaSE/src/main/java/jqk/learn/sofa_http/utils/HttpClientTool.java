package jqk.learn.sofa_http.utils;


import com.alibaba.excel.util.CollectionUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:JQK
 * @Date:2021/2/5 14:19
 * @Package:jqk.learn.sofa_http
 * @ClassName:HttpClientTool
 **/
@Component
public class HttpClientTool {
    private static final String CHARSET = "UTF-8";
    private static final CloseableHttpClient httpClient;
    public static HttpClientTool tool;
    public ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpClientTool httpClientTool;

    @PostConstruct
    public void init() {
        mapper = objectMapper;
        tool = httpClientTool;
    }

    // 采用静态代码块，初始化超时时间配置，再根据配置生成默认httpClient对象
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public <T> T doGet(String url, Map<String, String> params, TypeReference<T> typeReference) {
        return this.doGet(url, params, typeReference, CHARSET, false);
    }

    public <T> T doPost(String url, Map<String, String> params, Object object, TypeReference<T> typeReference) {
        return doPost(url, params, object, null, typeReference, CHARSET, false);
    }

    public <T> T doPut(String url, Object object, TypeReference<T> typeReference) {
        return doPut(url, object, typeReference, CHARSET, false);
    }

    public <T> T cicGet(String url, Map<String, String> params, TypeReference<T> typeReference) {
        return this.doGet(url, params, typeReference, CHARSET, true);
    }

    public <T> T cicPost(String url, Map<String, String> params, Object object, TypeReference<T> typeReference) {
        return doPost(url, params, object, null, typeReference, CHARSET, true);
    }

    public <T> T cicPostHeader(String url, Map<String, String> params, Object object, Map<String, String> header,
                               TypeReference<T> typeReference) {
        return doPost(url, params, object, header, typeReference, CHARSET, true);
    }


    /**
     * HTTP Get 获取内容
     *
     * @param url           请求的url地址 ?之前的地址
     * @param params        请求的参数
     * @param typeReference 转换类型
     * @param charset       编码格式
     * @return 页面内容
     */
    public <T> T doGet(String url, Map<String, String> params, TypeReference<T> typeReference, String charset,
                       Boolean cilacFg) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                // 将请求参数和url进行拼接
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new Exception("出错了");
            }
            HttpEntity entity = response.getEntity();
            String str = null;
            if (entity != null) {
                str = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return this.result(str, typeReference, cilacFg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HTTP put
     *
     * @param url           请求的url地址 ?之前的地址
     * @param typeReference 转换类型
     * @return 页面内容
     */
    public <T> T doPut(String url, Object object, TypeReference<T> typeReference, String charset, Boolean cicFlag) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPut httpPut = new HttpPut(url);
            if (object != null) {
                String jsonString = mapper.writeValueAsString(object);
                httpPut.setEntity(new StringEntity(jsonString, charset));
                httpPut.setHeader("Content-Type", "application/json;charset=utf8");
            }
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpPut);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPut.abort();
                throw new Exception("出错了");
            }
            HttpEntity entity = response.getEntity();
            String str = null;
            if (entity != null) {
                str = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return this.result(str, typeReference, cicFlag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * HTTP Post 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param object  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public <T> T doPost(String url, Map<String, String> params, Object object, Map<String,
            String> header, TypeReference<T> typeReference, String charset, Boolean cicFlag) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (!CollectionUtils.isEmpty(params)) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                // 将请求参数和url进行拼接
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpPost httpPost = new HttpPost(url);
            if (object != null) {
                String jsonString = mapper.writeValueAsString(object);
                httpPost.setEntity(new StringEntity(jsonString, charset));
                httpPost.setHeader("Content-Type", "application/json;charset=utf8");
            }
            if (header != null) {
                header.keySet().forEach(data -> {
                    httpPost.setHeader(data, header.get(data));
                });
            }
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new Exception("出错了");
            }
            HttpEntity entity = response.getEntity();
            String str = null;
            if (entity != null) {
                str = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return this.result(str, typeReference, cicFlag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 返回信息
     *
     * @param str
     * @param typeReference
     * @return
     * @throws Exception
     */
    private <T> T result(String str, TypeReference<T> typeReference, Boolean cicFlag) throws Exception {
        if (typeReference == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (cicFlag) {
            BaseResponse baseResponse = mapper.readValue(str, BaseResponse.class);
            if (!baseResponse.getCode().equals("200")) {
                throw new Exception("出错了");
            }
            if (baseResponse.getData() != null) {
                String data = mapper.writeValueAsString(baseResponse.getData());
                T result = mapper.readValue(data, typeReference);
                return result;
            }
        }
        T result = mapper.readValue(str, typeReference);
        return result;
    }

}