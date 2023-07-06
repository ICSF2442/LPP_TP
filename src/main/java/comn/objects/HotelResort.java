package comn.objects;

import comn.functions.Database;
import comn.interfaces.slogan;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class HotelResort extends Hotel implements slogan {
    private Integer id;

    private int hotel_fk;

    public HotelResort() {
    }

    public HotelResort(Acomodacao acomodacao, Hotel outroHotel) {
        super(acomodacao, outroHotel);
    }

    public HotelResort(Acomodacao acomodacao, Integer id) {
        super(acomodacao);
        this.id = id;
    }

    public HotelResort(Integer id) throws SQLException, IOException {
        super(getHotelId(id));

        if (id != null && Database.getConnection() != null) {
            String sql = "SELECT * FROM hotelresort WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // Retrieve data from the result set and assign it to the class properties
                    this.id = resultSet.getInt("id");
                    this.hotel_fk = resultSet.getInt("hotel_fk");

                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Integer getHotelId(Integer hotelresortId) throws SQLException, IOException {
        Integer hotelId = null;

        if (hotelresortId != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT hotel_fk FROM hotelresort WHERE id = " + hotelresortId);

                if (resultSet.next()) {
                    hotelId = resultSet.getInt("hotel_fk");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return hotelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void store() {
        String[] fields = { "id" };

        if (this.id == null) {
            this.id = Database.getNextIncrement("hotelresort");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO hotelresort (" + columns + ") VALUES (" + values + ")";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE hotelresort SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE hotelresort SET " + values + " WHERE id = " + this.id;

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
            String sql = "DELETE FROM hotelresort WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static HotelResort[] search(Integer id) {
        String sql = "SELECT id FROM hotelresort WHERE 1=1";

        if (id != null) {
            sql += " AND id = " + id;
        }

        HotelResort[] result = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                result = new HotelResort[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int hotelResortId = resultSet.getInt("id");
                    result[index] = new HotelResort(hotelResortId);
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
            String sql = "DELETE FROM hotelresort WHERE id = " + id;

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
            default -> null;
        };
    }

    @Override
    public String toString() {
        return super.toString() +
                "Slogan: " + slogan() + "\n" +
                "Descrição: "+ descricao() + "\n"
                ;
    }

    //conceito de polimorfismo
    public String descricao() {
        return "HotelResort";
    }
    //utilização da classe abstrata

    public int getHotel_fk() {
        return hotel_fk;
    }

    public void setHotel_fk(int hotel_fk) {
        this.hotel_fk = hotel_fk;
    }

    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
