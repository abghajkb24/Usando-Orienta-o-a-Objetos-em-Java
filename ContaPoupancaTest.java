package banco.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para Conta Poupança
 */
public class ContaPoupancaTest {
    
    private Cliente cliente;
    private ContaPoupanca conta;
    
    @Before
    public void setUp() {
        cliente = new Cliente("Maria Santos", "987.654.321-00");
        conta = new ContaPoupanca(cliente);
    }
    
    @Test
    public void testDepositarValorPositivo() {
        conta.depositar(500);
        assertEquals(500, conta.getSaldo(), 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSacarMaiorQueSaldo() {
        conta.depositar(100);
        conta.sacar(200);
    }
    
    @Test
    public void testAplicarJuros() {
        conta.depositar(1000);
        double saldoAntes = conta.getSaldo();
        conta.aplicarRendimento();
        double saldoDepois = conta.getSaldo();
        
        // 0.5% de 1000 = 5
        assertEquals(5, saldoDepois - saldoAntes, 0.01);
    }
    
    @Test
    public void testTaxaOperacao() {
        assertEquals(0.0, conta.obterTaxaOperacao(), 0.01);
    }
    
    @Test
    public void testHistoricoTransacoes() {
        conta.depositar(100);
        conta.sacar(50);
        
        assertEquals(2, conta.getTransacoes().size());
    }
}