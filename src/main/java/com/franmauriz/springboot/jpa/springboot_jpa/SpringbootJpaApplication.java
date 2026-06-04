package com.franmauriz.springboot.jpa.springboot_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.franmauriz.springboot.jpa.springboot_jpa.entities.Person;
import com.franmauriz.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	public PersonRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgramLanguage("Java");
		//List<Person> persons = (List<Person>) repository.buscarByProgramName("Andres");
		//List<Person> persons = (List<Person>) repository.buscarByProgramLanguageAndName("Java", "Andres");
		//List<Person> persons = (List<Person>) repository.findByProgramLanguageAndName("Java", "Andres");

		
		//repository.findOne(3L).ifPresent(System.out::println);
		//repository.findByName("Andres").ifPresent(System.out::println);
		repository.findByNameContaining("Pe").ifPresent(System.out::println);

		//persons.stream().forEach(person -> System.out.println(person));
	}

}
