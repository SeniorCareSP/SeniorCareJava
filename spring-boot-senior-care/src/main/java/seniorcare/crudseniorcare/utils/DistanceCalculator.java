package seniorcare.crudseniorcare.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import seniorcare.crudseniorcare.domain.endereco.Endereco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistanceCalculator {
    private static final String API_KEY = "AIzaSyBZzaNRfUyAzEqHW1qhduC6iqCiTgf_Qeo";

    public static void main(String[] args) throws IOException {

        String enderecoDestino = "Endereço de Destino";

        // Criar uma lista de endereços de origem
        List<Endereco> enderecosOrigem = new ArrayList<>();
        // Adicionar endereços de origem à lista

        List<String> enderecosOrigemString = new ArrayList<>();

        for (Endereco endereco : enderecosOrigem) {
            String enderecoCompleto = endereco.getLogradouro() + ", " +
                    endereco.getNumero() + " - " +
                    endereco.getBairro() + ", " +
                    endereco.getCidade() + " - " +
                    endereco.getCep();
            enderecosOrigemString.add(enderecoCompleto);
        }

        List<Double> distancias = (List<Double>) calcularDistancias(enderecosOrigemString, enderecoDestino);
        for (int i = 0; i < distancias.size(); i++) {
            System.out.println("Distância de " + enderecosOrigem.get(i).getLogradouro() +
                    " até " + enderecoDestino + ": " + distancias.get(i) + " km");
        }
    }



    public static ListaObj<Double> calcularDistancias(List<String> origens, String destino) throws IOException {
        ListaObj<Double> distancias = new ListaObj<>(origens.size());
        Coordenadas coordenadasDestino = obterCoordenadas(destino);

        for (String origem : origens) {
            Coordenadas coordenadasOrigem = obterCoordenadas(origem);
            double distancia = calcularDistancia(coordenadasOrigem, coordenadasDestino);
            distancias.adiciona(distancia);
        }

        return distancias;
    }

    public static Coordenadas obterCoordenadas(String endereco) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/geocode/json" +
                "?address=" + endereco +
                "&key=" + API_KEY;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(url))) {

            HttpEntity entity = response.getEntity();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(entity.getContent());

            double latitude = rootNode.get("results").get(0).get("geometry").get("location").get("lat").asDouble();
            double longitude = rootNode.get("results").get(0).get("geometry").get("location").get("lng").asDouble();

            return new Coordenadas(latitude, longitude);
        }
    }

    public static double calcularDistancia(Coordenadas origem, Coordenadas destino) {
        double latOrigem = Math.toRadians(origem.getLatitude());
        double lonOrigem = Math.toRadians(origem.getLongitude());
        double latDestino = Math.toRadians(destino.getLatitude());
        double lonDestino = Math.toRadians(destino.getLongitude());

        double deltaLat = latDestino - latOrigem;
        double deltaLon = lonDestino - lonOrigem;

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(latOrigem) * Math.cos(latDestino) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double raioTerra = 6371; // em quilômetros
        return raioTerra * c;
    }
}
