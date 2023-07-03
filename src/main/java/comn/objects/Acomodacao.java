package comn.objects;



import comn.functions.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Acomodacao  {

    private Integer id;
    private String nome;
    private String endereco;
    private int classificacao;
    private double precoNoite;


    public Acomodacao(int id, String nome, String endereco, int classificacao, double precoNoite) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.precoNoite = precoNoite;
    }
    public Acomodacao(Integer id) throws SQLException, IOException {
        if (id != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id = " + id);

                if (resultSet.next()) {
                    this.id = resultSet.getInt("id");
                    this.nome = resultSet.getString("nome");
                    this.endereco = resultSet.getString("endereco");
                    this.classificacao = resultSet.getInt("classificacao");
                    this.precoNoite = resultSet.getDouble("precoNoite");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
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

    public Acomodacao(Acomodacao acomodacao) {
        this.id = acomodacao.getId();
        this.nome = acomodacao.getNome();
        this.endereco = acomodacao.getEndereco();
        this.classificacao = acomodacao.getClassificacao();
        this.precoNoite = acomodacao.getPrecoNoite();
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
            String sql = "DELETE FROM user WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int find(Integer id, String username, String email, String password) {
        String sql = "SELECT id FROM user WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (username != null) {
            sql += " AND (username = '" + username + "')";
        }
        if (email != null) {
            sql += " AND (email = '" + email + "')";
        }
        if (password != null) {
            sql += " AND (password = '" + password + "')";
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
            String sql = "DELETE FROM user WHERE id = " + id;

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

    @Override
    public String toString() {
        return
                "Nome: " + nome + '\'' + "\n"+
                "Endereco: " + endereco + '\'' + "\n"+
                "Classificação: " + classificacao + "\n"+
                "PrecoNoite: " + precoNoite + "\n"+
                "Descrição: " + descricao() + '\'' + "\n"+
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
}
