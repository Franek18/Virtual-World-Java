/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package wirtualny_swiat_java;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Franciszek
 */
public class Organism {
    private int x;
    private int y;
    private Rectangle2D rect;
    private Path2D hex;
    
    public Organism(int y, int x, Rectangle2D rect){
        this.y = y;
        this.x = x;
        this.rect = rect;
    }
    
    public Organism(int y, int x, Path2D rect){
        this.y = y;
        this.x = x;
        this.hex = hex;
    }    
    
    public int getY(){
        return this.y;
    }
    
    public int getX(){
        return this.x;
    }    
    
    public Rectangle2D getRect(){
        return this.rect;
    } 
    
    public Path2D getHex(){
        return this.hex;
    }
}
