package com.franmauriz.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franmauriz.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person,Long>{
    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneByNameLike(String name);

    Optional<Person> findByName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgramLanguage(String programLanguage);
    @Query("select p from  Person p where p.name = ?1")
    List<Person> buscarByProgramName(String name);
    @Query("select p from  Person p where p.programLanguage = ?1 and p.name = ?2")
    List<Person> buscarByProgramLanguageAndName(String programLanguage, String name);

    List<Person> findByProgramLanguageAndName(String programLanguage, String name);
}
