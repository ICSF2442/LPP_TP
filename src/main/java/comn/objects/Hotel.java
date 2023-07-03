package comn.objects;

import com.example.projetolpp.Controlador;
import comn.interfaces.slogan;

import java.util.Objects;

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
    private Integer id;
    private String categoria;
    private int numeroEstrelas;

    private int acessibilidade;


    public Hotel(int idAcomodacao, String nome, String endereco, int classificacao, double precoNoite, int acessibilidade, String categoria, int numeroEstrelas) {
        super(idAcomodacao, nome, endereco, classificacao, precoNoite);
        this.categoria = categoria;
        this.acessibilidade = acessibilidade;
        this.numeroEstrelas = numeroEstrelas;
    }

    public Hotel(){
        super();

    }
    public Hotel(Acomodacao acomodacao) { super(acomodacao);
    }

    public Hotel(Acomodacao acomodacao,String categoria,int acessibilidade , int numeroEstrelas) {
        super(acomodacao);
        this.categoria = categoria;
        this.acessibilidade = acessibilidade;
        this.numeroEstrelas = numeroEstrelas;
    }
    public Hotel(Acomodacao acomodacao, Hotel outroHotel) {

        super(acomodacao);
        this.categoria = outroHotel.getCategoria();
        this.acessibilidade = outroHotel.getAcessibilidade();
        this.numeroEstrelas = outroHotel.getNumeroEstrelas();
    }

    @Override
    public void print() {
        super.print();
        System.out.print("Estrelas: " +getNumeroEstrelas() + "Categoria de hotel: " + getCategoria() + " Acessibilidade? "+getAcessibilidade()+"\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        if (!super.equals(o)) return false;
        return getNumeroEstrelas() == hotel.getNumeroEstrelas() && getAcessibilidade() == hotel.getAcessibilidade() && Objects.equals(getCategoria(), hotel.getCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoria(), getNumeroEstrelas(), getAcessibilidade());
    }

    public String toString() {
        String acessibilidadeString = (this.acessibilidade == 1) ? "Sim" : "Não";

        return super.toString() +
                "Categoria do hotel: " + this.categoria + "\n" +
                "Acessibilidae?: " + acessibilidadeString + "\n" +
                "Numero de estrelas: " + this.numeroEstrelas + "\n" +
                "Slogan: " + slogan() + "\n" +
                "Descrição: " + descricao() + "\n";
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

    public int getNumeroEstrelas() {return numeroEstrelas;}

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(int acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public void setNumeroEstrelas(int numeroEstrelas) {this.numeroEstrelas = numeroEstrelas;}


}
