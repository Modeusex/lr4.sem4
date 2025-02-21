package com.example.lr4.service;

import com.example.lr4.model.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final String filePath = "C:\\Users\\Pavel\\Desktop\\OOP\\lr4\\students.json";

    public void addStudent(Student student) {
        JSONArray studentList = new JSONArray();

        try {
            JSONParser parser = new JSONParser();
            File file = new File(filePath);
            if (file.exists()) {
                studentList = (JSONArray) parser.parse(new FileReader(filePath));
            }

            JSONObject studentJson = new JSONObject();
            studentJson.put("name", student.getName());
            studentJson.put("surname", student.getSurname());
            studentJson.put("age", student.getAge());
            studentJson.put("group", student.getGroup());
            studentJson.put("email", student.getEmail());
            studentJson.put("telephone_number", student.getTelephoneNumber());

            studentList.add(studentJson);

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(studentList.toJSONString());
            fileWriter.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Student student = new Student();
                student.setName((String) jsonObject.get("name"));
                student.setSurname((String) jsonObject.get("surname"));
                student.setAge(Integer.parseInt(jsonObject.get("age").toString()));
                student.setGroup((String) jsonObject.get("group"));
                student.setEmail((String) jsonObject.get("email"));
                student.setTelephoneNumber((String) jsonObject.get("telephone_number"));

                studentList.add(student);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}