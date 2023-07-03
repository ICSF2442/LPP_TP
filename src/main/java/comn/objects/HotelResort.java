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

    public HotelResort(Integer id) throws SQLException, IOException {
        this.id = id;

        if (id != null && Database.getConnection() != null) {
            String sql = "SELECT * FROM hotelResort WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // Retrieve data from the result set and assign it to the class properties
                    this.id = resultSet.getInt("id");
                    // Assign other properties as needed
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void store() {
        if (id == null) {
            id = Database.getNextIncrement("hotelResort");
        }

        String sql = "INSERT INTO hotelResort (id) VALUES (" + id + ") ON DUPLICATE KEY UPDATE id = " + id;

        try {
            Statement statement = Database.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        if (id != null) {
            String sql = "DELETE FROM hotelResort WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int find(Integer id) {
        String sql = "SELECT id FROM hotelResort WHERE id = " + id;

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
            String sql = "DELETE FROM hotelResort WHERE id = " + id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public HotelResort() {
        super();
    }

    public HotelResort(Acomodacao acomodacao){
        super(acomodacao);
    }

    public HotelResort(Acomodacao acomodacao, int id) {
        super(acomodacao);
        this.id = id;
    }

    public HotelResort(Acomodacao acomodacao, String categoria, int acessibilidade, int numeroEstrelas, int id) {
        super(acomodacao, categoria, acessibilidade, numeroEstrelas);
        this.id = id;
    }

    public HotelResort(Acomodacao acomodacao, Hotel outroHotel) {
        super(acomodacao, outroHotel);
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

    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
