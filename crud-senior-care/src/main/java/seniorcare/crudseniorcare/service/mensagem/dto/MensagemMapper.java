package seniorcare.crudseniorcare.service.mensagem.dto;


import seniorcare.crudseniorcare.domain.mensagem.Mensagem;
import seniorcare.crudseniorcare.service.mensagem.dto.MensagemCriacaoDto;
import seniorcare.crudseniorcare.service.mensagem.dto.MensagemListagemDto;

import java.time.LocalDateTime;

public class MensagemMapper {

    public Mensagem toMensagem(MensagemCriacaoDto dto) {
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(dto.getMensagem());
        mensagem.setDt_envio(LocalDateTime.now()); // Define a data e hora atual como a data de envio
        return mensagem;
    }

    public MensagemListagemDto toMensagemListagemDto(Mensagem mensagem) {
        MensagemListagemDto dto = new MensagemListagemDto();
        dto.setIdMensagem(mensagem.getId_mensagem());
        dto.setMensagem(mensagem.getMensagem());
        dto.setDtEnvio(mensagem.getDt_envio());
        return dto;
    }
}
