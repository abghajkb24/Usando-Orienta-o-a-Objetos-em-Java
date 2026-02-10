package banco.pattern.structural.decorator;

import banco.model.Conta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DECORATOR PATTERN
 * Classe abstrata que decora uma conta com funcionalidades adicionais
 * Permite adicionar responsabilidades sem modificar a classe original
 */
public abstract class ContaDecorator extends Conta {

    protected Conta contaDecorada;
    protected List<String> funcionalidadesAdicionadas;

    public ContaDecorator(Conta conta) {
        super(conta.getTitular());
        this.contaDecorada = conta;
        this.funcionalidadesAdicionadas = new ArrayList<>();
    }

    @Override
    public void depositar(double valor) {
        contaDecorada.depositar(valor);
    }

    @Override
    public void sacar(double valor) {
        contaDecorada.sacar(valor);
    }

    @Override
    public void transferir(Conta contaDestino, double valor) {
        contaDecorada.transferir(contaDestino, valor);
    }

    @Override
    public double getSaldo() {
        return contaDecorada.getSaldo();
    }

    public List<String> getFuncionalidadesAdicionadas() {
        return funcionalidadesAdicionadas;
    }

    @Override
    public abstract void aplicarRendimento();

    @Override
    public abstract double obterTaxaOperacao();
}