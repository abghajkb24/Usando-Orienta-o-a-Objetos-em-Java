package banco.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que define o contrato para todas as contas bancárias.
 * 
 * ABSTRAÇÃO: Define a interface comum para todas as contas
 * ENCAPSULAMENTO: Protege os dados da conta com modificadores private
 */
public abstract class Conta {
    
    private static int sequencial = 1000;
    
    // ENCAPSULAMENTO: Dados privados
    private String numeroConta;
    private double saldo;
    private Cliente titular;
    private LocalDateTime dataCriacao;
    private List<Transacao> transacoes;
    private boolean ativa;
    
    public Conta(Cliente titular) {
        this.numeroConta = String.valueOf(++sequencial);
        this.titular = titular;
        this.saldo = 0;
        this.dataCriacao = LocalDateTime.now();
        this.transacoes = new ArrayList<>();
        this.ativa = true;
    }
    
    /**
     * ABSTRAÇÃO: Método abstrato que será implementado por cada tipo de conta
     * POLIMORFISMO: Cada conta tem seu próprio rendimento
     */
    public abstract void aplicarRendimento();
    
    /**
     * ABSTRAÇÃO: Método abstrato para definir taxa
     */
    public abstract double obterTaxaOperacao();
    
    /**
     * Depositar dinheiro na conta
     */
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (!ativa) {
            throw new IllegalStateException("Conta inativa!");
        }
        
        this.saldo += valor;
        registrarTransacao("DEPÓSITO", valor, "Depósito realizado com sucesso");
        System.out.println("✅ Depósito de R$ " + String.format("%.2f", valor) + " realizado!");
    }
    
    /**
     * Sacar dinheiro da conta
     */
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (!ativa) {
            throw new IllegalStateException("Conta inativa!");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        
        this.saldo -= valor;
        registrarTransacao("SAQUE", -valor, "Saque realizado com sucesso");
        System.out.println("✅ Saque de R$ " + String.format("%.2f", valor) + " realizado!");
    }
    
    /**
     * Transferir para outra conta
     */
    public void transferir(Conta contaDestino, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (!ativa || !contaDestino.ativa) {
            throw new IllegalStateException("Conta inativa!");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        
        this.saldo -= valor;
        contaDestino.saldo += valor;
        
        this.registrarTransacao("TRANSFERÊNCIA ENVIADA", -valor, 
            "Transferência para " + contaDestino.getNumeroConta());
        contaDestino.registrarTransacao("TRANSFERÊNCIA RECEBIDA", valor, 
            "Transferência de " + this.getNumeroConta());
        
        System.out.println("✅ Transferência de R$ " + String.format("%.2f", valor) + " realizada!");
    }
    
    /**
     * Registrar uma transação
     */
    private void registrarTransacao(String tipo, double valor, String descricao) {
        Transacao transacao = new Transacao(tipo, valor, descricao);
        this.transacoes.add(transacao);
    }
    
    /**
     * Consultar saldo
     */
    public double getSaldo() {
        return saldo;
    }
    
    /**
     * Consultar número da conta
     */
    public String getNumeroConta() {
        return numeroConta;
    }
    
    /**
     * Consultar titular
     */
    public Cliente getTitular() {
        return titular;
    }
    
    /**
     * Obter data de criação
     */
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    /**
     * Obter histórico de transações
     */
    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    
    /**
     * Desativar conta
     */
    public void desativar() {
        if (saldo > 0) {
            throw new IllegalStateException("Conta possui saldo!");
        }
        this.ativa = false;
        System.out.println("❌ Conta desativada com sucesso!");
    }
    
    /**
     * Exibir extrato resumido
     */
    public void exibirExtrato() {
        System.out.println("\n========== EXTRATO ==========");
        System.out.println("Titular: " + titular.getNome());
        System.out.println("Número: " + numeroConta);
        System.out.println("Tipo: " + this.getClass().getSimpleName());
        System.out.println("Saldo: R$ " + String.format("%.2f", saldo));
        System.out.println("Data Criação: " + dataCriacao);
        System.out.println("Status: " + (ativa ? "ATIVA" : "INATIVA"));
        System.out.println("=============================\n");
    }
    
    /**
     * Exibir histórico completo
     */
    public void exibirHistorico() {
        exibirExtrato();
        System.out.println("========== HISTÓRICO ==========");
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação realizada");
        } else {
            for (Transacao t : transacoes) {
                System.out.println(t);
            }
        }
        System.out.println("==============================\n");
    }
    
    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numeroConta + '\'' +
                ", saldo=" + String.format("%.2f", saldo) +
                ", titular='" + titular.getNome() + '\'' +
                ", ativa=" + ativa +
                '}';
    }
}