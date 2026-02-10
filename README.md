# Usando Orientação a Objetos em Java 🏦

> Desafio completo de Programação Orientada a Objetos (POO) e **Design Patterns** com implementação de um Sistema Bancário em Java e Spring Framework.

## 📚 Sobre o Projeto

Este projeto evoluiu de um simples sistema bancário para uma implementação profissional que demonstra:

### **POO - 4 Pilares:**
✅ Abstração  
✅ Encapsulamento  
✅ Herança  
✅ Polimorfismo  

### **Design Patterns - 23 Padrões GoF:**

#### **Creational Patterns (Padrões de Criação)**
- ✨ **Factory Pattern** - Criação de diferentes tipos de contas
- ✨ **Builder Pattern** - Construção de contas com múltiplas configurações
- ✨ **Singleton Pattern** - Instância única do Banco
- ✨ **Prototype Pattern** - Clonagem de contas

#### **Structural Patterns (Padrões Estruturais)**
- 🏗️ **Decorator Pattern** - Adicionar funcionalidades (taxa, seguro) às contas
- 🏗️ **Facade Pattern** - Simplificar operações complexas
- 🏗️ **Adapter Pattern** - Compatibilidade com sistemas legados
- 🏗️ **Bridge, Composite, Flyweight** - Disponíveis para extensão

#### **Behavioral Patterns (Padrões Comportamentais)**
- 🎯 **Observer Pattern** - Notificações de transações
- 🎯 **Strategy Pattern** - Diferentes estratégias de rendimento
- 🎯 **Command Pattern** - Comandos reversíveis (undo/redo)
- 🎯 **State Pattern** - Estados da conta (ativa/inativa)
- 🎯 **Template Method, Chain of Responsibility, Visitor** - Disponíveis para extensão

## 🎯 Funcionalidades

### Sistema Bancário Básico
- 💳 Contas Corrente, Poupança e Investimento
- 💸 Depositar, Sacar, Transferir
- 📊 Consultar Saldo e Extrato
- 📈 Rendimento automático por tipo de conta

### Design Patterns Implementados
- 🏭 **Factory**: Criar contas facilmente
- 🔨 **Builder**: Configurar contas complexas
- 🔒 **Singleton**: Banco único na aplicação
- 🎁 **Decorator**: Adicionar taxa e seguro
- 📡 **Facade**: API simplificada de operações
- 👀 **Observer**: Notificações em tempo real
- 🔄 **Strategy**: Rendimento personalizável
- ⌨️ **Command**: Operações reversíveis

### REST API (Spring Boot)
- `POST /api/contas/criar` - Criar nova conta
- `GET /api/contas/{numero}/saldo` - Consultar saldo
- `POST /api/contas/{numero}/depositar` - Realizar depósito
- `POST /api/contas/{numero}/sacar` - Realizar saque
- `GET /api/contas/{numero}/extrato` - Obter extrato
- `POST /api/transacoes/comando/depositar` - Comando com desfazer
- `GET /api/transacoes/rendimento` - Calcular rendimento

## 🏗️ Estrutura do Projeto
