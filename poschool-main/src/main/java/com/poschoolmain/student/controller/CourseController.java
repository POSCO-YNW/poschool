package com.poschoolmain.student.controller;

import com.poschoolmain.domain.Course;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/add")
    public ResponseEntity<Void> addCourse(@RequestBody HashMap<String, Object> map) {
        enrollmentKafkaService.requestCreateEnrollment(Long.parseLong(map.get("studentId").toString()), Long.parseLong(map.get("courseId").toString()));
        return ResponseEntity.ok().build();
    }
}
