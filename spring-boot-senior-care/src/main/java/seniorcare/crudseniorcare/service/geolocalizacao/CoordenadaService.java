package seniorcare.crudseniorcare.service.geolocalizacao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;
import seniorcare.crudseniorcare.utils.Coordenadas;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordenadaService {
    private static final String API_KEY = "39f3201c61d00d0e95e241fde3fde742";
    private final ResponsavelService responsavelService;
    private final CuidadorService cuidadorService;

    // Método para calcular a distância entre duas coordenadas geográficas
    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Raio da Terra em km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<UsuarioListagemResponsavelDto> converterEnderecoParaCoordenadasResponsavel(Cuidador cuidador2) throws IOException {
        List<UsuarioListagemResponsavelDto> responsaveis = ResponsavelMapper.toUsuarioListagemResponsavelDtoList(responsavelService.list());
        UsuarioListagemCuidadorDto cuidador = CuidadorMapper.toUsuarioListagemCuidadorDto(cuidador2);
        Coordenadas coordenadasCuidador = obterCoordenadas(cuidador.getEndereco());
        cuidador.setCoordernada(coordenadasCuidador);

        for (UsuarioListagemResponsavelDto responsavel : responsaveis) {
            EnderecoListagemDto enderecoResponsavel = responsavel.getEndereco();
            try {
                Coordenadas coordenadasResponsavel = obterCoordenadas(enderecoResponsavel);
                responsavel.setCoordernada(coordenadasResponsavel);

                double distancia = calcularDistancia(
                        coordenadasCuidador.getLatitude(),
                        coordenadasCuidador.getLongitude(),
                        coordenadasResponsavel.getLatitude(),
                        coordenadasResponsavel.getLongitude()
                );
                responsavel.setDistancia(distancia);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responsaveis;
    }

    public List<UsuarioListagemCuidadorDto> converterEnderecoParaCoordenadasCuidador(Responsavel responsavel2) throws IOException {
        List<UsuarioListagemCuidadorDto> cuidadores = CuidadorMapper.toUsuarioListagemCuidadorDtoList(cuidadorService.list());
        UsuarioListagemResponsavelDto responsavel = ResponsavelMapper.toUsuarioListagemResponsavelDto(responsavel2);
        Coordenadas coordenadasResponsavel = obterCoordenadas(responsavel.getEndereco());
        responsavel.setCoordernada(coordenadasResponsavel);

        for (UsuarioListagemCuidadorDto cuidador : cuidadores) {
            EnderecoListagemDto enderecoCuidador = cuidador.getEndereco();
            try {
                Coordenadas coordenadasCuidador = obterCoordenadas(enderecoCuidador);
                cuidador.setCoordernada(coordenadasCuidador);

                double distancia = calcularDistancia(
                        coordenadasResponsavel.getLatitude(),
                        coordenadasResponsavel.getLongitude(),
                        coordenadasCuidador.getLatitude(),
                        coordenadasCuidador.getLongitude()
                );
                cuidador.setDistancia(distancia);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cuidadores;
    }

    public static Coordenadas obterCoordenadas(EnderecoListagemDto endereco) throws IOException {
        // Validação do endereço
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty() ||
                endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("Logradouro e cidade devem ser fornecidos.");
        }

        // Formatação do endereço
        String logradouro = endereco.getLogradouro().trim().replace(" ", "+");
        String cidade = endereco.getCidade().trim().replace(" ", "+");

        String url = "https://api.positionstack.com/v1/forward?access_key=" + API_KEY + "&query=" +
                logradouro + "," + cidade + ",Brasil";

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(url))) {

            HttpEntity entity = response.getEntity();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(entity.getContent());

            if (rootNode.has("data") && rootNode.get("data").isArray() && !rootNode.get("data").isEmpty()) {
                double latitude = rootNode.get("data").get(0).get("latitude").asDouble();
                double longitude = rootNode.get("data").get(0).get("longitude").asDouble();
                return new Coordenadas(latitude, longitude);
            } else {
                throw new IOException("Nenhum resultado encontrado para o endereço: " + endereco);
            }
        }
    }

}
