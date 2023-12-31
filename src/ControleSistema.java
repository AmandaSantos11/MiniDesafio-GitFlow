import java.util.*;
public class ControleSistema {
    static Map<String, Cliente> clientes = new HashMap<>();
    static Map<String, Vendedor> vendedores = new HashMap<>();
    static List <Venda> vendasCadastradas = new ArrayList<>();
    static Scanner ler = new Scanner(System.in);
    public Cliente cadastrarCliente() {
        System.out.println("----------Cadastro de Cliente----------\n");
        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (!verificarEmailExistente(clientes, email)) {
                System.out.println("Email já cadastrado.\n");
                return clientes.get(email);
            }
            if (!email.contains("@")) {
                System.out.println("Para o email ser válido, ele deve conter '@'!");
            } else {
                break;
            }
        }

        System.out.println("Digite o CPF: ");
        String cpf = ler.nextLine();

        if (clientes.containsKey(cpf)) {
            System.out.println("CPF já cadastrado.\n");
            return clientes.get(cpf);
        }

        System.out.println("Digite a senha: ");
        String senha = ler.nextLine();

        Cliente cliente = new Cliente(nome, cpf,email, senha);
        clientes.put(cpf, cliente);

        System.out.println("\nCliente cadastrado com sucesso!\n");

