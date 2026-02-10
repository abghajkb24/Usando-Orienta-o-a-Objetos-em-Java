package banco.pattern.behavioral.state;

/**
 * STATE CONCRETO
 * Comportamento quando conta está ativa
 */
public class ContaAtivaState implements ContaState {

    @Override
    public void depositar(double valor) {
        System.out.println("✅ Depósito de R$ " + String.format("%.2f", valor) + " permitido!");
    }

    @Override
    public void sacar(double valor) {
        System.out.println("✅ Saque de R$ " + String.format("%.2f", valor) + " permitido!");
    }

    @Override
    public void ativar() {
        System.out.println("ℹ️ Conta já está ativa!");
    }

    @Override
    public void desativar() {
        System.out.println("⏸️ Conta será desativada!");
    }

    @Override
    public String getEstado() {
        return "ATIVA";
    }
}