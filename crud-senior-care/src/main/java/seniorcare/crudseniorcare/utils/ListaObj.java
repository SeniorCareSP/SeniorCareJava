package seniorcare.crudseniorcare.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ListaObj<T> {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            throw new IllegalStateException("Capacidade máxima excedida");
        } else {
            vetor[nroElem++] = elemento;
        }
    }


    public void limpa() {
        Arrays.fill(vetor, null);
        nroElem = 0;
    }

    public int tamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {

        if (indice <= vetor.length) {
            for (int i = 0; i < vetor.length; i++) {
                if (i == indice){
                    return vetor[i];
                }
            }
        }
        return null;
    }

    public void setPeloIndice(int indice, T valor) {
        // Verifica se o índice está dentro dos limites válidos
        if (indice < 0 || indice >= nroElem) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }

        // Se o vetor estiver cheio, redimensiona para acomodar mais elementos
        if (nroElem == vetor.length) {
            redimensionar();
        }

        // Move os elementos para abrir espaço para o novo valor
        for (int i = nroElem - 1; i >= indice; i--) {
            vetor[i + 1] = vetor[i];
        }

        // Insere o novo valor no índice especificado
        vetor[indice] = valor;
        nroElem++;
    }

    private void redimensionar() {
        // Cria um novo vetor com o dobro do tamanho do vetor atual
        T[] novoVetor = (T[]) new Object[vetor.length * 2];

        // Copia os elementos do vetor atual para o novo vetor
        for (int i = 0; i < vetor.length; i++) {
            novoVetor[i] = vetor[i];
        }

        // Atualiza a referência do vetor para o novo vetor
        vetor = novoVetor;
    }


    public T obtem(int indice) {
        if (indice < 0 || indice >= nroElem) {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }
        return vetor[indice];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nroElem; i++) {
            sb.append(vetor[i]);
            if (i < nroElem - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}





