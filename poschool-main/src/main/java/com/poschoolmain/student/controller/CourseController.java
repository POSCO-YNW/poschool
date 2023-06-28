package com.poschoolmain.student.controller;

import com.poschoolmain.domain.Course;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.common.network.Mode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("course")
public class CourseController {

    @GetMapping("/list")
    public String list(Model model) {
        try {
            String url = "http://localhost:8081/course/list";

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

            model.addAttribute("courseList", courseList);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            return "course/list";
        }
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam("studentId") Long studentId, @RequestParam("courseId") Long courseId) {

    }
}
