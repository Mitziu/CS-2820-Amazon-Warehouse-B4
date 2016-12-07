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

    public void createAndShowGUI() {
        JFrame f = new JFrame("Amazon Warehouse Visualizer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p = new MyPanel());
        f.pack();
        f.setVisible(true);
    }

    public void run(List<Map<String, Point>> newList) {
        warehouseHistory = newList;
        r.run();
        getNextMap();
    }

    public void getNextMap () {
        boolean counterAtMax = false;

        if (counter >= warehouseHistory.size() - 1) counterAtMax = true;

        if (!counterAtMax) counter++;
        floorMap = warehouseHistory.get(counter);


        System.out.println(counter);
        p.repaint();
    }

    public void getPreviousMap () {
        boolean counterAtMin = false;

        if (counter <= 0) counterAtMin = true;
        if (!counterAtMin) counter--;

        floorMap = warehouseHistory.get(counter);

        System.out.println(counter);
        p.repaint();
    }

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

    class MyPanel extends JPanel {

        java.util.List<Square> squaresInPanel;
        Square currentSquare = null;
        JButton stepButton = new JButton("Step");

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



        private int inWhichSquare(int x, int y) {

            int indexToReturn = 0;

            for (Square currentSquare: squaresInPanel) {

                if (x >= currentSquare.getX() && x <= currentSquare.getX() + currentSquare.getWidth()
                        && y >= currentSquare.getY() && y <= currentSquare.getY() + currentSquare.getWidth()) {
                    return indexToReturn;
                }
                indexToReturn++;
            }

            //Didn't click on a square
            return -1;
        }


        public Dimension getPreferredSize() {
            return new Dimension(4000,4000);
        }

        public void drawObjects(Graphics g, String k, Point p) {
            int x = p.GetX();
            int y = p.GetY();

            if (grid[x][y] != null) {
                drawSquare(g, x, y, k);
            }
        }

        public void drawBackground (Graphics g) {

            for (int i = 0; i < WIDTH_OF_GRID; i++) {

                for (int j = 0; j < HEIGHT_OF_GRID; j++) {

                    if (grid[i][j] != null) {
                        drawSquare(g, i, j, "");
                    }
                }
            }
        }

        private void drawSquare(Graphics g, int x, int y, String objectType) {
            Square tempSquare = new Square((x * 15), (y * 15), 15, findColor(objectType));
            g.setColor(tempSquare.getColor());
            g.fillRect(tempSquare.getX(), tempSquare.getY(), tempSquare.getWidth(), tempSquare.getWidth());
            g.setColor(Color.BLACK);
            g.drawRect(tempSquare.getX(), tempSquare.getY(), tempSquare.getWidth(), tempSquare.getWidth());
        }

        protected void paintComponent(Graphics g) {
            g.drawString("This is our warehouse!",100,950);
            drawBackground(g);
            floorMap.forEach((k,v) -> drawObjects(g, (String)k, (Point)v));

        }

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
