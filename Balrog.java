import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.lang.Object;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class Balrog extends enemy{

	public static int facing;

	protected float x, y, dy, dx;
	protected boolean alive =  true;
	private boolean ground = true;
	private int timer = 0;
	private int t2 = 0;
	private static float gravity =.1f;
	private float currentG = 0;
	private boolean visible;
	private Rectangle top,bottom,left,right,rec;
	protected static int health = 10;
	private BufferedImage bal1,bal2,bal3,bal4,currentImage;
	private int d,width,height;
	private ArrayList<BufferedImage> spriteLoader;
	
    public Balrog(int x, int y) {
    	super(x,y);
    	ImageIcon i = new ImageIcon(this.getClass().getResource("images/Balrog/balrog1.png"));
    	width=i.getIconWidth();
    	height=i.getIconHeight();
    	spriteLoader = new ArrayList<BufferedImage>();
    	loadImages();
    	currentImage = spriteLoader.get(0);
    	rec = new Rectangle(x,y,width,height);
    	
    }
    
    public void movedraw(Graphics2D win){
    	doAnim();
    	if(t2 <= 600){
    		setDx(1.5f);
    		t2++;
    	}else if(t2 <= 1200){
    		setDx(-1.5f);
    		t2++;
    	}else t2= 0;
    	

		
		if(t2%400 == 60 && ground){
			setDy(-8f);
		}
		if(t2%200<70 && t2%200>50){
			if(getDx()>=0)setDx(5f);
			else setDx(-5f);
		}

     	this.x += getDx();
     	
     	setDy(getDy() + gravity);
     	this.y += getDy();
     	
     
     	if(Math.abs(getDy()) > 10){
     		if(getDy() >= 0) setDy(10);
     		else setDy(-10);
     	}
     	if(this.y > TheGame.size.getHeight()-100){ 
     		this.y = (int)TheGame.size.getHeight()-100;
     		
     	}
    	rec.setLocation((int)this.x, (int)this.y);
    	win.drawImage(currentImage,(int)x,(int)y,null);
    }
    
    public void loadImages(){
    	try{
    		bal1 = ImageIO.read(this.getClass().getResource("images/Balrog/balrog1.png"));
    		bal2 = ImageIO.read(this.getClass().getResource("images/Balrog/balrog2.png"));
    		bal3 = ImageIO.read(this.getClass().getResource("images/Balrog/balrog3.png"));
    		bal4 = ImageIO.read(this.getClass().getResource("images/Balrog/balrog4.png"));
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    	spriteLoader.add(bal1);
    	spriteLoader.add(bal2);
    	spriteLoader.add(bal3);
    	spriteLoader.add(bal4);
    	
    }
    
    private void doAnim(){
    	if(getDx()<0){
			if(timer <= 15){
				currentImage = spriteLoader.get(2);
				timer++;
			} else if (timer <= 30){
				currentImage = spriteLoader.get(3);
				timer++;
			} else if (timer <= 60){
				timer = 0;
			}	
    	} else if(getDy()>=0){
    		if(timer <= 15){
				currentImage = spriteLoader.get(0);
				timer++;
			} else if (timer <= 30){
				currentImage = spriteLoader.get(1);
				timer++;
			} else if (timer <= 60){
				timer = 0;
			}
    	}
    }
    public Rectangle getRec(){
    	return rec;
    }
    
    public void hit(){
    	health--;
    }
    
    public int health(){
    	return health;
    }
}