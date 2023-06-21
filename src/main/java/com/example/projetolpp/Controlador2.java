package com.example.projetolpp;

import comn.main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controlador2 {
    @FXML
    private TextArea comodidadeList;
    @FXML
    private TextField nomeAcomodacao;
    @FXML
    private TextField enderecoAcomodacao;
    @FXML
    private ChoiceBox<Integer> classificacaoChoice;
    @FXML
    private TextField precoAcomodacao;
    @FXML
    private TextField comodidadeInsert;
    @FXML
    private Button submeterComodidade;
    @FXML
    private Button submeterAcomodacao;
    @FXML
    private ChoiceBox<String> tipoDeAcomodacao;


    public void initialize(){
        comn.main main = new main();
        main.initializeCriarAcomodacao(nomeAcomodacao, enderecoAcomodacao, classificacaoChoice, precoAcomodacao, comodidadeInsert, submeterComodidade,submeterAcomodacao, tipoDeAcomodacao, comodidadeList);
        for (int i=1; i<11; i++){
            classificacaoChoice.getItems().add(i);
        }
        tipoDeAcomodacao.getItems().add("Hotel");
        tipoDeAcomodacao.getItems().add("Hotel Resort");
        tipoDeAcomodacao.getItems().add("Hostel");
        tipoDeAcomodacao.getItems().add("Apartamento");


    }


}
