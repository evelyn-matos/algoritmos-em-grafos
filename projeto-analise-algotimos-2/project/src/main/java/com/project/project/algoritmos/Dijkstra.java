package com.project.project.algoritmos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] shortestPath(List<List<int[]>> graph, int start) {
        int vertices = graph.size();
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);  // Inicializa todas as distâncias como infinito
        distances[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];  // Vértice atual
            int currentDistance = current[1];

            // Se a distância atual for maior do que a melhor conhecida, ignora
            if (currentDistance > distances[u]) continue;

            // Verifica todos os vizinhos
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0]; // Destino
                int weight = neighbor[1]; // Peso

                // Se encontramos um caminho melhor para v, atualiza
                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.offer(new int[]{v, distances[v]});
                }
            }
        }

        return distances;
    }
}
