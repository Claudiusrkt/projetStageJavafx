package com.project.javafx4.controller;

import com.google.gson.Gson;
import com.project.javafx4.model.Parametre;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.TypeToken;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class Operation {
    public boolean isValideAuhtentif(WebDriver driver , String adrMail , String  MotPasse){
        boolean ok =false;
        Parametre param = new Parametre();
        try {
            driver.get(param.urlApi+"login");
            WebElement emailField = driver.findElement(By.id("inputEmail"));
            WebElement passwordField = driver.findElement(By.id("inputPassword"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
            emailField.sendKeys(adrMail);
            passwordField.sendKeys(MotPasse);
            loginButton.click();
            try {
                WebElement alertElement = driver.findElement(By.className("alert-danger"));
                System.out.println("Adresse e-mail ou identifiant invalide  ");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Connecté");
                ok = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }
    public String recupereSiteData(String json, String name ,String aRecuperer , String value ){
        String rst =null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString(name).equals(value)) {
                    rst = jsonObject.getString(aRecuperer);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rst;
    }
    public String dechiffrementMdp(String MdpChiffrer){
        String decryptedPassword = null;
        Parametre parametre = new Parametre();
        try {
            String encryptedResult = MdpChiffrer;
            String[] parts = encryptedResult.split("::");
            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] encryptedPassword = Base64.getDecoder().decode(parts[1]);
            byte[] key = hexStringToByteArray(parametre.keyString);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPassword);
            decryptedPassword = new String(decryptedPasswordBytes, "UTF-8");

            System.out.println("Decrypted Password: " + decryptedPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return decryptedPassword;
    }
    //addition avec le dechiffrement
    private  byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
    public void remplirLog(WebDriver driver,String url, String identifiant, String MotPasse ,String id_login,String id_mpd , String id_submit) {
        try {
            driver.get(url);
            Thread.sleep(3000);
            WebElement emailField = driver.findElement(By.cssSelector(id_login));
            WebElement passwordField = driver.findElement(By.cssSelector(id_mpd));
            WebElement loginButton = driver.findElement(By.cssSelector(id_submit));
            emailField.sendKeys(identifiant);
            passwordField.sendKeys(MotPasse);
//            loginButton.click();
//            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String[] jsonToTable(String ficJson , String nomColonne){
        List<String> liste = new ArrayList<>();
        Gson gson = new Gson();
        String[] rst ;
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        List<Map<String, Object>> data = gson.fromJson(ficJson, listType);
        for (Map<String, Object> item : data) {
            liste.add((String) item.get(nomColonne));
        }
        rst = liste.toArray(new String[0]);
        return rst;
    }
    public String recupererficJson(WebDriver driver, String Url) {
        String rst = null;
        try {
            driver.get(Url);
            try {
                rst = driver.findElement(By.tagName("body")).getText();
                int startIndex = rst.indexOf('[');
//                rst = rst.substring(startIndex + 1);
                int endIndex = rst.lastIndexOf(']') + 1;
                rst = rst.substring(startIndex, endIndex);
//                rst = rst.substring(0, rst.length() - 1);
            } catch (Exception e) {
                rst = driver.getPageSource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(rst);
        return rst;
    }

    public void seDeconnecterApi(WebDriver driver){
        Parametre parametre = new Parametre();
        String url = parametre.urlApi+"site/";
        String classDeconn= "btn btn-lg btn-outline-secondary";
        WebElement logoutButton = driver.findElement(By.cssSelector("."+classDeconn.replace(" ",".")));
        logoutButton.click();
        driver.get(url);
    }
}
