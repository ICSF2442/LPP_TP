package objects;

import java.util.Arrays;
import java.util.Objects;

public class Acomodacao {

    /*
    No contexto de um projeto de reserva de hotéis, pode-se ter uma superclasse que representa uma entidade genérica, como por exemplo, a classe "Acomodação".
 Essa superclasse poderia conter propriedades e métodos comuns a várias entidades, como:
 "nome", "endereço", "classificação", "preço", entre outros.
     */

    private String nome;
    private String endereco;
    private int classificacao;
    private double precoNoite;
    private String descricao;
    private String[] comodidades;


    public Acomodacao(String nome, String endereco, int classificacao, double precoNoite, String descricao) {
        String[] comodidade = new String[0];
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.precoNoite = precoNoite;
        this.descricao = descricao;
        this.comodidades = comodidade;

    }

    public void print() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Classificação: " + classificacao);
        System.out.println("Preço: " + precoNoite);
        System.out.println("Descrição: " + descricao);
        System.out.println("Comodidades: ");
        for(int i = 0; i < getComodidades().length; i++){
            System.out.print(getComodidades()[i]);
            System.out.println(" ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acomodacao)) return false;
        Acomodacao that = (Acomodacao) o;
        return getClassificacao() == that.getClassificacao() && Double.compare(that.getPrecoNoite(), getPrecoNoite()) == 0 && Objects.equals(getNome(), that.getNome()) && Objects.equals(getEndereco(), that.getEndereco()) && Objects.equals(getDescricao(), that.getDescricao()) && Arrays.equals(getComodidades(), that.getComodidades());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getNome(), getEndereco(), getClassificacao(), getPrecoNoite(), getDescricao());
        result = 31 * result + Arrays.hashCode(getComodidades());
        return result;
    }

    @Override
    public String toString() {
        return "Acomodacao{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", classificacao=" + classificacao +
                ", precoNoite=" + precoNoite +
                ", descricao='" + descricao + '\'' +
                ", comodidades=" + Arrays.toString(comodidades) +
                '}';
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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public double getPrecoNoite() {
        return precoNoite;
    }

    public void setPrecoNoite(double precoNoite) {
        this.precoNoite = precoNoite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getComodidades() {
        return comodidades;
    }

    public void setComodidades(String[] comodidades) {
        this.comodidades = comodidades;
    }
}
