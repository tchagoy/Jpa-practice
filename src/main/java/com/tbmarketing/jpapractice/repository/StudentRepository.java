package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
