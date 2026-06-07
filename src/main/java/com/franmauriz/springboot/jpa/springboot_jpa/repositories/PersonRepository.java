package com.franmauriz.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franmauriz.springboot.jpa.springboot_jpa.DTO.PersonDto;
import com.franmauriz.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person,Long>{

    @Query("select sum(p.id) from Person p")
    Long sumId();

    @Query("select p.name,length(p.name) from Person p  group by p.name having max(length(p.name)) = (select max(length(c.name)) from Person c)")
    List<Object[]> maxTamName();
    
    @Query("select p.name,length(p.name) from Person p  group by p.name having min(length(p.name)) = (select min(length(c.name)) from Person c)")
    List<Object[]> minTamName();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> tamNamelPerson();

    @Query("select count(p) from Person p")
    Long totalPerson();

    @Query("select min(p.id) from Person p")
    Long minIdPerson();

    @Query("select max(p.id) from Person p")
    Long maxIdPerson();

    @Query("select avg(p.id) from Person p")
    Long avgIdPerson();

    List<Person> findAllByOrderByName();

    List<Person> findByIdBetweenOrderByNameDescIdDesc(Long Id1, Long Id2);
    
    List<Person> findByIdBetweenOrderByName(Long Id1, Long Id2);

	List<Person> findByIdBetween(Long Id1, Long Id2);

    List<Person> findByNameBetween(String n1, String n2);

    @Query("select p from Person p order by p.name")
    List<Person> OrdeByname();

    @Query("select p from Person p where p.id between 2 and 6")
    List<Person> findBetween();

    @Query("select p.name, p.lastName from Person p where p.name like %?1%")
    List<String> findLikeNames(String comodin);

    @Query("select concat(upper(p.name),' ',upper(p.lastName)) from Person p")
    List<String> findAllConcatUppernames();

    @Query("select count(distinct(p.programLanguage)) from Person p")
    List<String> findAllCountprogramLanguages();

    @Query("select distinct(p.programLanguage) from Person p")
    List<String> findAllprogramLanguages();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNames();

    @Query("select new com.franmauriz.springboot.jpa.springboot_jpa.DTO.PersonDto(p.name,p.lastName) from Person p")
    List<PersonDto> findAllPersonDtoPersonalized();

    @Query("select p.id, p.name, p.lastName, p.programLanguage from Person p")
    List<Object[]> getPersonDataList();

    @Query("select p.id, p.name, p.lastName, p.programLanguage from Person p where p.id = ?1")
    Object getPersonDataListById(Long id);

    @Query("select p.name from Person p where p.id =?1")
    String getNameById(Long id);

    @Query("select concat(p.name,' ',p.lastName) from Person p where p.id =?1")
    String getFullNameById(Long id);

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
