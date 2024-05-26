package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.ajuda.AjudaService;
import seniorcare.crudseniorcare.service.caracteristica.CaracteristicaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.idioma.IdiomaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuidadorService {
    private final PasswordEncoder passwordEncoder;
    private final CuidadorRepository repository;
    private final UsuarioRepository usuarioRepository;


    private final EnderecoService enderecoService;
    private final AgendaService agendaService;
    private final IdiomaService idiomaService;
    private final AjudaService ajudaService;
    private final CaracteristicaService caracteristicaService;

    public Cuidador criar(Cuidador novoCuidador) {

        String senhaCriptografada = passwordEncoder.encode(novoCuidador.getSenha());
        novoCuidador.setSenha(senhaCriptografada);

        if (emailJaExiste(novoCuidador.getEmail())){
            throw new ConflitoException("Email Responsavel");
        }

        Cuidador usuarioSalvo = repository.save(novoCuidador);
        usuarioSalvo.getEndereco().setUsuario(usuarioSalvo);
        enderecoService.create(usuarioSalvo.getEndereco());


        usuarioSalvo.getAgenda().setUsuario(usuarioSalvo);
        agendaService.create(usuarioSalvo.getAgenda());

        for (Idioma idioma : usuarioSalvo.getIdiomas()){
            idioma.setUsuario(usuarioSalvo);
            idiomaService.create(idioma);
        }
        for (Ajuda ajuda : usuarioSalvo.getAjudas()){
            ajuda.setCuidador(usuarioSalvo);
            ajudaService.create(ajuda);
        }
        for (Caracteristica caracteristica : usuarioSalvo.getCaracteristicas()){
            caracteristica.setCuidador(usuarioSalvo);
            caracteristicaService.create(caracteristica);
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
        cuidadorUpd.setExperiencia(cuidadorUpd.getExperiencia());
        cuidadorUpd.setFaixaEtaria(cuidador.getFaixaEtaria());

        return cuidadorUpd;
    }
}
