package com.kasia.sickstory.patient;

import com.kasia.sickstory.sickness.Sickness;
import com.kasia.sickstory.user.User;
//import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "patient")
    private Set<Sickness> sicknesses = new HashSet<>();

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
