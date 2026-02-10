package banco.pattern.structural.decorator;

import banco.model.Conta;

/**
 * DECORATOR CONCRETO
 * Adiciona taxas à conta
 */
public class ContaComTaxa extends ContaDecorator {

    private double taxaMensal;

    public ContaComTaxa(Conta conta, double taxaMensal) {
        super(conta);
        this.taxaMensal = taxaMensal;
        this.funcionalidadesAdicionadas.add("Taxa Mensal de R$ " + String.format("%.2f", taxaMensal));
    }

    @Override
    public void aplicarRendimento() {
        contaDecorada.aplicarRendimento();
    }

    @Override
    public double obterTaxaOperacao() {
        return taxaMensal;
    }

    public void cobrarTaxa() {
        if (getSaldo() >= taxaMensal) {
            sacar(taxaMensal);
            System.out.println("💳 Taxa de R$ " + String.format("%.2f", taxaMensal) + " cobrada!");
        } else {
            System.out.println("❌ Saldo insuficiente para cobrar taxa!");
        }
    }

    @Override
    public String toString() {
        return "ContaComTaxa{" +
                "taxaMensal=" + String.format("%.2f", taxaMensal) +
                ", funcionalidades=" + funcionalidadesAdicionadas +
                '}';
    }
}