package com.example.projetolpp;

import comn.main;
import comn.objects.Atividade;
import comn.objects.Comodidade;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;



public class ControladorHotelResort {
    public TextField listaAtividade;
    public Button adicionarAtividade;
    public ChoiceBox<String> insertAtividades;
    public Button submeterResort;

    public void initialize(){
        main main = new main();
        main.initializeCriarHotelResort(listaAtividade, adicionarAtividade,insertAtividades,submeterResort);
        Atividade[] ret = Atividade.search(null,null);

        if(ret !=null){
            insertAtividades.getItems().add("");
            for (Atividade atividade : ret) {
                insertAtividades.getItems().add(atividade.getNome());
            }
        }else {
            insertAtividades.getItems().add("Não há comodidades a apresentar.");
        }
    }
}
