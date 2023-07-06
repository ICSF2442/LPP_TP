package comn;

import comn.objects.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


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

    public String[] listaDeComodidades;

        public void initialize(ListView<Acomodacao> listagemAcomodacao, Button botaoHotel, Button botaoHostel, Button botaoApartamento, Button normalHotelBotao, Button resortHotelBotao, Button criarAcomodacao, ButtonBar barraBotoes){
            acomodacao = new Acomodacao();
            listaDeComodidades = new String[0];
            hotel = new Hotel();
            hostel = new Hostel();
            apartamento = new Apartamento();
            hotelResort = new HotelResort();
            textoDaListagem = new StringBuilder();
            ObservableList<Acomodacao> acomodacoes = FXCollections.observableArrayList();
            ObservableList<Acomodacao> apartmentos = FXCollections.observableArrayList();
            ObservableList<Acomodacao> hoteis = FXCollections.observableArrayList();
            ObservableList<Acomodacao> resortHoteis = FXCollections.observableArrayList();
            ObservableList<Acomodacao> hosteis = FXCollections.observableArrayList();
            normalHotelBotao.setVisible(false);
            resortHotelBotao.setVisible(false);
            barraBotoes.setVisible(false);
            Acomodacao[] ret = Acomodacao.search(null,null,null,null, null);
            if(ret != null){
                acomodacoes.addAll(Arrays.asList(ret));
                listagemAcomodacao.setItems(acomodacoes);
                listagemAcomodacao.getItems();
            }




            //barraBotoes.setVisible(false);
            //listagemAcomodacao.setText("AAAAAAA");
            listagemAcomodacao.setCellFactory(param -> new ListCell<Acomodacao>() {
                @Override
                protected void updateItem(Acomodacao item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        // Customize the cell based on the type of the object
                        if (item instanceof Apartamento) {
                            Apartamento apartamento = (Apartamento) item;
                            setText(apartamento.toString());
                            // Set other customization for Apartamento cell if needed
                        }else if (item instanceof HotelResort) {
                            HotelResort hotelResort = (HotelResort) item;
                            setText(hotelResort.toString());
                                // Set other customization for HotelResort cell if needed
                        } else if (item instanceof Hotel) {
                            Hotel hotel = (Hotel) item;
                            setText(hotel.toString());
                            // Set other customization for Hotel cell if needed
                        } else if (item instanceof Hostel) {
                            Hostel hostel = (Hostel) item;
                            setText(hostel.toString());
                            // Set other customization for Hostel cell if needed
                        } else {
                            Acomodacao acomodacao = (Acomodacao) item;
                            setText(acomodacao.toString());
                        }
                    }
                }
            });

            botaoHotel.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(true);
                    resortHotelBotao.setVisible(true);
                   Hotel[] hoteis1 = Hotel.search(null,null,null,null);
                   if(hoteis1 != null){
                       hoteis.addAll(Arrays.asList(hoteis1));
                       listagemAcomodacao.getItems().clear();
                       listagemAcomodacao.setItems(hoteis);
                   }

                }
            });
            botaoHostel.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(false);
                    resortHotelBotao.setVisible(false);
                    Hostel[] hosteis1 = Hostel.search(null,null,null,null);
                    if(hosteis1 != null){
                        hosteis.addAll(Arrays.asList(hosteis1));
                        listagemAcomodacao.getItems().clear();
                        listagemAcomodacao.setItems(hosteis);
                    }

                }

            });
            botaoApartamento.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(false);
                    resortHotelBotao.setVisible(false);
                    Apartamento[] apartamentos1 = Apartamento.search(null,null,null,null,null,null);
                    if(apartamentos1 != null){
                        apartmentos.addAll(Arrays.asList(apartamentos1));
                        listagemAcomodacao.getItems().clear();
                        listagemAcomodacao.setItems(apartmentos);
                    }

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
                    normalHotelBotao.setVisible(true);
                    resortHotelBotao.setVisible(true);
                    HotelResort[] hoteisResort1 = (HotelResort[]) HotelResort.search(null,null,null,null);
                    if(hoteisResort1 != null){
                        resortHoteis.addAll(Arrays.asList(hoteisResort1));
                        listagemAcomodacao.getItems().clear();
                        listagemAcomodacao.setItems(resortHoteis);
                    }

                }
            });
            normalHotelBotao.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(true);
                    resortHotelBotao.setVisible(true);
                    Hotel[] hoteis1 = Hotel.search(null,null,null,null);
                    if(hoteis1 != null){
                        hoteis.addAll(Arrays.asList(hoteis1));
                        listagemAcomodacao.getItems().clear();
                        listagemAcomodacao.setItems(hoteis);
                    }
                }
            });

        }

    public void addComodidade(String comodidade) {
        // Create a new array with increased size
        if(listaDeComodidades != null){
            String[] newArray = new String[listaDeComodidades.length + 1];

            // Copy existing elements to the new array
            System.arraycopy(listaDeComodidades, 0, newArray, 0, listaDeComodidades.length);

            // Add the new value to the end of the new array
            newArray[newArray.length - 1] = comodidade;

            // Update the reference to the new array
            listaDeComodidades = newArray;
        }else{
            listaDeComodidades = new String[1];
            listaDeComodidades[0] = comodidade;
        }

    }


    public void initializeCriarAcomodacao(TextField nomeAcomodacao, TextField enderecoAcomodacao, ChoiceBox<Integer> classificacaoChoice, TextField precoAcomodacao, ChoiceBox<String> comodidadeInsert, Button submeterComodidade, Button submeterAcomodacao, ChoiceBox<String> tipoDeAcomodacao, TextArea comodidadeList){
            acomodacao = new Acomodacao();
            StringBuilder listaComodidades = new StringBuilder();
            Acomodacao novaAcomodacao = new Acomodacao();

        submeterComodidade.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

                if(!Objects.equals(comodidadeInsert.getValue(), "Não há comodidades a apresentar.") && !Objects.equals(comodidadeInsert.getValue(),null) && !Objects.equals(comodidadeInsert.getValue(),"")){
                    listaComodidades.append(comodidadeInsert.getValue());
                    listaComodidades.append(", ");
                    comodidadeList.setText(String.valueOf(listaComodidades));
                    addComodidade(comodidadeInsert.getValue());
                }

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
                    novaAcomodacao.store();
                    for (String listaDeComodidade : listaDeComodidades) {
                        Comodidade[] ret = Comodidade.search(null,listaDeComodidade);
                        ret[0].addComodidadeAcomodacao(ret[0].getId(),novaAcomodacao.getId());
                    }
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Hotel")){
                        Node node = (Node) actionEvent.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarHotel.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            stage.setUserData(novaAcomodacao);
                            stage.setTitle("Criar Hotel");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(Objects.equals(tipoDeAcomodacao.getValue(), "Hotel Resort")){
                        Node node = (Node) actionEvent.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        Hotel novoHotel = new Hotel(novaAcomodacao);
                        hotelResort = new HotelResort(novaAcomodacao, novoHotel);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projetolpp/CriarHotelResort.fxml"));
                            stage.setUserData(novaAcomodacao);
                            Parent root1 = (Parent) fxmlLoader.load();
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
                            Node node = (Node) actionEvent.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            Parent root1 = (Parent) fxmlLoader.load();
                            stage.setUserData(novaAcomodacao);
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
                            Node node = (Node) actionEvent.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            Parent root1 = (Parent) fxmlLoader.load();
                            stage.setUserData(novaAcomodacao);
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

    public void initializeCriarHotel(CheckBox acessibilidadeCheck, ChoiceBox<Integer> nEstrelasInsert, Button submeterHotel, TextField categoriaText){
        submeterHotel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Acomodacao u = (Acomodacao) stage.getUserData();
                Hotel novoHotel = new Hotel(u);
                if(acessibilidadeCheck.isSelected()) novoHotel.setAcessibilidade(1);
                novoHotel.setCategoria(categoriaText.getText());
                novoHotel.setNumeroEstrelas(Integer.parseInt(String.valueOf(nEstrelasInsert.getValue())));
                novoHotel.store();
                novoHotel.updateAcomodacaoSubclasse("hotel",u.getId(),novoHotel.getId());
            }
        });
    }

    public void initializeCriarHostel(CheckBox casaBanho, CheckBox internet, CheckBox quartos, Button botao){
        botao.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Acomodacao u = (Acomodacao) stage.getUserData();
                Hostel novoHostel = new Hostel(u);
                System.out.println(novoHostel.getPrecoNoite());
                if(casaBanho.isSelected()) novoHostel.setCasaDeBanhoCompartilhada(1);
                if(internet.isSelected()) novoHostel.setInternet(1);
                if(quartos.isSelected()) novoHostel.setQuartosCompartilhados(1);
                novoHostel.store();
                novoHostel.updateAcomodacaoSubclasse("hostel",u.getId(),novoHostel.getId());
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
