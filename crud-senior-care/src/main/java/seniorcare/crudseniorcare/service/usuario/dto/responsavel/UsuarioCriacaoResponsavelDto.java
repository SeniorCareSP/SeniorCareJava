        package seniorcare.crudseniorcare.service.usuario.dto.responsavel;

        import jakarta.persistence.OneToMany;
        import lombok.Data;
        import seniorcare.crudseniorcare.domain.idoso.Idoso;
        import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;

        import java.util.List;
        @Data
        public class UsuarioCriacaoResponsavelDto extends UsuarioCriacaoDto {

            private double precoHora;
            private List<Idoso> idosos;

        }
