package comn.objects;

import comn.interfaces.slogan;

public class HotelResort extends Hotel implements slogan {
    private String[] atividades;


    public HotelResort(String[] atividades) {
        this.atividades = atividades;
    }

    public HotelResort(Acomodacao acomodacao, String[] atividades) {
        super(acomodacao);
        this.atividades = atividades;
    }

    public HotelResort(Acomodacao acomodacao, int numeroQuartos, int quartosDisponiveis, int numeroEstrelas, String[] atividades) {
        super(acomodacao, numeroQuartos, quartosDisponiveis, numeroEstrelas);
        this.atividades = atividades;
    }

    public HotelResort(Acomodacao acomodacao, Hotel outroHotel, String[] atividades) {
        super(acomodacao, outroHotel);
        this.atividades = atividades;
    }

    public void adicionarAtividade(String atividade){
        if(getComodidades().length != 0){
            String[] atividades = new String[getComodidades().length+1];
            for(int i = 0; i < getComodidades().length; i++){
                atividades[i] = getComodidades()[i];
            }
            atividades[getAtividades().length] = atividade;
            setComodidades(atividades);
        }else {
            String[] ativades = new String[1];
            atividades[0] = atividade;
            setAtividades(atividades);
        }
    }
    public String[] getAtividades() {
        return atividades;
    }

    public void setAtividades(String[] atividades) {
        this.atividades = atividades;
    }

    //conceito de polimorfismo
    public void descricao() {
        System.out.println("HotelResort");
    }
    //utilização da classe abstrata

    @Override
    public String slogan(){
        return "Bem vindo á reserva de HotelResort!";
    }
}
