package seniorcare.crudseniorcare.controller.notificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import seniorcare.crudseniorcare.domain.notificacao.Notificacao;
import seniorcare.crudseniorcare.service.notificacao.NotificacaoService;
import seniorcare.crudseniorcare.service.notificacao.dto.NotificacaoCriacaoDto;
import seniorcare.crudseniorcare.service.notificacao.dto.NotificacaoListagemDto;
import seniorcare.crudseniorcare.service.notificacao.dto.NotificacaoMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {
    private final NotificacaoService service;

    @GetMapping
    public ResponseEntity<List<NotificacaoListagemDto>> listar() {
        List<Notificacao> notificacoes = service.list();
        if (notificacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NotificacaoListagemDto> dto = NotificacaoMapper.toDtoList(notificacoes);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoListagemDto> porId(@PathVariable UUID id) {
        Notificacao notificacao = service.byId(id);
        NotificacaoListagemDto dto = NotificacaoMapper.toNotificacaoDto(notificacao);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<NotificacaoListagemDto> criar(@RequestBody NotificacaoCriacaoDto dto) {
        Notificacao salvo = service.criar(dto);
        NotificacaoListagemDto responseDto = NotificacaoMapper.toNotificacaoDto(salvo);
        URI uri = URI.create("/notificacoes/" + salvo.getId());
        return ResponseEntity.created(uri).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoListagemDto> update(@PathVariable UUID id, @RequestBody Notificacao notificacao) {
        Notificacao atualizado = service.update(id, notificacao);
        NotificacaoListagemDto dto = NotificacaoMapper.toNotificacaoDto(atualizado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/processar")
    public ResponseEntity<NotificacaoListagemDto> processarNotificacao() {
        Notificacao notificacao = service.processarNotificacao();
        if (notificacao == null) {
            return ResponseEntity.noContent().build();
        }
        NotificacaoListagemDto dto = NotificacaoMapper.toNotificacaoDto(notificacao);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/exibir")
    public ResponseEntity<List<NotificacaoListagemDto>> exibirFila() {
        List<Notificacao> notificacoes = service.exibirFila();
        if (notificacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NotificacaoListagemDto> dto = NotificacaoMapper.toDtoList(notificacoes);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/vazia")
    public ResponseEntity<Boolean> isFilaVazia() {
        boolean isVazia = service.isFilaVazia();
        return ResponseEntity.ok(isVazia);
    }
}
