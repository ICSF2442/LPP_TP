package objects;

import interfaces.slogan;

public class Hostel extends Acomodacao implements slogan {





    //conceito de polimorfismo
    public void descricao() {
        System.out.println("Hostel");
    }



    //utilização da interface
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hostel!";
    }
}
