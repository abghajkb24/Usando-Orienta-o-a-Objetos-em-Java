package banco.pattern.creational.factory;

import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.model.ContaInvestimento;
import banco.model.Cliente;
import org.springframework.stereotype.Component;

/**
 * FACTORY PATTERN
 * Responsável por criar diferentes tipos de contas
 * Centraliza a lógica de criação de objetos
 */
@Component
public class ContaFactory {

    /**
     * Cria uma conta baseado no tipo especificado
     * @param tipo Tipo de conta a ser criada
     * @param cliente Cliente proprietário da conta
     * @return Instância da conta criada
     */
    public Conta criarConta(ContaType tipo, Cliente cliente) {
        return switch (tipo) {
            case CORRENTE -> new ContaCorrente(cliente);
            case POUPANCA -> new ContaPoupanca(cliente);
            case INVESTIMENTO -> new ContaInvestimento(cliente);
        };
    }

    /**
     * Cria conta a partir do nome do tipo (string)
     */
    public Conta criarContaPorNome(String nomeTipo, Cliente cliente) {
        try {
            ContaType tipo = ContaType.valueOf(nomeTipo.toUpperCase());
            return criarConta(tipo, cliente);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de conta inválido: " + nomeTipo);
        }
    }
}