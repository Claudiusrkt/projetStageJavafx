module com.project.javafx4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.chrome_driver;
    requires dev.failsafe.core;
    requires com.google.auto.service;
    requires com.google.gson;
    requires org.json.chargebee;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.seleniumhq.selenium.edge_driver;
    requires org.seleniumhq.selenium.firefox_driver;
    requires okio;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.project.javafx4 to javafx.fxml;
    exports com.project.javafx4;
    exports com.project.javafx4.controller;
    opens com.project.javafx4.controller to javafx.fxml;
}