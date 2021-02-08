package com.company;

public class Graph extends AGraph{
    private int[][] graph = new int[size][size];


    public Graph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void writeMatrix() {
        System.out.print("  ");
        for(int i=0; i<size; i++)
        {
            System.out.print(i+" ");

        }
        System.out.println("");
        for(int i=0; i<size; i++)
        {   System.out.print(i+" ");
            for(int j=0; j<size; j++)
            {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean check(int i, int j) throws IllegalArgumentException {
        try {
            if(i<=size&&j<=size) {
                if(graph[i][j]==1){return true;}
                else {return false;}}
            else {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException a) {
            System.err.println("Niepoprawny parametr: " + i + " , " + j+ " // " + a);
            return false;
        }
    }

    @Override
    public void connect(int i, int j) throws IllegalArgumentException {
        try {
            if(i<=size&&j<=size&&i!=j) {
                graph[i][j] = 1;
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException a) {
            System.err.println("Niepoprawny parametr: "+i+" , "+j + " // " + a);
        }
    }

    @Override
    public void writeList() {
        for(int i=0; i<size; i++)
        {
            System.out.print(i+" :");
            for(int j=0; j<size; j++)
            {
                if(graph[i][j]==1&&i!=j)
                {
                    System.out.print(" "+j);
                }
            }
            System.out.println();
        }
    }
}
