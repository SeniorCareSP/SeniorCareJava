package seniorcare.crudseniorcare.service.endereco.dto;

import seniorcare.crudseniorcare.domain.endereco.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoCriacaoDto dto) {
        if (dto == null) return null;
        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setComplemento(dto.getComplemento());
        endereco.setNumero(dto.getNumero());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setUsuario(dto.getUsuario());
        return endereco;
    }

    public static EnderecoListagemDto toEnderecoListagemDto(Endereco endereco) {
        if (endereco == null) return null;
        EnderecoListagemDto dto = new EnderecoListagemDto();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setComplemento(endereco.getComplemento());
        dto.setNumero(endereco.getNumero());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        return dto;
    }

    public static List<Endereco> toEnderecos(List<EnderecoCriacaoDto> dtos) {
        return dtos.stream().map(EnderecoMapper::toEndereco).collect(Collectors.toList());
    }

    public static List<EnderecoListagemDto> toEnderecosListagemDto(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoMapper::toEnderecoListagemDto)
                .collect(Collectors.toList());
    }
}

