package objects;

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

    public void print() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Classificação: " + classificacao);
        System.out.println("Preço: " + precoNoite);
        System.out.println("Descrição: " + descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acomodacao)) return false;
        Acomodacao that = (Acomodacao) o;
        return getClassificacao() == that.getClassificacao() && Double.compare(that.getPrecoNoite(), getPrecoNoite()) == 0 && Objects.equals(getNome(), that.getNome()) && Objects.equals(getEndereco(), that.getEndereco()) && Objects.equals(getDescricao(), that.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getEndereco(), getClassificacao(), getPrecoNoite(), getDescricao());
    }

    @Override
    public String toString() {
        return "Acomodacao{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", classificacao=" + classificacao +
                ", precoNoite=" + precoNoite +
                ", descricao='" + descricao + '\'' +
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
}
