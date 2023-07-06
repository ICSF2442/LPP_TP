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
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


public class main {

    public String[] listaDeComodidades;

    public String[] listaDeAtividades;

    public ListView<Acomodacao> listagemAcomodacaoGlobal;

        public void initialize(ListView<Acomodacao> listagemAcomodacao, Button botaoHotel, Button botaoHostel, Button botaoApartamento, Button normalHotelBotao, Button resortHotelBotao, Button criarAcomodacao, ButtonBar barraBotoes,Button remover){
            listagemAcomodacaoGlobal = listagemAcomodacao;
            listaDeComodidades = new String[0];
            listaDeAtividades = new String[0];
            ObservableList<Acomodacao> acomodacoes = FXCollections.observableArrayList();
            ObservableList<Acomodacao> apartmentos = FXCollections.observableArrayList();
            ObservableList<Acomodacao> hoteis = FXCollections.observableArrayList();
            ObservableList<Acomodacao> resortHoteis = FXCollections.observableArrayList();
            ObservableList<Acomodacao> hosteis = FXCollections.observableArrayList();
            normalHotelBotao.setVisible(false);
            resortHotelBotao.setVisible(false);
            barraBotoes.setVisible(false);

            remover.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Acomodacao selectedItem = listagemAcomodacaoGlobal.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        if (Hotel.find(null, null, null, null, selectedItem.getId()) == 1) {
                            Hotel[] ret = Hotel.search(null, null, null, null, selectedItem.getId());
                            selectedItem.remove();
                            ret[0].remove();
                            Hotel[] hoteis1 = Hotel.search((Integer) null, (String) null, (Integer) null,null,null);
                            listagemAcomodacaoGlobal.getItems().clear();
                            hoteis.clear();
                            if(hoteis1 != null){

                                hoteis.addAll(Arrays.asList(hoteis1));
                                if (!hoteis.isEmpty()) {

                                    listagemAcomodacaoGlobal.setItems(hoteis);
                                }
                            }
                        }
                        if (Hostel.find(null, null, null, null, selectedItem.getId()) == 1) {
                            Hostel[] ret = Hostel.search(null, null, null, null, selectedItem.getId());
                            selectedItem.remove();
                            ret[0].remove();
                            Hostel[] hosteis1 = Hostel.search((Integer) null, (Integer) null,null,null,null);
                            listagemAcomodacaoGlobal.getItems().clear();
                            hosteis.clear();
                            if(hosteis1 != null){

                                hosteis.addAll(Arrays.asList(hosteis1));
                                if (!hosteis.isEmpty()) {
                                    listagemAcomodacaoGlobal.getItems().clear();
                                    listagemAcomodacaoGlobal.setItems(hosteis);
                                }
                            }
                        }

                    }

                }

            });


            listagemAcomodacaoGlobal.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    // Get the selected item
                    Acomodacao selectedItem = listagemAcomodacaoGlobal.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        if(selectedItem.getReserva() != 1){
                            selectedItem.setReserva(1);
                        }else {
                            selectedItem.setReserva(0);
                        }

                        selectedItem.store();
                        Hotel[] hoteis1 = Hotel.search((Integer) null, (String) null, (Integer) null,null,null);
                        listagemAcomodacaoGlobal.getItems().clear();
                        hoteis.clear();
                        if(hoteis1 != null){

                            hoteis.addAll(Arrays.asList(hoteis1));
                            if (!hoteis.isEmpty()) {

                                listagemAcomodacaoGlobal.setItems(hoteis);
                            }
                        }

                    }
                }
            });


            //barraBotoes.setVisible(false);
            //listagemAcomodacaoGlobal.setText("AAAAAAA");
            listagemAcomodacaoGlobal.setCellFactory(param -> new ListCell<Acomodacao>() {
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
                   Hotel[] hoteis1 = Hotel.search((Integer) null, (String) null, (Integer) null,null,null);
                    listagemAcomodacaoGlobal.getItems().clear();
                    hoteis.clear();
                   if(hoteis1 != null){

                       hoteis.addAll(Arrays.asList(hoteis1));
                       if (!hoteis.isEmpty()) {

                           listagemAcomodacaoGlobal.setItems(hoteis);
                       }
                   }

                }
            });
            botaoHostel.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(false);
                    resortHotelBotao.setVisible(false);
                    Hostel[] hosteis1 = Hostel.search((Integer) null, (Integer) null,null,null,null);
                    listagemAcomodacaoGlobal.getItems().clear();
                    hosteis.clear();
                    if(hosteis1 != null){

                        hosteis.addAll(Arrays.asList(hosteis1));
                        if (!hosteis.isEmpty()) {
                            listagemAcomodacaoGlobal.getItems().clear();
                            listagemAcomodacaoGlobal.setItems(hosteis);
                        }
                    }

                }

            });
                botaoApartamento.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        normalHotelBotao.setVisible(false);
                        resortHotelBotao.setVisible(false);
                        Apartamento[] apartamentos1 = Apartamento.search(null,null,null,null,null,null);
                        listagemAcomodacaoGlobal.getItems().clear();
                        apartmentos.clear();
                        if(apartamentos1 != null){

                            apartmentos.addAll(Arrays.asList(apartamentos1));
                            if (!apartmentos.isEmpty()) {
                                listagemAcomodacaoGlobal.getItems().clear();
                                listagemAcomodacaoGlobal.setItems(apartmentos);
                            }
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
                    HotelResort[] hoteisResort1 = HotelResort.search(null);
                    listagemAcomodacaoGlobal.getItems().clear();
                    resortHoteis.clear();
                    if(hoteisResort1 != null){

                        resortHoteis.addAll(Arrays.asList(hoteisResort1));
                        listagemAcomodacaoGlobal.getItems().clear();
                        listagemAcomodacaoGlobal.setItems(resortHoteis);
                    }

                }
            });
            normalHotelBotao.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    normalHotelBotao.setVisible(true);
                    resortHotelBotao.setVisible(true);
                    Hotel[] hoteis1 = Hotel.search((Integer) null, (String) null, (Integer) null,null,null);
                    if(hoteis1 != null){
                        hoteis.addAll(Arrays.asList(hoteis1));
                        if (!hoteis.isEmpty()) {

                            listagemAcomodacaoGlobal.setItems(hoteis);
                        }
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
    public void addAtividade(String atividade) {
        // Create a new array with increased size
        if(listaDeAtividades != null){
            String[] newArray = new String[listaDeAtividades.length + 1];

            // Copy existing elements to the new array
            System.arraycopy(listaDeAtividades, 0, newArray, 0, listaDeAtividades.length);

            // Add the new value to the end of the new array
            newArray[newArray.length - 1] = atividade;

            // Update the reference to the new array
            listaDeAtividades = newArray;
        }else{
            listaDeAtividades = new String[1];
            listaDeAtividades[0] = atividade;
        }

    }


    public void initializeCriarAcomodacao(TextField nomeAcomodacao, TextField enderecoAcomodacao, ChoiceBox<Integer> classificacaoChoice, TextField precoAcomodacao, ChoiceBox<String> comodidadeInsert, Button submeterComodidade, Button submeterAcomodacao, ChoiceBox<String> tipoDeAcomodacao, TextArea comodidadeList){
            Acomodacao acomodacao = new Acomodacao();
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
                    if(listaDeComodidades != null){
                        for (String listaDeComodidade : listaDeComodidades) {
                            Comodidade[] ret = Comodidade.search(null,listaDeComodidade);
                            ret[0].addComodidadeAcomodacao(ret[0].getId(),novaAcomodacao.getId());
                        }
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
                       HotelResort hotelResort = new HotelResort(novaAcomodacao, novoHotel);
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
                       Apartamento apartamento = new Apartamento(novaAcomodacao);
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
                      Hostel hostel = new Hostel(novaAcomodacao);
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

                stage.close();
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

                stage.close();
            }
        });
    }


    public void initializeCriarHotelResort(TextField listaAtividade, Button adicionarAtividade, ChoiceBox<String> insertAtividades, Button botao){
        botao.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Acomodacao u = (Acomodacao) stage.getUserData();
                Hostel novoHostel = new Hostel(u);
                System.out.println(novoHostel.getPrecoNoite());


                novoHostel.store();
                novoHostel.updateAcomodacaoSubclasse("hostel",u.getId(),novoHostel.getId());

                stage.close();
            }
        });

        adicionarAtividade.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Acomodacao u = (Acomodacao) stage.getUserData();
                Hostel novoHostel = new Hostel(u);
                System.out.println(novoHostel.getPrecoNoite());


                novoHostel.store();
                novoHostel.updateAcomodacaoSubclasse("hostel",u.getId(),novoHostel.getId());

                stage.close();
            }
        });
    }


}
