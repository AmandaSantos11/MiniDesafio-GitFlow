import java.util.List;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private double totalCompra;

    public Venda(Vendedor vendedor, Cliente cliente, List<Produto> listaProdutos, double totalCompra) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.listaProdutos = listaProdutos;
        this.totalCompra = totalCompra;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }
}
