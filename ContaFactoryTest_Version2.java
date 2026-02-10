package banco.pattern.creational;

import banco.model.Conta;
import banco.model.Cliente;
import banco.pattern.creational.factory.ContaFactory;
import banco.pattern.creational.factory.ContaType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes para Factory Pattern
 */
public class ContaFactoryTest {

    private ContaFactory factory;
    private Cliente cliente;

    @Before
    public void setUp() {
        factory = new ContaFactory();
        cliente = new Cliente("João Silva", "123.456.789-00");
    }

    @Test
    public void testCriarContaCorrente() {
        Conta conta = factory.criarConta(ContaType.CORRENTE, cliente);
        assertNotNull(conta);
        assertEquals("ContaCorrente", conta.getClass().getSimpleName());
    }

    @Test
    public void testCriarContaPoupanca() {
        Conta conta = factory.criarConta(ContaType.POUPANCA, cliente);
        assertNotNull(conta);
        assertEquals("ContaPoupanca", conta.getClass().getSimpleName());
    }

    @Test
    public void testCriarContaPorNome() {
        Conta conta = factory.criarContaPorNome("corrente", cliente);
        assertNotNull(conta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCriarContaTipoInvalido() {
        factory.criarContaPorNome("invalida", cliente);
    }
}