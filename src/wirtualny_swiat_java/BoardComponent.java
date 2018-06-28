/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualny_swiat_java;

import Animals.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import static java.lang.Math.sqrt;
import java.util.*;
import Organism.Organism;
import Plants.*;

/**
 *
 * @author Franciszek
 */
public class BoardComponent extends JComponent{
    private Organism[][]organisms;
    private Rectangle2D[][] rects;
    private Path2D[][] hexes;
//    private ArrayList<Rectangle2D>rects;
//   private ArrayList<Path2D>hexes;
   // private Rectangle2D gameBoard;
    private Rectangle2D current;
    private Path2D currentHex;
    private World world;
    private int SIDELENGTH;
    private int N;
    private int M;
    private int bornY;
    private int bornX;
    
    public BoardComponent(int N, int M, World world){
        if(N <= 10){
            if(world instanceof GridWorld){
            this.SIDELENGTH = 40;            
            }
            else{
            this.SIDELENGTH = 20;
            }
        }
        else if(N <= 20){
            if(world instanceof GridWorld){
            this.SIDELENGTH = 35;            
            }
            else{
            this.SIDELENGTH = 14;
            }            
        }        
        else if(N <= 50){
           if(world instanceof GridWorld){
            this.SIDELENGTH = 20;            
            }
            else{
            this.SIDELENGTH = 6;
            }             
        }
        else if(N <= 100){
            this.SIDELENGTH = 4;
        }
        else if(N > 100){
            this.SIDELENGTH = 2;
        }        
        this.N = N;
        this.M = M;
        this.world = world;
        if(world instanceof HexWorld){
            hexes = new Path2D[N][M];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    hexes[i][j] = createHex(20 + j*SIDELENGTH*sqrt(3) + i*SIDELENGTH/2*sqrt(3), 20 + i*(3.0/2.0*SIDELENGTH));
                }
            }
            currentHex = null;
        }
        else if(world instanceof GridWorld){
            rects = new Rectangle2D[N][M];
            //gameBoard = new Rectangle2D.Double(10,10, N*SIDELENGTH,M*SIDELENGTH);
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    rects[i][j] = new Rectangle2D.Double(10 + j * SIDELENGTH, 10 + i * SIDELENGTH, SIDELENGTH, SIDELENGTH);
                }
            }   
            current = null;
        }
        organisms = new Organism[N][M];
        
