package comn.objects;

import comn.interfaces.slogan;

import java.util.Arrays;

public class HotelResort extends Hotel implements slogan {
    private Integer id;
    public HotelResort() {
        super();
    }

    public HotelResort(Acomodacao acomodacao){
        super(acomodacao);
    }

    public HotelResort(Acomodacao acomodacao, int id) {
        super(acomodacao);
        this.id = id;
    }

    public HotelResort(Acomodacao acomodacao, String categoria, int acessibilidade, int numeroEstrelas, int id) {
        super(acomodacao, categoria, acessibilidade, numeroEstrelas);
        this.id = id;
    }

    public HotelResort(Acomodacao acomodacao, Hotel outroHotel) {
        super(acomodacao, outroHotel);
    }



    @Override
    public String toString() {
        return super.toString() +
                "Slogan: " + slogan() + "\n" +
                "Descrição: "+ descricao() + "\n"
                ;
    }

    //conceito de polimorfismo
    public String descricao() {
        return "HotelResort";
    }
    //utilização da classe abstrata

    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
