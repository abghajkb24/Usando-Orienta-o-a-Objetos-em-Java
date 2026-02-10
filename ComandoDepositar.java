package banco.pattern.behavioral.command;

import banco.model.Conta;

/**
 * COMMAND CONCRETO
 * Comando para realizar depósito
 */
public class ComandoDepositar implements ComandoBancario {

    private Conta conta;
    private double valor;
    private boolean executado;

    public ComandoDepositar(Conta conta, double valor) {
        this.conta = conta;
        this.valor = valor;
        this.executado = false;
    }

    @Override
    public void executar() {
        conta.depositar(valor);
        this.executado = true;
        System.out.println("✅ Depósito de R$ " + String.format("%.2f", valor) + " executado!");
    }

    @Override
    public void desfazer() {
        if (executado) {
            conta.sacar(valor);
            this.executado = false;
            System.out.println("↩️ Depósito desfeito!");
        }
    }

    @Override
    public String getDescricao() {
        return "Depositar R$ " + String.format("%.2f", valor);
    }
}