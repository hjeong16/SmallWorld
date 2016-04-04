
package smallworld;

/* Simple graph drawing class
Bert Huang
COMS 3137 Data Structures and Algorithms, Spring 2009

This class is really elementary, but lets you draw 
reasonably nice graphs/trees/diagrams. Feel free to 
improve upon it!
 */


import java.util.*;
import java.awt.*;
import javax.swing.*;


public class GraphDraw extends JFrame  {
    int width;
    int height;

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

     private JTextField t = new JTextField(15);
    private JComboBox c = new JComboBox();
    private JTextField k = new JTextField(15);
    private JComboBox d = new JComboBox();

    private JButton b = new JButton("Add");

    private int count = 0;
    
    
    
    /**
     * Constructor
     */
    public GraphDraw() { //Constructor
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	nodes = new ArrayList<Node>();
	edges = new ArrayList<edge>();
	width = 30;
	height = 30;
    }

    /**
     * Construct with label
     * @param name
     */
    public GraphDraw(String name) { //Construct with label
	this.setTitle(name);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	nodes = new ArrayList<Node>();
	edges = new ArrayList<edge>();
	width = 30;
	height = 30;
    }

    class Node {
	int x, y;
	String name;
	
	public Node(String myName, int myX, int myY) {
	    x = myX;
	    y = myY;
	    name = myName;
	}
    }
    
    class edge {
	int i,j;
	
	public edge(int ii, int jj) {
	    i = ii;
	    j = jj;	    
	}
    }
    
    /**
     * Adding nodes at pixel (x,y)
     * @param name String name
     * @param x int
     * @param y int
     */
    public void addNode(String name, int x, int y) { 
	//add a node 
	nodes.add(new Node(name,x,y));
	this.repaint();
    }

    /**
     * Adding edges between nodes i and j
     * @param i int
     * @param j int
     */
    public void addEdge(int i, int j) {
	//add an edge 
	edges.add(new edge(i,j));
	this.repaint();
    }
    
    /**
     * Draw the nodes and edges
     * @param g Graphics 
     */
    
    @Override
    public void paint(Graphics g) { // draw the nodes and edges
       
       
	g.setFont(new Font("TimesRoman", Font.BOLD, 17));
        FontMetrics f = g.getFontMetrics();
	int nodeHeight = Math.max(height, f.getHeight());
        
	g.setColor(Color.BLACK);
         Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
	for (edge e : edges) {
	    g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
		     nodes.get(e.j).x, nodes.get(e.j).y);
	}

	for (Node n : nodes) {
             
	    int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
	    g.setColor(Color.YELLOW);
	    g.fillRoundRect(n.x-nodeWidth/2, n.y-nodeHeight/2, 
		       nodeWidth, nodeHeight,25,25);
	    g.setColor(Color.GRAY);

             g.drawRoundRect(n.x-nodeWidth/2, n.y-nodeHeight/2,nodeWidth, nodeHeight,25,25);
	    g.setColor(Color.RED);
           
	    g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
			 n.y+f.getHeight()/2);
	}
    }
}


