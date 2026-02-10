package banco.pattern.creational.builder;

import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.Cliente;

/**
 * BUILDER PATTERN
 * Permite construir objetos complexos passo a passo
 * Facilita a criação de contas com múltiplas configurações
 */
public class ContaBuilder {

    private Cliente cliente;
    private double saldoInicial;
    private String descricao;

    public ContaBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ContaBuilder comSaldoInicial(double saldo) {
        this.saldoInicial = saldo;
        return this;
    }

    public ContaBuilder comDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    /**
     * Constrói a conta com as configurações definidas
     */
    public Conta construir() {
        if (cliente == null) {
            throw new IllegalStateException("Cliente é obrigatório!");
        }

        Conta conta = new ContaCorrente(cliente);

        if (saldoInicial > 0) {
            conta.depositar(saldoInicial);
        }

        return conta;
    }

    /**
     * Exemplo de uso
     */
    public static void exemplo() {
        Cliente cliente = new Cliente("João", "123.456.789-00");
        Conta conta = new ContaBuilder()
                .comCliente(cliente)
                .comSaldoInicial(1000)
                .comDescricao("Conta principal")
                .construir();
    }
}