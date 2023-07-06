package comn.objects;

import comn.functions.Database;
import comn.interfaces.slogan;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Apartamento extends Acomodacao implements slogan {

    private Integer id;

    private int nQuartos;

    private double areaTotal;

    private int nCasaDeBanhos;

    private int internet;

    private int cozinha;

    private int acomodacao_fk;

    public Apartamento(Integer id) throws SQLException, IOException {
        super(getAcomodacaoId(id));

        if (id != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM apartamento WHERE id = " + id);

                if (resultSet.next()) {

                    this.id = resultSet.getInt("id");
                    this.nQuartos = resultSet.getInt("nQuartos");
                    this.areaTotal = resultSet.getDouble("areaTotal");
                    this.nCasaDeBanhos = resultSet.getInt("nCasaDeBanhos");
                    this.internet = resultSet.getInt("internet");
                    this.cozinha = resultSet.getInt("cozinha");
                    this.acomodacao_fk = resultSet.getInt("acomodacao_fk");

                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Integer getAcomodacaoId(Integer apartamentoId) throws SQLException, IOException {
        Integer acomodacaoId = null;

        if (apartamentoId != null && Database.getConnection() != null) {
            try {
                Statement statement = Database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT acomodacao_fk FROM apartamento WHERE id = " + apartamentoId);

                if (resultSet.next()) {
                    acomodacaoId = resultSet.getInt("acomodacao_fk");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return acomodacaoId;
    }

    public Object[] toArray() {
        Object[] array = new Object[6];
        array[0] = this.id;
        array[1] = this.nQuartos;
        array[2] = this.areaTotal;
        array[3] = this.nCasaDeBanhos;
        array[4] = this.internet;
        array[5] = this.cozinha;
        return array;
    }

    public void store() {
        String[] fields = {"id", "nQuartos", "areaTotal", "nCasaDeBanhos", "internet", "cozinha"};

        if (this.id == null) {
            this.id = Database.getNextIncrement("apartamento");

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String field : fields) {
                columns.append(", ").append(field);
                values.append(", ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            columns = new StringBuilder(columns.substring(2));
            values = new StringBuilder(values.substring(2));
            String sql = "INSERT INTO apartamento (" + columns + ") VALUES (" + values + ");";

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder values = new StringBuilder();
            String sql = "UPDATE apartamento SET ";
            for (String field : fields) {
                values.append(",").append(field).append(" = ").append(this.getFieldValue(field) != null ? "'" + this.getFieldValue(field) + "'" : "NULL");
            }

            values = new StringBuilder(values.substring(1));
            sql = "UPDATE apartamento SET " + values + " WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Apartamento[] search(Integer id, Integer nQuartos, Double areaTotal, Integer nCasaDeBanhos, Integer internet, Integer cozinha) {
        String sql = "SELECT id FROM apartamento WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (nQuartos != null) {
            sql += " AND (nQuartos = " + nQuartos + ")";
        }
        if (areaTotal != null) {
            sql += " AND (areaTotal = " + areaTotal + ")";
        }
        if (nCasaDeBanhos != null) {
            sql += " AND (nCasaDeBanhos = " + nCasaDeBanhos + ")";
        }
        if (internet != null) {
            sql += " AND (internet = " + internet + ")";
        }
        if (cozinha != null) {
            sql += " AND (cozinha = " + cozinha + ")";
        }

        Apartamento[] ret = null;

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            if (rowCount > 0) {
                ret = new Apartamento[rowCount];
                int index = 0;

                while (resultSet.next()) {
                    int apartamentoId = resultSet.getInt("id");
                    ret[index] = new Apartamento(apartamentoId);
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
            String sql = "DELETE FROM apartamento WHERE id = " + this.id;

            try {
                Statement statement = Database.getConnection().createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int find(Integer id, Integer nQuartos, Double areaTotal, Integer nCasaDeBanhos, Integer internet, Integer cozinha) {
        String sql = "SELECT id FROM apartamento WHERE 1=1";

        if (id != null) {
            sql += " AND (id = " + id + ")";
        }
        if (nQuartos != null) {
            sql += " AND (nQuartos = " + nQuartos + ")";
        }
        if (areaTotal != null) {
            sql += " AND (areaTotal = " + areaTotal + ")";
        }
        if (nCasaDeBanhos != null) {
            sql += " AND (nCasaDeBanhos = " + nCasaDeBanhos + ")";
        }
        if (internet != null) {
            sql += " AND (internet = " + internet + ")";
        }
        if (cozinha != null) {
            sql += " AND (cozinha = " + cozinha + ")";
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
            case "nQuartos" -> this.nQuartos;
            case "areaTotal" -> this.areaTotal;
            case "nCasaDeBanhos" -> this.nCasaDeBanhos;
            case "internet" -> this.internet;
            case "cozinha" -> this.cozinha;
            default -> null;
        };
    }

    //conceito de polimorfismo
    public String descricao() {
        return"Apartamento";
    }


    //utilização do ‘slogan’
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Apartamentos!";
    }
    @Override
    public void print() {
        super.print();
        System.out.println("Numero de quartos: " + getnQuartos());
        System.out.println("Area total: " + getAreaTotal());
        System.out.println("Numero de casa de banhos: " + getnCasaDeBanhos());
        System.out.println("Internet incluída? ");
        System.out.print(getInternet() == 1 ? "Sim" : "Não");
        System.out.print("Cozinha incluída? ");
        System.out.print(getCozinha() == 1 ? "Sim" : "Não");
    }
    public Apartamento(int idAcomodacao, String nome, String endereco,int reserva, int classificacao, double precoNoite, int nQuartos, double areaTotal, int nCasaDeBanhos, int internet, int cozinha) {
        super(idAcomodacao, nome, endereco, classificacao, precoNoite, reserva);
        this.nQuartos = nQuartos;
        this.areaTotal = areaTotal;
        this.nCasaDeBanhos = nCasaDeBanhos;
        this.internet = internet;
        this.cozinha = cozinha;
    }

    public Apartamento(Acomodacao acomodacao) {
        super(acomodacao);
    }

    public Apartamento(Acomodacao acomodacao, int nQuartos, double areaTotal, int nCasaDeBanhos, int internet, int cozinha) {
        super(acomodacao);
        this.nQuartos = nQuartos;
        this.areaTotal = areaTotal;
        this.nCasaDeBanhos = nCasaDeBanhos;
        this.internet = internet;
        this.cozinha = cozinha;
    }

    public Apartamento(int nQuartos, double areaTotal, int nCasaDeBanhos, int internet, int cozinha) {
        this.nQuartos = nQuartos;
        this.areaTotal = areaTotal;
        this.nCasaDeBanhos = nCasaDeBanhos;
        this.internet = internet;
        this.cozinha = cozinha;
    }

    public Apartamento(){
        super();
    }

    public Apartamento(Acomodacao acomodacao, Apartamento outroApartamento) {
        super(acomodacao);
        this.nQuartos = outroApartamento.getnQuartos();
        this.areaTotal = outroApartamento.getAreaTotal();
        this.nCasaDeBanhos = outroApartamento.getnCasaDeBanhos();
        this.internet = outroApartamento.getInternet();
        this.cozinha = outroApartamento.getCozinha();
    }


    public String toString() {
        return super.toString() +

                "  Numero de quartos: " + nQuartos + "\n" +
                "  Area total: " + areaTotal + "\n" +
                "  Numero de casa de banhos: " + nCasaDeBanhos + "\n" +
                "  Internet: " + internet  + "\n" +
                "  Cozinha  " + cozinha  + "\n" +
                "Slogan: " + slogan() + "\n"+
                "Descrição" + descricao() + "\n";


    }

    public int getnQuartos() {
        return nQuartos;
    }

    public void setnQuartos(int nQuartos) {
        this.nQuartos = nQuartos;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public int getnCasaDeBanhos() {
        return nCasaDeBanhos;
    }

    public void setnCasaDeBanhos(int nCasaDeBanhos) {
        this.nCasaDeBanhos = nCasaDeBanhos;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getCozinha() {
        return cozinha;
    }

    public void setCozinha(int cozinha) {
        this.cozinha = cozinha;
    }

}
