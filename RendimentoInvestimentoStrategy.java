package banco.pattern.behavioral.strategy;

/**
 * STRATEGY CONCRETO
 * Estratégia de rendimento para conta investimento (rendimento alto)
 */
public class RendimentoInvestimentoStrategy implements CalculoRendimentoStrategy {

    private static final double TAXA_RENDIMENTO = 0.01; // 1%

    @Override
    public double calcularRendimento(double saldo) {
        return saldo * TAXA_RENDIMENTO;
    }

    @Override
    public String getDescricao() {
        return "Rendimento de 1% para Conta Investimento";
    }
}