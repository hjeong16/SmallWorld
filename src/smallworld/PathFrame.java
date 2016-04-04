package smallworld;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class PathFrame extends JFrame {

    public static final int FRAME_WIDTH = 512;
    public static final int FRAME_HEIGHT = 512;
    public static final String TITLE = "Pathfinder";

    public PathFrame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        PathPanel panel = new PathPanel();
        pane.add(panel);
//        
        this.setVisible(true);
    } // PathFrame()

    public static void main(String[] args) {
        PathFrame pathFrame = new PathFrame();
    } // main( String [] )

} // PathFrame
