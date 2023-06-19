package comn.objects;



import java.util.Arrays;
import java.util.Objects;

public class Acomodacao  {

    private String nome;
    private String endereco;
    private int classificacao;
    private double precoNoite;
    private String descricao;
    private String[] comodidades;


    public Acomodacao(String nome, String endereco, int classificacao, double precoNoite, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.precoNoite = precoNoite;
        this.descricao = descricao;
        this.comodidades = new String[0];}

    public Acomodacao(Acomodacao acomodacao) {
        this.nome = acomodacao.getNome();
        this.endereco = acomodacao.getEndereco();
        this.classificacao = acomodacao.getClassificacao();
        this.precoNoite = acomodacao.getPrecoNoite();
        this.descricao = acomodacao.getDescricao();
        this.comodidades = acomodacao.getComodidades();
    }

    public Acomodacao() {
        this.comodidades = new String[0];
    }

    /**
     * Adicionar comodidade á acomodaçao.
     */
    public void adicionarComodidade(String comodidade){
        if(getComodidades().length != 0){
            String[] comodidades = new String[getComodidades().length+1];
            for(int i = 0; i < getComodidades().length; i++){
                comodidades[i] = getComodidades()[i];
            }
            comodidades[getComodidades().length] = comodidade;
            setComodidades(comodidades);
        }else {
            String[] comodidades = new String[1];
            comodidades[0] = comodidade;
            setComodidades(comodidades);
        }
    }
    //conceito de polimorfismo
    public String descricao() {
        return "Acomodação genérica";
    }

    public void print() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Endereco: " + this.endereco);
        System.out.println("Classificacao: " + this.classificacao);
        System.out.println("Preco: " + this.precoNoite);
        System.out.println("Descricao: " + this.descricao);
        System.out.println("Comodidades: ");
        for(int i = 0; i < getComodidades().length; i++){
            System.out.print(getComodidades()[i]);
            System.out.println(" ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acomodacao that)) return false;
        return getClassificacao() == that.getClassificacao() && Double.compare(that.getPrecoNoite(), getPrecoNoite()) == 0 && Objects.equals(getNome(), that.getNome()) && Objects.equals(getEndereco(), that.getEndereco()) && Objects.equals(getDescricao(), that.getDescricao()) && Arrays.equals(getComodidades(), that.getComodidades());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return
                "Nome: " + nome + '\'' + "\n"+
                "Endereco: " + endereco + '\'' + "\n"+
                "Classificação: " + classificacao + "\n"+
                "PrecoNoite: " + precoNoite + "\n"+
                "Descrição: " + descricao + '\'' + "\n"+
                "Coomodidades: " + Arrays.toString(comodidades) + "\n"+
                "\n";}





    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public double getPrecoNoite() {
        return precoNoite;
    }

    public void setPrecoNoite(double precoNoite) {
        this.precoNoite = precoNoite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getComodidades() {
        return comodidades;
    }

    public void setComodidades(String[] comodidades) {
        this.comodidades = comodidades;
    }


}
