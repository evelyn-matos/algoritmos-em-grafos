package com.project.project.algoritmos;

import java.util.*;



public class FordFulkerson {

    // Função BFS adaptada para trabalhar com o mapa de capacidades
    private boolean bfs(Map<Integer, Map<Integer, Integer>> capacity, int source, int sink, Map<Integer, Integer> parent) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        parent.put(source, -1);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            // Se o nó u tem vizinhos com capacidade positiva, vamos tentar ir até o destino
            if (capacity.containsKey(u)) {
                for (Map.Entry<Integer, Integer> entry : capacity.get(u).entrySet()) {
                    int v = entry.getKey();
                    int residualCapacity = entry.getValue();
                    if (!visited.contains(v) && residualCapacity > 0) {
                        queue.add(v);
                        visited.add(v);
                        parent.put(v, u);
                        if (v == sink) {
                            return true; // Encontrou o caminho até o destino
                        }
                    }
                }
            }
        }
        return false;
    }

    // Função principal do Ford-Fulkerson 
    public int maxFlow(Map<Integer, Map<Integer, Integer>> capacity, int source, int sink) {
        Map<Integer, Map<Integer, Integer>> residualGraph = new HashMap<>();
        
        // Inicializar o grafo residual com as capacidades do grafo original
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : capacity.entrySet()) {
            int u = entry.getKey();
            residualGraph.put(u, new HashMap<>(entry.getValue()));
        }

        Map<Integer, Integer> parent = new HashMap<>();
        int maxFlow = 0;

        // Continuar procurando por caminhos aumentantes
        while (bfs(residualGraph, source, sink, parent)) {
            // Encontrar o fluxo máximo no caminho encontrado
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent.get(v)) {
                int u = parent.get(v);
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v));
            }

            // Atualizar o grafo residual com o fluxo encontrado
            for (int v = sink; v != source; v = parent.get(v)) {
                int u = parent.get(v);
                residualGraph.get(u).put(v, residualGraph.get(u).get(v) - pathFlow);
                residualGraph.get(v).put(u, residualGraph.get(v).getOrDefault(u, 0) + pathFlow);
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
