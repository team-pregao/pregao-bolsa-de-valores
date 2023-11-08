module com.teampregao.pregaobolsadevalores {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;
    requires eu.hansolo.tilesfx;
    requires org.junit.jupiter.api;

    opens com.teampregao.pregaobolsadevalores to javafx.fxml;
    exports com.teampregao.pregaobolsadevalores;
    exports com.teampregao.pregaobolsadevalores.controllers;
    opens com.teampregao.pregaobolsadevalores.controllers to javafx.fxml;
}