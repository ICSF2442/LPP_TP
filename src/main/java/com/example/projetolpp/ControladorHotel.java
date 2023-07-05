package com.example.projetolpp;

import comn.main;
import javafx.scene.control.*;

public class ControladorHotel {
    public ChoiceBox<Integer> nEstrelasInsert;
    public Button submeterHotel;
    public TextField categoriaText;
    public CheckBox acessibilidadeCheck;

    public void initialize(){
        main main = new main();
        for (int i=1; i<6; i++){
          nEstrelasInsert.getItems().add(i);
        }
        main.initializeCriarHotel(acessibilidadeCheck,nEstrelasInsert, submeterHotel, categoriaText);

    }
}
