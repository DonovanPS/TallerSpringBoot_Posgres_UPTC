package com.uptc.TallerSpringBoot_Posgres_UPTC.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private SchoolDirector schoolDirector;


    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<Subject> subjects;

    public School() {
    }

    public School(Integer id, String name, String address, String email, String description, SchoolDirector schoolDirector, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.description = description;
        this.schoolDirector = schoolDirector;
        this.subjects = subjects;
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

    public SchoolDirector getSchoolDirector() {
        return schoolDirector;
    }

    public void setSchoolDirector(SchoolDirector schoolDirector) {
        this.schoolDirector = schoolDirector;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
