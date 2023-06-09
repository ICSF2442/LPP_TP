package objects;

import interfaces.slogan;

public class HotelResort extends Hotel implements slogan {

    //utilização da classe abstrata
    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
