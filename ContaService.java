package banco.service;

import banco.model.Conta;

/**
 * Serviço com operações específicas de conta
 */
public class ContaService {
    
    /**
     * Consultar saldo
     */
    public static double consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }
    
    /**
     * Realizar depósito
     */
    public static void realizarDeposito(Conta conta, double valor) {
        conta.depositar(valor);
    }
    
    /**
     * Realizar saque
     */
    public static void realizarSaque(Conta conta, double valor) {
        conta.sacar(valor);
    }
    
    /**
     * Realizar transferência
     */
    public static void realizarTransferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        contaOrigem.transferir(contaDestino, valor);
    }
    
    /**
     * Exibir extrato
     */
    public static void exibirExtrato(Conta conta) {
        conta.exibirExtrato();
    }
    
    /**
     * Exibir histórico completo
     */
    public static void exibirHistorico(Conta conta) {
        conta.exibirHistorico();
    }
}