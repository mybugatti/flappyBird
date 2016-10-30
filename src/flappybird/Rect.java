package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Rect extends JFrame implements ActionListener, KeyListener,MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Rect r;
	public MyPanel panel = new MyPanel();
	public ArrayList<Rectangle> myrect = new ArrayList<Rectangle>();
	public Random rdm = new Random();
	public final int WIDTH = 1200, HEIGHT = 600;
	public int score, chute;
	public Rectangle saut;
	
	public Rect(){
		
		this.add(this.panel); 
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setSize(this.WIDTH,this.HEIGHT);
		this.setVisible(true);
		
		Timer time = new Timer(8, this);
		
		saut = new Rectangle(300, 200, 20, 20);
		
		addRectangle(true);
		addRectangle(true);
		addRectangle(true);
		
		time.start();
		
	}
	
	public void addRectangle(Boolean bol){
		
		int space = 300;
		int width = 100;
		int height = 50 + rdm.nextInt(200);
		
		if(bol){
			myrect.add(new Rectangle(200 + width + myrect.size() *30, HEIGHT -height -120, width, height));
			myrect.add(new Rectangle(200 + width + (myrect.size()-1) *30, 0 ,width, HEIGHT- height - space));				
		
		} else {
			myrect.add(new Rectangle(myrect.get(myrect.size() -1).x + 300, HEIGHT -height -120, width, height));
			myrect.add(new Rectangle(myrect.get(myrect.size() -1).x,0, width, HEIGHT -height - space));			
		}
	}
	
	public void paintRect(Graphics g, Rectangle r){
		g.setColor(Color.BLUE);
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public void paint(Graphics g){

		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.lightGray);
		g.fillRect(0, HEIGHT -120, WIDTH, 120);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT -120, WIDTH, 10);
		
		g.setColor(Color.orange);
		
		g.setColor(Color.MAGENTA);
		g.fillRect(saut.x, saut.y, saut.width, saut.height);
		
		for(Rectangle rec : this.myrect){
			paintRect(g,rec);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",1,100));
		
		g.drawString(String.valueOf(score),WIDTH/2-15,125);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		r = new Rect();
	}

	public void jeSaute(){
		
		chute = 0;
		chute -=15;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int speed = 4;
		
		for(int i=0; i < myrect.size(); i++){
			
			Rectangle column = myrect.get(i);
			column.x -=speed;
			//System.out.println(column.x);
			
			if(column.x + column.width < 0){
				myrect.remove(column);
				if(column.y == 0){
					addRectangle(false);
				}
			}
		}
		 
		if(chute < 1 ){
			chute += 2;
		}
		
		saut.y +=chute;
		
		//System.out.println(e.getSource());
		panel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			jeSaute();
		}
		
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		jeSaute();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
