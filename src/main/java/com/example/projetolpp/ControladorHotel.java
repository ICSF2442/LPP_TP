package com.example.projetolpp;

import comn.main;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ControladorHotel {
    public TextField nQuartosDisponiveisInsert;
    public ChoiceBox<Integer> nEstrelasInsert;
    public Button submeterHotel;
    public TextField nQuartosInsert;

    public void initialize(){
        main main = new main();
        for (int i=1; i<6; i++){
          nEstrelasInsert.getItems().add(i);
        }
        main.initializeCriarHotel(nQuartosDisponiveisInsert, nEstrelasInsert, submeterHotel, nQuartosInsert);

    }
}
