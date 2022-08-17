package com.project.authentication.util;

import com.project.authentication.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class RestUtil {

    @Autowired
    RestTemplate restTemplate;

//    public <S, T> T get(String url, S requestBody, Class<T> tClass) {
//        try {
//            log.debug("Post to          : {}", url);
//            HttpEntity<S> request = new HttpEntity<>(requestBody);
//            T t = restTemplate.getForObject(url, request, tClass);
//            return t;
//        } catch (Exception e) {
//            throw new BusinessException("Error Post Data To Client ", e);
//        }
//    }

    public <S, T> T getExtObject(String url,  Class<T> classType) {
        log.debug("Get from         : {}", url);
        ResponseEntity<T> response = restTemplate.getForEntity(
                url, classType);
        T body = response.getBody();
        return body;
    }

}