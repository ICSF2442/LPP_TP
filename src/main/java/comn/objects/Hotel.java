package comn.objects;

import com.example.projetolpp.Controlador;
import comn.interfaces.slogan;

public class Hotel extends Acomodacao implements slogan {

    /*
    Atributos protegidos: número de quartos disponíveis, lista de comodidades (piscina, academia, restaurante, etc.).
Atributos de classe: lista de hotéis disponíveis (pode ser um ArrayList ou outra estrutura de dados semelhante).
Construtores: com e sem parâmetros, para definir os atributos da classe.
Métodos de acesso específicos: getters e setters para os atributos.
Sobreposição dos métodos toString(), clone() e equals() herdados da classe Object.
Método print() para imprimir os atributos.
Método adicionarComodidade() para adicionar comodidades ao hotel.
     */
    private Hotel[] listadeHotel;
    private int numeroQuartos;
    private int quartosDisponiveis;
    private static int numeroTotalHoteis;
    private int numeroEstrelas;


    public Hotel(){
        super();
        numeroTotalHoteis++;
    }
    public Hotel(Acomodacao acomodacao) { super(acomodacao);
        numeroTotalHoteis++;
    }

    public Hotel(Acomodacao acomodacao, int numeroQuartos, int quartosDisponiveis, int numeroEstrelas) {
        super(acomodacao);
        this.numeroQuartos = numeroQuartos;
        this.quartosDisponiveis = quartosDisponiveis;
        this.numeroEstrelas = numeroEstrelas;
        numeroTotalHoteis++;
    }

    public Hotel(Acomodacao acomodacao, Hotel outroHotel) {
        super(acomodacao);
        this.numeroQuartos = outroHotel.getNumeroQuartos();
        this.quartosDisponiveis = outroHotel.getQuartosDisponiveis();
        this.numeroEstrelas = outroHotel.getNumeroEstrelas();
        numeroTotalHoteis++;
    }


    public void adicionarHotellista(){
        Hotel[] lista = new Hotel[this.listadeHotel.length+1];


    }
    public void listar(){
        for (Hotel hotel : listadeHotel) {
            hotel.print();
        }
    }
    @Override
    public void print() {
        super.print();
        Controlador.descriptionHotels.append("Estrelas: " +getNumeroEstrelas() + "Número de quartos disponiveis: " + getQuartosDisponiveis() + " de "+getNumeroQuartos()+" totais.\n");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        if (!super.equals(o)) return false;
        return getNumeroQuartos() == hotel.getNumeroQuartos() && getQuartosDisponiveis() == hotel.getQuartosDisponiveis() && getNumeroEstrelas() == hotel.getNumeroEstrelas();
    }

    public String toString() {
        return "Hotel{" +
                "numeroQuartos=" + this.numeroQuartos +
                ", quartosDisponiveis=" + this.quartosDisponiveis +
                ", numeroEstrelas=" + this.numeroEstrelas +
                '}';
    }
    //conceito de polimorfismo
    public void descricao() {
        System.out.println("Hotel");
    }

    //utilização da classe abstrata
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hotel!";
    }

    public int getQuartosDisponiveis() {
        return quartosDisponiveis;
    }

    public void setQuartosDisponiveis(int quartosDisponiveis) {
        this.quartosDisponiveis = quartosDisponiveis;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public static int getNumeroTotalHoteis() {
        return numeroTotalHoteis;
    }

    public static void setNumeroTotalHoteis(int numeroTotalHoteis) {
        Hotel.numeroTotalHoteis = numeroTotalHoteis;
    }

    public Hotel[] getListadeHotel() {
        return listadeHotel;
    }

    public void setListadeHotel(Hotel[] listadeHotel) {
        this.listadeHotel = listadeHotel;
    }

    public int getNumeroEstrelas() {return numeroEstrelas;}

    public void setNumeroEstrelas(int numeroEstrelas) {this.numeroEstrelas = numeroEstrelas;}
}
