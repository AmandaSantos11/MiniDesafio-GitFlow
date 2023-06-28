public class Produto {
    private String nome, codigo;
    private double preco;
    private int quantidade;

    public Produto(String nome, String codigo, double preco, int quantidade) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
    }


    public double getPreco() {
        return preco;
    }



    public int getQuantidade() {
        return quantidade;
    }

    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}
