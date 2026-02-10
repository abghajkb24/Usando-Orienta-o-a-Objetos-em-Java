package banco.controller;

import banco.model.Conta;
import banco.pattern.behavioral.command.ComandoBancario;
import banco.pattern.behavioral.command.ComandoDepositar;
import banco.pattern.behavioral.command.ComandoSacar;
import banco.pattern.behavioral.strategy.CalculoRendimentoStrategy;
import banco.pattern.behavioral.strategy.RendimentoCorrenteStrategy;
import banco.pattern.behavioral.strategy.RendimentoPoupancaStrategy;
import banco.pattern.behavioral.strategy.RendimentoInvestimentoStrategy;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller para operações de transações
 * Demonstra Command Pattern e Strategy Pattern
 */
@RestController
@RequestMapping("/api/transacoes")
@CrossOrigin(origins = "*")
public class TransacaoController {

    private List<ComandoBancario> historicoComandos = new ArrayList<>();
    private Map<String, Conta> contas = new HashMap<>();

    /**
     * Executar comando de depósito
     * POST /api/transacoes/comando/depositar?numeroConta=1001&valor=100
     */
    @PostMapping("/comando/depositar")
    public ResponseEntity<Map<String, Object>> executarComandoDeposito(
            @RequestParam String numeroConta,
            @RequestParam double valor) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ComandoBancario comando = new ComandoDepositar(conta, valor);
            comando.executar();
            historicoComandos.add(comando);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("comando", comando.getDescricao());
            response.put("saldoAtual", conta.getSaldo());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Executar comando de saque
     * POST /api/transacoes/comando/sacar?numeroConta=1001&valor=50
     */
    @PostMapping("/comando/sacar")
    public ResponseEntity<Map<String, Object>> executarComandoSaque(
            @PathVariable String numeroConta,
            @RequestParam double valor) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ComandoBancario comando = new ComandoSacar(conta, valor);
            comando.executar();
            historicoComandos.add(comando);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("comando", comando.getDescricao());
            response.put("saldoAtual", conta.getSaldo());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Calcular rendimento com Strategy Pattern
     * GET /api/transacoes/rendimento?tipo=CORRENTE&saldo=1000
     */
    @GetMapping("/rendimento")
    public ResponseEntity<Map<String, Object>> calcularRendimento(
            @RequestParam String tipo,
            @RequestParam double saldo) {

        try {
            CalculoRendimentoStrategy strategy = switch (tipo.toUpperCase()) {
                case "CORRENTE" -> new RendimentoCorrenteStrategy();
                case "POUPANCA" -> new RendimentoPoupancaStrategy();
                case "INVESTIMENTO" -> new RendimentoInvestimentoStrategy();
                default -> throw new IllegalArgumentException("Tipo inválido!");
            };

            double rendimento = strategy.calcularRendimento(saldo);

            Map<String, Object> response = new HashMap<>();
            response.put("tipo", tipo);
            response.put("saldo", saldo);
            response.put("rendimento", rendimento);
            response.put("descricao", strategy.getDescricao());
            response.put("saldoFinal", saldo + rendimento);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    /**
     * Obter histórico de comandos executados
     * GET /api/transacoes/historico
     */
    @GetMapping("/historico")
    public ResponseEntity<Map<String, Object>> obterHistorico() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalComandos", historicoComandos.size());
        response.put("comandos", historicoComandos.stream()
                .map(ComandoBancario::getDescricao)
                .toList());

        return ResponseEntity.ok(response);
    }
}