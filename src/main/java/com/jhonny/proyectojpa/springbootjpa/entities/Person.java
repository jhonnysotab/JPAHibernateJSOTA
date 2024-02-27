package com.jhonny.proyectojpa.springbootjpa.entities;

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
    private String lastname;
    
    @Column(name = "programming_language")
    private String programmingLanguage;
    
    @Column(name="create_at")
    private LocalDateTime creatAt;

    @Column(name="update_at")
    private LocalDateTime updateAt;
    
    public Person() { 
    }
    
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }


    @PrePersist
    public void prePersist(){

        System.out.println("Evento del Ciclo de vida del Entity prePersist");
        this.creatAt= LocalDateTime.now();
    } 
    @PreUpdate
    public void preUpdate(){
        System.out.println();
        System.out.println("Evento del Ciclo de vida del Entity preUpdate");
        this.updateAt= LocalDateTime.now();
    } 

    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
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
    public String getlastname() {
        return lastname;
    }
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }


    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
                + programmingLanguage +", Create_at "+creatAt+" update_at, "+updateAt+" ]";
    }

}
