package com.company;

/**
 * Klasa abstrakcyjna reprezentujaca graf.
 */
public abstract class AGraph {

    protected int size;
    
    public AGraph(int vertexCount) {
		size = vertexCount;
		if (size<=0) {
			throw new IllegalArgumentException("Rozmiar grafu musi byc wiekszy od zera!");
		}
    }

    public int getSize() {
        return size;
    }   

    /**
     * Wypisuje macierz sasiedztwa grafu.
     */
    public abstract void writeMatrix();

    /**
     * Sprawdza, czy istnieje krawedz pomiedzy wierzcholkiem i oraz j.
     */
    public abstract boolean check(int i, int j) throws IllegalArgumentException;

    /**
     * Tworzy krawedz pomiedzy wierzcholkiem i oraz j.
     */
    public abstract void connect(int i, int j) throws IllegalArgumentException;

    /**
     * Wypisuje graf jako listy sasiedztwa.
     */
    public abstract void writeList();

}

