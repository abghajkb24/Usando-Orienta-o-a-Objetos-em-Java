package banco.pattern.creational;

import banco.model.Conta;
import banco.model.Cliente;
import banco.pattern.creational.builder.ContaBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes para Builder Pattern
 */
public class ContaBuilderTest {

    @Test
    public void testConstruirContaComSaldoInicial() {
        Cliente cliente = new Cliente("Maria", "987.654.321-00");
        
        Conta conta = new ContaBuilder()
                .comCliente(cliente)
                .comSaldoInicial(5000)
                .construir();

        assertNotNull(conta);
        assertEquals(5000, conta.getSaldo(), 0.01);
    }

    @Test(expected = IllegalStateException.class)
    public void testConstruirSemCliente() {
        new ContaBuilder()
                .comSaldoInicial(1000)
                .construir();
    }

    @Test
    public void testConstruirComMultiplasConfiguracoes() {
        Cliente cliente = new Cliente("Pedro", "111.222.333-44");
        
        Conta conta = new ContaBuilder()
                .comCliente(cliente)
                .comSaldoInicial(2000)
                .comDescricao("Conta principal")
                .construir();

        assertNotNull(conta);
        assertEquals(2000, conta.getSaldo(), 0.01);
    }
}