/*        if(world instanceof HexWorld){
            hexes = new ArrayList<>();
        }
        else if(world instanceof GridWorld){
            rects = new ArrayList<>();
            gameBoard = new Rectangle2D.Double(10,10, N*SIDELENGTH,M*SIDELENGTH);
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    rects.add(new Rectangle2D.Double(10 + j * SIDELENGTH, 10 + i * SIDELENGTH, SIDELENGTH, SIDELENGTH));
                }
            }           
        } 
*/

        
        addMouseListener(new MouseHandler());
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if(world instanceof HexWorld){
            this.drawHexMap(g2);
        }
        else if(world instanceof GridWorld){
            this.drawGridMap(g2);
        }       
    }
    
    public void drawHexMap(Graphics2D g2){
        boolean isAlive = false;
        g2.setColor(Color.yellow);
        g2.setColor(Color.black);        
        for(int i = 0; i<N;++i){
            for(int j = 0; j<M;++j){
                if(organisms[i][j] != null){
                    isAlive = true;                    
                    if(organisms[i][j] instanceof Wolf){
                        g2.setColor(Color.gray);                 
                    }   
                    else if(organisms[i][j] instanceof Sheep){
                        g2.setColor(Color.white);                 
                    } 
                    else if(organisms[i][j] instanceof Fox){
                        g2.setColor(Color.orange);                 
                    }     
                    else if(organisms[i][j] instanceof Turtle){
                        g2.setColor(Color.yellow);                 
                    }  
                    else if(organisms[i][j] instanceof Antelope){
                        g2.setColor(Color.blue);                 
                    }       
                    else if(organisms[i][j] instanceof Grass){
                        g2.setColor(Color.green);                 
                    } 
                    else if(organisms[i][j] instanceof Dandelion){
                        g2.setColor(Color.pink);                 
                    }
                    else if(organisms[i][j] instanceof Guarana){
                        g2.setColor(Color.magenta);                 
                    } 
                    else if(organisms[i][j] instanceof Belladona){
                        g2.setColor(Color.cyan);                 
                    } 
                    else if(organisms[i][j] instanceof Hogweed){
                        g2.setColor(Color.red);                 
                    }   
                    else if(organisms[i][j] instanceof Human){
                        g2.setColor(Color.lightGray);                 
                    }                    
                }              
                if (!isAlive) {
                    g2.setColor(Color.black);
                }
                g2.fill(hexes[i][j]);
                isAlive = false;                
                //this.drawHex(g2,j,i);
            }
        }        
    }
    
    public void drawGridMap(Graphics2D g2){
        boolean isAlive = false;
        g2.setColor(Color.yellow);
        g2.setColor(Color.black);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if(organisms[i][j] != null){
                        isAlive = true;
                    if(organisms[i][j] instanceof Wolf){
                        g2.setColor(Color.gray);                 
                    }   
                    else if(organisms[i][j] instanceof Sheep){
                        g2.setColor(Color.white);                 
                    } 
                    else if(organisms[i][j] instanceof Fox){
                        g2.setColor(Color.orange);                 
                    }     
                    else if(organisms[i][j] instanceof Turtle){
                        g2.setColor(Color.yellow);                 
                    }  
                    else if(organisms[i][j] instanceof Antelope){
                        g2.setColor(Color.blue);                 
                    }       
                    else if(organisms[i][j] instanceof Grass){
                        g2.setColor(Color.green);                 
                    } 
                    else if(organisms[i][j] instanceof Dandelion){
                        g2.setColor(Color.pink);                 
                    }
                    else if(organisms[i][j] instanceof Guarana){
                        g2.setColor(Color.magenta);                 
                    } 
                    else if(organisms[i][j] instanceof Belladona){
                        g2.setColor(Color.cyan);                 
                    } 
                    else if(organisms[i][j] instanceof Hogweed){
                        g2.setColor(Color.red);                 
                    }   
                    else if(organisms[i][j] instanceof Human){
                        g2.setColor(Color.lightGray);                 
                    }                
                }              
                if (!isAlive) {
                    g2.setColor(Color.black);
                }
                g2.fill(rects[i][j]);
                isAlive = false;
            }
        }        
    }
    
    public Path2D createHex(double x, double y){
        double xPoints[] = {x,x + (SIDELENGTH/2 * sqrt(3)),x + (SIDELENGTH/2 * sqrt(3)),x,x -(SIDELENGTH/2 * sqrt(3)),x -(SIDELENGTH/2 * sqrt(3))};
        double yPoints[] = {y, y + SIDELENGTH/2, y + SIDELENGTH/2 + SIDELENGTH,y + SIDELENGTH + SIDELENGTH,y + SIDELENGTH/2 + SIDELENGTH,y + SIDELENGTH/2};
        
        Path2D hex = new Path2D.Double();
        hex.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; ++i) {
            hex.lineTo(xPoints[i], yPoints[i]);
        }
        hex.closePath(); 
        
        return hex;
    }
    
    public void drawHex(Graphics2D g2, int x, int y){
        
/*        double xPoints[] = {x,x + (10 * sqrt(3)),x + (10 * sqrt(3)),x,x -(10 * sqrt(3)),x -(10 * sqrt(3))};
        double yPoints[] = {y, y + 10, y + 10 + SIDELENGTH,y + 20 + SIDELENGTH,y + 10 + SIDELENGTH,y + 10};
        
        Path2D hex = new Path2D.Double();
        hex.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; ++i) {
            hex.lineTo(xPoints[i], yPoints[i]);
        }
        hex.closePath();    
*/
        g2.draw(hexes[y][x]);
    }  
    public Organism[][] getOrganisms(){
        return organisms;
    }    
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
// Dodanie nowego organizmu, jeśli kursor jest wewnątrz planszy
            if (onBoard(event.getPoint())) {
                if (!findIsLife(event.getPoint())) {
                    this.add(event.getPoint());                    
                }
            }
        }

/*        public void mouseClicked(MouseEvent event) {
// Usunięcie kwadratu w wyniku jego dwukrotnego kliknięcia
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2) {
                remove(current);
            }
        }
*/        
        
        public boolean onBoard(Point2D p) {
            if (world instanceof GridWorld) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < M; ++j) {
                        if (rects[i][j].contains(p)) {
                            bornY = i;
                            bornX = j;
                            return true;
                        }
                    }
                }

            } else if (world instanceof HexWorld) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < M; ++j) {
                        if (hexes[i][j].contains(p)) {
                            bornY = i;
                            bornX = j;
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        
        public boolean findIsLife(Point2D p){

            if (organisms[bornY][bornX] != null) {
                return true;
            } else {
                return false;
            }
        }
        
        public Rectangle2D find(Point2D p){
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (rects[i][j].contains(p)) {
                        return rects[i][j];
                    }
                }
            }  
            return null;
        }
        
        public void add(Point2D p) {
            world.activate(p,bornY,bornX);            
/*            int y = bornY;
            int x = bornX;
            if (world instanceof GridWorld) {
                //organisms[y][x] = new Organism(y, x, rects[y][x]);
            } else if (world instanceof HexWorld) {
               // organisms[y][x] = new Organism(y, x, hexes[y][x]);
            }
*/
            repaint();
        }
        

/*
        public void remove(Rectangle2D r) {
            current = null;
            organisms.remove(r);
            repaint();
        }
*/
    }
}
