package com.project.project.Util;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vertices; // Número de vértices
    private List<Edge> edges; // Lista de arestas

    // Construtor
    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    // Adicionar aresta
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Getters e outros métodos
    public int getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
