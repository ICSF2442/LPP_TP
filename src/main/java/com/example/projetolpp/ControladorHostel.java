package com.example.projetolpp;

import comn.main;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

public class ControladorHostel {
    public CheckBox quartosCompartilhadosCheck;
    public CheckBox InternetCheck;
    public CheckBox casaDeBanhoCheck;
    public Button botaoSubmeter;

    public void initialize() throws IOException {
        main main = new main();
        main.initializeCriarHostel(casaDeBanhoCheck, InternetCheck, quartosCompartilhadosCheck,botaoSubmeter);
    }
}
