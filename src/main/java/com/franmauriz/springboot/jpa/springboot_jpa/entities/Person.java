package com.franmauriz.springboot.jpa.springboot_jpa.entities;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String lastName;
    
    @Column(name="program_language")
    private String programLanguage;

    @Embedded
    private Audit audit = new Audit();

    public Person() {
    }

    public Person(Long id, String name, String lastName, String programLanguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.programLanguage = programLanguage;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getProgramLanguage() {
        return programLanguage;
    }
    public void setProgramLanguage(String programLanguage) {
        this.programLanguage = programLanguage;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastName=" + lastName + ", programLanguage=" + programLanguage + ", create_At=" + audit.getCreateAt() + ", update_At= " + audit.getUpdateAt()
                + "]";
    }
    
}
