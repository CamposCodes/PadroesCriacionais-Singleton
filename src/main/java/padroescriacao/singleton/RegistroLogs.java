package padroescriacao.singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroLogs {

    private RegistroLogs() {};
    private static RegistroLogs instance = new RegistroLogs();
    public static RegistroLogs getInstance() {
        return instance;
    }

    private String caminhoArquivo;
    private String usuarioAtivo;
    private List<String> logs = new ArrayList<>();

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(String usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }

    public synchronized void registrar(String nivel, String mensagem) {
        String linha = formatLog(nivel, mensagem);
        logs.add(linha);

        if (caminhoArquivo != null && !caminhoArquivo.isBlank()) {
            try {
                Path path = Paths.get(caminhoArquivo);
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }
                Files.write(path, (linha + System.lineSeparator()).getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Erro ao gravar log: " + e.getMessage());
            }
        }
    }

    public synchronized void registrar(String mensagem) {
        registrar("INFO", mensagem);
    }

    private String formatLog(String nivel, String mensagem) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String user = (usuarioAtivo == null || usuarioAtivo.isBlank()) ? "-" : usuarioAtivo;
        return String.format("%s [%s] (%s) - %s", ts, nivel, user, mensagem);
    }

    public synchronized List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public synchronized void limpar() {
        logs.clear();
        if (caminhoArquivo != null && !caminhoArquivo.isBlank()) {
            try {
                Files.deleteIfExists(Paths.get(caminhoArquivo));
            } catch (IOException e) {
                System.err.println("Erro ao limpar arquivo de logs: " + e.getMessage());
            }
        }
    }
}
