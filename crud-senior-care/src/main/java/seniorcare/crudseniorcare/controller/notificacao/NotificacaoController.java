package seniorcare.crudseniorcare.controller.notificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.notificacao.Notificacao;
import seniorcare.crudseniorcare.exception.FilaCheiaException;
import seniorcare.crudseniorcare.service.notificacao.NotificacaoService;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @PostMapping("/notificacoes")
    public ResponseEntity<String> adicionarNotificacao(@RequestBody Notificacao notificacao) {
        try {
            notificacaoService.adicionarNotificacao(notificacao.getMensagem(), notificacao.getDestinatario());
            return ResponseEntity.ok("Notificação adicionada à fila.");
        } catch (FilaCheiaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/notificacoes/processar")
    public ResponseEntity<String> processarNotificacao() {
        if (notificacaoService.isFilaVazia()) {
            return ResponseEntity.ok("Não há notificações para processar.");
        }
        Notificacao notificacao = notificacaoService.processarNotificacao();
        // Aqui você pode adicionar lógica para enviar a notificação, por exemplo, por e-mail ou SMS.
        return ResponseEntity.ok("Notificação processada: " + notificacao);
    }

    @PostMapping("/notificacoes/exibir")
    public ResponseEntity<String> exibirFila() {
        if (notificacaoService.isFilaVazia()) {
            return ResponseEntity.ok("Fila vazia!");
        }
        notificacaoService.exibirFila();
        return ResponseEntity.ok("Fila exibida no console.");
    }

    @ExceptionHandler(FilaCheiaException.class)
    public ResponseEntity<String> handleFilaCheiaException(FilaCheiaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
