package com.uptc.TallerSpringBoot_Posgres_UPTC.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "schools")
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "school")
    @JoinColumn(name = "schoolDirector", referencedColumnName = "school_id")
    private SchoolDirector schoolDirector;


    @OneToMany(mappedBy = "school")
    private List<Subject> subjects;

    public School() {
    }

    public School(Integer id, String name, String address, String email, String description ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
