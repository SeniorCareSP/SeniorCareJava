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
    import seniorcare.crudseniorcare.domain.endereco.Endereco;
    import seniorcare.crudseniorcare.exception.EnderecoInvalidoException;
    import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
    import seniorcare.crudseniorcare.utils.Coordenadas;

    import java.io.IOException;
    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class CoordenadaService {
        private static final String API_KEY = "pk.0066fab4a7ea3ab5e322fa5194898a35";


        public Endereco   pegarPosicoes(Endereco endereco) throws IOException {

            if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty() ||
                    endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
                throw new IllegalArgumentException("Logradouro e cidade devem ser fornecidos.");
            }

            String logradouro = endereco.getLogradouro().trim().replace(" ", "+");
            String cidade = endereco.getCidade().trim().replace(" ", "+");

            String url = "https://us1.locationiq.com/v1/search.php?key=" + API_KEY + "&q=" +
                    logradouro + "," + cidade + ",Brasil&format=json";

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(new HttpGet(url))) {

                HttpEntity entity = response.getEntity();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(entity.getContent());

                if (rootNode.isArray() && rootNode.size() > 0) {
                    double latitude = rootNode.get(0).get("lat").asDouble();
                    double longitude = rootNode.get(0).get("lon").asDouble();
                    endereco.setLatidude(latitude);
                    endereco.setLongitude(longitude);
                    return endereco;
                } else {
                    throw new EnderecoInvalidoException("Endereço inválido");
                }
            }
        }

        public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
            final int R = 6371; // Raio da Terra em km
            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c;
        }

        public static Coordenadas obterCoordenadas(EnderecoListagemDto endereco) throws IOException {
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
