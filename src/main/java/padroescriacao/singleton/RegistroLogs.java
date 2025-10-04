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
    private List<String> logs;

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

    public List<String> getLogs() {
        if (logs == null) {
            logs = new ArrayList<>();
        }
        return logs;
    }

    public void registrar(String nivel, String mensagem) throws IOException {
        if (nivel == null || nivel.isBlank()) {
            throw new IllegalArgumentException("Nível do log não pode ser nulo ou vazio");
        }
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Mensagem do log não pode ser nula ou vazia");
        }

        if (logs == null) {
            logs = new ArrayList<>();
        }

        String linha = formatLog(nivel, mensagem);
        logs.add(linha);

        if (caminhoArquivo != null && !caminhoArquivo.isBlank()) {
            gravarEmArquivo(linha);
        }
    }

    public void registrar(String mensagem) throws IOException {
        registrar("INFO", mensagem);
    }

    public void limpar() throws IOException {
        if (logs != null) {
            logs.clear();
        }
        if (caminhoArquivo != null && !caminhoArquivo.isBlank()) {
            Path path = Paths.get(caminhoArquivo);
            if (!Files.deleteIfExists(path)) {
                throw new IOException("Arquivo de log não existe: " + caminhoArquivo);
            }
        }
    }

    private String formatLog(String nivel, String mensagem) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String user = (usuarioAtivo == null || usuarioAtivo.isBlank()) ? "-" : usuarioAtivo;
        return String.format("%s [%s] (%s) - %s", ts, nivel, user, mensagem);
    }

    private void gravarEmArquivo(String linha) throws IOException {
        Path path = Paths.get(caminhoArquivo);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }
        Files.write(path, (linha + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
