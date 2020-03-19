package com.welcome.bot.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welcome.bot.models.AuthProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "zaposlenik", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

 private String password;

private String lastName;

    @Email
    @Column(nullable = false)
    private String email;
   
    public User(String email) {
    	this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}
