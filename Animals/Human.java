/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animals;

import wirtualny_swiat_java.World;

/**
 *
 * @author Franciszek
 */
public class Human extends Animal{
    
    private int direction;
    private int turns = 0;
    private boolean isSkill = false;
    public Human(int y, int x, World world) {
        super(5, 4, y, x, world);
    }
    public void setDirection(int d){
        this.direction = d;
    }
    public int getTurns(){
        return this.turns;
    }
    public void setTurns(int i){
        this.turns = i;
    }    
    public boolean getSkill(){
        return this.isSkill;
    }
    public void setSkill(boolean skill){
        this.isSkill = skill;
        if(skill == true && this.power < 10){
           this.power = 10;  
           world.showMessage("The Human power increase to "+this.power+"!");
        }
        
    }
            @Override
    public void action(){
            ++this.age;
            if(isSkill){
               --this.power;
               --this.turns;   
               if(this.turns == 0){
                   this.isSkill = false;
                   world.setSkill(false);
               }
            }
            world.changeTurns(this.turns);
            boolean canMove = false;
            int tab[][] = null;
            tab = world.returnPlaceToBorn(y, x);
            switch(direction){
                case 1:
                    newY = y - 1;
                    newX = x;
                    break;
                case 2:
                    newY = y;
                    newX = x + 1;
                    break;
                case 3:
                    newY = y + 1;
                    newX = x;
                    break;
                case 4:
                    newY = y;
                    newX = x - 1;
                    break;
            }
            for(int i = 0;i<tab.length;++i){
                if(tab[i][0] == newY && tab[i][1] == newX){
                    canMove = true;
                    break;
                }
            }
            if(canMove){
                if (world.getOrganisms()[newY][newX] == null) {
                    world.showMessage("The organism of type " + world.showOrganism(this) + " move from position (" + y + ", " + x + ") to position (" + newY + ", " + newX + ")");
                    world.ChangeOrganism(y, x, newX, newY, this);
                } else {
                    super.collision(newY, newX, true);
                    if (world.getOrganisms()[y][x] != null && world.getOrganisms()[newY][newX] == null) {
                        world.showMessage("The organism of type " + world.showOrganism(this) + " move from position (" + y + ", " + x + ") to position (" + newY + ", " + newX + ")");
                        world.ChangeOrganism(y, x, newX, newY, this);
                    }
                }                
            }
            else{
                world.showMessage("Human can't move!");
            }
    }
}
