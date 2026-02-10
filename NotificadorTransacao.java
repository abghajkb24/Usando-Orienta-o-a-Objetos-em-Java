package banco.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * OBSERVER PATTERN - Concrete Subject
 * Gerencia observadores e notifica sobre transações
 */
public class NotificadorTransacao {

    private List<TransacaoObserver> observadores;

    public NotificadorTransacao() {
        this.observadores = new ArrayList<>();
    }

    /**
     * Registra um observador
     */
    public void registrarObservador(TransacaoObserver observer) {
        observadores.add(observer);
        System.out.println("✅ Observador registrado!");
    }

    /**
     * Remove um observador
     */
    public void removerObservador(TransacaoObserver observer) {
        observadores.remove(observer);
    }

    /**
     * Notifica todos os observadores
     */
    public void notificarTodos(String tipo, double valor, String descricao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        for (TransacaoObserver observer : observadores) {
            observer.notificarTransacao(tipo, valor, descricao);
        }
    }

    public int getQuantidadeObservadores() {
        return observadores.size();
    }
}