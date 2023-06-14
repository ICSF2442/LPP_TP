package objects;

import interfaces.slogan;

public class Apartamento extends Acomodacao implements slogan {


    //conceito de polimorfismo
    public void descricao() {
        System.out.println("Apartamento");
    }
    //utilização do slogan
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Apartamentos!";
    }
}
