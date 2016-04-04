package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.util.Scanner;
import java.util.Stack;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static smallworld.dropdown.run;

/**
 * ****************************************************************************
 * Compilation: javac PathFinder.java Execution: java Pathfinder
 * routes/movies.txt delimiter source Dependencies: Queue.java Stack.java
 * Graph.java
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
/**
 *
 * @author Hwi Ram Jeong
 * @version 3/31 CSC144 : Software Architect
 */
public class PathFinder extends JFrame {

    // prev[v] = previous vertex on shortest path from s to v
    // dist[v] = length of shortest path from s to v
    private ST<String, String> prev = new ST<String, String>();
    private ST<String, Integer> dist = new ST<String, Integer>();

    private JTextField t = new JTextField(15);
    private JComboBox c = new JComboBox();
    
    // run BFS in graph G from given source vertex s
    /**
     * put source on the queue / repeated remove next vertex v from queue and
     * insert all its neighbors, provided they haven't yet been visited
     *
     * @param G Graph
     * @param s String
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
     *
     * @param v String
     * @return dist.contains(v)
     */
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }

    // return the length of the shortest path from v to s
    /**
     * return the length of the shortest path from v to s
     *
     * @param v String
     * @return return dist.get(v)
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
     *
     * @param v
     * @return path
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
     * @param args String[]
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filename = args[0];
        String delimiter = args[1];

        System.out.println(filename);
        System.out.println(">" + delimiter + "<");
        In in = new In(filename);
        Graph G = GraphGenerator.read(in, delimiter);

        System.out.println("Enter the actor's name/departure airport :");
        Scanner input1 = new Scanner(System.in);
        String argument1 = input1.nextLine();

        String s = argument1;

        System.out.println("Enter the actor's name/arrival airpot :");
        Scanner input2 = new Scanner(System.in);
        String argument2 = input2.nextLine();

        PathFinder pf = new PathFinder(G, s);
        pf.report(argument2);
        
        
        GraphDraw frame = new GraphDraw("PathFinder");
        
        String[] movieList = new String[pf.report(argument2) + 1] ;

        int i = 100;
        int j = 100;
        int k = 0;
        int movie =0;
        for (String v : pf.pathTo(argument2)) {

            //frame.setLocationRelativeTo(null);
            frame.addNode(v, i, j);
            frame.setSize(800, 500);
            frame.setVisible(true);
            frame.addEdge(0, k);

            i = i + 100;
            j = j + 50;
            k++; 
            
            movieList[movie++] = v;
            
        }

        dropdown frame1 = new dropdown(movieList);
        run(frame1, 300, 300);
    } // main( String [] )

    /**
     *
     * @param a String
     */
    private int report(String a) {

        for (String v : this.pathTo(a)) {
            StdOut.println("   " + v);
        }
        StdOut.println("distance " + this.distanceTo(a));
    return  this.distanceTo(a);
        
    } // report( PathFinder, String )

    
    
} // PathFinder
