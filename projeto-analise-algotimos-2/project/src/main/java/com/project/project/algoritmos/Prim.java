package com.project.project.algoritmos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    public static int primMST(List<List<int[]>> graph) {
        int vertices = graph.size();
        boolean[] inMST = new boolean[vertices];  // Marca os vértices que já estão na árvore
        int[] minEdge = new int[vertices];        // Armazena o peso da aresta mínima para cada vértice
        Arrays.fill(minEdge, Integer.MAX_VALUE);  // Inicializa com valor infinito
        minEdge[0] = 0;  // Começa do vértice 0

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{0, 0});  // (vértice, peso da aresta)

        int mstCost = 0;

        while (!pq.isEmpty()) {
            // Extrai o vértice com a aresta mínima
            int[] current = pq.poll();
            int u = current[0];

            if (inMST[u]) {
                continue;  // Se já estiver na MST, ignora
            }

            // Marca o vértice como pertencente à MST
            inMST[u] = true;
            mstCost += current[1];  // Adiciona o peso da aresta à MST

            // Para cada vizinho de u
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int weight = edge[1];

                // Se v não está na MST e o peso da aresta é menor que o valor atual de minEdge[v]
                if (!inMST[v] && weight < minEdge[v]) {
                    minEdge[v] = weight;
                    pq.add(new int[]{v, weight});
                }
            }
        }

        return mstCost;
    }
}
