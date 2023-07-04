package com.example.projetolpp;
import comn.main;
import comn.objects.Acomodacao;
import comn.objects.Apartamento;
import comn.objects.Hostel;
import comn.objects.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controlador {

    @FXML
    private ButtonBar barraBotoes;
    @FXML
    private Pane paneAcomodacao;
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<Acomodacao> listagemAcomodacao;
    @FXML
    private Button botaoHotel;
    @FXML
    private Button botaoHostel;
    @FXML
    private Button botaoApartamento;
    @FXML
    private Button normalHotelBotao;
    @FXML
    private Button resortHotelbotao;
    @FXML
    private Button criarAcomodacao;


    public static StringBuilder descriptionHotels = new StringBuilder();

    public void initialize() throws IOException { //Inicialização do código para fazer a analíse do texto exportando os objetos.
        comn.main main = new main();

        main.initialize(listagemAcomodacao, botaoHotel, botaoHostel,botaoApartamento,normalHotelBotao,resortHotelbotao,criarAcomodacao,barraBotoes);
    }

    public void clicarAcomodacao(){

    }



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onMostrarHosteis() {
    }

    @FXML
    protected void onMostrarApartamentos() {
    }
}