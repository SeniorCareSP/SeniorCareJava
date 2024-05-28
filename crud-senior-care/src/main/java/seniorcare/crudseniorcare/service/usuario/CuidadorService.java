package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;

import java.util.List;
import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class CuidadorService {
        private final CuidadorRepository repository;
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;
        private final EnderecoService enderecoService;

        public Cuidador criar(Cuidador novoCuidador) {
            String senhaCriptografada = passwordEncoder.encode(novoCuidador.getSenha());
            novoCuidador.setSenha(senhaCriptografada);

            // Aqui você precisa verificar se novoCuidador.getEndereco() é nulo antes de chamar enderecoService.create()
            if (novoCuidador.getEndereco() != null) {
                // Chama o método create do enderecoService para salvar o novo endereço
                Endereco enderecoCriado = enderecoService.create(novoCuidador.getEndereco());
                // Define o endereço criado no novoCuidador
                novoCuidador.setEndereco(enderecoCriado);
            }

            if (emailJaExiste(novoCuidador.getEmail())) {
                throw new ConflitoException("Email Cuidador");
            }

            return repository.save(novoCuidador);
        }



    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Cuidador> list(){ return repository.findAll();}

    public Cuidador byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cuidador")
        );
    }

    public void delete(Integer id){
        Optional<Cuidador> cuidador = repository.findById(id);
        if (cuidador.isEmpty()){
            throw new NaoEncontradoException("Cuidador");
        }
        repository.delete(cuidador.get());
    }

        public Cuidador update(Integer id, Cuidador cuidador){
            Optional<Cuidador> cuidadadorOpt = repository.findById(id);

            if (cuidadadorOpt.isEmpty()) {
                throw new NaoEncontradoException("Cuidador");
            }
            Cuidador cuidadorUpd = cuidadadorOpt.get();

            cuidadorUpd.setCpf(cuidador.getCpf());
            cuidadorUpd.setEmail(cuidador.getEmail());
            cuidadorUpd.setApresentacao(cuidador.getApresentacao());
            cuidadorUpd.setPrecoHora(cuidador.getPrecoHora());
            cuidadorUpd.setNome(cuidador.getNome());
            cuidadorUpd.setSenha(passwordEncoder.encode(cuidador.getSenha()));
            cuidadorUpd.setTelefone(cuidador.getTelefone());
            cuidadorUpd.setSexoBiologico(cuidador.getSexoBiologico());
            cuidadorUpd.setDtNascimento(cuidador.getDtNascimento());
            cuidadorUpd.setExperiencia(cuidador.getExperiencia());
            cuidadorUpd.setFaixaEtaria(cuidador.getFaixaEtaria());

            return cuidadorUpd;
        }

    }
