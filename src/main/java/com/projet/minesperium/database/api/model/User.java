package com.projet.minesperium.database.api.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String pseudo;

    @Column
    private String password;

    @Column
    private String phonenumber;

    @Column
    private String civilisation;

    @Column
    private Integer rights;

    @Column
    private Boolean activated;

    @Column
    private String mail;

    public User(String pseudo, String password, String phonenumber, String civilisation, Integer rights, Boolean activated, String mail) {
        this.pseudo = pseudo;
        this.password = password;
        this.phonenumber = phonenumber;
        this.civilisation = civilisation;
        this.rights = rights;
        this.activated = activated;
        this.mail = mail;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCivilisation() {
        return civilisation;
    }

    public void setCivilisation(String civilisation) {
        this.civilisation = civilisation;
    }

    public Integer getRights() {
        return rights;
    }

    public void setRights(Integer rights) {
        this.rights = rights;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "pseudo='" + pseudo + '\'' +
                '}';
    }
}
