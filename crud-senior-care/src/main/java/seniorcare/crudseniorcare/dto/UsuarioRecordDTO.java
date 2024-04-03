package seniorcare.crudseniorcare.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioRecordDTO(String nome, String email, String senha, String telefone, String sexoBiologico, LocalDate dtNascimento, String apresentacao) {
}
