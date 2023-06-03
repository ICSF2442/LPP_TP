package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Hotel {

    /*
    Atributos protegidos: nome, endereço, número de quartos disponíveis, lista de comodidades (piscina, academia, restaurante, etc.).
Atributos de classe: lista de hotéis disponíveis (pode ser um ArrayList ou outra estrutura de dados semelhante).
Construtores: com e sem parâmetros, para definir os atributos da classe.
Métodos de acesso específicos: getters e setters para os atributos.
Sobreposição dos métodos toString(), clone() e equals() herdados da classe Object.
Método print() para imprimir os atributos.
Método adicionarComodidade() para adicionar comodidades ao hotel.
     */

    private String nome;
    private String endereco;

    private int numeroQuartos;
    private int quartosDisponiveis;
    private String[] comodidades;

    private static int numeroTotalHoteis;

    public Hotel() {
        numeroTotalHoteis++;
    }

    public Hotel(String nome, String endereco, int numeroQuartos, String[] comodidades) {
        this.nome = nome;
        this.endereco = endereco;
        this.numeroQuartos = numeroQuartos;
        this.comodidades = comodidades;
        this.quartosDisponiveis = this.numeroQuartos;
        numeroTotalHoteis++;
    }

    public Hotel(Hotel outroHotel) {
        this.nome = outroHotel.nome;
        this.endereco = outroHotel.endereco;
        this.numeroQuartos = outroHotel.numeroQuartos;
        this.quartosDisponiveis = outroHotel.quartosDisponiveis;
        this.comodidades = outroHotel.comodidades;
        numeroTotalHoteis++;
    }

    public void print() {
        System.out.println("Hotel: " + getNome());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("Número de quartos disponiveis: " + getQuartosDisponiveis() + " de "+getNumeroQuartos()+" totais.");
        System.out.println("Comodidades: ");
        for (int i = 0; i < getComodidades().length; i++){
            System.out.println(getComodidades()[i]);
            System.out.println(" ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return getNumeroQuartos() == hotel.getNumeroQuartos() && getNome().equals(hotel.getNome()) && getEndereco().equals(hotel.getEndereco()) && Arrays.equals(getComodidades(), hotel.getComodidades());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getNome(), getEndereco(), getNumeroQuartos());
        result = 31 * result + Arrays.hashCode(getComodidades());
        return result;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuartosDisponiveis() {
        return quartosDisponiveis;
    }

    public void setQuartosDisponiveis(int quartosDisponiveis) {
        this.quartosDisponiveis = quartosDisponiveis;
    }

    public String[] getComodidades() {
        return comodidades;
    }

    public void setComodidades(String[] comodidades) {
        this.comodidades = comodidades;
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
}
