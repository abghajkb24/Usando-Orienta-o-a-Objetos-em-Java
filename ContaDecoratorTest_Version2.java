package banco.pattern.structural;

import banco.model.Conta;
import banco.model.Cliente;
import banco.model.ContaCorrente;
import banco.pattern.structural.decorator.ContaComTaxa;
import banco.pattern.structural.decorator.ContaComSeguro;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes para Decorator Pattern
 */
public class ContaDecoratorTest {

    private Cliente cliente;
    private Conta conta;

    @Before
    public void setUp() {
        cliente = new Cliente("Ana", "555.666.777-88");
        conta = new ContaCorrente(cliente);
        conta.depositar(5000);
    }

    @Test
    public void testAdicionarTaxa() {
        ContaComTaxa contaComTaxa = new ContaComTaxa(conta, 10);
        
        assertTrue(contaComTaxa.getFuncionalidadesAdicionadas().size() > 0);
        assertEquals(10, contaComTaxa.obterTaxaOperacao(), 0.01);
    }

    @Test
    public void testAdicionarSeguro() {
        ContaComSeguro contaComSeguro = new ContaComSeguro(conta, 50);
        
        assertTrue(contaComSeguro.getFuncionalidadesAdicionadas().size() > 0);
        assertTrue(contaComSeguro.getCobertura() > 0);
    }

    @Test
    public void testDecorarMultiplas() {
        ContaComTaxa comTaxa = new ContaComTaxa(conta, 10);
        ContaComSeguro comSeguro = new ContaComSeguro(comTaxa, 50);
        
        assertEquals(2, comSeguro.getFuncionalidadesAdicionadas().size());
    }
}