package comn.objects;

import comn.interfaces.slogan;
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
    private Hostel[] listadeHostel;
    private int nDormitoriosTotal;

    private int nDormitoriosDisponiveis;

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
        this.nDormitoriosDisponiveis = nDormitoriosDisponiveis;
        this.casaDeBanhoCompartilhada = casaDeBanhoCompartilhada;
        this.internet = internet;
        this.quartosCompartilhados = quartosCompartilhados;

    }

    public Hostel(Acomodacao acomodacao, Hostel outroHostel) {
        super(acomodacao);
        this.nDormitoriosTotal = outroHostel.getnDormitoriosTotal();
        this.nDormitoriosDisponiveis = outroHostel.nDormitoriosDisponiveis;
        this.casaDeBanhoCompartilhada = outroHostel.getCasaDeBanhoCompartilhada();
        this.internet = outroHostel.getInternet();
        this.quartosCompartilhados = outroHostel.quartosCompartilhados;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hostel hostel)) return false;
        if (!super.equals(o)) return false;
        return getnDormitoriosTotal() == hostel.getnDormitoriosTotal() && getnDormitoriosDisponiveis() == hostel.getnDormitoriosDisponiveis() && getCasaDeBanhoCompartilhada() == hostel.getCasaDeBanhoCompartilhada() && getInternet() == hostel.getInternet() && getQuartosCompartilhados() == hostel.getQuartosCompartilhados() && Arrays.equals(getListadeHostel(), hostel.getListadeHostel());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getnDormitoriosTotal(), getnDormitoriosDisponiveis(), getCasaDeBanhoCompartilhada(), getInternet(), getQuartosCompartilhados());
        result = 31 * result + Arrays.hashCode(getListadeHostel());
        return result;
    }

    public void listar(){
        for (Hostel hostel : listadeHostel) {
            hostel.print();
        }
    }


    //conceito de polimorfismo
    public String descricao() {
        return "Hotel";
    }

    //utilização da interface
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hostel!";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Numero de dormitorios: " + nDormitoriosTotal + "\n"+
                "Numero de dormitorios disponiveis: " + nDormitoriosDisponiveis + "\n"+
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

    public int getnDormitoriosDisponiveis() {
        return nDormitoriosDisponiveis;
    }

    public void setnDormitoriosDisponiveis(int nDormitoriosDisponiveis) {
        this.nDormitoriosDisponiveis = nDormitoriosDisponiveis;
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

    public Hostel[] getListadeHostel() {
        return listadeHostel;
    }

    public void setListadeHostel(Hostel[] listadeHostel) {
        this.listadeHostel = listadeHostel;
    }

}
