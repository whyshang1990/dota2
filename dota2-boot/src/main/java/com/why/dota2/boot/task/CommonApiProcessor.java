package com.why.dota2.boot.task;

import com.why.dota2.boot.conf.SteamProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 通用的Steam API请求处理类，包含整个流程
 * @param <T> 接口对应返回值包装对象
 */
public abstract class CommonApiProcessor<T>{
    @Resource
    protected RestTemplate restTemplate;

    @Resource
    protected SteamProperties steamProperties;

    // 处理器执行方法
    protected void process() {
        String url = this.buildUrl();
        ParameterizedTypeReference<T> reference = this.buildReference();
        HttpEntity<HttpHeaders> httpEntity = this.buildHttpEntity();
        T result = this.getResult(url, reference, httpEntity);
        this.save(result);
    }

    /**
     * 构建URL
     */
    protected abstract String buildUrl();

    /**
     * 构建返回值类型
     */
    protected abstract ParameterizedTypeReference<T> buildReference();

    /**
     * 构建HttpEntity，包含通用的HttpHeader
     */
    protected HttpEntity<HttpHeaders> buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    /**
     * 执行API请求获取数据
     */
    protected T getResult(String url, ParameterizedTypeReference<T> reference,
                                   HttpEntity<HttpHeaders> httpEntity) {
        ResponseEntity<T> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, reference);
        return responseEntity.getBody();
    }

    /**
     * 对返回值进行操作写入数据库
     */
    protected abstract void save(T result);
}
