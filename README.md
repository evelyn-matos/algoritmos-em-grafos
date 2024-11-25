# Algoritmos em grafos

Este projeto implementa e executa os principais algoritmos de grafos em três grandes redes de estradas dos EUA: New York (NY), São Francisco (SF) e Colorado (COL). O objetivo é calcular:

 - Caminho Mínimo utilizando o algoritmo Dijkstra.
 - Árvore Geradora Mínima utilizando os algoritmos Kruskal e Prim.
 - Fluxo Máximo utilizando o algoritmo Ford-Fulkerson.
   
Os algoritmos são avaliados com base no custo computacional e no tempo de execução para cada grafo.

Algoritmos Implementados
 1. Dijkstra (Caminho Mínimo)

Determina o menor caminho entre um vértice de origem e todos os outros vértices do grafo.
 - Limitação: Não funciona corretamente com pesos negativos nas arestas.
   
 2. Kruskal (Árvore Geradora Mínima)

Constrói uma árvore mínima conectando todos os vértices com o menor custo total. <br>
Usa ordenação das arestas e uma estrutura de união-find para eficiência.

 3. Prim (Árvore Geradora Mínima)

Outro algoritmo para construir uma árvore mínima. <br>
Mais eficiente em grafos densos.

 4. Ford-Fulkerson (Fluxo Máximo)

Determina o fluxo máximo em uma rede de fluxo. <br>
Usa um grafo residual e a abordagem de caminho aumentante.
