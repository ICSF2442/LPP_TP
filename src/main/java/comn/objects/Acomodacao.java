package comn.objects;



import comn.functions.Database;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Acomodacao  {

    private Integer id;
    private String nome;
    private String endereco;
    private int classificacao;
    private double precoNoite;
    private int reservado;


    public Acomodacao(int id, String nome, String endereco, int classificacao, double precoNoite, int reserva) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.precoNoite = precoNoite;
        this.reservado = reserva;
    }
    public Acomodacao(Integer id) throws SQLException, IOException {
        if (id != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM acomodacao WHERE id = " + id);

                if (resultSet.next()) {
                    this.id = resultSet.getInt("id");
                    this.nome = resultSet.getString("nome");
                    this.endereco = resultSet.getString("endereco");
                    this.classificacao = resultSet.getInt("classificacao");
                    this.precoNoite = resultSet.getDouble("precoNoite");
                    this.reservado = resultSet.getInt("reservado");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Acomodacao(Acomodacao acomodacao) {
        this.id = acomodacao.getId();
        this.nome = acomodacao.getNome();
        this.endereco = acomodacao.getEndereco();
        this.classificacao = acomodacao.getClassificacao();
        this.precoNoite = acomodacao.getPrecoNoite();
        this.reservado = acomodacao.getReserva();
    }
    public Object[] toArray() {
        Object[] array = new Object[5];
        array[0] = this.id;
        array[1] = this.nome;
        array[2] = this.endereco;
        array[3] = this.classificacao;
        array[4] = this.precoNoite;
        return array;
    }
    public void updateAcomodacaoSubclasse(String subclassName, int acomodacaoId, int newSubclasse) {
        String sql = "UPDATE "+ subclassName +" SET acomodacao_FK = ? WHERE id = ?";

        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, acomodacaoId);
            statement.setInt(2, newSubclasse);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }




    public void store() {
        String[] fields = {"id", "nome", "endereco", "classificacao", "precoNoite","reservado"};

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
            String sql = "INSERT INTO acomodacao (" + columns + ") VALUES (" + values + ");";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {

            StringBuilder values = new StringBuilder();
            String sql = "UPDATE acomodacao SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE acomodacao SET " + values + " WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Acomodacao[] search(Integer id, String nome, String endereco, Integer classificacao, Double precoNoite) {
        String sql = "SELECT id FROM acomodacao WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (nome != null) {
            sql += " AND (nome = '" + nome + "')";
        }
        if (endereco != null) {
            sql += " AND (endereco = '" + endereco + "')";
        }
        if (classificacao != null) {
            sql += " AND (classificacao = " + classificacao + ")";
        }
        if (precoNoite != null) {
            sql += " AND (precoNoite = " + precoNoite + ")";
        }

        Acomodacao[] ret = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                ret = new Acomodacao[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int acomodacaoId = resultSet.getInt("id");
                    ret[index] = new Acomodacao(acomodacaoId);
                    index++;
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
    public void remove() {
        if (this.id != null) {
            String sql = "DELETE FROM acomodacao WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int find(Integer id, String nome, String endereco, Integer classificacao, Double precoNoite) {
        String sql = "SELECT id FROM acomodacao WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (nome != null) {
            sql += " AND (username = '" + nome + "')";
        }
        if (endereco != null) {
            sql += " AND (email = '" + endereco + "')";
        }
        if (classificacao != null) {
            sql += " AND (password = '" + classificacao + "')";
        }
        if (precoNoite != null) {
            sql += " AND (password = '" + precoNoite + "')";
        }

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = resultSet.next() ? 1 : 0;
            resultSet.close();
            statement.close();
            return count;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void remover(Integer id) {
        if (id != null) {
            String sql2 = "DELETE FROM comodidade_acomodacao WHERE acomodacao_fk ="+ id;
            String sql = "DELETE FROM acomodacao WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql2);
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
            case "reservado"->this.reservado;
            default -> null;
        };
    }


    public Acomodacao() {}

    /**
     * Adicionar comodidade á acomodaçao.
     */

    //conceito de polimorfismo
    public String descricao() {
        return "Acomodação genérica";
    }

    public void print() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Endereco: " + this.endereco);
        System.out.println("Classificacao: " + this.classificacao);
        System.out.println("Preco: " + this.precoNoite);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acomodacao that = (Acomodacao) o;
        return classificacao == that.classificacao && Double.compare(that.precoNoite, precoNoite) == 0 && Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, endereco, classificacao, precoNoite);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getComodidades(){
        StringBuilder comodidades = new StringBuilder();
        Comodidade[] allComodidades = Comodidade.searchAllComodidades(this.id);
        if(allComodidades != null) {
            for (Comodidade comodidadeA : allComodidades) {
                comodidades.append(comodidadeA.getNome());
                comodidades.append(", ");
            }
        }
        return String.valueOf(comodidades);
    }



    @Override
    public String toString() {
        String reserva = (this.getReserva() == 1) ? "Sim" : "Não";
        return
                "Nome: " + nome +   "\n"+
                "Endereco: " + endereco +  "\n"+
                "Classificação: " + classificacao + "\n"+
                "PrecoNoite: " + precoNoite + "\n"+
                        "Descrição: " + descricao() +  "\n"+
                        "Reservado? "+  reserva  +   "\n"+
                        "Comodidades: "+getComodidades()+   "\n"+
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReserva() {
        return reservado;
    }

    public void setReserva(int reserva) {
        this.reservado = reserva;
    }
}
