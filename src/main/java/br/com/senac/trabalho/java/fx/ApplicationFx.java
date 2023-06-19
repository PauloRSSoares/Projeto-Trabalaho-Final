package br.com.senac.trabalho.java.fx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationFx {

    public static void main(String[] args) {

        Application.launch(JavaFxApplication.class, args);
    }
}