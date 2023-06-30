package com.poschoolmain.student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolmain.dto.Course;
import com.poschoolmain.dto.Enrollment;
import com.poschoolmain.student.domain.type.EnrollmentStatus;
import com.poschoolmain.student.service.EnrollmentKafkaService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final EnrollmentKafkaService enrollmentKafkaService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody String json) {
        Enrollment enrollment = null;
        try {
            enrollment = objectMapper.readValue(json, Enrollment.class);
            enrollment.setEnrollmentStatus("REQUEST");

            enrollmentKafkaService.requestCreateEnrollment(enrollment);

            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/auto")
    public ResponseEntity<String> autoAddCourse() {
        for (int i = 1; i < 500; i++) {
            Enrollment enrollment = Enrollment.builder()
                    .studentId((long) i)
                    .courseId(1L)
                    .semester("1학기")
                    .enrollmentStatus("REQUEST")
                    .build();

            enrollmentKafkaService.requestCreateEnrollment(enrollment);
        }


        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelCourse(@RequestBody HashMap<String, Object> map) {
        enrollmentKafkaService.requestCreateEnrollment(
                Enrollment.builder()
                        .enrollmentId(Long.parseLong(map.get("enrollmentId").toString()))
                        .enrollmentStatus(EnrollmentStatus.CANCEL.toString())
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> list() {
        try {
            String url = "http://localhost:8081/api/course/list";

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            String responseData = EntityUtils.toString(response.getEntity());

            JSONArray jsonArray = new JSONArray(responseData);

            List<Course> courseList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Course course = Course.builder()
                        .courseId(jsonObject.getLong("courseId"))
                        .courseName(jsonObject.getString("courseName"))
                        .maxCount(jsonObject.getInt("maxCount"))
                        .currentCount(jsonObject.getInt("currentCount"))
                        .build();

                courseList.add(course);
            }

//            model.addAttribute("courseList", courseList);
            return ResponseEntity.ok().body(courseList);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
