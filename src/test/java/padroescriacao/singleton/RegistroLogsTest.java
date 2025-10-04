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

}
