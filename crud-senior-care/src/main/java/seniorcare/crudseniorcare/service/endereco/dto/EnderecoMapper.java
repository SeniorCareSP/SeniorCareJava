package seniorcare.crudseniorcare.service.endereco.dto;

import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;

import java.util.UUID;

public class EnderecoMapper {

    public Endereco toEndereco(EnderecoCriacaoDto dto) {
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setComplemento(dto.getComplemento());
        endereco.setNumero(dto.getNumero());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        return endereco;
    }

    public EnderecoListagemDto toEnderecoListagemDto(Endereco endereco) {
        EnderecoListagemDto dto = new EnderecoListagemDto();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setRua(endereco.getRua());
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setComplemento(endereco.getComplemento());
        dto.setNumero(endereco.getNumero());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        return dto;
    }
}

