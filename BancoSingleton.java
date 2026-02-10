package banco.pattern.creational.singleton;

import java.time.LocalDateTime;

/**
 * SINGLETON PATTERN
 * Garante que apenas uma instância do Banco exista em toda a aplicação
 * Thread-safe com lazy initialization
 */
public class BancoSingleton {

    private static volatile BancoSingleton instancia;
    
    private String nome;
    private LocalDateTime dataFundacao;
    private double saldoTotal;

    /**
     * Construtor privado para evitar instanciação externa
     */
    private BancoSingleton() {
        this.nome = "Banco Digital";
        this.dataFundacao = LocalDateTime.now();
        this.saldoTotal = 0;
    }

    /**
     * Obtém a instância única do Banco (Lazy Initialization)
     * Thread-safe com Double-Checked Locking
     */
    public static BancoSingleton getInstance() {
        if (instancia == null) {
            synchronized (BancoSingleton.class) {
                if (instancia == null) {
                    instancia = new BancoSingleton();
                }
            }
        }
        return instancia;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataFundacao() {
        return dataFundacao;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void adicionarSaldo(double valor) {
        this.saldoTotal += valor;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nome='" + nome + '\'' +
                ", dataFundacao=" + dataFundacao +
                ", saldoTotal=" + saldoTotal +
                '}';
    }
}