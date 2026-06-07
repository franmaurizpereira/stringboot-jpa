package com.franmauriz.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String lastName;
    @Column(name="create_at")
    private LocalDateTime createAt;
    @Column(name="update_at")
    private LocalDateTime updateAt;

    @Column(name="program_language")
    private String programLanguage;

    public Person() {
    }

    public Person(Long id, String name, String lastName, String programLanguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.programLanguage = programLanguage;
    }

    @PrePersist
    public void prePersist(){
        System.out.println("Evento de ciclo de vida del entity pre persist");
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Evento de ciclo de vida del entity pre update");
        this.updateAt = LocalDateTime.now();
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
        return "[id=" + id + ", name=" + name + ", lastName=" + lastName + ", programLanguage=" + programLanguage + ", create_At=" + createAt + ", update_At= " + updateAt
                + "]";
    }
    
}
