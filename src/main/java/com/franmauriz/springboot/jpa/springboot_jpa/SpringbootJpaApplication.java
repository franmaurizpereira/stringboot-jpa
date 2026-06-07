package com.franmauriz.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.franmauriz.springboot.jpa.springboot_jpa.DTO.PersonDto;
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
		consultas2();
	}

	@Transactional(readOnly = true)
	public void consultas2(){
		List<PersonDto> personDto = repository.findAllPersonDtoPersonalized();
		personDto.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<String> programLanguages = repository.findAllprogramLanguages();
		programLanguages.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<String> countProgramLanguages = repository.findAllCountprogramLanguages();
		System.out.print("Total lenguajes de programacion: ");
		countProgramLanguages.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<String> concatUppernames = repository.findAllConcatUppernames();
		System.out.print("Nombre concatenado y en mayus: ");
		concatUppernames.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<String> likeNames = repository.findLikeNames("Ma");
		System.out.println("Buscar nombre por comodin: ");
		likeNames.forEach(System.out::println);
		System.out.println("-".repeat(60));
		List<Person> betweenId = repository.findBetween();
		System.out.println("Id entre: ");
		betweenId.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Ordenar por nombres: ");
		List<Person> ordrName = repository.OrdeByname();
		System.out.println("Id entre: ");
		ordrName.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Buscar por Id por metodo: ");
		List<Person> byId = repository.findByIdBetween(2L, 4L);
		byId.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Buscar por nombre por metodo: ");
		List<Person> byName = repository.findByNameBetween("A", "G");
		byName.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Buscar por nombre por Id y ordenar por nombre: ");
		List<Person> byidOrderName = repository.findByIdBetweenOrderByName(2L, 7L);
		byidOrderName.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Buscar por nombre por nombre y ordenar por nombre: ");
		List<Person> byidOrderNameId = repository.findByIdBetweenOrderByNameDescIdDesc(2L, 7L);
		byidOrderNameId.forEach(System.out::println);
		System.out.println("-".repeat(60));
		System.out.println("Todas las personas ordenadas por nombre: ");
		List<Person> byidOrderNameLastname = repository.findAllByOrderByName();
		byidOrderNameLastname.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void consultas(){
		Scanner scanner = new Scanner(System.in);
		String txt = "Escribe el Id de la persona que quieres conocer su nombre: ";
		System.out.print(txt);
		Long id = scanner.nextLong();
		scanner.close();
		System.out.println("_".repeat(txt.length()));
		Object[] personReg = (Object[]) repository.getPersonDataListById(id);
		System.out.println("Id: " + personReg[0] +" Name: " + personReg[1] + " " + personReg[2] + " Program: " + personReg[3]);
		System.out.println("_".repeat(txt.length()));
		List<Object[]> regs = repository.getPersonDataList();
		regs.stream().forEach(personList -> System.out.println("Id: " + personList[0] +" Name: " + personList[1] + " " + personList[2] + " Program: " + personList[3]));
		System.out.println("_".repeat(txt.length()));
		System.out.println(repository.getNameById(id));
		System.out.println("_".repeat(txt.length()));
		System.out.println(repository.getFullNameById(id));
		System.out.println("_".repeat(txt.length()));
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
