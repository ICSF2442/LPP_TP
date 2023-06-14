package objects;

import interfaces.slogan;

public class HotelResort extends Hotel implements slogan {
    //conceito de polimorfismo
    public void descricao() {
        System.out.println("HotelResort");
    }
    //utilização da classe abstrata
    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
