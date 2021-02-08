package com.company;

public class Main {

    public static void main(String[] args) {
	    Graph graph = new Graph(5);
	    graph.connect(0,1);
	    graph.connect(2,3);
	    graph.connect(4,0);
	    graph.connect(3,1);
	    graph.connect(2,4);
	    graph.connect(3,0);
	    graph.connect(2,1);
	    graph.writeMatrix();
	    System.out.println(graph.check(3,1));
	    graph.writeList();
    }
}
