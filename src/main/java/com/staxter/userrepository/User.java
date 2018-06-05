package com.staxter.userrepository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by serge on 6/4/2018.
 */
public class User implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User(String id, String firstName, String lastName, String userName) {
        this(firstName,lastName,userName);
        this.id = id;

    }

    public User(String id, String firstName, String lastName, String userName, String plainTextPassword, String hashedPassword) {
        this(id,firstName,lastName,userName);
        this.plainTextPassword = plainTextPassword;
        this.hashedPassword = hashedPassword;
    }

    @JsonIgnore
    private String plainTextPassword;
    @JsonIgnore
    private String hashedPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return plainTextPassword != null ? plainTextPassword.equals(user.plainTextPassword) : user.plainTextPassword == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (plainTextPassword != null ? plainTextPassword.hashCode() : 0);
        return result;
    }
}
