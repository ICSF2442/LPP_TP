package objects;

import interfaces.slogan;

public class Hostel extends Acomodacao implements slogan {

    //utilização da classe abstrata
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hostel!";
    }
}
