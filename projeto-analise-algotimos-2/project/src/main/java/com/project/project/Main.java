package com.project.project;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.project.Util.GraphLoader;
import com.project.project.algoritmos.Dijkstra;
import com.project.project.algoritmos.FordFulkerson;
import com.project.project.algoritmos.Kruskal;
import com.project.project.algoritmos.Prim;

public class Main {
    public static void main(String[] args) throws IOException {
        // Arquivos dos grafos
        String[] files = {
            "/home/evelynmatos/Documentos/faculdade/projeto-analise-algotimos-2/project/src/main/java/com/project/project/Files/USA-road-d.NY.gr",
            "/home/evelynmatos/Documentos/faculdade/projeto-analise-algotimos-2/project/src/main/java/com/project/project/Files/USA-road-d.BAY.gr",
            "/home/evelynmatos/Documentos/faculdade/projeto-analise-algotimos-2/project/src/main/java/com/project/project/Files/USA-road-d.COL.gr"
        };

        for (String file : files) {
            System.out.println("Arquivo: " + file);

            // Lê o grafo do arquivo usando o GraphLoader
            List<List<int[]>> graph = GraphLoader.loadGraph(file);

            // Número de vértices e arestas
            int vertices = graph.size();
            int edges = 0;
            for (List<int[]> adjList : graph) {
                edges += adjList.size();
            }

            System.out.println("Número de vértices: " + vertices);
            System.out.println("Número de arestas: " + edges / 2); // Dividido por 2 porque o grafo é não direcionado

            // Executa Dijkstra
            System.out.println("\nCaminho Mínimo:");
            long startTime = System.nanoTime();
            int[] shortestPaths = Dijkstra.shortestPath(graph, 0); // Supondo vértice inicial 0
            long endTime = System.nanoTime();
            double tempoDijkstraEmMs = (endTime - startTime) / 1e6; // Tempo em milissegundos
            double tempoDijkstraEmSegundos = tempoDijkstraEmMs / 1000.0; // Converte de milissegundos para segundos
            System.out.println("  - Custo: " + Arrays.stream(shortestPaths).sum());
            System.out.println("  - Tempo de execução: " + tempoDijkstraEmSegundos + " segundos");

            // Executa Árvore Geradora Mínima com Kruskal
            System.out.println("\nÁrvore Geradora Mínima (Kruskal):");
            startTime = System.nanoTime();
            int mstCostKruskal = Kruskal.kruskalMST(graph);
            endTime = System.nanoTime();
            double tempoKrusalEmMs = (endTime - startTime) / 1e6; // Tempo em milissegundos
            double tempoKrusalEmSegundos = tempoKrusalEmMs / 1000.0; // Converte de milissegundos para segundos
            System.out.println("  - Custo: " + mstCostKruskal);
            System.out.println("  - Tempo de execução: " + tempoKrusalEmSegundos + " segundos");

            // Executa Árvore Geradora Mínima com Prim
            System.out.println("\nÁrvore Geradora Mínima (Prim):");
            startTime = System.nanoTime();
            int mstCostPrim = Prim.primMST(graph);
            endTime = System.nanoTime();
            double tempoPrimEmMs = (endTime - startTime) / 1e6;
            double tempoPrimEmSegundos = tempoPrimEmMs / 1000.0; 
            System.out.println("  - Custo: " + mstCostPrim);
            System.out.println("  - Tempo de execução: " + tempoPrimEmSegundos + " segundos");

             // Executa Fluxo Máximo
            System.out.println("\nFluxo Máximo:");
            Map<Integer, Map<Integer, Integer>> capacityMap = createCapacityMap(graph);
            startTime = System.nanoTime();
            int maxFlow = new FordFulkerson().maxFlow(capacityMap, 0, vertices - 1); // Entre vértices 0 e último
            endTime = System.nanoTime();
            double tempoFMaxEmMs = (endTime - startTime) / 1e6;
            double tempoFMaxEmSegundos = tempoFMaxEmMs / 1000.0; 

            System.out.println("  - Custo: " + maxFlow);
            System.out.println("  - Tempo de execução: " + tempoFMaxEmSegundos + " segundos");
 
            System.out.println("\n-------------------------------------------\n");
        }
    }

  
    // Cria a matriz de capacidades para o fluxo máximo
    public static Map<Integer, Map<Integer, Integer>> createCapacityMap(List<List<int[]>> graph) {
        Map<Integer, Map<Integer, Integer>> capacityMap = new HashMap<>();

        for (int i = 0; i < graph.size(); i++) {
            for (int[] edge : graph.get(i)) {
                int dest = edge[0];
                int weight = edge[1];

                // Adiciona a capacidade da aresta no mapa
                capacityMap.computeIfAbsent(i, k -> new HashMap<>()).put(dest, weight);
            }
        }

        return capacityMap;
    }
}





