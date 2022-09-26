package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMateriaRepository extends JpaRepository<CourseMaterial, Long> {
}
