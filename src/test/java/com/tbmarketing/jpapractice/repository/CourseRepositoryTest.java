package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Course;
import com.tbmarketing.jpapractice.entity.CourseMaterial;
import com.tbmarketing.jpapractice.loader.InfoLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        InfoLoader.printWithDelay(courses,"course",1);
    }

    /** Paging example, JPARepository already extends PagingRepository **/
    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3); //3rd argument is a Sort object.

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> courses
                = courseRepository.findAll(secondPageWithTwoRecords)
                    .getContent();

        Long totalElements = courseRepository.findAll(secondPageWithTwoRecords)
                        .getTotalElements();

        int totalPages = courseRepository.findAll(secondPageWithTwoRecords)
                        .getTotalPages();

        System.out.println("TOTAL ELEMENTS: " + totalElements);
        System.out.println("TOTAL PAGES: " + totalPages);


        InfoLoader.printWithDelay(courses, "course",3);

    }

    @Test
    public void findAllSortByTitle(){
        Pageable pageable =
                PageRequest.of(0,2,
                        Sort.by("title"));

        List<Course> courses
                = courseRepository.findAll(pageable).getContent();

        InfoLoader.printWithDelay(courses,"course",3);
    }

    @Test
    public void findAllSortByCreditDesc(){
        Pageable pageable =
                PageRequest.of(0,2, Sort.by("credit").descending());

        List<Course> courses
                = courseRepository.findAll(pageable).getContent();

        InfoLoader.printWithDelay(courses,"course",3);
    }

    @Test
    public void findAllSortByTitleAndCreditDesc(){
        Pageable pageable =
                PageRequest.of(0,2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit")));

        List<Course> courses
                = courseRepository.findAll(pageable).getContent();

        InfoLoader.printWithDelay(courses,"course",3);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByTitleContaining("a", firstPageTenRecords).getContent();

        InfoLoader.printWithDelay(courses, "course", 3);
    }
}