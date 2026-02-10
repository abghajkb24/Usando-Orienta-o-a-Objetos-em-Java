package banco.model;

/**
 * Conta Poupança - HERANÇA: Estende Conta
 * POLIMORFISMO: Implementa seu próprio rendimento com juros
 */
public class ContaPoupanca extends Conta {
    
    private static final double TAXA_OPERACAO = 0.0;
    private static final double TAXA_JUROS = 0.005; // 0.5% ao mês
    
    public ContaPoupanca(Cliente titular) {
        super(titular);
    }
    
    @Override
    public void aplicarRendimento() {
        double juros = getSaldo() * TAXA_JUROS;
        if (juros > 0) {
            depositar(juros);
            System.out.println("📈 Juros de R$ " + String.format("%.2f", juros) + " creditados!");
        }
    }
    
    @Override
    public double obterTaxaOperacao() {
        return TAXA_OPERACAO;
    }
    
    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "numero='" + getNumeroConta() + '\'' +
                ", saldo=" + String.format("%.2f", getSaldo()) +
                ", titular='" + getTitular().getNome() + '\'' +
                '}';
    }
}