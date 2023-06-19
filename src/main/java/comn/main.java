package comn;

import com.example.projetolpp.Aplicacao;
import com.example.projetolpp.Reserva;
import comn.objects.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.VirtualContainerBase;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Objects;
import java.util.Vector;


public class main {

    private StringBuilder textoDaListagem;
    private Hotel[] listadeHotel;

    private Apartamento[] listadeApartamento;
    private Hostel[] listadeHostel;
    private HotelResort[] listadeHotelResort;
    private TextArea listagemAcomodacao;
    private Button botaoHotel;
    private Button botaoHostel;
    private Button botaoApartamento;
    private Button normalHotelBotao;
    private Button resortHotelBotao;
    private ButtonBar barraBotoes;
    private Button criarAcomodacao;


        public void initialize(TextArea listagemAcomodacao, Button botaoHotel, Button botaoHostel, Button botaoApartamento, Button normalHotelBotao, Button resortHotelBotao, Button criarAcomodacao, ButtonBar barraBotoes){
            textoDaListagem = new StringBuilder("");
            barraBotoes.setVisible(false);
            listadeHotel = new Hotel[1];
            Acomodacao b = new Acomodacao("nome", "endereco", 10, 25, "a"  );
            Hotel a = new Hotel(b);
            listadeHotel[0] = a;
            listagemAcomodacao.setText("AAAAAAA");


            botaoHotel.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    barraBotoes.setVisible(true);
                    obterListagemAcomodacao(1);
                    listagemAcomodacao.setText(String.valueOf(textoDaListagem));
                }
            });
            botaoHostel.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    obterListagemAcomodacao(2);
                    listagemAcomodacao.setText(String.valueOf(textoDaListagem));
                }
            });
            botaoApartamento.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    obterListagemAcomodacao(3);
                    listagemAcomodacao.setText(String.valueOf(textoDaListagem));
                }
            });

            criarAcomodacao.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    Reserva.main(null);
                }
            });

            resortHotelBotao.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    obterListagemAcomodacao(4);
                    listagemAcomodacao.setText(String.valueOf(textoDaListagem));
                }
            });
            normalHotelBotao.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    obterListagemAcomodacao(1);
                    listagemAcomodacao.setText(String.valueOf(textoDaListagem));
                }
            });



        }

    public void obterListagemAcomodacao(int tipo){
        textoDaListagem = new StringBuilder("");

        switch (tipo){
            case 1:
                if(listadeHotel != null){
                    for(Hotel hotel : listadeHotel){
                        textoDaListagem.append(hotel.toString());
                        textoDaListagem.append("\n");
                        textoDaListagem.append("------");
                        textoDaListagem.append("\n");
                    }
                }else {
                    textoDaListagem = new StringBuilder("");
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum hotel a apresentar--");
                    textoDaListagem.append("\n");
                }
                break;


            case 2:
                if(listadeHostel != null){
                    for(Hostel hostel : listadeHostel){
                        textoDaListagem.append(hostel.toString());
                    }

                }else {
                    textoDaListagem = new StringBuilder("");
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum hostel a apresentar--");
                    textoDaListagem.append("\n");
                }
                break;

            case 3:
                if(listadeApartamento != null){
                    for(Apartamento apartamento : listadeApartamento){
                        textoDaListagem.append(apartamento.toString());
                    }
                }else {
                    textoDaListagem = new StringBuilder("");
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum apartamento a apresentar--");
                    textoDaListagem.append("\n");
                }
                break;

            case 4:
                if(listadeHotelResort != null){
                    for(HotelResort hotelResort : listadeHotelResort){
                        textoDaListagem.append(hotelResort.toString());
                    }
                }else{
                    textoDaListagem = new StringBuilder("");
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum Hotel Resort a apresentar--");
                    textoDaListagem.append("\n");
                }
        }
    }

    public void aumentarListaHotel(Hotel hotel) {
        if (listadeHotel.length > 0) {
            Hotel[] hoteis = new Hotel[listadeHotel.length + 1];
            System.arraycopy(listadeHotel, 0, hoteis, 0, listadeHotel.length);
            hoteis[listadeHotel.length] = hotel;
            listadeHotel = hoteis;
        }
    }

    public StringBuilder getTextoDaListagem() {
        return textoDaListagem;
    }

    public void setTextoDaListagem(StringBuilder textoDaListagem) {
        this.textoDaListagem = textoDaListagem;
    }

    public Hotel[] getListadeHotel() {
        return listadeHotel;
    }

    public void setListadeHotel(Hotel[] listadeHotel) {
        this.listadeHotel = listadeHotel;
    }

    public Apartamento[] getListadeApartamento() {
        return listadeApartamento;
    }

    public void setListadeApartamento(Apartamento[] listadeApartamento) {
        this.listadeApartamento = listadeApartamento;
    }

    public Hostel[] getListadeHostel() {
        return listadeHostel;
    }

    public void setListadeHostel(Hostel[] listadeHostel) {
        this.listadeHostel = listadeHostel;
    }

    public HotelResort[] getListadeHotelResort() {
        return listadeHotelResort;
    }

    public void setListadeHotelResort(HotelResort[] listadeHotelResort) {
        this.listadeHotelResort = listadeHotelResort;
    }

    public TextArea getListagemAcomodacao() {
        return listagemAcomodacao;
    }

    public void setListagemAcomodacao(TextArea listagemAcomodacao) {
        this.listagemAcomodacao = listagemAcomodacao;
    }

    public Button getBotaoHotel() {
        return botaoHotel;
    }

    public void setBotaoHotel(Button botaoHotel) {
        this.botaoHotel = botaoHotel;
    }

    public Button getBotaoHostel() {
        return botaoHostel;
    }

    public void setBotaoHostel(Button botaoHostel) {
        this.botaoHostel = botaoHostel;
    }

    public Button getBotaoApartamento() {
        return botaoApartamento;
    }

    public void setBotaoApartamento(Button botaoApartamento) {
        this.botaoApartamento = botaoApartamento;
    }

    public Button getNormalHotelBotao() {
        return normalHotelBotao;
    }

    public void setNormalHotelBotao(Button normalHotelBotao) {
        this.normalHotelBotao = normalHotelBotao;
    }

    public Button getResortHotelBotao() {
        return resortHotelBotao;
    }

    public void setResortHotelBotao(Button resortHotelBotao) {
        this.resortHotelBotao = resortHotelBotao;
    }

    public ButtonBar getBarraBotoes() {
        return barraBotoes;
    }

    public void setBarraBotoes(ButtonBar barraBotoes) {
        this.barraBotoes = barraBotoes;
    }

    public Button getCriarAcomodacao() {
        return criarAcomodacao;
    }

    public void setCriarAcomodacao(Button criarAcomodacao) {
        this.criarAcomodacao = criarAcomodacao;
    }
}
