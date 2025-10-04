package padroescriacao.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroLogsTest {

    @Test
    public void deveRetornarCaminhoArquivo() {
        RegistroLogs.getInstance().setCaminhoArquivo("logs/aplicacao.log");
        assertEquals("logs/aplicacao.log", RegistroLogs.getInstance().getCaminhoArquivo());
    }

    @Test
    public void deveRetornarUsuarioAtivo() {
        RegistroLogs.getInstance().setUsuarioAtivo("Usuario Teste");
        assertEquals("Usuario Teste", RegistroLogs.getInstance().getUsuarioAtivo());
    }

    @Test
    public void deveLancarExcecaoQuandoNivelForNulo() {
        RegistroLogs logs = RegistroLogs.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            logs.registrar(null, "Mensagem teste");
        });
    }

    @Test
    public void deveLancarExcecaoQuandoMensagemForNula() {
        RegistroLogs logs = RegistroLogs.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            logs.registrar("INFO", null);
        });
    }

    @Test
    public void deveLancarExcecaoQuandoNivelForVazio() {
        RegistroLogs logs = RegistroLogs.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            logs.registrar("", "Mensagem teste");
        });
    }

    @Test
    public void deveLancarExcecaoQuandoMensagemForVazia() {
        RegistroLogs logs = RegistroLogs.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            logs.registrar("INFO", "");
        });
    }
}
