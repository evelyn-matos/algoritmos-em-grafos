package com.project.project.algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static int kruskalMST(List<List<int[]>> graph) {
        int vertices = graph.size();
        List<int[]> edges = new ArrayList<>();

        // Coletar todas as arestas do grafo
        for (int u = 0; u < vertices; u++) {
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                if (u < v) {  // Evita adicionar arestas duplicadas
                    edges.add(new int[]{u, v, weight});
                }
            }
        }

        // Ordenar as arestas por peso
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // Estrutura de dados para União-Find (Disjoint Set Union - DSU)
        UnionFind uf = new UnionFind(vertices);

        int mstCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            // Se u e v não estão no mesmo conjunto, adicionar a aresta
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mstCost += weight;  // Adiciona o peso da aresta ao custo total
            }
        }

        return mstCost;
    }

    // Classe para a estrutura de dados Union-Find
    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Compressão de caminho
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                // Une os dois conjuntos com base no rank
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
}
