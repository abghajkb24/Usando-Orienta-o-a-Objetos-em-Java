package banco.model;

import java.time.LocalDateTime;

/**
 * Classe Cliente - ENCAPSULAMENTO: Dados privados com acesso controlado
 */
public class Cliente {
    
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataCadastro = LocalDateTime.now();
    }
    
    // ENCAPSULAMENTO: Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio!");
        }
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (email != null && !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido!");
        }
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}