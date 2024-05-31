package seniorcare.crudseniorcare.service.notificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.notificacao.Notificacao;
import seniorcare.crudseniorcare.domain.notificacao.repository.NotificacaoRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.notificacao.dto.NotificacaoCriacaoDto;
import seniorcare.crudseniorcare.service.notificacao.dto.NotificacaoMapper;
import seniorcare.crudseniorcare.utils.FilaCircularObj;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final FilaCircularObj<Notificacao> filaNotificacoes = new FilaCircularObj<>(10);

    public List<Notificacao> list() {
        return repository.findAll();
    }

    public Notificacao byId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Notificação")
        );
    }

    @Transactional
    public Notificacao criar(NotificacaoCriacaoDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getUsuarioEmail()).orElseThrow(
                () -> new NaoEncontradoException("Usuário não encontrado com o e-mail: " + dto.getUsuarioEmail())
        );

        Notificacao notificacao = NotificacaoMapper.toNotificacao(dto);
        notificacao.setUsuarioId(usuario.getIdUsuario());

        Notificacao salva = repository.save(notificacao);
        filaNotificacoes.insert(salva);  // Adiciona a notificação à fila
        return salva;
    }

    public void delete(Integer id) {
        Notificacao notificacao = repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Notificação")
        );
        repository.delete(notificacao);
    }

    public Notificacao update(Integer id, Notificacao notificacao) {
        Notificacao existente = repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Notificação")
        );
            notificacao.setId(id);
            return repository.save(notificacao);
    }

    public Notificacao processarNotificacao() {
        return filaNotificacoes.poll();
    }

    public List<Notificacao> exibirFila() {
        return filaNotificacoes.toList();
    }

    public boolean isFilaVazia() {
        return filaNotificacoes.isEmpty();
    }
}
