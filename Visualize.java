/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualny_swiat_java;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import Organism.Organism;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franciszek
 */

public class Visualize extends JFrame{  
    private int posX;
    private int posY;
    private Point2D current;
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JTextField inputN;
    private JTextField inputM;
    private JButton switchW;
    private JButton newGame;
    private JButton saveGame;
    private JButton loadGame;
    private JButton nextTurn;
    private JButton skill;
    private JLabel x;
    private JLabel y;
    private JLabel move;
    private Toolkit tk;
    private Dimension dim;
    private JComboBox<String> legend = new JComboBox<>();
    private JTextArea logger = new JTextArea(15,20);
    private World world;
    private boolean isHex = false;
    private BoardComponent boardGame;
    
    public Visualize(String title, int width, int height){
        this.setTitle(title);
        this.setSize(width, height);
        tk = Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();
        
        posX = ((dim.width - this.getWidth())/ 2);
        posY = ((dim.height - this.getHeight())/ 2);
        
        this.setLocation(posX,posY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        panel.setFocusable(false);
        panel2.setFocusable(false);
        logger.setFocusable(false);
        legend.setFocusable(false);

        
        MouseHandler mouse = new MouseHandler();
        this.addMouseListener(mouse);
        

        
        Choose choose = new Choose();        
        legend.addActionListener(choose);        
        this.fillLegend();
        //panel.add(legend);
        logger.setLineWrap(true);
        JScrollPane scrollbar1 = new JScrollPane(logger, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbar1.setFocusable(false);
        panel2.add(scrollbar1,BorderLayout.SOUTH);
        this.add(panel2);
        switchW = new JButton();
        switchW.setFocusable(false);
        changeWorld change = new changeWorld();
        switchW.addActionListener(change);
        if(isHex){
        switchW.setText("Change to Grid");            
        }
        else{
         switchW.setText("Change to Hex");   
        }
        panel.add(switchW);
        PressTurn turnListener = new PressTurn();
        nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(turnListener);
        
        newGame = new JButton("New Game");
        newGame.setToolTipText("Press to set size of world");    
        PressNew newListener = new PressNew();
        newGame.addActionListener(newListener);
        panel.add(newGame);
        
        loadGame = new JButton("Load Game");
        loadGame.setToolTipText("Press to load one of the saved games");   
        PressLoad loadListener = new PressLoad();
        loadGame.addActionListener(loadListener);        
        panel.add(loadGame);
        
        skill = new JButton();
        skill.setText("Use skill");
        Activator activator = new Activator();
        skill.addActionListener(activator);
        
        PressSave save = new PressSave();
        saveGame = new JButton("Save Game");
        saveGame.addActionListener(save);
        
        move = new JLabel("Human direction: ");
        y = new JLabel("Y:");
        panel.add(y);        
        inputN = new JTextField("0",5);
        panel.add(inputN);
        
        x = new JLabel("X:");
        panel.add(x);
        inputM = new JTextField("0",5);
        panel.add(inputM);
        
        this.add(panel,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);
        
        y.setFocusable(false);
        x.setFocusable(false);
        move.setFocusable(false);
        newGame.setFocusable(false);
        saveGame.setFocusable(false);
        loadGame.setFocusable(false);
        nextTurn.setFocusable(false);
        x.setFocusable(false);
        y.setFocusable(false);
        move.setFocusable(false);        
        skill.setFocusable(false);
        this.setVisible(true);
        
        //this.DrawMap();
/*        JLabel label1 = new JLabel("Hello World!");
        panel.add(label1);
        
        label1.setText("We are going to end with Hello World's welcomes!");
        label1.setToolTipText("Previously text: Hello World!");
        
        JButton button1 = new JButton("First button");
        button1.setText("New button");
        button1.setToolTipText("Previously text: First button");
       
        panel.add(button1);
        
        JTextField textField1 = new JTextField("Write sth", 15);
        textField1.setText("Write sth strange");
        textField1.setColumns(10);
        textField1.setToolTipText("Previously text: Write sth");
        panel.add(textField1);
        
        JTextArea textArea1 = new JTextArea(15,20);
        textArea1.setText("Write something again!");
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollbar1);
*/        

        
        
    }
    public void changeSkillButton(int turns){
       if(turns == 0){
           skill.setText("Use skill");
       }
       else{
           skill.setText(turns+" turns");
       }
    }
    
    private class MoveEvent implements KeyListener{
        
        public MoveEvent(){
            
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {         
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    move.setText("Human direction: UP");
                    world.useHuman(1);
                    break;
                case KeyEvent.VK_RIGHT:
                    move.setText("Human direction: RIGHT");
                    world.useHuman(2);
                    break;
                case KeyEvent.VK_DOWN:
                    move.setText("Human direction: DOWN");
                    world.useHuman(3);
                    break;
                case KeyEvent.VK_LEFT:
                    move.setText("Human direction: LEFT");
                    world.useHuman(4);
                    break;                    
            }            
        }
        
    }
    public void fillLegend(){
        legend.insertItemAt("Wolf", 0);
        legend.insertItemAt("Sheep", 0);  
        legend.insertItemAt("Fox", 0); 
        legend.insertItemAt("Turtle", 0);
        legend.insertItemAt("Antelope", 0);
        legend.insertItemAt("Grass", 0);
        legend.insertItemAt("Dandelion", 0);
        legend.insertItemAt("Guarana", 0);
        legend.insertItemAt("Belladona", 0);
        legend.insertItemAt("Hogweed", 0);
        legend.insertItemAt("Human", 0);
    }
    private class changeWorld implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            if(!isHex){
                switchW.setText("Change to Grid");
                isHex = true; 
            }
            else{
                switchW.setText("Change to Hex");
                isHex = false;
            }
        }        
    }    
    private class Activator implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            world.setAbility();
        }        
    }    
    private class PressTurn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            clearLog();
            world.executeTurn();
        }        
    }
    private class PressNew implements ActionListener{
        
        public PressNew(){
        }
        
        @Override
        public void actionPerformed(ActionEvent event){
            int N, M;
            N = Integer.parseInt(inputN.getText());
            M = Integer.parseInt(inputM.getText());
            if (N > 0 && M > 0) {
                setWorld(N, M);
            }
        }
    }
    
    private class PressLoad implements ActionListener{
 
        public PressLoad(){
        }        
        @Override
        public void actionPerformed(ActionEvent event){
            int N, M;
            N = Integer.parseInt(inputN.getText());
            M = Integer.parseInt(inputM.getText());
            if (N > 0 && M > 0) {
                try {
                    loadGame(N, M);
                } catch (IOException ex) {
                    //Logger.getLogger(Visualize.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }    
    
    private class PressSave implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            try {
                saveGame(world.getN(),world.getM());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Visualize.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    private class Choose implements ActionListener{
        public Choose(){
        }        
        @Override
        public void actionPerformed(ActionEvent event){
//            current = find(event.getPoint());
            world.setOrg(legend.getItemAt(legend.getSelectedIndex()));      
            boardGame.remove(legend);
            boardGame.repaint();

            world.setOrganism(posY, posX);
        }        
/*        public void mouseClicked(MouseEvent event) {
// Usunięcie kwadratu w wyniku jego dwukrotnego kliknięcia
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2) {
                remove(current);
            }
        }
        */ 
    }
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {
// Usunięcie legendy z planszy == rezygnacja z dodania organimzu
            if (event.getClickCount() >= 2) {
                boardGame.remove(legend);
            }
        }
    }    
    public void setWorld(int N, int M){
        if(isHex){
            world = new HexWorld(N,M,this);            
        }
        else{
            world = new GridWorld(N,M,this);
        }
        boardGame = new BoardComponent(N,M,world);
        boardGame.setFocusable(false);
        this.add(boardGame,BorderLayout.CENTER);
        MoveEvent moveEvent = new MoveEvent();
        this.addKeyListener(moveEvent);
        inputN.setFocusable(false);
        inputM.setFocusable(false);
        world.setBoard(boardGame);
        panel.remove(inputN);
        panel.remove(inputM);
        panel.remove(newGame);
        panel.remove(loadGame);     
        panel.remove(y);
        panel.remove(x);
        panel.remove(legend);
        panel.remove(switchW);
        panel.add(saveGame);
        panel.add(nextTurn);
        panel.add(skill);
        panel.add(move);
        //legend.setVisible(false);
        panel.add(legend);
        this.setSize(1000,1000);
        posX = ((dim.width - this.getWidth())/ 2);
        posY = ((dim.height - this.getHeight())/ 2);        
        this.setLocation(posX,posY);
        this.setVisible(true);
    }
    
    public void AddToLog(String text){
        logger.append(text+"\n");
    }
    public void clearLog(){
        logger.setText(null);
    }
    
    public void chooseOrganism(Point2D p, int bornY, int bornX){
        //legend.setVisible(true);
       // panel.add(legend);
        posY = bornY;
        posX = bornX;
        legend.setLocation((int)p.getX(), (int)p.getY());
        boardGame.add(legend);
        //System.out.println(Org);        
    }
    public Organism[][] getOrganism(){
        return boardGame.getOrganisms();
    }
    
    public void loadGame(int N, int M) throws IOException{
        Scanner in = new Scanner(Paths.get(N+"x"+M+".txt"));
        String type = in.next();
        if(type.equals("Hex")){
            isHex = true;
        }
        else if(type.equals("Grid")){
            isHex = false;
        }
        System.out.print(type);
        this.setWorld(N, M);
        world.load(in);
    }
    
    public void saveGame(int N, int M) throws FileNotFoundException{
        PrintWriter out = new PrintWriter(N+"x"+M+".txt");
        world.save(out);
        out.close();
    }
}
