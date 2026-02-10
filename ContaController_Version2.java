package banco.controller;

import banco.model.Conta;
import banco.model.Cliente;
import banco.pattern.creational.factory.ContaFactory;
import banco.pattern.creational.factory.ContaType;
import banco.pattern.structural.decorator.ContaComTaxa;
import banco.pattern.structural.decorator.ContaComSeguro;
import banco.pattern.structural.facade.OperacoesBancariosFacade;
import banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller para operações com contas
 * Integra os Design Patterns com a API REST
 */
@RestController
@RequestMapping("/api/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaFactory contaFactory;

    @Autowired
    private OperacoesBancariosFacade operacoesFacade;

    private Map<String, Conta> contas = new HashMap<>();

    /**
     * Criar uma nova conta
     * GET /api/contas/criar?tipo=CORRENTE&nome=João&cpf=123.456.789-00&saldo=1000
     */
    @PostMapping("/criar")
    public ResponseEntity<Map<String, Object>> criarConta(
            @RequestParam String tipo,
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam(required = false, defaultValue = "0") double saldoInicial) {

        try {
            Cliente cliente = new Cliente(nome, cpf);
            Conta novaConta = operacoesFacade.abrirContaComDepositoInicial(
                    cliente,
                    ContaType.valueOf(tipo.toUpperCase()),
                    saldoInicial
            );

            contas.put(novaConta.getNumeroConta(), novaConta);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Conta criada com sucesso!");
            response.put("numero", novaConta.getNumeroConta());
            response.put("saldo", novaConta.getSaldo());
            response.put("tipo", tipo);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    /**
     * Obter saldo da conta
     * GET /api/contas/{numeroConta}/saldo
     */
    @GetMapping("/{numeroConta}/saldo")
    public ResponseEntity<Map<String, Object>> obterSaldo(@PathVariable String numeroConta) {
        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> response = new HashMap<>();
            response.put("numeroConta", numeroConta);
            response.put("saldo", conta.getSaldo());
            response.put("titular", conta.getTitular().getNome());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Realizar depósito
     * POST /api/contas/{numeroConta}/depositar?valor=100
     */
    @PostMapping("/{numeroConta}/depositar")
    public ResponseEntity<Map<String, Object>> depositar(
            @PathVariable String numeroConta,
            @RequestParam double valor) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ContaService.realizarDeposito(conta, valor);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Depósito realizado!");
            response.put("valor", valor);
            response.put("saldoAtual", conta.getSaldo());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    /**
     * Realizar saque
     * POST /api/contas/{numeroConta}/sacar?valor=50
     */
    @PostMapping("/{numeroConta}/sacar")
    public ResponseEntity<Map<String, Object>> sacar(
            @PathVariable String numeroConta,
            @RequestParam double valor) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ContaService.realizarSaque(conta, valor);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Saque realizado!");
            response.put("valor", valor);
            response.put("saldoAtual", conta.getSaldo());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    /**
     * Adicionar taxa à conta (Decorator Pattern)
     * POST /api/contas/{numeroConta}/adicionar-taxa?taxa=10
     */
    @PostMapping("/{numeroConta}/adicionar-taxa")
    public ResponseEntity<Map<String, Object>> adicionarTaxa(
            @PathVariable String numeroConta,
            @RequestParam double taxa) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ContaComTaxa contaComTaxa = new ContaComTaxa(conta, taxa);
            contas.put(numeroConta, contaComTaxa);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Taxa adicionada!");
            response.put("taxa", taxa);
            response.put("funcionalidades", contaComTaxa.getFuncionalidadesAdicionadas());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Adicionar seguro à conta (Decorator Pattern)
     * POST /api/contas/{numeroConta}/adicionar-seguro?seguro=50
     */
    @PostMapping("/{numeroConta}/adicionar-seguro")
    public ResponseEntity<Map<String, Object>> adicionarSeguro(
            @PathVariable String numeroConta,
            @RequestParam double seguro) {

        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            ContaComSeguro contaComSeguro = new ContaComSeguro(conta, seguro);
            contas.put(numeroConta, contaComSeguro);
            contaComSeguro.ativarSeguro();

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Seguro adicionado!");
            response.put("seguro", seguro);
            response.put("cobertura", contaComSeguro.getCobertura());
            response.put("funcionalidades", contaComSeguro.getFuncionalidadesAdicionadas());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Obter extrato da conta
     * GET /api/contas/{numeroConta}/extrato
     */
    @GetMapping("/{numeroConta}/extrato")
    public ResponseEntity<Map<String, Object>> obterExtrato(@PathVariable String numeroConta) {
        try {
            Conta conta = contas.get(numeroConta);
            if (conta == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> response = new HashMap<>();
            response.put("numeroConta", numeroConta);
            response.put("titular", conta.getTitular().getNome());
            response.put("saldo", conta.getSaldo());
            response.put("dataCriacao", conta.getDataCriacao());
            response.put("tipo", conta.getClass().getSimpleName());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}