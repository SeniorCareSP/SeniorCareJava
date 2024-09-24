package seniorcare.crudseniorcare.utils;

import seniorcare.crudseniorcare.exception.FilaCheiaException;

import java.util.ArrayList;
import java.util.List;

public class FilaCircularObj<T> {
    int tamanho, inicio, fim;
    T[] fila;

    // Construtor - Recebe a capacidade da fila (tamanho total do vetor)
    public FilaCircularObj(int capacidade) {
        // Cria o vetor para armazenar a fila e inicializa o tamanho
        this.fila = (T[]) new Object[capacidade];
        tamanho = 0;
        inicio = 0;
        fim = 0;
    }

    public boolean isEmpty() {
        if (tamanho == 0){
            return true;
        }
        return false;
    }

    // Método isFull() - Retorna true se a fila está cheia e false caso contrário
    public boolean isFull() {
        if (fila.length == tamanho){
            return true;
        }
        return false;
    }

    // Método insert - Recebe informação a ser inserida na fila
    public void insert(T info) {
        if (isFull()){
            throw new FilaCheiaException("A fila está cheia. Não é possível adicionar a notificação.");
        }
        fila[fim] = info;
        fim = (fim + 1) % fila.length;
        tamanho++;
    }

    // Método peek() - Retorna o primeiro da fila, sem remover
    public T peek() {
        return fila[inicio];
    }

    // Método poll() - Retorna o primeiro da fila, removendo-o
    public T poll() {
        if (!isEmpty()){
            T var = fila[inicio];
            fila[inicio] = null;
            inicio = (inicio + 1) % fila.length;
            tamanho--;
            return var;
        }
        return null;
    }

    // Método exibe() - exibe os elementos da fila
    public void exibe() {
        if (isEmpty()){
            System.out.println("Fila vazia!");
        }


        int contador = 0;
        for (int i = inicio; contador < tamanho;) {
            System.out.println(fila[i]);
            i = (i + 1) % fila.length;
            contador++;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getInicio() {
        return inicio;
    }

    public int getFim() {
        return fim;
    }

    // Cria um vetor e percorre a fila adicionando os elementos no vetor (
    // Retorna o vetor criado e não a fila
    // Esse método é equivalente ao exibe, mas em vez de exibir os elementos da fila na console,
    // copia os elementos da fila para um vetor, na ordem em que seriam exibidos
    public T[] getFila(){
        T[] vetor = (T[]) new Object[tamanho];

        for (int i = inicio, contador = 0; contador < tamanho; contador++) {
            vetor[contador] = fila[i];
            i = (i + 1) % fila.length;
        }

        return vetor;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            list.add(fila[(inicio + i) % fila.length]);
        }
        return list;
    }
}

