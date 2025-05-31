package com.spring.faculty_services.services;

import com.spring.faculty_services.dto.CourseRequestDTO;
import com.spring.faculty_services.dto.CourseResponseDTO;
import com.spring.faculty_services.dto.ServiceResponse;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServices
{
    @Autowired
    public RestTemplate restTemplate;

    public final static String COURSE_SERVICE_URL = "http://localhost:8080/course/course/";

    public ServiceResponse<CourseResponseDTO> addCourseToDashboard(CourseRequestDTO courseRequestDTO) {

        return restTemplate.postForObject(COURSE_SERVICE_URL+"add", courseRequestDTO, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDTO>> getAllCourses() {
        return restTemplate.getForObject(COURSE_SERVICE_URL + "view", ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> getCourseById(Integer courseId) {
        return restTemplate.getForObject(COURSE_SERVICE_URL + "search/" + courseId, ServiceResponse.class);
    }
    public ServiceResponse<CourseResponseDTO> findCourseByIdUsingRequestParam(Integer courseId) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("courseId", courseId);
        return restTemplate.getForObject(COURSE_SERVICE_URL + "search/request?courseId={courseId}", ServiceResponse.class, requestMap);
    }

    public void updateCourseInDashboard(int courseId,CourseRequestDTO courseRequestDTO){
        restTemplate.put(COURSE_SERVICE_URL+"update/"+courseId, courseRequestDTO);
    }

    public void deleteCourseFromDashboard(int courseId){
        restTemplate.delete(COURSE_SERVICE_URL+"delete/"+courseId);
    }
}
