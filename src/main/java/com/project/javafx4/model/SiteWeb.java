package com.project.javafx4.model;

public class SiteWeb {
    private  String nomSite,urlSite,passwordSite,loginSite,category,id_login,id_password,id_submit,classLogin,classPassword,SiteDispo;
    private String[] listeSite;
    public String getNomSite(){
        return nomSite;
    }
    public String getUrlSite(){
        return urlSite;
    }
    public String getPasswordSite(){
        return passwordSite;
    }
    public String getLoginSite(){
        return loginSite;
    }

    public String getCategory(){return category;}
    public String getSiteDispo(){
        return SiteDispo;
    }
    public String[] getlisteSite(){
        return listeSite;
    }
    public String getId_login(){
        return id_login;
    }
    public String getId_password(){
        return id_password;
    }
    public String getId_submit(){
        return id_submit;
    }
    public String getClassLogin(){
        return classLogin;
    }
    public String getClassPassword(){
        return classPassword;
    }
    public void setNomSite( String NomSite){
        this.nomSite = NomSite;
    }
    public void setUrlSite(String Url){
        this.urlSite= Url;
    }
    public void setPasswordSite(String MotPasse){
        this.passwordSite =MotPasse;
    }
    public void setloginSite(String Id){
        this.loginSite = Id;
    }
    public void setIdChampMotPasse(String Mot){
        this.loginSite = Mot;
    }

    public void setCategory(String Cat){ this.category=Cat;}
    public void setSiteDispo(String site){
        this.SiteDispo=site;
    }
    public void setId_login(String id){
        this.id_login = id;
    }
    public void setId_password(String id){
        this.id_password = id;
    }
    public void setId_submit(String id){
        this.id_submit = id;
    }
    public void setListeSite(String[] liste){
        this.listeSite=liste;
    }
    public void setClassLogin(String classeLog){
        this.classLogin= classeLog;
    }
    public void setClassPassword(String classePass){
        this.classPassword=classePass;
    }
}
