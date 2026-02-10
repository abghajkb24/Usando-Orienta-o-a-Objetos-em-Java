package banco.pattern.behavioral;

import banco.pattern.behavioral.observer.NotificadorTransacao;
import banco.pattern.behavioral.observer.TransacaoObserver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes para Observer Pattern
 */
public class TransacaoObserverTest {

    private NotificadorTransacao notificador;
    private int contagemNotificacoes;

    @Before
    public void setUp() {
        notificador = new NotificadorTransacao();
        contagemNotificacoes = 0;
    }

    @Test
    public void testRegistrarObservador() {
        TransacaoObserver observer = (tipo, valor, desc) -> contagemNotificacoes++;
        
        notificador.registrarObservador(observer);
        assertEquals(1, notificador.getQuantidadeObservadores());
    }

    @Test
    public void testNotificarTodos() {
        TransacaoObserver observer1 = (tipo, valor, desc) -> contagemNotificacoes++;
        TransacaoObserver observer2 = (tipo, valor, desc) -> contagemNotificacoes++;
        
        notificador.registrarObservador(observer1);
        notificador.registrarObservador(observer2);
        
        notificador.notificarTodos("DEPOSITO", 100, "Teste");
        assertEquals(2, contagemNotificacoes);
    }

    @Test
    public void testRemoverObservador() {
        TransacaoObserver observer = (tipo, valor, desc) -> contagemNotificacoes++;
        
        notificador.registrarObservador(observer);
        notificador.removerObservador(observer);
        
        assertEquals(0, notificador.getQuantidadeObservadores());
    }
}