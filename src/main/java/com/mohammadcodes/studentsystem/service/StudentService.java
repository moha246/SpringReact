package com.mohammadcodes.studentsystem.service;

import com.mohammadcodes.studentsystem.model.Student;
import com.mohammadcodes.studentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();}

    public Student getStudent(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(!optionalStudent.isPresent())
            throw new IllegalStateException("Customer Record is not available...");

        return optionalStudent.get();
    }

    public void saveStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByName(student.getName());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        studentRepository.save(student);
    }



    public void deleteStudent(Long studentId) {
        boolean exists =studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }


//    @Transactional
//    public void updateStudent(Long studentId,
//                              String name,
//                              String address) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "student with id" + studentId + "doesnot exist")
//                );
//        if(address != null &&
//                address.length() > 0 &&
//                !Objects.equals(student.getAddress(), address)) {
//            student.setAddress(address);
//        }
//        if(name != null &&
//                name.length() > 0 &&
//                !Objects.equals(student.getName(), name)) {
//            Optional<Student> studentOptional = studentRepository
//                    .findStudentByName(name);
//            if (studentOptional.isPresent()) {
//                throw new IllegalStateException("name taken");
//            }
//
//            student.setName(name);
//        }
//
//    }


    public Student updateStudent(Long studentId, Student student) {
        student.setId(studentId);
        return studentRepository.save(student);
    }
}
