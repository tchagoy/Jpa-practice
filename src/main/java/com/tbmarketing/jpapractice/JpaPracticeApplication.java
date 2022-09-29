package com.tbmarketing.jpapractice;

import com.github.javafaker.Faker;
import com.tbmarketing.jpapractice.entity.Guardian;
import com.tbmarketing.jpapractice.entity.Student;
import com.tbmarketing.jpapractice.loader.InfoLoader;
import com.tbmarketing.jpapractice.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class JpaPracticeApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpaPracticeApplication.class, args);

	}
	/** just a comment **/

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository
	){
		return args -> {
			Long seed = 1000010L;

			InfoLoader.fillWithStudents(studentRepository,seed,50);

		};
	}
}
