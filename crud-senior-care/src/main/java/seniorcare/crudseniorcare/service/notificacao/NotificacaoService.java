package seniorcare.crudseniorcare.service.notificacao;

import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.notificacao.Notificacao;
import seniorcare.crudseniorcare.exception.FilaCheiaException;
import seniorcare.crudseniorcare.utils.FilaCircularObj;

@Service
public class NotificacaoService {

    private FilaCircularObj<Notificacao> filaNotificacoes;

    public NotificacaoService() {
        this.filaNotificacoes = new FilaCircularObj<>(10); // Tamanho da fila ajustável
    }

    public void adicionarNotificacao(String mensagem, String destinatario) {
        Notificacao notificacao = new Notificacao(mensagem, destinatario);
        filaNotificacoes.insert(notificacao);
    }

    public Notificacao processarNotificacao() {
        return filaNotificacoes.poll();
    }

    public void exibirFila() {
        filaNotificacoes.exibe();
    }

    public boolean isFilaVazia() {
        return filaNotificacoes.isEmpty();
    }
}
