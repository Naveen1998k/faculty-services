package com.spring.faculty_services.Controller;


import com.spring.faculty_services.dto.CourseRequestDTO;
import com.spring.faculty_services.dto.CourseResponseDTO;
import com.spring.faculty_services.dto.ServiceResponse;
import com.spring.faculty_services.services.FacultyServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@Slf4j
public class FacultyController
{
    @Autowired
    private FacultyServices services;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDTO> addCourseToStudent(@RequestBody CourseRequestDTO courseRequestDTO) {

        log.info("FacultyController :: addCourseToStudent Method Started with request payload {}", courseRequestDTO);
        return services.addCourseToDashboard(courseRequestDTO);
    }

    @GetMapping("/viewCourse")
    public ServiceResponse<List<CourseResponseDTO>> getAllCourses() {

        return services.getAllCourses();
    }

    @GetMapping("/viewCourse/{courseId}")
    public ServiceResponse<CourseResponseDTO> getCourseById(@PathVariable Integer courseId) {
        log.info("FacultyController :: getCourseById Method Started with courseId {}", courseId);
        return services.getCourseById(courseId);

    }
    @GetMapping("/getCourse/request")
    public ServiceResponse<CourseResponseDTO> getCourseByIdRequestParam(@RequestParam(required = false) Integer courseId) {
        return services.findCourseByIdUsingRequestParam(courseId);
    }


    @PutMapping("/updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO requestDTO) {
        services.updateCourseInDashboard(courseId, requestDTO);
        return services.getCourseById(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {
        services.deleteCourseFromDashboard(courseId);
        return "course deleted successfully with id " + courseId;
    }
}
