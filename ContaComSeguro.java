package banco.pattern.structural.decorator;

import banco.model.Conta;

/**
 * DECORATOR CONCRETO
 * Adiciona proteção de seguro à conta
 */
public class ContaComSeguro extends ContaDecorator {

    private double valorSeguro;
    private double cobertura;

    public ContaComSeguro(Conta conta, double valorSeguro) {
        super(conta);
        this.valorSeguro = valorSeguro;
        this.cobertura = conta.getSaldo() * 2; // Cobertura = 2x o saldo
        this.funcionalidadesAdicionadas.add("Seguro de R$ " + String.format("%.2f", valorSeguro));
    }

    @Override
    public void aplicarRendimento() {
        contaDecorada.aplicarRendimento();
    }

    @Override
    public double obterTaxaOperacao() {
        return valorSeguro;
    }

    public double getCobertura() {
        return cobertura;
    }

    public void ativarSeguro() {
        System.out.println("🛡️ Seguro ativado! Cobertura: R$ " + String.format("%.2f", cobertura));
    }

    @Override
    public String toString() {
        return "ContaComSeguro{" +
                "valorSeguro=" + String.format("%.2f", valorSeguro) +
                ", cobertura=" + String.format("%.2f", cobertura) +
                ", funcionalidades=" + funcionalidadesAdicionadas +
                '}';
    }
}