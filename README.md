# Singleton — Sistema de Logs e Parâmetros

<p align="center">
  <a href="https://www.ufjf.br/" rel="noopener">
    <img width=261 height=148 src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Logo_da_UFJF.png/640px-Logo_da_UFJF.png" alt="Logo UFJF">
  </a>
</p>

<h3 align="center">DCC078-2025.3-A — Aspectos Avançados em Engenharia de Software (UFJF/ICE)</h3>

---

## � Sumário
- [Sobre](#sobre)
- [Diagrama Nível de Projeto](#diagrama)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Exemplo de Uso](#exemplo)
- [Como Executar e Testes](#testes)
- [Autor](#autor)

## 🧐 Sobre <a name="sobre"></a>
> **Disciplina:** DCC078 – Aspectos Avançados em Engenharia de Software  
> **Projeto:** Sistema de Logs e Parâmetros com Singleton
> **Docente:** Prof. Marco Antônio Pereira Araújo
> **Data de entrega:** 04/10/2025  
> **Aluno:** [Gabriel Campos Lima Alves](#autor)

### Padrão Singleton
Implementação do padrão **Singleton** para criação de um sistema de gerenciamento de logs e parâmetros globais da aplicação.
O **Singleton** é um padrão criacional que garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a ela, demonstrando:
- Instância única compartilhada em toda a aplicação
- Acesso global controlado a recursos
- Inicialização eager (thread-safe por padrão)

## 📐 Diagrama de Classe <a name="diagrama"></a>
O diagrama abaixo representa a arquitetura do projeto, destacando a aplicação do padrão **Singleton** no sistema:

<p align="center">
  <img src="./Singleton.png" alt="Diagrama de Classe - Singleton" width="800"/>
</p>

## 🚀 Funcionalidades <a name="funcionalidades"></a>
### Sistema de Registro de Logs (Singleton)
- **RegistroLogs**: Sistema completo de gerenciamento de logs da aplicação

### Recursos
- ✅ Instância única garantida (padrão Singleton)
- ✅ Thread-safe por inicialização eager
- ✅ Registro de logs com múltiplos níveis (INFO, ERROR, WARNING, etc.)
- ✅ Persistência automática de logs em arquivo
- ✅ Armazenamento de logs em memória para consulta rápida
- ✅ Formatação automática com timestamp e usuário
- ✅ Métodos sincronizados para segurança em ambientes multi-thread
- ✅ Função de limpeza de logs (memória e arquivo)

##  Tecnologias <a name="tecnologias"></a>
- **Java 11+**
- **JUnit 5** - Framework de testes
- **Maven** - Gerenciamento de dependências
- **Git** - Controle de versão


## Exemplo de Uso <a name="exemplo"></a>
```java
## 📊 Exemplo de Uso <a name="exemplo"></a>
```java
// Obtendo a instância única do RegistroLogs (Singleton)
RegistroLogs logs = RegistroLogs.getInstance();

// Configurando o sistema de logs
logs.setCaminhoArquivo("logs/aplicacao.log");
logs.setUsuarioAtivo("admin");

// Registrando logs com nível padrão (INFO)
logs.registrar("Aplicação iniciada");

// Registrando logs com nível específico
logs.registrar("INFO", "Sistema configurado com sucesso");
logs.registrar("ERROR", "Erro ao processar requisição");
logs.registrar("WARNING", "Memória acima de 80%");

// Verificando que é realmente Singleton
RegistroLogs logs2 = RegistroLogs.getInstance();
System.out.println(logs == logs2); // true

// Consultando logs em memória
for (String log : logs.getLogs()) {
    System.out.println(log);
}

// Formato de saída dos logs:
// 2025-10-04 19:32:15 [INFO] (admin) - Aplicação iniciada
// 2025-10-04 19:32:15 [INFO] (admin) - Sistema configurado com sucesso
// 2025-10-04 19:32:15 [ERROR] (admin) - Erro ao processar requisição

// Limpando logs quando necessário
logs.limpar();
```
```

## 🧪 Como Executar e Testes <a name="testes"></a>
### Cobertura de Testes
- ✅ **Testes de Singleton**: Validam que apenas uma instância é criada
- ✅ **Testes de Configuração**: Verificam getters/setters (caminhoArquivo, usuarioAtivo)
- ✅ **Testes de Registro**: Validam registro de logs com diferentes níveis
- ✅ **Testes de Persistência**: Cobrem gravação e leitura de arquivo
- ✅ **Testes de Limpeza**: Verificam função de limpar logs

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6+

### Comandos
```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Executar a aplicação principal
java -cp target/classes padroescriacao.singleton.Main

# Empacotar
mvn package
```

## 👨‍💻 Autor <a name="autor"></a>
**Gabriel Campos Lima Alves**  
Matrícula: 202176005  
Email: campos.gabriel@estudante.ufjf.br  
GitHub: [@CamposCodes](https://github.com/CamposCodes)

---

*Projeto de uso acadêmico exclusivo para a disciplina DCC078 - UFJF*