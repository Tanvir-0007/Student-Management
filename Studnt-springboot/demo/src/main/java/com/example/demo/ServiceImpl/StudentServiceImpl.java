package com.example.demo.ServiceImpl;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.SubjectRepository;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student updatedStudent) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            updatedStudent.setId(studentId);
            return studentRepository.save(updatedStudent);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student addSubjectToStudent(Long studentId, Subject subject) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Associate the subject with the student
            subject.setStudent(student);

            // Save the subject first
            subjectRepository.save(subject);

            // Add the subject to the student's list of subjects
            if (student.getSubjects() == null) {
                student.setSubjects(new ArrayList<>());
            }
            student.getSubjects().add(subject);

            // Save the student entity with the updated subjects list
            studentRepository.save(student);

            return student;
        }
        return null;
    }


}