package comn.objects;

import comn.interfaces.slogan;
public class Apartamento extends Acomodacao implements slogan {

    private int nQuartos;

    private double areaTotal;

    private int nCasaDeBanhos;

    private int internet;

    private int cozinha;

    //conceito de polimorfismo
    public void descricao() {
        System.out.println("Apartamento");
    }


    //utilização do slogan
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

    public Apartamento(String nome, String endereco, int classificacao, double precoNoite, String descricao, int nQuartos, double areaTotal, int nCasaDeBanhos, int internet, int cozinha) {
        super(nome, endereco, classificacao, precoNoite, descricao);
        this.nQuartos = nQuartos;
        this.areaTotal = areaTotal;
        this.nCasaDeBanhos = nCasaDeBanhos;
        this.internet = internet;
        this.cozinha = cozinha;
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

    @Override
    public String toString() {
        return "Apartamento{" +
                "nQuartos=" + nQuartos +
                ", areaTotal=" + areaTotal +
                ", nCasaDeBanhos=" + nCasaDeBanhos +
                ", internet=" + internet +
                ", cozinha=" + cozinha +
                '}';
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
