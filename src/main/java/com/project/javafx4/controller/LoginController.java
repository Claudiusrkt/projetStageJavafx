package com.project.javafx4.controller;

import com.project.javafx4.model.Compte;
import com.project.javafx4.model.Parametre;
import com.project.javafx4.model.SiteWeb;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicBoolean;

public class LoginController {
    @FXML
    private TextField mailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane anchorPaneSite;
    @FXML
    private AnchorPane anchorPaneLogin;
    @FXML
    private AnchorPane anchorPaneNavig;

    @FXML
    private AnchorPane anchorPaneLoad;

    @FXML
    private Text errorText;

    @FXML
    private Text mailError;

    @FXML
    private VBox listeSite;

    @FXML
    private Text nameUser,prenomUser,adressUser,situationUser,genreUser,fonctionuser;
    private String Webdriver;
    private boolean isLoggingIn = false,isSite =false;
    Compte compte = new Compte();
    SiteWeb siteWeb = new SiteWeb();

    @FXML//fonction de initialisation de l'interface
    public void initialize() {
        siteWeb.setListeSite(null);
        errorText.setText("");
        mailError.setText("");
        Platform.runLater(() -> {//Eviter la selection automatique de mailField
            mailField.requestFocus();
            mailField.getParent().requestFocus();
        });
        anchorPaneLogin.setVisible(false);
        anchorPaneSite.setVisible(false);
        anchorPaneNavig.setVisible(true);
        anchorPaneLoad.setVisible(false);
    }
    private void versSite() {
        anchorPaneLogin.setVisible(false);
        anchorPaneNavig.setVisible(false);
        anchorPaneSite.setVisible(true);
        anchorPaneLoad.setVisible(false);
    }
    private void versLogin() {
        anchorPaneLogin.setVisible(true);
        anchorPaneNavig.setVisible(false);
        anchorPaneSite.setVisible(false);
        anchorPaneLoad.setVisible(false);
    }

    private void versNavig() {
        anchorPaneLogin.setVisible(false);
        anchorPaneNavig.setVisible(true);
        anchorPaneSite.setVisible(false);
        anchorPaneLoad.setVisible(false);
    }

    @FXML
    protected void exit() {
        System.exit(0);
    }//fermer l'application

//    /*interface de login
    @FXML
    protected void confirmer() throws Exception {
        boolean vide = true;
        if(mailField.getText().isEmpty()|| passwordField.getText().isEmpty()){
            System.out.println("Veuillez remplir Tous les champs");
            informationMsg();
        }else {
            executionLogin();
            }
        }
    @FXML
    protected void verification() {
        String mail = mailField.getText();
        if (mail.endsWith("@gmail.com") || mail.endsWith("@yahoo.com") || (mail.endsWith("@outlook.com"))) {
            mailError.setText("");//adresse mail valide
        } else {
            mailError.setText("Adresse e-mail invalide");
        }
    }
    private void validation(WebDriver driverNoGui){
        Operation operation = new Operation();
        Parametre parametre = new Parametre();
        compte.setMail(mailField.getText());
        compte.setMotPasse(passwordField.getText());
        if(operation.isValideAuhtentif(driverNoGui,compte.getMail(),compte.getMotPasse())){
            siteWeb.setSiteDispo(operation.recupererficJson(driverNoGui,parametre.urlApi+"site/api/liste/site/Yes/json"));
            compte.setListeUser( operation.recupererficJson(driverNoGui,parametre.urlApi+"api/user/json"));
            siteWeb.setListeSite(operation.jsonToTable(siteWeb.getSiteDispo(),"site"));
        }else {
            errorText.setText("Adresse e-mail ou mot de passe invalide");
        }
//        driverNoGui.quit();
    }
    private void executionLogin() throws InterruptedException {
        if (isLoggingIn) {
            return;
        }

        isLoggingIn = true;

        Task<Void> loginVersSite = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                anchorPaneLoad.setVisible(true);
                anchorPaneLogin.setDisable(true);
                Pilote pilote = new Pilote();
                switch (Webdriver) {
                    case "edge":
                         validation(pilote.edgeDriver());
                        break;
                    case "chrome":
                         validation(pilote.chromeDriver());
                        break;
                    case "firefox": //201092000607 //201681004752 Rafanomezantsoa Jean Paul
                         validation(pilote.firefoxDriver());
                        break;
                }
                Thread.sleep(500);
                return null;
            }
            @Override
            protected void succeeded() {
                isLoggingIn = false;
                anchorPaneLoad.setVisible(false);
                anchorPaneLogin.setDisable(false);
                if ( siteWeb.getlisteSite() == null){
                    System.out.println("Aucun site disponible");
                }else {
                    listeSite.getChildren().clear();
                    ajoutButton(listeSite, siteWeb.getlisteSite());
                    rempliDonnePers();
                    versSite();
                }
            }
        };

        Thread thread = new Thread(loginVersSite);
        thread.start();
//        thread.join();
//        thread.join();
    }

    @FXML
    protected void retourNavigateur(){
        versNavig();
    }
    private void informationMsg() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("Veuillez remplir tous les champs !");
        alert.setContentText("Assurez-vous que tous les champs requis sont complétés.");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Ok");
            }
        });
    }
