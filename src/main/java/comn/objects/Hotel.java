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
    private int numeroQuartos;
    private int quartosDisponiveis;
    private static int numeroTotalHoteis;
    private int numeroEstrelas;


    public Hotel(String nome, String endereco, int classificacao, double precoNoite, String descricao, int numeroQuartos, int quartosDisponiveis, int numeroEstrelas) {
        super(nome, endereco, classificacao, precoNoite, descricao);
        this.numeroQuartos = numeroQuartos;
        this.quartosDisponiveis = quartosDisponiveis;
        this.numeroEstrelas = numeroEstrelas;
    }

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

    @Override
    public void print() {
        super.print();
        System.out.print("Estrelas: " +getNumeroEstrelas() + "Número de quartos disponiveis: " + getQuartosDisponiveis() + " de "+getNumeroQuartos()+" totais.\n");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        if (!super.equals(o)) return false;
        return getNumeroQuartos() == hotel.getNumeroQuartos() && getQuartosDisponiveis() == hotel.getQuartosDisponiveis() && getNumeroEstrelas() == hotel.getNumeroEstrelas();
    }

    public String toString() {
        return super.toString() +
                "Numero de quartos: " + this.numeroQuartos + "\n"+
                "Quartos disponiveis: " + this.quartosDisponiveis + "\n"+
                "Numero de estrelas: " + this.numeroEstrelas + "\n"+
                "Slogan: " + slogan() + "\n" +
                "Descrição: "+ descricao() +"\n"
                ;
    }
    //conceito de polimorfismo
    public String descricao() {
        return "Hotel";
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

    public int getNumeroEstrelas() {return numeroEstrelas;}

    public void setNumeroEstrelas(int numeroEstrelas) {this.numeroEstrelas = numeroEstrelas;}
}
