package comn.objects;

import comn.functions.Database;

import java.io.IOException;
import java.sql.*;

public class Atividade {

    private Integer id;

    private String nome;

    public Atividade(Integer id) throws SQLException, IOException {

        if (id != null && Database.getConnection() != null) {
            String sql = "SELECT * FROM atividade WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
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
            this.id = Database.getNextIncrement("atividade");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO atividade (" + columns + ") VALUES (" + values + ")";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE atividade SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE atividade SET " + values + " WHERE id = " + this.id;

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
            String sql = "DELETE FROM atividade WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Atividade[] search(Integer id, String nome) {
        String sql = "SELECT id FROM atividade WHERE 1=1";

        if (id != null) {
            sql += " AND id = " + id;
        }
        if (nome != null) {
            sql += " AND nome = '" + nome + "'";
        }

        Atividade[] result = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                result = new Atividade[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int atividadeId = resultSet.getInt("id");
                    result[index] = new Atividade(atividadeId);
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

    public static void remover(Integer id) {
        if (id != null) {
            String sql = "DELETE FROM atividade WHERE id = " + id;

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
            default -> null;
        };
    }

    public static void addHotelResortAtividade(int hotelResortId, int atividadeId) {
        String sql = "INSERT INTO hotelResort_atividade (hotelResort_FK, atividade_FK) VALUES (?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, hotelResortId);
            statement.setInt(2, atividadeId);

            statement.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
