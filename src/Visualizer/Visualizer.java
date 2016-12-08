package Visualizer;
import Floor.Point;

/**
 *@author Matt
 *@author Mitziu
 *This is the visualizer.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.lang.String.*;
import java.util.List;

/**
 * Created by Mitziu on 11/7/16.
 */
public class Visualizer {

    private Point[][] grid;
    private Map floorMap;
    private MyPanel p;
    private List<Map<String, Point>> warehouseHistory;
    private static final int WIDTH_OF_GRID = 160;
    private static final int HEIGHT_OF_GRID = 120;
    private Integer counter = -1;
    private Runnable r;

    /**
     * @author Matt
     * @author Mitziu
     * constructor for visualizer
     */
    public Visualizer () {
        int largestDim;
        if (WIDTH_OF_GRID > HEIGHT_OF_GRID) largestDim = WIDTH_OF_GRID;
        else largestDim = HEIGHT_OF_GRID;

        grid = new Point[largestDim][largestDim];
        for (int i = 0; i < WIDTH_OF_GRID; i++) {

            for (int j = 0; j < HEIGHT_OF_GRID; j++) {
                grid[i][j] = new Point(i, j);
            }

        }

        SwingUtilities.invokeLater(r = new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }

    /**
     * @author Matt
     * creates and shows GUI
     */
    public void createAndShowGUI() {
        JFrame f = new JFrame("Amazon Warehouse Visualizer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p = new MyPanel());
        f.pack();
        f.setVisible(true);
    }

    /**
     * @author Matt
     * @param newList list of Maps of warehouse
     * Call this to run the simulation
     */
    public void run(List<Map<String, Point>> newList) {
        warehouseHistory = newList;
        r.run();
        getNextMap();
    }

    /**
     * @author Matt
     * gets the next map of floor and calls repaint to show it
     */
    public void getNextMap () {
        boolean counterAtMax = false;

        if (counter >= warehouseHistory.size() - 1) counterAtMax = true;

        if (!counterAtMax) counter++;
        floorMap = warehouseHistory.get(counter);


        System.out.println(counter);
        p.repaint();
    }

    /**
     * @author Matt
     * gets previous map of floor and calls repaint to show it
     */
    public void getPreviousMap () {
        boolean counterAtMin = false;

        if (counter <= 0) counterAtMin = true;
        if (!counterAtMin) counter--;

        floorMap = warehouseHistory.get(counter);

        System.out.println(counter);
        p.repaint();
    }

    /**
     * @author Matt
     * Modified from previous project. Draws the squares that make up the visualizer
     * Could be refactored to remove unneeded methods
     */
    class Square {
        private int X;
        private int Y;
        private int width;
        private Color color;
        private boolean clicked;

        public Square(int X, int Y, int width, Color color) {
            this.X = X;
            this.Y = Y;
            this.width = width;
            this.color = color;
            clicked = false;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        public int getWidth() {
            return width;
        }

        public Color getColor() {
            return color;
        }

        public boolean isClicked() {
            return clicked;
        }

        public void setX(int x) {
            X = x;
        }

        public void setY(int y) {
            Y = y;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setClicked(boolean clicked) {
            System.out.println("Clicked set to: " + clicked);
            this.clicked = clicked;
        }


    }

    /**
     * @author Matt
     *  class MyPanel -- what I draw on
     */
    class MyPanel extends JPanel {

        java.util.List<Square> squaresInPanel;
        Square currentSquare = null;
        JButton stepButton = new JButton("Step");

        /**
         * @author Matt
         * constructor for MyPanel
         */
        public MyPanel() {

            squaresInPanel = new ArrayList<Square>();

            setBorder(BorderFactory.createLineBorder(Color.black));

            JComponent listener = new JComponent() {
                @Override
                public void setInheritsPopupMenu(boolean value) {
                    super.setInheritsPopupMenu(value);
                }
            };

            InputMap im = getInputMap(MyPanel.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");

            am.put("RightArrow", new ArrowAction("RightArrow"));
            am.put("LeftArrow", new ArrowAction("LeftArrow"));

        }


        /**
         * @author Matt
         * Goes forwards or back depending on right or left arrow pressed
         */
        class ArrowAction extends AbstractAction {

            private String cmd;

            public ArrowAction(String cmd) {
                this.cmd = cmd;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmd.equalsIgnoreCase("LeftArrow")) {
                    System.out.println("Step back 1.");
                    getPreviousMap();
                } else if (cmd.equalsIgnoreCase("RightArrow")) {
                    System.out.println("Step forward 1.");
                    getNextMap();
                }
            }

        }

        //TODO: make this a better size once panel is resized
        //DO NOT let the drawing get bigger than this--may cause problems
        public Dimension getPreferredSize() {
            return new Dimension(4000,4000);
        }

        /**
         * @author Matt
         * Draws the squares on the panel
         * @param g
         * @param k
         * @param p
         */
        public void drawObjects(Graphics g, String k, Point p) {
            int x = p.GetX();
            int y = p.GetY();

            if (grid[x][y] != null) {
                drawSquare(g, x, y, k);
            }
        }

        /**
         * @author Matt
         * @param g
         * draws background of panel
         */
        public void drawBackground (Graphics g) {

            for (int i = 0; i < WIDTH_OF_GRID; i++) {

                for (int j = 0; j < HEIGHT_OF_GRID; j++) {

                    if (grid[i][j] != null) {
                        drawSquare(g, i, j, "");
                    }
                }
            }
        }

        /**
         * Draws squares on panel--reused from previous swing assignment
         * @param g
         * @param x
         * @param y
         * @param objectType
         */
        private void drawSquare(Graphics g, int x, int y, String objectType) {
            Square tempSquare = new Square((x * 30), (y * 30), 30, findColor(objectType));
            g.setColor(tempSquare.getColor());
            g.fillRect(tempSquare.getX(), tempSquare.getY(), tempSquare.getWidth(), tempSquare.getWidth());
            g.setColor(Color.BLACK);
            g.drawRect(tempSquare.getX(), tempSquare.getY(), tempSquare.getWidth(), tempSquare.getWidth());
        }

        /**
         * @author Matt
         * @param g
         * Draws/redraws the floor
         */
        protected void paintComponent(Graphics g) {
            g.drawString("This is our warehouse!",100,950);
            drawBackground(g);

            if (floorMap != null)
                floorMap.forEach((k,v) -> drawObjects(g, (String)k, (Point)v));

        }

        /**
         * @author matt
         * @param objectType
         * @return Color an object should be painted.
         */
        public Color findColor (String objectType) {

            if (objectType.contains("RobotNotLoaded")) {return Color.BLUE;}
            else if (objectType.contains("RobotLoaded")) {return new Color(168, 73, 238);}
            else if (objectType.contains("Shelf")) {return Color.RED;}
            else if (objectType.contains("Charger")) {return Color.YELLOW;}
            else if (objectType.contains("BeltEmpty")) {return Color.BLACK;}
            else if (objectType.contains("BeltNotEmpty")) {return new Color(140, 84, 14);}
            else if (objectType.contains("Picker")) {return Color.GRAY;}
            else {return Color.WHITE;}
        }

    }



}
