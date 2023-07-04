package comn.objects;

import com.example.projetolpp.Controlador;
import comn.functions.Database;
import comn.interfaces.slogan;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Hotel extends Acomodacao implements slogan {

    /*
    Atributos protegidos: número de quartos disponíveis, lista de comodidades (piscina, academia, restaurante, etc.).
Atributos de classe: lista de hotéis disponíveis (pode ser um ArrayList ou outra estrutura de dados semelhante).
Construtores: com e sem parâmetros, para definir os atributos da classe.
Métodos de acesso específicos: getters e setters para os atributos.
Sobreposição dos métodos toString(), clone() e equals() herdados da classe Object.
Método print() para imprimir os atributos.
Método adicionarComodidade() para adicionar comodidades ao hotel.
     */
    private Integer id;
    private String categoria;
    private int numeroEstrelas;
    private int acessibilidade;

    public Hotel(Integer id) throws SQLException, IOException {
        if (id != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel WHERE id = " + id);

                if (resultSet.next()) {
                    this.id = resultSet.getInt("id");
                    this.categoria = resultSet.getString("categoria");
                    this.numeroEstrelas = resultSet.getInt("numeroEstrelas");
                    this.acessibilidade = resultSet.getInt("acessibilidade");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Hotel(int idAcomodacao, String nome, String endereco, int classificacao, double precoNoite, int acessibilidade, String categoria, int numeroEstrelas) {
        super(idAcomodacao, nome, endereco, classificacao, precoNoite);
        this.categoria = categoria;
        this.acessibilidade = acessibilidade;
        this.numeroEstrelas = numeroEstrelas;
    }


    public Object[] toArray() {
        Object[] array = new Object[4];
        array[0] = this.id;
        array[1] = this.categoria;
        array[2] = this.numeroEstrelas;
        array[3] = this.acessibilidade;
        return array;
    }

    public void store() {
        String[] fields = {"id", "categoria", "numeroEstrelas", "acessibilidade"};

        if (this.id == null) {
            this.id = Database.getNextIncrement("hotel");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO hotel (" + columns + ") VALUES (" + values + ");";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE hotel SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE hotel SET " + values + " WHERE id = " + this.id;

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
            String sql = "DELETE FROM hotel WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Hotel[] search(Integer id, String categoria, Integer numeroEstrelas, Boolean acessibilidade) {
        String sql = "SELECT id FROM hotel WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (categoria != null) {
            sql += " AND (categoria = '" + categoria + "')";
        }
        if (numeroEstrelas != null) {
            sql += " AND (numeroEstrelas = " + numeroEstrelas + ")";
        }
        if (acessibilidade != null) {
            sql += " AND (acessibilidade = " + (acessibilidade ? "1" : "0") + ")";
        }

        Hotel[] ret = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                ret = new Hotel[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int hotelId = resultSet.getInt("id");
                    ret[index] = new Hotel(hotelId);
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

    public static int find(Integer id, String categoria, Integer numeroEstrelas, Boolean acessibilidade) {
        String sql = "SELECT id FROM hotel WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (categoria != null) {
            sql += " AND (categoria = '" + categoria + "')";
        }
        if (numeroEstrelas != null) {
            sql += " AND (numeroEstrelas = " + numeroEstrelas + ")";
        }
        if (acessibilidade != null) {
            sql += " AND (acessibilidade = " + (acessibilidade ? "1" : "0") + ")";
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

    private Object getFieldValue(String fieldName) {
        return switch (fieldName) {
            case "id" -> this.id;
            case "categoria" -> this.categoria;
            case "numeroEstrelas" -> this.numeroEstrelas;
            case "acessibilidade" -> this.acessibilidade;
            default -> null;
        };
    }

    public Hotel(){
        super();

    }
    public Hotel(Acomodacao acomodacao) { super(acomodacao);
    }

    public Hotel(Acomodacao acomodacao,String categoria,int acessibilidade , int numeroEstrelas) {
        super(acomodacao);
        this.categoria = categoria;
        this.acessibilidade = acessibilidade;
        this.numeroEstrelas = numeroEstrelas;
    }
    public Hotel(Acomodacao acomodacao, Hotel outroHotel) {

        super(acomodacao);
        this.categoria = outroHotel.getCategoria();
        this.acessibilidade = outroHotel.getAcessibilidade();
        this.numeroEstrelas = outroHotel.getNumeroEstrelas();
    }

    @Override
    public void print() {
        super.print();
        System.out.print("Estrelas: " +getNumeroEstrelas() + "Categoria de hotel: " + getCategoria() + " Acessibilidade? "+getAcessibilidade()+"\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        if (!super.equals(o)) return false;
        return getNumeroEstrelas() == hotel.getNumeroEstrelas() && getAcessibilidade() == hotel.getAcessibilidade() && Objects.equals(getCategoria(), hotel.getCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoria(), getNumeroEstrelas(), getAcessibilidade());
    }

    public String toString() {
        String acessibilidadeString = (this.acessibilidade == 1) ? "Sim" : "Não";

        return super.toString() +
                "Categoria do hotel: " + this.categoria + "\n" +
                "Acessibilidae?: " + acessibilidadeString + "\n" +
                "Numero de estrelas: " + this.numeroEstrelas + "\n" +
                "Slogan: " + slogan() + "\n" +
                "Descrição: " + descricao() + "\n";
    }
    //conceito de polimorfismo
    public String descricao() {
        return "Hotel";
    }

    //utilização da classe abstrata
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hotel!";
    }

    public int getNumeroEstrelas() {return numeroEstrelas;}

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(int acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public void setNumeroEstrelas(int numeroEstrelas) {this.numeroEstrelas = numeroEstrelas;}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
