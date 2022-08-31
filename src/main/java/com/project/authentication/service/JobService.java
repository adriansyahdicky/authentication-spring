package com.project.authentication.service;

import com.project.authentication.dto.ResponseApplication;
import com.project.authentication.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class JobService {

    @Autowired
    private RestUtil restUtil;

    @Value("${integration.list-job}")
    private String urlListJob;

    @Value("${integration.job-by-id}")
    private String urlListJobById;

    public ResponseApplication<List<Map<String, Object>>> getJobList() {
        try {
            String url = urlListJob;
            return ResponseApplication.result(restUtil.getExtObject(url,
                    List.class));
        }catch (Exception ex){
            log.error("Error get job list result {} ", ex.getMessage());
            throw ex;
        }
    }

    public ResponseApplication<Map<String, Object>> getJobById(String id) {
        try {
            String url = urlListJobById + id;
            return ResponseApplication.result(restUtil.getExtObject(url,
                    HashMap.class));
        }catch (Exception ex){
            log.error("Error get job by id {} ", ex.getMessage());
            throw ex;
        }
    }
}
