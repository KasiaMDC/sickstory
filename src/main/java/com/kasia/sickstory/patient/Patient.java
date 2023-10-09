package com.kasia.sickstory.patient;

import com.kasia.sickstory.sickness.Sickness;
import com.kasia.sickstory.user.User;
//import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;

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
