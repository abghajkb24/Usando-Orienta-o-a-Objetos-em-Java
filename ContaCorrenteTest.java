package banco.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para Conta Corrente
 */
public class ContaCorrenteTest {
    
    private Cliente cliente;
    private ContaCorrente conta;
    
    @Before
    public void setUp() {
        cliente = new Cliente("João Silva", "123.456.789-00");
        conta = new ContaCorrente(cliente);
    }
    
    @Test
    public void testDepositarValorPositivo() {
        conta.depositar(100);
        assertEquals(100, conta.getSaldo(), 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositarValorNegativo() {
        conta.depositar(-50);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositarZero() {
        conta.depositar(0);
    }
    
    @Test
    public void testSacarValorValido() {
        conta.depositar(100);
        conta.sacar(50);
        assertEquals(50, conta.getSaldo(), 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSacarMaiorQueSaldo() {
        conta.depositar(50);
        conta.sacar(100);
    }
    
    @Test
    public void testAplicarRendimento() {
        conta.depositar(1000);
        double saldoAntes = conta.getSaldo();
        conta.aplicarRendimento();
        double saldoDepois = conta.getSaldo();
        assertTrue(saldoDepois > saldoAntes);
    }
    
    @Test
    public void testTransferencia() {
        ContaCorrente contaDestino = new ContaCorrente(cliente);
        conta.depositar(100);
        conta.transferir(contaDestino, 50);
        
        assertEquals(50, conta.getSaldo(), 0.01);
        assertEquals(50, contaDestino.getSaldo(), 0.01);
    }
    
    @Test
    public void testTaxaOperacao() {
        assertEquals(10.0, conta.obterTaxaOperacao(), 0.01);
    }
}