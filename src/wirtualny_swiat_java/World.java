/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualny_swiat_java;

import Animals.*;
import java.util.*;
import Organism.Organism;
import Plants.*;
import java.awt.geom.Point2D;
import java.io.PrintWriter;
//import wirtualny_swiat_java.Visualize;
/**
 *
 * @author Franciszek
 */
public abstract class World {
    private  ArrayList <Organism> queue;
    private int N,M;
    private Visualize visualize;
    private String Org;
    private BoardComponent boardGame;
    private boolean isSkill = false;
    public World(int N, int M,Visualize visualize){
        queue = new ArrayList<>();
        this.setN(N);
        this.setM(M);    
        this.visualize = visualize;
    }
    public void setBoard(BoardComponent board){
        this.boardGame = board;
    }    
    public void setN(int newN){
        this.N = newN;
    }
    public void setM(int newM){
        this.M = newM;
    }
    public int getN(){
        return N;
    }
    public int getM(){
        return M;
    }
    public void useHuman(int d){
        for(Organism org : queue){
            if(org instanceof Human){
                Human human = (Human) org;
                human.setDirection(d);
                break;
            }
        }
    }
    public void setSkill(boolean skill){
        this.isSkill = skill;
    }
    public boolean getSkill(){
        return this.isSkill;
    }
    public void setAbility(){
        if(!this.isSkill){
            for (Organism org : queue) {
                if (org instanceof Human) {
                    Human human = (Human) org;
                    this.isSkill = true;
                    human.setSkill(true);
                    human.setTurns(5);
                    visualize.changeSkillButton(human.getTurns());
                    break;
                }
            }            
        }
    }    
    public abstract int[] setMove(int y, int x);
    public void changeTurns(int turns){
        visualize.changeSkillButton(turns);
    }
    public String showOrganism(Organism org){
        if(org instanceof Wolf){
            return "Wolf";
        }
        else if(org instanceof Sheep){
            return "Sheep";
        }   
        else if(org instanceof Fox){
            return "Fox";
        }  
        else if(org instanceof Turtle){
            return "Turtle";
        } 
        else if(org instanceof Antelope){
            return "Antelope";
        }    
        else if(org instanceof Grass){
            return "Grass";
        }    
        else if(org instanceof Dandelion){
            return "Dandelion";
        }    
        else if(org instanceof Guarana){
            return "Guarana";
        } 
        else if(org instanceof Belladona){
            return "Belladona";
        }   
        else if(org instanceof Hogweed){
            return "Hogweed";
        }   
        else if(org instanceof Human){
            return "Human";
        }        
        return "Error, that type of organism doesn't exist!";
    }
    public Organism[][] getOrganisms(){
        return boardGame.getOrganisms();
    }
    public void setOrganism(int y, int x){
        Organism[][] organisms = this.getOrganisms();
        switch(Org){
            case "Wolf":
                organisms[y][x] = new Wolf(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;
            case "Sheep":
                organisms[y][x] = new Sheep(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;    
            case "Fox":
                organisms[y][x] = new Fox(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;     
            case "Turtle":
                organisms[y][x] = new Turtle(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;  
            case "Antelope":
                organisms[y][x] = new Antelope(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;   
            case "Grass":
                organisms[y][x] = new Grass(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;
            case "Dandelion":
                organisms[y][x] = new Dandelion(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;   
            case "Guarana":
                organisms[y][x] = new Guarana(y,x,this);
                this.addToQueue(organisms[y][x]);
                break; 
            case "Belladona":
                organisms[y][x] = new Belladona(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;    
            case "Hogweed":
                organisms[y][x] = new Hogweed(y,x,this);
                this.addToQueue(organisms[y][x]);
                break; 
            case "Human":
                organisms[y][x] = new Human(y,x,this);
                this.addToQueue(organisms[y][x]);
                break;                
        }
        this.showMessage(Org);
    }
    public void ChangeOrganism(int y, int x,int newX, int newY, Organism org){
        for(Organism Org : queue){
            if(Org == org){
                org.setY(newY);
                org.setX(newX);
                break;
            }
        }
        boardGame.getOrganisms()[y][x] = null;
        boardGame.getOrganisms()[newY][newX] = org;
    }
    public void removeOrganism(int y, int x){
        for(int i = 0;i<queue.size();++i){
            if(queue.get(i).getY() == y && queue.get(i).getX() == x){
                String type = this.showOrganism(queue.get(i));
                this.showMessage("Organism type "+type+" from position ("+y+", "+x+") has just been died!");
                this.getOrganisms()[y][x] = null;
                queue.remove(i);
                break;
            }
        }
    }
    public void executeTurn(){
        for(Organism org : queue){
            if(org.getIsMove()){
                org.setIsMove(false);
            }
        }        
        String type;
        for(int i = 0;i<queue.size();++i){
            if(!queue.get(i).getIsMove()){
                queue.get(i).setIsMove(true);
                Organism org = queue.get(i);
                int y = queue.get(i).getY();
                int x = queue.get(i).getX();
                type = this.showOrganism(queue.get(i));
                
                this.showMessage("This is a turn of organism "+type+" from position ("+y+", "+x+")");
                //queue.get(i).action();
                org.action();
                y = org.getY();
                x = org.getX();
                //y = queue.get(i).getY();
                //x = queue.get(i).getX();
                if(this.getOrganisms()[y][x] == null){
                    queue.remove(org);
                    //queue.remove(i);
                }
                this.drawWorld();
            }            
        }       
    }
    public void drawWorld(){
        this.visualize.repaint();
    }   
    
    public void showMessage(String text){
        visualize.AddToLog(text);
    }
    
    public void setOrg(String org){
        this.Org = org;
    }
    
    public void activate(Point2D p, int bornY, int bornX){
        visualize.chooseOrganism(p,bornY,bornX);        
    }
    public void save(PrintWriter out){
        String type = "";
        if(this instanceof HexWorld){
            type = "Hex";
        }
        else if(this instanceof GridWorld){
            type = "Grid";
        }   
        out.println(type);
        //out.println(N+" "+M);
        for(Organism org : queue){

            out.println(org.printInfo());
        }
    }
    public void load(Scanner scan){
        Organism[][] organisms = this.getOrganisms();
        String type;
        int y;
        int x;
        int power;
        //int initiative;
        int age;
        while(scan.hasNext()){
            type = scan.next();
            y = Integer.parseInt(scan.next());
            x = Integer.parseInt(scan.next());
            power = Integer.parseInt(scan.next());
            //initiative = Integer.parseInt(scan.next());
            age = Integer.parseInt(scan.next());
            this.setOrg(type);
            this.setOrganism(y, x);
            organisms[y][x].setPower(power);
            organisms[y][x].setAge(age);
            System.out.println(age);
        }
    }
    public void addToQueue(Organism newOrg){
        boolean end = false;
        if(queue.isEmpty()){
            queue.add(newOrg);
            queue.get(0).setIsMove(true);
        }
        else{
            for(int i = 0;i<queue.size();++i){
                if(queue.get(i).getInitiative() < newOrg.getInitiative()){
                    queue.add(i, newOrg);
                    queue.get(i).setIsMove(true);                  
                    break;
                }
                while(i < queue.size() && queue.get(i).getInitiative() == newOrg.getInitiative()){
                    if(queue.get(i).getAge() == newOrg.getAge()){
                        queue.add(i+1, newOrg);
                        queue.get(i+1).setIsMove(true);
                        end = true;
                        break;
                    }
                    ++i;
                }
                if(end){
                    break;
                }
                if(i == queue.size()){
                    queue.add(i, newOrg);
                    queue.get(i).setIsMove(true);                  
                    break;                    
                }                
                if(i == queue.size() - 1){
                    queue.add(i + 1, newOrg);
                    queue.get(i + 1).setIsMove(true);                  
                    break;                    
                }                
            }
        }
        String type = this.showOrganism(newOrg);
        this.showMessage("The organism "+type+" has just born at position ("+newOrg.getY()+", "+newOrg.getX()+")");        
    }  
    public abstract int[][] returnPlaceToBorn(int y, int x);
}
