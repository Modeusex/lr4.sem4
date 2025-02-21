package com.example.lr4.controller;
import com.example.lr4.model.Student;
import com.example.lr4.service.StudentService;
import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int age = Integer.parseInt(request.getParameter("age"));
        String group = request.getParameter("group");
        String email = request.getParameter("email");
        String telephoneNumber = request.getParameter("telephone_number");

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setAge(age);
        student.setGroup(group);
        student.setEmail(email);
        student.setTelephoneNumber(telephoneNumber);

        studentService.addStudent(student);

        response.sendRedirect("/lr4");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = studentService.getAllStudents();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setAttribute("students", studentList);
        out.println("<html><body><h2>Данные о студентах</h2><table border='1'><tr><th>Имя</th><th>Фамилия</th><th>" +
        "Возраст</th><th>Группа</th><th>Email</th><th>Номер телефона</th></tr>");

        for (Student student : studentList) {
            out.println("<tr><td>" + student.getName() + "</td><td>" + student.getSurname() + "</td><td>"
                    + student.getAge() + "</td><td>" + student.getGroup() + "</td><td>"
                    + student.getEmail() + "</td><td>" + student.getTelephoneNumber() + "</td></tr>");
        }

        out.println("</table></body></html>");
    }
}