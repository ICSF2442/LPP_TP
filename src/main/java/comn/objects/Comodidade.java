package comn.objects;

import comn.functions.Database;

import java.io.IOException;
import java.sql.*;

public class Comodidade {

    private Integer id;

    private String nome;

    public Comodidade() {
    }

    public Comodidade(Integer id) throws SQLException, IOException {
        this.id = id;

        if (id != null && Database.getConnection() != null) {
            String sql = "SELECT * FROM comodidade WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // Retrieve data from the result set and assign it to the class properties
                    this.id = resultSet.getInt("id");
                    this.nome = resultSet.getString("nome");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void store() {
        String[] fields = { "id", "nome" };

        if (this.id == null) {
            this.id = Database.getNextIncrement("comodidade");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO comodidade (" + columns + ") VALUES (" + values + ")";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE comodidade SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE comodidade SET " + values + " WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        if (this.id != null) {
            String sql = "DELETE FROM comodidade WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Comodidade[] search(Integer id, String nome) {
        String sql = "SELECT id FROM comodidade WHERE 1=1";

        if (id != null) {
            sql += " AND id = " + id;
        }
        if (nome != null) {
            sql += " AND nome = '" + nome + "'";
        }

        Comodidade[] result = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                result = new Comodidade[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int comodidadeId = resultSet.getInt("id");
                    result[index] = new Comodidade(comodidadeId);
                    index++;
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Object getFieldValue(String fieldName) {
        return switch (fieldName) {
            case "id" -> this.id;
            case "nome" -> this.nome;
            default -> null;
        };
    }

    public void addComodidadeAcomodacao(int comodidadeId, int acomodacaoId) {
        String sql = "INSERT INTO comodidade_acomodacao (comodidade_FK, acomodacao_FK) VALUES (?, ?)";

        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, comodidadeId);
            statement.setInt(2, acomodacaoId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
