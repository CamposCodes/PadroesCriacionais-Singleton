package padroescriacao.singleton;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            // Configurando RegistroLogs (Singleton)
            RegistroLogs logs = RegistroLogs.getInstance();
            logs.setCaminhoArquivo("logs/aplicacao.log");
            logs.setUsuarioAtivo("admin");

            // Demonstrando uso
            System.out.println("=== Demonstração do Padrão Singleton - RegistroLogs ===");
            System.out.println("Usuário Ativo: " + logs.getUsuarioAtivo());
            System.out.println("Caminho do Arquivo: " + logs.getCaminhoArquivo());
            System.out.println();

            // Registrando logs
            logs.registrar("Aplicação iniciada");
            logs.registrar("INFO", "Sistema de logs configurado");
            logs.registrar("ERROR", "Exemplo de log de erro");
            logs.registrar("INFO", "Processamento concluído");

            // Exibindo logs em memória
            System.out.println("=== Logs Registrados ===");
            for (String log : logs.getLogs()) {
                System.out.println(log);
            }
            System.out.println();

            // Testando que é realmente Singleton
            RegistroLogs logs2 = RegistroLogs.getInstance();

            System.out.println("=== Verificação de Singleton ===");
            System.out.println("RegistroLogs é o mesmo objeto? " + (logs == logs2));
            System.out.println("Quantidade de logs na segunda instância: " + logs2.getLogs().size());

            // Adicionando mais um log pela segunda referência
            logs2.registrar("INFO", "Log adicionado pela segunda referência");
            System.out.println("Logs agora têm: " + logs.getLogs().size() + " entradas");

            // Demonstrando validação de exceptions
            System.out.println("\n=== Testando Validações ===");
            try {
                logs.registrar(null, "Teste");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Exception capturada: " + e.getMessage());
            }

            try {
                logs.registrar("INFO", null);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Exception capturada: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Erro ao gerenciar logs: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
