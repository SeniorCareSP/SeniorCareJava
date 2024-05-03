package seniorcare.crudseniorcare.utils;

public class ListaObj <T> {
    private T[] vetor;

    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length){
            throw  new IllegalStateException();
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == elementoBuscado){
                return i;
            }
        }
        return  - 1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice > nroElem  || indice < 0){
            return false;
        }
        else {
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor [i + 1];
            }
            nroElem--;
        }
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getNroElem() {

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

    public void limpa() {
        vetor =  (T[]) new Object[vetor.length];
    }

    public void exibe() {
        for (int i = 0; i < nroElem ; i++) {
            System.out.println(vetor[i]);
        }
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


    public T[] getVetor() {
        return vetor;
    }


}