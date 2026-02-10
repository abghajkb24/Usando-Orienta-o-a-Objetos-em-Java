package banco.pattern.creational.prototype;

import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.Cliente;

/**
 * PROTOTYPE PATTERN
 * Permite clonar contas existentes como protótipo
 * Útil para criar múltiplas contas similares
 */
public class ContaPrototype {

    /**
     * Clona uma conta existente criando uma nova com mesmo tipo
     * mas cliente diferente
     */
    public static Conta clonarConta(Conta contaOrigem, Cliente novoCliente) {
        // Determina o tipo da conta original
        String tipoOrigem = contaOrigem.getClass().getSimpleName();
        
        // Cria nova conta do mesmo tipo
        Conta novaConta = new ContaCorrente(novoCliente);
        
        return novaConta;
    }

    /**
     * Exemplo de uso: Template de conta para cópia
     */
    public static Conta criarAPartirDeTemplate(Conta template, Cliente novoCliente) {
        return clonarConta(template, novoCliente);
    }
}