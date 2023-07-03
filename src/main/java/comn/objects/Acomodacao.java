package comn.objects;



import comn.functions.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Objects;

public class Acomodacao  {

    private Integer id;
    private String nome;
    private String endereco;
    private int classificacao;
    private double precoNoite;
    private String[] comodidades;


    public Acomodacao(int id, String nome, String endereco, int classificacao, double precoNoite) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.precoNoite = precoNoite;
        this.comodidades = new String[0];}

    public Acomodacao(Acomodacao acomodacao) {
        this.id = acomodacao.getId();
        this.nome = acomodacao.getNome();
        this.endereco = acomodacao.getEndereco();
        this.classificacao = acomodacao.getClassificacao();
        this.precoNoite = acomodacao.getPrecoNoite();
        this.comodidades = acomodacao.getComodidades();
    }
    public void store() {
        String[] fields = {"id", "nome", "endereco", "classificacao", "precoNoite"};

        if (this.id == null) {
            this.id = Database.getNextIncrement("acomodacao");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO USER (" + columns + ") VALUES (" + values + ");";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE USER SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE user SET " + values + " WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object getFieldValue(String fieldName) {
        return switch (fieldName) {
            case "id" -> this.id;
            case "nome" -> this.nome;
            case "endereco" -> this.endereco;
            case "classificacao" -> this.classificacao;
            case "precoNoite" -> this.precoNoite;
            default -> null;
        };
    }


    public Acomodacao() {
        this.comodidades = new String[0];
    }

    /**
     * Adicionar comodidade á acomodaçao.
     */
    public void adicionarComodidade(String comodidade){
        if(getComodidades().length != 0){
            String[] comodidades = new String[getComodidades().length+1];
            for(int i = 0; i < getComodidades().length; i++){
                comodidades[i] = getComodidades()[i];
            }
            comodidades[getComodidades().length] = comodidade;
            setComodidades(comodidades);
        }else {
            String[] comodidades = new String[1];
            comodidades[0] = comodidade;
            setComodidades(comodidades);
        }
    }
    //conceito de polimorfismo
    public String descricao() {
        return "Acomodação genérica";
    }

    public void print() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Endereco: " + this.endereco);
        System.out.println("Classificacao: " + this.classificacao);
        System.out.println("Preco: " + this.precoNoite);
        System.out.println("Comodidades: ");
        for(int i = 0; i < getComodidades().length; i++){
            System.out.print(getComodidades()[i]);
            System.out.println(" ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acomodacao that = (Acomodacao) o;
        return classificacao == that.classificacao && Double.compare(that.precoNoite, precoNoite) == 0 && Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(endereco, that.endereco) && Arrays.equals(comodidades, that.comodidades);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nome, endereco, classificacao, precoNoite);
        result = 31 * result + Arrays.hashCode(comodidades);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return
                "Nome: " + nome + '\'' + "\n"+
                "Endereco: " + endereco + '\'' + "\n"+
                "Classificação: " + classificacao + "\n"+
                "PrecoNoite: " + precoNoite + "\n"+
                "Descrição: " + descricao() + '\'' + "\n"+
                "Coomodidades: " + Arrays.toString(comodidades) + "\n"+
                "\n";}




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


    public String[] getComodidades() {
        return comodidades;
    }

    public void setComodidades(String[] comodidades) {
        this.comodidades = comodidades;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
