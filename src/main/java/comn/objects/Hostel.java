package comn.objects;

import comn.functions.Database;
import comn.interfaces.slogan;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Objects;

public class Hostel extends Acomodacao implements slogan {

/*
Dormitórios: Indica o número de dormitórios disponíveis no hostel.
Quartos privativos: Indica se o hostel oferece quartos privativos para hóspedes que preferem mais privacidade.
Camas: Indica o número total de camas disponíveis no hostel, incluindo camas em dormitórios e quartos privativos.
Banheiros compartilhados: Indica se o hostel possui banheiros compartilhados entre os hóspedes.
Armários: Indica se o hostel oferece armários individuais ou compartilhados para os hóspedes guardarem seus pertences de forma segura.
Áreas comuns: Indica se o hostel possui áreas comuns, como salas de estar, cozinhas compartilhadas, salas de jogos ou terraços.
Cozinha compartilhada: Indica se o hostel disponibiliza uma cozinha para os hóspedes prepararem suas próprias refeições.
Wi-Fi: Indica se o hostel oferece acesso à internet sem fio para os hóspedes.
Café da manhã: Indica se o hostel oferece café da manhã incluso na diária.
 */
    private Integer id;
    private int nDormitoriosTotal;
    private int casaDeBanhoCompartilhada;
    private int internet;
    private int quartosCompartilhados;



    public Hostel() {
        super();
    }

    public Hostel(Acomodacao acomodacao) {
        super(acomodacao);
    }

    public Hostel(int id,int nDormitoriosTotal, int nDormitoriosDisponiveis, int casaDeBanhoCompartilhada, int internet, int quartosCompartilhados, String[] areasCompartilhadas) {
        this.nDormitoriosTotal = nDormitoriosTotal;
        this.id = id;

        this.casaDeBanhoCompartilhada = casaDeBanhoCompartilhada;
        this.internet = internet;
        this.quartosCompartilhados = quartosCompartilhados;

    }

    public Hostel(Acomodacao acomodacao, Hostel outroHostel) {
        super(acomodacao);
        this.nDormitoriosTotal = outroHostel.getnDormitoriosTotal();
        this.casaDeBanhoCompartilhada = outroHostel.getCasaDeBanhoCompartilhada();
        this.internet = outroHostel.getInternet();
        this.quartosCompartilhados = outroHostel.quartosCompartilhados;
    }
    public Hostel(Integer id) throws SQLException, IOException {
        if (id != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hostel WHERE id = " + id);

                if (resultSet.next()) {
                    this.id = resultSet.getInt("id");
                    this.casaDeBanhoCompartilhada = resultSet.getInt("casaDeBanhoCompartilhada");
                    this.internet = resultSet.getInt("internet");
                    this.quartosCompartilhados = resultSet.getInt("quartosCompartilhados");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[4];
        array[0] = this.id;
        array[1] = this.casaDeBanhoCompartilhada;
        array[2] = this.internet;
        array[3] = this.quartosCompartilhados;
        return array;
    }

    public void store() {
        String[] fields = {"id", "casaDeBanhoCompartilhada", "internet", "quartosCompartilhados"};

        if (this.id == null) {
            this.id = Database.getNextIncrement("hostel");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO hostel (" + columns + ") VALUES (" + values + ");";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE hostel SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE hostel SET " + values + " WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Hostel[] search(Integer id, Integer casaDeBanhoCompartilhada, Integer internet, Integer quartosCompartilhados) {
        String sql = "SELECT id FROM hostel WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (casaDeBanhoCompartilhada != null) {
            sql += " AND (casaDeBanhoCompartilhada = " + casaDeBanhoCompartilhada + ")";
        }
        if (internet != null) {
            sql += " AND (internet = " + internet + ")";
        }
        if (quartosCompartilhados != null) {
            sql += " AND (quartosCompartilhados = " + quartosCompartilhados + ")";
        }

        Hostel[] ret = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                ret = new Hostel[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int hostelId = resultSet.getInt("id");
                    ret[index] = new Hostel(hostelId);
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
            String sql = "DELETE FROM hostel WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int find(Integer id, Integer casaDeBanhoCompartilhada, Integer internet, Integer quartosCompartilhados) {
        String sql = "SELECT id FROM hostel WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (casaDeBanhoCompartilhada != null) {
            sql += " AND (casaDeBanhoCompartilhada = " + casaDeBanhoCompartilhada + ")";
        }
        if (internet != null) {
            sql += " AND (internet = " + internet + ")";
        }
        if (quartosCompartilhados != null) {
            sql += " AND (quartosCompartilhados = " + quartosCompartilhados + ")";
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
            case "casaDeBanhoCompartilhada" -> this.casaDeBanhoCompartilhada;
            case "internet" -> this.internet;
            case "quartosCompartilhados" -> this.quartosCompartilhados;
            default -> null;
        };
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hostel hostel = (Hostel) o;
        return nDormitoriosTotal == hostel.nDormitoriosTotal && casaDeBanhoCompartilhada == hostel.casaDeBanhoCompartilhada && internet == hostel.internet && quartosCompartilhados == hostel.quartosCompartilhados && Objects.equals(id, hostel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, nDormitoriosTotal, casaDeBanhoCompartilhada, internet, quartosCompartilhados);
    }

    //conceito de polimorfismo
    public String descricao() {
        return "Hotel";
    }

    //utilização da ‘interface’
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hostel!";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Numero de dormitorios: " + nDormitoriosTotal + "\n"+
                "Casa de banho compartilhadas: " + casaDeBanhoCompartilhada + "\n"+
                "Internet: " + internet + "\n"+
                "Quartos compartilhados: " + quartosCompartilhados + "\n"+
                "Slogan: "+ slogan() + "\n"+
                "Descrição: "+ descricao() + "\n";

    }

    public int getnDormitoriosTotal() {
        return nDormitoriosTotal;
    }

    public void setnDormitoriosTotal(int nDormitoriosTotal) {
        this.nDormitoriosTotal = nDormitoriosTotal;
    }

    public int getCasaDeBanhoCompartilhada() {
        return casaDeBanhoCompartilhada;
    }

    public void setCasaDeBanhoCompartilhada(int casaDeBanhoCompartilhada) {
        this.casaDeBanhoCompartilhada = casaDeBanhoCompartilhada;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getQuartosCompartilhados() {
        return quartosCompartilhados;
    }

    public void setQuartosCompartilhados(int quartosCompartilhados) {
        this.quartosCompartilhados = quartosCompartilhados;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
