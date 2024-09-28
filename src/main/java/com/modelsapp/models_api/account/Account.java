package com.modelsapp.models_api.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String username;
    private int age;
    private String email;
    private String senha;

    private void updateUsername() {
        this.username = this.firstName.trim() + this.lastName.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.updateUsername();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.updateUsername();
    }

}
