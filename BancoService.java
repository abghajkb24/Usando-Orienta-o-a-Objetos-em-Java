package banco.service;

import banco.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço para gerenciar operações do banco
 * POLIMORFISMO: Trabalha com referência de Conta (classe abstrata)
 */
public class BancoService {
    
    private List<Cliente> clientes;
    private List<Conta> contas;
    private String nomeBanco;
    
    public BancoService(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }
    
    /**
     * Cadastrar cliente
     */
    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }
        clientes.add(cliente);
        System.out.println("✅ Cliente " + cliente.getNome() + " cadastrado com sucesso!");
    }
    
    /**
     * Criar conta - POLIMORFISMO em ação
     */
    public Conta criarConta(Cliente cliente, String tipoConta) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }
        
        Conta novaConta;
        
        switch (tipoConta.toLowerCase()) {
            case "corrente":
                novaConta = new ContaCorrente(cliente);
                break;
            case "poupanca":
                novaConta = new ContaPoupanca(cliente);
                break;
            case "investimento":
                novaConta = new ContaInvestimento(cliente);
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido!");
        }
        
        contas.add(novaConta);
        System.out.println("✅ Conta " + tipoConta + " criada com sucesso!");
        System.out.println("   Número: " + novaConta.getNumeroConta());
        return novaConta;
    }
    
    /**
     * Aplicar rendimento em todas as contas
     * POLIMORFISMO: Cada conta aplica seu próprio rendimento
     */
    public void aplicarRendimentoGeral() {
        System.out.println("\n💳 Aplicando rendimento em todas as contas...\n");
        for (Conta conta : contas) {
            conta.aplicarRendimento();
        }
    }
    
    /**
     * Obter saldo total do banco
     */
    public double obterSaldoTotalBanco() {
        double total = 0;
        for (Conta conta : contas) {
            total += conta.getSaldo();
        }
        return total;
    }
    
    /**
     * Listar todas as contas
     */
    public void listarContas() {
        System.out.println("\n========== CONTAS DO " + nomeBanco + " ==========");
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada");
        } else {
            for (int i = 0; i < contas.size(); i++) {
                System.out.println((i + 1) + ". " + contas.get(i));
            }
        }
        System.out.println("Saldo Total: R$ " + String.format("%.2f", obterSaldoTotalBanco()));
        System.out.println("==========================================\n");
    }
    
    /**
     * Encontrar conta por número
     */
    public Conta encontrarConta(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        throw new IllegalArgumentException("Conta não encontrada!");
    }
    
    /**
     * Listar clientes
     */
    public void listarClientes() {
        System.out.println("\n========== CLIENTES DO " + nomeBanco + " ==========");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + ". " + clientes.get(i));
            }
        }
        System.out.println("=============================================\n");
    }
    
    public String getNomeBanco() {
        return nomeBanco;
    }
}