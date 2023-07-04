package comn;

import com.example.projetolpp.Aplicacao;
import comn.objects.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.VirtualContainerBase;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Objects;
import java.util.Vector;


public class main {
    private TextArea comodidadeList;

    private Acomodacao acomodacao;

    private Hotel hotel;

    private Hostel hostel;

    private Apartamento apartamento;

    private HotelResort hotelResort;

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

    private TextField nomeAcomodacao;

    private TextField enderecoAcomodacao;

    private ChoiceBox<Integer> classificacaoChoice;

    private TextField precoAcomodacao;

    private TextField comodidadeInsert;

    private Button submeterComodidade;

    private Button submeterAcomodacao;

    private ChoiceBox<String> tipoDeAcomodacao;

    public TextField nQuartosDisponiveisInsert;
    public MenuButton nEstrelasInsert;
    public Button submeterHotel;
    public TextField nQuartosInsert;

        public void initialize(TextArea listagemAcomodacao, Button botaoHotel, Button botaoHostel, Button botaoApartamento, Button normalHotelBotao, Button resortHotelBotao, Button criarAcomodacao, ButtonBar barraBotoes){
            acomodacao = new Acomodacao();
            hotel = new Hotel();
            hostel = new Hostel();
            apartamento = new Apartamento();
            hotelResort = new HotelResort();
            textoDaListagem = new StringBuilder();
            //barraBotoes.setVisible(false);
            //listagemAcomodacao.setText("AAAAAAA");

/*
 *
 *
 *
 */
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
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriaAcomodacao.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Criar Acomodacao");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
        textoDaListagem = new StringBuilder();

        switch (tipo) {
            case 1 -> {
                if (listadeHotel != null) {
                    for (Hotel hotel : listadeHotel) {
                        textoDaListagem.append(hotel.toString());
                        textoDaListagem.append("\n");
                        textoDaListagem.append("------");
                        textoDaListagem.append("\n");
                    }
                } else {
                    textoDaListagem = new StringBuilder();
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum hotel a apresentar--");
                    textoDaListagem.append("\n");
                }
            }
            case 2 -> {
                if (listadeHostel != null) {
                    for (Hostel hostel : listadeHostel) {
                        textoDaListagem.append(hostel.toString());
                    }

                } else {
                    textoDaListagem = new StringBuilder();
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum hostel a apresentar--");
                    textoDaListagem.append("\n");
                }
            }
            case 3 -> {
                if (listadeApartamento != null) {
                    for (Apartamento apartamento : listadeApartamento) {
                        textoDaListagem.append(apartamento.toString());
                    }
                } else {
                    textoDaListagem = new StringBuilder();
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum apartamento a apresentar--");
                    textoDaListagem.append("\n");
                }
            }
            case 4 -> {
                if (listadeHotelResort != null) {
                    for (HotelResort hotelResort : listadeHotelResort) {
                        textoDaListagem.append(hotelResort.toString());
                    }
                } else {
                    textoDaListagem = new StringBuilder();
                    textoDaListagem.append("\n");
                    textoDaListagem.append("--Nenhum Hotel Resort a apresentar--");
                    textoDaListagem.append("\n");
                }
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

    public void initializeCriarAcomodacao(TextField nomeAcomodacao, TextField enderecoAcomodacao, ChoiceBox<Integer> classificacaoChoice, TextField precoAcomodacao, TextField comodidadeInsert, Button submeterComodidade, Button submeterAcomodacao, ChoiceBox<String> tipoDeAcomodacao, TextArea comodidadeList){
            acomodacao = new Acomodacao();
            StringBuilder listaComodidades = new StringBuilder();
            Acomodacao novaAcomodacao = new Acomodacao();

        submeterComodidade.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

               listaComodidades.append(comodidadeInsert.getText());
               comodidadeList.setText(String.valueOf(listaComodidades));
               listaComodidades.append(", ");
               comodidadeInsert.setText("");
               comodidadeList.setText(String.valueOf(listaComodidades));
            }
        });

        submeterAcomodacao.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nomeAcomodacao.getText() != null && enderecoAcomodacao.getText() != null && classificacaoChoice.getValue() != null && precoAcomodacao.getText() != null && tipoDeAcomodacao.getValue() != null ){
                    novaAcomodacao.setNome(nomeAcomodacao.getText());
                    novaAcomodacao.setClassificacao(classificacaoChoice.getValue());
                    novaAcomodacao.setPrecoNoite(Double.parseDouble(precoAcomodacao.getText()));
                    novaAcomodacao.setEndereco(enderecoAcomodacao.getText());
                    acomodacao = novaAcomodacao;
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Hotel")){
                        hotel = new Hotel(novaAcomodacao);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarHotel.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Criar Hotel");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Hotel Resort")){
                        Hotel novoHotel = new Hotel(novaAcomodacao);
                        hotelResort = new HotelResort(novaAcomodacao, novoHotel);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarHotelResort.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Criar Acomodacao");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Apartamento")){
                        apartamento = new Apartamento(novaAcomodacao);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarApartamento.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Criar Acomodacao");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Hostel")){
                        hostel = new Hostel(novaAcomodacao);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarHostel.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Criar Acomodacao");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }

    public void initializeCriarHotel(TextField nQuartosDisponiveisInsert, ChoiceBox<Integer> nEstrelasInsert, Button submeterHotel, TextField nQuartosInsert){
        submeterHotel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                hotel.setNumeroEstrelas(Integer.parseInt(String.valueOf(nEstrelasInsert.getValue())));
            }
        });
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
