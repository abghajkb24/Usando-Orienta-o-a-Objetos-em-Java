package banco;

import banco.model.*;
import banco.service.BancoService;
import banco.service.ContaService;

/**
 * Classe principal que demonstra os 4 pilares da POO
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  SISTEMA BANCÁRIO - POO COM JAVA                  ║");
        System.out.println("║  Abstração, Encapsulamento, Herança, Polimorfismo ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // Criar banco
        BancoService banco = new BancoService("Banco DIO");
        
        // ============= CRIANDO CLIENTES =============
        System.out.println("➜ Cadastrando Clientes...\n");
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00");
        cliente1.setEmail("joao@email.com");
        cliente1.setTelefone("(11) 98765-4321");
        
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-00");
        cliente2.setEmail("maria@email.com");
        
        banco.cadastrarCliente(cliente1);
        banco.cadastrarCliente(cliente2);
        
        // ============= CRIANDO CONTAS (POLIMORFISMO) =============
        System.out.println("\n➜ Criando Contas de Diferentes Tipos...\n");
        
        // POLIMORFISMO: Referência Conta aponta para diferentes tipos
        Conta contaCorrente = banco.criarConta(cliente1, "corrente");
        Conta contaPoupanca = banco.criarConta(cliente1, "poupanca");
        Conta contaInvestimento = banco.criarConta(cliente2, "investimento");
        
        // ============= OPERAÇÕES BANCÁRIAS =============
        System.out.println("\n➜ Realizando Operações Bancárias...\n");
        
        // Depósitos
        ContaService.realizarDeposito(contaCorrente, 1000);
        ContaService.realizarDeposito(contaPoupanca, 5000);
        ContaService.realizarDeposito(contaInvestimento, 10000);
        
        // Saques
        System.out.println();
        ContaService.realizarSaque(contaCorrente, 200);
        ContaService.realizarSaque(contaPoupanca, 500);
        
        // Transferências
        System.out.println();
        ContaService.realizarTransferencia(contaCorrente, contaPoupanca, 300);
        ContaService.realizarTransferencia(contaInvestimento, contaCorrente, 1000);
        
        // ============= APLICAR RENDIMENTO (POLIMORFISMO) =============
        System.out.println("\n➜ Aplicando Rendimento em Todas as Contas...\n");
        banco.aplicarRendimentoGeral();
        
        // ============= EXIBIR EXTRATOS =============
        System.out.println("\n➜ Extratos das Contas:\n");
        ContaService.exibirExtrato(contaCorrente);
        ContaService.exibirExtrato(contaPoupanca);
        ContaService.exibirExtrato(contaInvestimento);
        
        // ============= LISTAR INFORMAÇÕES =============
        banco.listarClientes();
        banco.listarContas();
        
        // ============= HISTÓRICO COMPLETO =============
        System.out.println("\n➜ Histórico Completo - Conta Corrente:\n");
        ContaService.exibirHistorico(contaCorrente);
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║  CONCEITOS POO APLICADOS COM SUCESSO!             ║");
        System.out.println("║                                                    ║");
        System.out.println("║  ✅ ABSTRAÇÃO: Classe Conta abstrata define      ║");
        System.out.println("║     o contrato para todas as contas               ║");
        System.out.println("║                                                    ║");
        System.out.println("║  ✅ ENCAPSULAMENTO: Dados privados com           ║");
        System.out.println("║     getters/setters                               ║");
        System.out.println("║                                                    ║");
        System.out.println("║  ✅ HERANÇA: ContaCorrente, ContaPoupanca e      ║");
        System.out.println("║     ContaInvestimento estendem Conta              ║");
        System.out.println("║                                                    ║");
        System.out.println("║  ✅ POLIMORFISMO: Cada conta implementa seu      ║");
        System.out.println("║     próprio aplicarRendimento()                   ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }
}