package com.example.projetolpp;

import comn.objects.Apartamento;
import comn.objects.Hostel;
import comn.objects.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controlador {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane listaAcomodacao;

    private Hotel hoteis;

    private Hostel hostels;

    private Apartamento apartamentos;

    @FXML
    private TextField listagemAcomodacao;

    public static StringBuilder descriptionHotels = new StringBuilder();



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onMostrarHoteis() {
        listagemAcomodacao.setText(descriptionHotels.toString());
    }

    @FXML
    protected void onMostrarHosteis() {
    }

    @FXML
    protected void onMostrarApartamentos() {
    }
}