package banco.pattern.creational.factory;

/**
 * Enum com os tipos de contas suportados
 * FACTORY PATTERN: Define os tipos de produtos que podem ser criados
 */
public enum ContaType {
    CORRENTE("Conta Corrente"),
    POUPANCA("Conta Poupança"),
    INVESTIMENTO("Conta Investimento");

    private final String descricao;

    ContaType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}