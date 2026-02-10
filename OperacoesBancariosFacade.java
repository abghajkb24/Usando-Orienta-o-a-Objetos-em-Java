package banco.pattern.structural.facade;

import banco.model.Conta;
import banco.model.Cliente;
import banco.pattern.creational.factory.ContaFactory;
import banco.pattern.creational.factory.ContaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FACADE PATTERN
 * Simplifica a interface para operações bancárias complexas
 * Esconde a complexidade dos subsistemas
 */
@Component
public class OperacoesBancariosFacade {

    @Autowired
    private ContaFactory contaFactory;

    /**
     * Operação simplificada: Abrir conta e fazer depósito inicial
     */
    public Conta abrirContaComDepositoInicial(Cliente cliente, ContaType tipo, double depositoInicial) {
        // Cria a conta
        Conta novaConta = contaFactory.criarConta(tipo, cliente);
        
        // Faz o depósito inicial
        if (depositoInicial > 0) {
            novaConta.depositar(depositoInicial);
        }
        
        System.out.println("✅ Conta aberta com sucesso!");
        return novaConta;
    }

    /**
     * Operação simplificada: Transferência com verificações
     */
    public boolean realizarTransferenciaSegura(Conta origem, Conta destino, double valor) {
        try {
            // Validações
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor deve ser positivo!");
            }
            if (origem.getSaldo() < valor) {
                throw new IllegalArgumentException("Saldo insuficiente!");
            }
            
            // Executa transferência
            origem.transferir(destino, valor);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Erro na transferência: " + e.getMessage());
            return false;
        }
    }

    /**
     * Operação simplificada: Relatório completo da conta
     */
    public void emitirRelatorioConta(Conta conta) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         RELATÓRIO DA CONTA         ║");
        System.out.println("╠════════════════════════════════════╣");
        
        conta.exibirExtrato();
        conta.exibirHistorico();
        
        System.out.println("╚════════════════════════════════════╝\n");
    }
}