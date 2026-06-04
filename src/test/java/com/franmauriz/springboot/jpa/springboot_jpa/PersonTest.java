package com.franmauriz.springboot.jpa.springboot_jpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.franmauriz.springboot.jpa.springboot_jpa.entities.Person;
import com.franmauriz.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonTest {

    public Person person = new Person(1L,"Pepe","Grillo","Python");
                        

    @Mock
    private PersonRepository repo;
    
    @InjectMocks
    private  SpringbootJpaApplication personrApp;

    @Test
    void shouldReturnPersonFromDB(){
        when(repo.findById(1L)).thenReturn(Optional.of(person));

        Optional<Person> result = personrApp.repository.findById(1L);

        assertNotNull(result);
        assertEquals("Pepe", result.get().getName());
        verify(repo,times(1)).findById(1L);
    }

    @Test
    void shouldReturnPersonFromDBLanguage(){
        when(repo.findById(1L)).thenReturn(Optional.of(person));

        Optional<Person> result = personrApp.repository.findById(1L);

        assertNotNull(result);
        assertEquals("Python", result.get().getProgramLanguage());
        verify(repo,times(1)).findById(1L);
    }
}




