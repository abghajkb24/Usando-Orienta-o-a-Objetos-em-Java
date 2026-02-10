package banco.pattern.behavioral.strategy;

/**
 * STRATEGY CONCRETO
 * Estratégia de rendimento para conta corrente (bônus)
 */
public class RendimentoCorrenteStrategy implements CalculoRendimentoStrategy {

    private static final double PERCENTUAL_BONUS = 0.001; // 0.1%

    @Override
    public double calcularRendimento(double saldo) {
        return saldo * PERCENTUAL_BONUS;
    }

    @Override
    public String getDescricao() {
        return "Bônus de 0.1% para Conta Corrente";
    }
}