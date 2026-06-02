package com.franmauriz.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franmauriz.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;


public interface PersonRepository extends CrudRepository<Person,Long>{
    List<Person> findByProgramLanguage(String programLanguage);
    @Query("select p from  Person p where p.name = ?1")
    List<Person> buscarByProgramName(String name);
    @Query("select p from  Person p where p.programLanguage = ?1 and p.name = ?2")
    List<Person> buscarByProgramLanguageAndName(String programLanguage, String name);

    List<Person> findByProgramLanguageAndName(String programLanguage, String name);
}
