package banco.pattern.structural.adapter;

/**
 * Interface legada (de um sistema antigo)
 * Com método diferente dos padrões atuais
 */
public interface LegacyContaInterface {
    void executarDeposito(double valor);
    void executarSaque(double valor);
    double obterSaldoAtual();
}