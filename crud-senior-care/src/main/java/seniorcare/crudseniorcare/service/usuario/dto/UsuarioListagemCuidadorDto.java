package seniorcare.crudseniorcare.service.usuario.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import seniorcare.crudseniorcare.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UsuarioListagemCuidadorDto {


    private UUID idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String sexoBiologico;
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    @OneToMany(mappedBy = "usuario")
    private List<Agenda> agendas;
    @OneToMany(mappedBy = "usuario")
    private List<Idioma> idiomas;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
    private String experiencia;
    private String faixaEtaria;
    private int qtdIdoso;
    private double precoHora;
    @ManyToMany
    @JoinTable(name = "cuidador_caracteristica", joinColumns = @JoinColumn(name = "cuidador_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas;
    @ManyToMany
    @JoinTable(name = "cuidador_ajuda",
            joinColumns = @JoinColumn(name = "cuidador_id"),
            inverseJoinColumns = @JoinColumn(name = "ajuda_id"))
    private List<Ajuda> ajudas;


}
