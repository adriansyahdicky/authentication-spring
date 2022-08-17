package com.project.authentication.service;

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

    public List<Map<String, Object>> getJobList() {
        String url = urlListJob;
        return restUtil.getExtObject(url,
                List.class);
    }

    public Map<String, Object> getJobById(String id) {
        String url = urlListJobById+id;
        return restUtil.getExtObject(url,
                HashMap.class);
    }
}
