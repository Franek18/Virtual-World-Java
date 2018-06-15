/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import wirtualny_swiat_java.World;
/**
 *
 * @author Franciszek
 */
public abstract class Organism {
    	protected int power, initiative, age, x, y;
	protected World world;
        private boolean isMove = false;
        protected int newX;
        protected int newY;
        
	public Organism(int power, int initiative, int y, int x, World world){
            this.power = power;
            this.initiative = initiative;
            this.age = 0;
            this.y = y;
            this.x = x;
            this.world = world;
        }
	public int getPower(){
            return this.power;
        }
	public void setPower(int newPower){
            this.power = newPower;
        }
	public int getInitiative(){
            return this.initiative;
        }
	public void setInitiative(int newInitiative){
            this.initiative = newInitiative;
        }
	public int getAge(){
            return this.age;
        }
	public void setAge(int newAge){
            this.age = newAge;
        }
	public int getX(){
            return this.x;
        }
	public void setX(int newX){
            this.x = newX;
        }
	public int getY(){
            return this.y;
        }
	public void setY(int newY){
            this.y = newY;
        }
	public boolean getIsMove(){
            return this.isMove;
        }
	public void setIsMove(boolean answer){
            this.isMove = answer;
        }  
        public void missAttack(int y, int x){
            newY = y;
            newX = x;
        }        
        public String printInfo(){
            String type = world.showOrganism(this);
            String info = type+" "+this.y+" "+this.x+" "+this.power+" "+this.age+"\n";
            return info;
        }
	public abstract void action();
	public abstract void collision(int newY, int newX, boolean isAttack);
	//void rysowanie();        
}
