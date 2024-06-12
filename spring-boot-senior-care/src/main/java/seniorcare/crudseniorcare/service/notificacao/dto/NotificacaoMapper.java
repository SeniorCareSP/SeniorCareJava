package seniorcare.crudseniorcare.service.notificacao.dto;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.domain.notificacao.Notificacao;

import java.util.List;

@Mapper
public class NotificacaoMapper {
    public static Notificacao toNotificacao(NotificacaoCriacaoDto dto) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(dto.getTitulo());
        notificacao.setMensagem(dto.getMensagem());
        notificacao.setDataCriacao(dto.getDataCriacao());
        return notificacao;
    }

    public static NotificacaoListagemDto toNotificacaoDto(Notificacao notificacao) {
        NotificacaoListagemDto dto = new NotificacaoListagemDto();
        dto.setId(notificacao.getId());
        dto.setTitulo(notificacao.getTitulo());
        dto.setMensagem(notificacao.getMensagem());
        dto.setDataCriacao(notificacao.getDataCriacao());
        dto.setUsuarioId(notificacao.getUsuarioId());
        return dto;
    }

    public static List<NotificacaoListagemDto> toDtoList(List<Notificacao> notificacoes) {
        return notificacoes.stream().map(NotificacaoMapper::toNotificacaoDto).toList();
    }
}
