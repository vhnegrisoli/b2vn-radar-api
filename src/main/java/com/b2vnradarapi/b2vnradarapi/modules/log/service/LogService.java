package com.b2vnradarapi.b2vnradarapi.modules.log.service;

import com.b2vnradarapi.b2vnradarapi.modules.log.dto.LogRequest;
import com.b2vnradarapi.b2vnradarapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.b2vnradarapi.b2vnradarapi.modules.log.enums.ETipoOperacao.ALTERANDO;
import static com.b2vnradarapi.b2vnradarapi.modules.log.enums.ETipoOperacao.REMOVENDO;
import static com.b2vnradarapi.b2vnradarapi.modules.log.enums.ETipoOperacao.SALVANDO;
import static com.b2vnradarapi.b2vnradarapi.modules.log.enums.ETipoOperacao.CONSULTANDO;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Service
@SuppressWarnings("PMD.TooManyStaticImports")
public class LogService {

    private static final String SERVICO_NOME = "B2VN_RADAR_API";
    private static final String SERVICO_DESCRICAO = "Api de Radares";
    private static final List<String> URLS_SEM_LOG = List.of("/api/log", "/teste", "/api/usuarios/novo");

    @Autowired
    private UsuarioService usuarioService;

    public void gerarLogUsuario(HttpServletRequest request) throws IOException {
        if (!URLS_SEM_LOG.contains(request.getRequestURI()) && !hasSwaggerUrl(request.getRequestURI())) {
            var usuarioLogado = usuarioService.getUsuarioAutenticado();
            usuarioService.enviarLogUsuario(LogRequest
                .builder()
                .dataAcesso(LocalDateTime.now())
                .tipoOperacao(definirTipoAcesso(request.getMethod()))
                .metodo(request.getMethod())
                .urlAcessada(request.getRequestURI())
                .usuarioId(usuarioLogado.getId())
                .usuarioNome(usuarioLogado.getNome())
                .usuarioEmail(usuarioLogado.getEmail())
                .usuarioPermissao(usuarioLogado.getPermissao())
                .usuarioDescricao(usuarioLogado.getDescricao())
                .servicoNome(SERVICO_NOME)
                .servicoDescricao(SERVICO_DESCRICAO)
                .build());
        }
    }

    private boolean hasSwaggerUrl(String url) {
        return url.contains("swagger") || url.contains("error");
    }

    private String definirTipoAcesso(String metodo) {
        if (metodo.equals(GET.name())) {
            return CONSULTANDO.getOperacao();
        } else if (metodo.equals(POST.name())) {
            return SALVANDO.getOperacao();
        } else if (metodo.equals(PUT.name())) {
            return ALTERANDO.getOperacao();
        } else {
            return REMOVENDO.getOperacao();
        }
    }
}