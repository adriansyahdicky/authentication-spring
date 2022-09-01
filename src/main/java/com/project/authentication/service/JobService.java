package com.project.authentication.service;

import com.project.authentication.dto.ResponseApplication;
import com.project.authentication.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobService {

    @Autowired
    private RestUtil restUtil;

    @Value("${integration.list-job}")
    private String urlListJob;

    @Value("${integration.job-by-id}")
    private String urlListJobById;

    public ResponseApplication<Map<String, List<Map<String, Object>>>> getJobList() {
        try {
            String url = urlListJob;
            List<Map<String, Object>> resultJobList = restUtil.getExtObject(url,
                    List.class);

            Map<String, List<Map<String, Object>>> groupByPriceMap =
                    resultJobList.stream().collect(Collectors.groupingBy(stringObjectMap ->
                            stringObjectMap.get("location").toString()));

            return ResponseApplication.result(groupByPriceMap);
        }catch (Exception ex){
            log.error("Error get job list result {} ", ex.getMessage());
            throw ex;
        }
    }

    public ResponseApplication<Map<String, Object>> getJobById(String id) {
        try {
            String url = urlListJob;
            List<Map<String, Object>> resultJobList = restUtil.getExtObject(url,
                    List.class);
            return ResponseApplication.result(resultJobList.stream().filter(stringObjectMap ->
                    stringObjectMap.get("id").equals(id))
                    .findFirst().get());
        }catch (Exception ex){
            log.error("Error get job by id {} ", ex.getMessage());
            throw ex;
        }
    }
}