//     */
    //    /*interface de Site
    @FXML
    protected void retour() {
        if (showLogoutConfirmation()) {
            anchorPaneLogin.setVisible(true);
            anchorPaneSite.setVisible(false);
            listeSite.getChildren().clear();
            mailField.setText("");
            passwordField.setText("");
        }
    }
    private void ajoutButton(VBox vbox, String[] labels) {
        Pilote pilote = new Pilote();
        for (String label : labels) {
            Button button = new Button(label);
            button.setPrefHeight(60);
            button.setPrefWidth(171);
            button.setFont(Font.font("Microsoft YaHei", 12));
            vbox.getChildren().add(button);
            VBox.setMargin(button, new javafx.geometry.Insets(10, 0, 10, 0));
            button.getStyleClass().add("siteBtn");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Button clicked: " + label);
                    switch (Webdriver) {
                        case "edge":
                            buttonOnClicked(label,pilote.edgeDriver());
                            break;
                        case "chrome":
                            buttonOnClicked(label,pilote.chromeDriver());
                            break;
                        case "firefox":
                            buttonOnClicked(label, pilote.firefoxDriver());
                            break;
                    }
                }
            });
        }
    }
    private void buttonOnClicked(String bouton, WebDriver driver){
        Operation operation = new Operation();
        String idChampLogin = null, idChampPassword = null, idBtnSubmit = null,passwordDecrypter;
        siteWeb.setUrlSite(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","url",bouton));
        siteWeb.setloginSite(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","login",bouton));
        siteWeb.setPasswordSite(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","password",bouton));
        siteWeb.setId_login(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","id-login",bouton));
        siteWeb.setClassLogin(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","class-login",bouton));
        siteWeb.setId_password(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","id-mdp",bouton));
        siteWeb.setClassPassword(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","class-mdp",bouton));
        siteWeb.setId_submit(operation.recupereSiteData(siteWeb.getSiteDispo(),"site","class-submit",bouton));
        if (siteWeb.getId_login() == null){
            idChampLogin = "."+siteWeb.getClassLogin().replace(" ",".");
        }else {
            idChampLogin = "#"+siteWeb.getId_login();
        }
        //idchampPassword
        if (siteWeb.getId_password() == null){
            idChampPassword = "."+siteWeb.getClassPassword().replace(" ",".");
        }else {
            idChampPassword = "#"+siteWeb.getId_password();
        }
        idBtnSubmit ="."+siteWeb.getId_submit().replace(" ",".");
        passwordDecrypter = operation.dechiffrementMdp(siteWeb.getPasswordSite());
        operation.remplirLog(driver,siteWeb.getUrlSite(),siteWeb.getLoginSite(),passwordDecrypter,idChampLogin,idChampPassword,idBtnSubmit);

    }
    private void rempliDonnePers(){
        Operation operation = new Operation();
        compte.setNom(operation.recupereSiteData(compte.getListeUser(),"e-mail","nom",compte.getNom()));
        compte.setPrenom(operation.recupereSiteData(compte.getListeUser(),"e-mail","prénom",compte.getMail()));
        compte.setGenre(operation.recupereSiteData(compte.getListeUser(),"e-mail","genre",compte.getMail()));
        compte.setFonction(operation.recupereSiteData(compte.getListeUser(),"e-mail","fonction",compte.getMail()));
        compte.setSituation(operation.recupereSiteData(compte.getListeUser(),"e-mail","situation familiale",compte.getMail()));
        compte.setAddress(operation.recupereSiteData(compte.getListeUser(),"e-mail","address",compte.getMail()));
        nameUser.setText(compte.getNom());
        prenomUser.setText(compte.getPrenom());
        adressUser.setText(compte.getAddress());
        genreUser.setText(compte.getGenre());
        fonctionuser.setText(compte.getFonction());
        situationUser.setText(compte.getSituation());
    }
    private boolean showLogoutConfirmation() {
        AtomicBoolean deconnexion = new AtomicBoolean(false);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("Êtes-vous sûr de vouloir se déconnecter ?");
        alert.setContentText("Cette action est irréversible.");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                deconnexion.set(true);
            } else {
                System.out.println("Déconnexion annulée.");
            }
        });
        return deconnexion.get();
    }
//     */

//    /*interface de Navigateur
    @FXML
    protected void edgeButton() {
        Webdriver = "edge";
        System.out.println("Navigateur Choisi: " + Webdriver);
        executionVersLogin();
    }

    @FXML
    protected void chromeButton() {
        Webdriver = "chrome";
        System.out.println("Navigateur Choisi: " + Webdriver);
        executionVersLogin();
    }

    @FXML
    protected void firefoxButton() {
        Webdriver = "firefox";
        System.out.println("Navigateur Choisi: " + Webdriver);
        executionVersLogin();
    }

    private void executionVersLogin(){
        if (isSite) {
            return;
        }

        isSite = true;

        Task<Void> navigVersLogin = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                anchorPaneLoad.setVisible(true);
                anchorPaneNavig.setDisable(true);
                Thread.sleep(500);
                versLogin();
                return null;
            }
            @Override
            protected void succeeded() {
                isSite = false;
                anchorPaneLoad.setVisible(false);
                anchorPaneNavig.setDisable(false);
            }
        };
        Thread thread = new Thread(navigVersLogin);
        thread.start();
    }
    //     */
}
