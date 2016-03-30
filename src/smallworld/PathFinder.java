package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import java.util.Scanner;
import java.util.Stack;

/**
 * ****************************************************************************
 * Compilation: javac PathFinder.java Execution: java Pathfinder input.txt
 * delimiter source Dependencies: Queue.java Stack.java Graph.java
 *
 * Runs breadth first search algorithm from source s on a graph G. After
 * preprocessing the graph, can process shortest path queries from s to any
 * vertex t.
 *
 * % java PathFinder routes.txt " " JFK LAX JFK ORD PHX LAX distance 3 MCO JFK
 * MCO distance 1 DFW JFK ORD DFW distance 2
 *
 *****************************************************************************
 */
public class PathFinder {

    // prev[v] = previous vertex on shortest path from s to v
    // dist[v] = length of shortest path from s to v
    private ST<String, String> prev = new ST<String, String>();
    private ST<String, Integer> dist = new ST<String, Integer>();

    // run BFS in graph G from given source vertex s

    /**
     * put source on the queue
     * repeated remove next vertex v from queue and insert
     * all its neighbors, provided they haven't yet been visited
     * @param G
     * @param s
     */
    public PathFinder(Graph G, String s) {

        // put source on the queue
        Queue<String> q = new Queue<String>();
        q.enqueue(s);
        dist.put(s, 0);

        // repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while (!q.isEmpty()) {
            String v = q.dequeue();
            for (String w : G.adjacentTo(v)) {
                if (!dist.contains(w)) {
                    q.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }

    // is v reachable from the source s?

    /**
     * is v reachable from the source s?
     * @param v
     * @return
     */
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }

    // return the length of the shortest path from v to s

    /**
     * return the length of the shortest path from v to s
     * @param v
     * @return
     */
    public int distanceTo(String v) {
        if (!hasPathTo(v)) {
            return Integer.MAX_VALUE;
        }
        return dist.get(v);
    }

    // return the shortest path from v to s as an Iterable

    /**
     * return the shortest path from v to s as an Iterable
     * @param v
     * @return
     */
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];

        System.out.println(filename);
        System.out.println(">" + delimiter + "<");
        In in = new In(filename);
        Graph G = GraphGenerator.read(in, delimiter);

        System.out.println("Enter the name/departure :");
        Scanner input1 = new Scanner(System.in);
        String argument1 = input1.nextLine();
//        input1.useDelimiter("1");
        String s = argument1;

        System.out.println("Enter the name/arrival :");
        Scanner input2 = new Scanner(System.in);
        String argument2 = input2.nextLine();

        PathFinder pf = new PathFinder(G, s);
        pf.report(argument2);
//       pf.report( "Frederic, Patrick" );

    } // main( String [] )

    private void report(String a) {

        for (String v : this.pathTo(a)) {
            StdOut.println("   " + v);
        }
        StdOut.println("distance " + this.distanceTo(a));
    } // report( PathFinder, String )

} // PathFinder
