package com.project.javafx4.model;

public class Compte {
    private String mail;
    private String nom;
    private String prenom;
    private String motPasse;
    private String listeUser;
    private String genre;
    private String address;
    private String fonction;
    private String situation;

    public String getMail(){
        return mail;
    }
    public String getMotPasse(){
        return motPasse;
    }

    public String getListeUser(){
        return listeUser;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getGenre(){
        return genre;
    }
    public String getAddress(){
        return address;
    }
    public String getFonction(){
        return fonction;
    }
    public String getSituation(){
        return situation;
    }

    public void setMail(String email){
        this.mail =email;
    }
    public void setMotPasse(String motDePasse){
        this.motPasse= motDePasse;
    }
    public void setListeUser(String liste){
        this.listeUser =liste;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setFonction(String fonction){
        this.fonction = fonction;
    }
    public void setSituation(String situation){
        this.situation = situation;
    }
}
