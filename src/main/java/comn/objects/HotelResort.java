package comn.objects;

import comn.interfaces.slogan;

import java.util.Arrays;

public class HotelResort extends Hotel implements slogan {
    private Integer id;
    private String[] atividades;

    public HotelResort() {
        super();
        this.atividades = new String[0];
    }

    public HotelResort(Acomodacao acomodacao, String[] atividades) {
        super(acomodacao);
        this.atividades = new String[0];
    }

    public HotelResort(Acomodacao acomodacao, String categoria, int acessibilidade, int numeroEstrelas, String[] atividades) {
        super(acomodacao, categoria, acessibilidade, numeroEstrelas);
        this.atividades = atividades;
    }

    public HotelResort(Acomodacao acomodacao, Hotel outroHotel) {
        super(acomodacao, outroHotel);
        this.atividades = new String[0];
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

    @Override
    public String toString() {
        return super.toString() +
                "Atividades=" + Arrays.toString(atividades) + "\n" +
                "Slogan: " + slogan() + "\n" +
                "Descrição: "+ descricao() + "\n"
                ;
    }

    public String[] getAtividades() {
        return atividades;
    }

    public void setAtividades(String[] atividades) {
        this.atividades = atividades;
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
