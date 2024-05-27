package seniorcare.crudseniorcare.utils;

import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class GravaArquivoCsv {
    public static File gravarArquivo(List <UsuarioListagemDto> lista , String nomeArq) throws IOException {

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

// Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

// Bloco try-catch para gravar o arquivo
        try{
            for (int i = 0; i < lista.size(); i++) {
                UsuarioListagemDto user = lista.get(i);
                saida.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        user.getIdUsuario() != null ? user.getIdUsuario().toString() : user.getIdUsuario(),
                        user.getNome(),
                        user.getEmail(),
                        user.getTelefone(),
                        user.getCpf(),
                        user.getSexoBiologico(),
                        user.getDtNascimento(),
                        user.getApresentacao(),
                        user.getDtCadastro(),
                        user.getAgenda(),
                        user.getIdiomas(),
                        user.getEndereco());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

        return new File(nomeArq);
    }


    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

// Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

// Bloco try-catch para ler o arquivo
        try {
//Leia e formate a saída no console aqui:

            // Cabeçalho
            System.out.printf("%-36s %-20s %-30s %-15s %-15s %-12s %-15s %-20s %-20s %-20s %-20s %-20s\n",
                    "idUsuario", "nome", "email", "telefone", "cpf", "sexoBiologico",
                    "dtNascimento", "apresentacao", "dtCadastro", "agendas", "idiomas",
                    "enderecos");
            while (entrada.hasNext()) {

                //Corpo
                Integer idUsuario = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String telefone = entrada.next();
                String cpf = entrada.next();
                String sexoBiologico = entrada.next();
                String dtNascimento = entrada.next();
                String apresentacao = entrada.next();
                String dtCadastro = entrada.next();
                String agendas = entrada.next();
                String idiomas = entrada.next();
                String enderecos = entrada.next();

                System.out.printf("%-36s %-20s %-30s %-15s %-15s %-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                        idUsuario.toString(), nome, email, telefone, cpf, sexoBiologico,
                        dtNascimento, apresentacao, dtCadastro, agendas, idiomas,
                        enderecos);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
