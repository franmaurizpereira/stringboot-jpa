package com.franmauriz.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		update();
	}

	@Transactional
	public void create(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese el nombre: ");
		String name = scanner.nextLine();
		System.out.print("Ingrese el apellido: ");
		String lastName = scanner.nextLine();
		System.out.print("Ingrese el lenguaje de programacion: ");
		String program = scanner.nextLine();
		scanner.close();
		Person person = new Person(null,name, lastName, program);
		Person personNew = repository.save(person);
		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Escribe el ID de la persona que quieres modificar: ");
		Long Id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(Id);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.orElseThrow();
			String title = "Modificar Datos de la persona";
			System.out.println(title);
			System.out.println("-".repeat(title.length()));
			System.out.println("1 - Name: " + person.getName());
			System.out.println("2 - LastName: " + person.getLastName());
			System.out.println("3 - Program Languaje: " + person.getProgramLanguage());
			System.out.println("-".repeat(title.length()));
			System.out.print("¿Que dato quieres modificar? 1, 2 o 3: ");
			scanner.nextLine();
			String op = scanner.nextLine();
			if(op.equals("1")){
				System.out.print("Name: ");
				String name = scanner.nextLine();
				person.setName(name);
			}else if(op.equals("2")){
				System.out.print("Lastname: ");
				String lastname = scanner.nextLine();
				person.setName(lastname);
			}else if(op.equals("3")){
				System.out.print("Program Language: ");
				String program = scanner.nextLine();
				person.setProgramLanguage(program);
			}else{
				System.out.println("The option is not correct.");
			}
			scanner.close();
			Person personModify = repository.save(person);
			String title2 = "Datos de la persona";
			System.out.println(title2);
			System.out.println("-".repeat(title2.length()));
			System.out.println("Name: " + personModify.getName());
			System.out.println("LastName: " + personModify.getLastName());
			System.out.println("Program Languaje: " + personModify.getProgramLanguage());
			System.out.println("-".repeat(title2.length()));
		}else{
			System.out.println("El " + Id + " no existe en la base de datos");
		}
	}

	@Transactional(readOnly = true)
	public void find(){
			//List<Person> persons = (List<Person>) repository.findAll();
			//List<Person> persons = (List<Person>) repository.findByProgramLanguage("Java");
			//List<Person> persons = (List<Person>) repository.buscarByProgramName("Andres");
			//List<Person> persons = (List<Person>) repository.buscarByProgramLanguageAndName("Java", "Andres");
			//List<Person> persons = (List<Person>) repository.findByProgramLanguageAndName("Java", "Andres");
			//repository.findOne(3L).ifPresent(System.out::println);
			//repository.findByName("Andres").ifPresent(System.out::println);
			repository.findByNameContaining("Pe").ifPresent(System.out::println);
			//persons.stream().forEach(person -> System.out.println(person));
	}

	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Escribe el ID de la persona que quieres eliminar: ");
		Long Id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(Id);
		optionalPerson.ifPresentOrElse(person -> repository.delete(person),
			() -> System.out.println("El id no existe en la base de datos"));
		scanner.close();
	}

}
