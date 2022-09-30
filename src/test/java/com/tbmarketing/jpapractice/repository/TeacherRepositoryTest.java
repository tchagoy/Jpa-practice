package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Teacher;
import com.tbmarketing.jpapractice.loader.InfoLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void printAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        InfoLoader.printWithDelay(teachers,"teacher",2);
    }
}