        return cliente;
    }
    public boolean verificarEmailExistente(Map<String, Cliente> clientes, String email) {
        for (Cliente chave : clientes.values()) {
            if (chave.getEmail().contains(email)) {
                return false;
            }
        }
        return true;
    }
    public Vendedor cadastrarVendedor() {
        System.out.println("----------Cadastro de Vendedor----------\n");
        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (!email.contains("@")) {
                System.out.println("Para o email ser válido, ele deve conter '@'!");
            } else {
                break;
            }

            if (vendedores.containsKey(email)) {
                System.out.println("Email já cadastrado.\n");
                return vendedores.get(email);
            }
        }

        System.out.println("Digite o CPF: ");
        String cpf = ler.nextLine();

        if (!verificarCpfExistente(vendedores, cpf)) {
            System.out.println("Cpf já cadastrado.\n");
            return vendedores.get(cpf);
        }

        System.out.println("Digite a senha: ");
        String senha = ler.nextLine();

        Vendedor vendedor = new Vendedor(nome, cpf,email,senha);
        vendedores.put(email, vendedor);

        System.out.println("\nVendedor cadastrado com sucesso!\n");

        return vendedor;
    }
    public boolean verificarCpfExistente(Map<String, Vendedor> vendedores, String cpf) {
        for (Vendedor chave : vendedores.values()) {
            if (chave.getCpf().contains(cpf)) {
                return false;
            }
        }
        return true;
    }
    public  void cadastrarVenda() {
        System.out.println("~~~~~~Cadastrando Venda~~~~~~");

        String cpf;
        System.out.print("Informe o CPF do cliente: ");
        cpf = ler.nextLine();

        if (!clientes.containsKey(cpf)) {
            throw new UnsupportedOperationException("Cliente não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        String email;
        do {
            System.out.print("Informe o e-mail do vendedor: ");
            email = ler.nextLine();

            if (!email.contains("@")) {
                System.out.println("Para o email ser válido, ele deve conter '@'!");
            }

        } while (!email.contains("@"));

        if (!vendedores.containsKey(email)) {
            throw new UnsupportedOperationException("Vendedor não encontrado! Realize o cadastro antes de prosseguir\n");
        }

        if (clientes.get(cpf).getCpf().equals(vendedores.get(email).getCpf())) {
            throw new UnsupportedOperationException("Não é permitido vender para si mesmo!\n");
        }

        List<Produto> listaProdutos = adicionarProdutos();

        Venda venda = new Venda(vendedores.get(email), clientes.get(cpf),listaProdutos, calcularTotal(listaProdutos));

        vendasCadastradas.add(venda);

        System.out.println("\nVenda cadastrada com sucesso!");
    }
    private static List<Produto> adicionarProdutos(){
        List<Produto> listaProdutos = new ArrayList<>();

        boolean continuar = true;

        do{
            System.out.print("Digite o nome do produto: ");
            String nome = ler.nextLine();

            System.out.println("Digite o codigo do produto: ");
            String codigo = ler.nextLine();

            System.out.print("Digite o preço por unidade: ");
            double preco = ler.nextDouble();

            System.out.print("Informe a quantidade do produto: ");
            int quantidade = ler.nextInt();

            listaProdutos.add(new Produto(nome,codigo, preco, quantidade));

            System.out.println("""

                Deseja adicionar mais produtos à essa venda?\s
                \t1 - Sim
                \t2 - Não""");

            switch (ler.nextInt()){
                case 1:
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    throw new IllegalArgumentException("Opção Inválida!");
            }

            ler.nextLine();

        }while (continuar);

        return listaProdutos;
    }
    private static double calcularTotal(List<Produto> listaProdutos){
        double soma = 0;

        for (Produto produto : listaProdutos){
            soma = soma + produto.getPreco() * produto.getQuantidade();
        }

        return soma;
    }
    public void listarVendas(){
        if(vendasCadastradas.isEmpty()){
            System.out.println("Nenhuma venda foi cadastrada!");
        } else {
            System.out.println("~~~~~~Listando todas as vendas cadastradas~~~~~~");
            for (Venda vendas: vendasCadastradas) {
                System.out.print("Vendedor: " + vendas.getVendedor().getNome() +
                        "\t| Cliente: " + vendas.getCliente().getNome());
                System.out.print("\t| Produtos: ");
                for(int i = 0; i < vendas.getListaProdutos().size(); i++){
                    System.out.print(vendas.getListaProdutos().get(i));{
                        if(i < vendas.getListaProdutos().size() - 1){
                            System.out.print(", ");
                        }
                    }
                }
                System.out.println("\t| Preço Total: " + vendas.getTotalCompra());

            }
        }
    }
    public void listarVendedores(){
        if(vendedores.isEmpty()){
            System.out.println("Nenhum vendedor foi cadastrado!");
        } else {
            System.out.println("~~~~~~Listando todos os vendedores cadastrados~~~~~~");
            for (Map.Entry<String, Vendedor> vendedor: vendedores.entrySet()){
                System.out.println("Nome: " + vendedor.getValue().getNome() +
                        "\t| CPF: " + vendedor.getValue().getCpf() +
                        "\t| E-mail: " + vendedor.getKey());
            }
        }
    }
    public void listarClientes(){
        if(clientes.isEmpty()){
            System.out.println("Nenhum cliente foi cadastrado!");
        } else {
            System.out.println("~~~~~~Listando todos os clientes cadastrados~~~~~~");
            for (Map.Entry<String, Cliente> cliente : clientes.entrySet()){
                System.out.println("Nome: " + cliente.getValue().getNome() +
                        "\t| CPF: " + cliente.getKey() +
                        "\t| E-mail: " + cliente.getValue().getEmail());
            }
        }
    }
    public void pesquisarCompraCliente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o cpf do cliente para realizar a busca: ");
        String cpf = ler.nextLine();

        String existeCompra = "";
        for (Venda buscar : vendasCadastradas) {

            if (buscar.getCliente().getCpf().equals(cpf)) {
                existeCompra = cpf;
                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.println("Produtos: "+buscar.getListaProdutos());

                System.out.printf("Valor Total: %.2f\n" , buscar.getTotalCompra());
                System.out.println("---------------------------");
            }
        }
        if (existeCompra.isEmpty()) {
            throw new NullPointerException("NullPointerException: Não há vendas para este cliente.");
        }
    }
    public void pesquisarCompraVendedor() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o email do vendedor para realizar a busca: ");
        String email = ler.nextLine();
        String existeCompra = "";

        for (Venda buscar : vendasCadastradas) {

            if (buscar.getVendedor().getEmail().equals(email)) {
                existeCompra = email;
                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.println("Produtos: "+buscar.getListaProdutos());

                System.out.printf("Valor Total: %.2f\n" , buscar.getTotalCompra());
                System.out.println("---------------------------");
            }
        }
        if (existeCompra.isEmpty()) {
            throw new NullPointerException("NullPointerException: Não há vendas para este vendedor.");
        }
    }
}