package com.project.project.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphLoader {
    public static List<List<int[]>> loadGraph(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        int vertices = 0, edges = 0;
        List<List<int[]>> graph = null;

        while ((line = br.readLine()) != null) {
            // Ignorar comentários e cabeçalhos
            if (line.startsWith("c")) {
                continue; // Ignorar linha de comentário
            } else if (line.startsWith("p")) {
                // Processar o cabeçalho
                String[] parts = line.split(" ");
                vertices = Integer.parseInt(parts[2]); // Número de vértices
                edges = Integer.parseInt(parts[3]); // Número de arestas

                // Inicializar a lista de adjacência
                graph = new ArrayList<>();
                for (int i = 0; i < vertices; i++) {
                    graph.add(new ArrayList<>());
                }
            } else if (line.startsWith("a")) {
                // Processar uma aresta
                String[] parts = line.split(" ");
                int u = Integer.parseInt(parts[1]) - 1; // Vértice 1 (ajustado para índice 0)
                int v = Integer.parseInt(parts[2]) - 1; // Vértice 2 (ajustado para índice 0)
                int weight = Integer.parseInt(parts[3]); // Peso da aresta

                // Adicionar a aresta ao grafo
                graph.get(u).add(new int[]{v, weight});
                graph.get(v).add(new int[]{u, weight}); // Para grafos não direcionados
            }
        }

        br.close();
        return graph;
    }
}
