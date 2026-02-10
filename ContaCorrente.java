package banco.model;

/**
 * Conta Corrente - HERANÇA: Estende Conta
 * POLIMORFISMO: Implementa seu próprio rendimento
 */
public class ContaCorrente extends Conta {
    
    private static final double TAXA_OPERACAO = 10.0;
    private static final double PERCENTUAL_BONUS = 0.001; // 0.1% de bônus
    
    public ContaCorrente(Cliente titular) {
        super(titular);
    }
    
    @Override
    public void aplicarRendimento() {
        double bonus = getSaldo() * PERCENTUAL_BONUS;
        if (bonus > 0) {
            depositar(bonus);
            System.out.println("🎁 Bônus de R$ " + String.format("%.2f", bonus) + " adicionado!");
        }
    }
    
    @Override
    public double obterTaxaOperacao() {
        return TAXA_OPERACAO;
    }
    
    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero='" + getNumeroConta() + '\'' +
                ", saldo=" + String.format("%.2f", getSaldo()) +
                ", titular='" + getTitular().getNome() + '\'' +
                '}';
    }
}