package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student create(Student s) {
        return repo.save(s);
    }

    public List<Student> listAll() {
        return repo.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repo.findById(id);
    }

    public Student update(Long id, Student s) {
        return repo.findById(id).map(existing -> {
            existing.setName(s.getName());
            existing.setEmail(s.getEmail());
            existing.setAge(s.getAge());
            existing.setCourse(s.getCourse());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
