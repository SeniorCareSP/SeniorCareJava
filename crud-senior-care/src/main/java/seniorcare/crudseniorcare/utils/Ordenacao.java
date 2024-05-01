package seniorcare.crudseniorcare.utils;

import seniorcare.crudseniorcare.service.usuario.dto.UsuarioListagemDto;

public class Ordenacao {
    static public void quickSort(ListaObj<UsuarioListagemDto> v, int indInicio, int indFim){
        int i = indInicio;
        int j = indFim;

        String pivo = v.getElemento((indInicio + indFim) / 2).getNome();

        while (i <= j){

            while (v.getElemento(i).getNome().compareTo(pivo) < 0){
                i++;
            }


            while (v.getElemento(j).getNome().compareTo(pivo) > 0){
                j--;
            }

            // Trocar os elementos, se necessário, e mover os índices i e j
            if (i <= j){
                UsuarioListagemDto aux = v.getElemento(i);
                v.setPeloIndice(i, v.getElemento(j));
                v.setPeloIndice(j, aux);
                i++;
                j--;
            }
        }
        // Chamadas recursivas para ordenar as sub-listas
        if (indInicio < j){
            quickSort(v, indInicio, j);
        }

        if (i < indFim){
            quickSort(v, i, indFim);
        }
    }

    static String pesquisaBinaria(String[] v, String valor) {
        int indInf = 0;
        int indSup = v.length - 1;

        while (indInf <= indSup) {
            int meio = (indInf + indSup) / 2;
            int comparacao = valor.compareTo(v[meio]);

            if (comparacao == 0) {
                System.out.println("Nome encontrado!");
                return v[meio];
            } else if (comparacao < 0) {
                indSup = meio - 1;
            } else {
                indInf = meio + 1;
            }
        }
        System.out.println("Nome não encontrado");
        return null;
    }


}
