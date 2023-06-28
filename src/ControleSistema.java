import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControleSistema {
    static Map<String, Cliente> clientes = new HashMap<>();

    public Cliente cadastrarCliente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("----------Cadastro de Cliente----------\n");
        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (!verificarEmailExistente(clientes,email)) {
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

        Cliente cliente = new Cliente(nome, email, cpf, senha);
        clientes.put(cpf,cliente);

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
}

