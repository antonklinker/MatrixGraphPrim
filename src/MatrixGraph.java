import java.util.Arrays;
import java.util.PriorityQueue;

public class MatrixGraph {
    int[][] matrixedgegraph;
    int[][] matrixweightgraph;

    MatrixGraph(int vertices) {
        matrixedgegraph = new int[vertices][vertices];
        matrixweightgraph = new int[vertices][vertices];
    }

    public void addEdge(int from, int to, int weight) {
        matrixedgegraph[from][to]=1;
        matrixweightgraph[from][to]=weight;
    }

    public void printGraph() {
        for (int i=0; i<matrixedgegraph.length; i++) {
            System.out.println("Edges from vertex " + i);
            for (int j=0; j<matrixedgegraph.length; j++) {
                if (matrixedgegraph[i][j]==1) {
                    System.out.print(" to "+ j);
                    System.out.println(" weight " + matrixweightgraph[i][j]);
                }
            }
            System.out.println(" ");
        }
    }

    public void MSTPrims() {
        int[] distance = new int[matrixedgegraph.length];
        int[] predecessor = new int[matrixedgegraph.length];
        int[] visited = new int[matrixedgegraph.length];
        PriorityQueue<Pair> Q = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);
        Arrays.fill(visited, 0);

        if (matrixedgegraph.length>0) {
            distance[0]=0;
            Q.offer(new Pair (0,0));
        }
        int counter=0;
        int MST=0;

        while (!Q.isEmpty() && counter<matrixedgegraph.length) {
            Pair u = Q.poll();
            if (visited[u.index]!=1) {
               for (int v=0; v<matrixedgegraph.length; v++) {
                    if (matrixedgegraph[u.index][v] == 1 && matrixweightgraph[u.index][v] < distance[v]) {
                       distance[v] = matrixweightgraph[u.index][v];
                         predecessor[v] = u.index;
                        Q.offer(new Pair(distance[v], v));
                      }
                 }
               visited[u.index]=1;
               counter++;
               MST+=distance[u.index];
            }
        }
        System.out.println("Minimum spanning tree distance: " + MST);
        printMST(predecessor, distance);
    }
    public void printMST(int[] pred, int[] dist) {
        for (int i=0; i<matrixedgegraph.length; i++) {
            if (pred[i]>-1)
            System.out.println(i + " parent " + pred[i] + " Edge Weight: " + dist[i]);
            else {
                System.out.println(i + " is the first vertex");
            }
        }
    }
}

class Pair implements Comparable<Pair> {
    Integer distance;
    Integer index;
    public Pair (Integer distance, Integer vertex) {
        this.distance = distance;
        this.index=vertex;
    }
    @Override
    public int compareTo(Pair other) {
        return this.distance.compareTo(other.distance);
    }
}
