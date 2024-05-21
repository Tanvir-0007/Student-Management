package com.example.demo.Service;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import java.util.List;


public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId);
    Student createStudent(Student student);
    Student updateStudent(Long studentId, Student updatedStudent);
    void deleteStudent(Long studentId);
    Student addSubjectToStudent(Long studentId, Subject subject);
}