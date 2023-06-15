package objects;

import interfaces.slogan;

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

    private int nDormitoriosTotal;

    private int nDormitoriosDisponiveis;

    private int casaDeBanhoCompartilhada;

    private int internet;

    private int quartosCompartilhados;

    private String[] areasCompartilhadas;





    //conceito de polimorfismo
    public void descricao() {
        System.out.println("Hostel");
    }



    //utilização da interface
    @Override
    public String slogan(){
        return "Bem vindo á reserva de Hostel!";
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

    public String[] getAreasCompartilhadas() {
        return areasCompartilhadas;
    }

    public void setAreasCompartilhadas(String[] areasCompartilhadas) {
        this.areasCompartilhadas = areasCompartilhadas;
    }
}
