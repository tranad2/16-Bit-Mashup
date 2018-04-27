
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


public class Protag{
	public static int facing;
	protected float x, y, dy, dx, width, height;
	protected boolean jumping = false;
	public Rectangle rec = new Rectangle(0,0,0,0);
	private int timer = 0;
	private static float gravity =.1f;
	private float currentG = 0;
	public static boolean ground = true;
	private boolean visible,sidecollision;
	static BufferedImage currentImage;
	static BufferedImage quote1, quote2, quote3, quote4, quote1L, quote2L, quote3L, quote4L;
	protected Rectangle left,right,top,bottom,leftleg,rightleg;
	
	ArrayList<BufferedImage> spriteLoader;
	
    public Protag(int xPos, int yPos) {
    	x = (float)xPos;
    	y = (float)yPos;
    	ImageIcon ii = new ImageIcon(this.getClass().getResource("images/protag/quoteR1.png"));
    	width = ii.getIconWidth();
	    height = ii.getIconHeight();
    	rec = new Rectangle((int)x, (int)y, (int)width, (int)height);
    	top = new Rectangle((int)x+17 ,(int)y, (int)23+3,3);
    	bottom = new Rectangle((int)x+17,(int)(y+61),(int)23+3,3);
    	left = new Rectangle((int)x+17,(int)y+3,3,(int)58);
    	right = new Rectangle((int)(x+17+23),(int)y+3,3,(int)58);
    	visible = true;
    	setDx(0);
    	setDy(0);
    	spriteLoader = new ArrayList<BufferedImage>();
    	loadImages();
    	currentImage = spriteLoader.get(0);
    	
    	
    }
    public void loadImages(){
    	try{
    		quote1 = ImageIO.read(this.getClass().getResource("images/protag/quoteR1.png"));
    		quote2 = ImageIO.read(this.getClass().getResource("images/protag/quoteR2.png"));
    		quote3 = ImageIO.read(this.getClass().getResource("images/protag/quoteR3.png"));
    		quote4 = ImageIO.read(this.getClass().getResource("images/protag/quoteR4.png"));
    		quote1L = ImageIO.read(this.getClass().getResource("images/protag/quoteL1.png"));
    		quote2L = ImageIO.read(this.getClass().getResource("images/protag/quoteL2.png"));
    		quote3L = ImageIO.read(this.getClass().getResource("images/protag/quoteL3.png"));
    		quote4L = ImageIO.read(this.getClass().getResource("images/protag/quoteL4.png"));
    		
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    	
    	spriteLoader.add(quote1);
    	spriteLoader.add(quote2);
    	spriteLoader.add(quote3);
    	spriteLoader.add(quote4);
    	spriteLoader.add(quote1L);
    	spriteLoader.add(quote2L);
    	spriteLoader.add(quote3L);
    	spriteLoader.add(quote4L);
    	
    }
    
    public void movedrawProtag(Graphics2D win, boolean[] dir){
    	doAnim(dir);
    	if(dir[15]) {
			setDx(1.85f);

		} else if (dir[14]) {
			setDx(-1.85f);

		} else {
			setDx(0);
		}

		 
		
		if(dir[12] && isOnGround()){
			setDy(-5.5f);
		}
		if(getX()<0){
			setX(1);
		} else if(getX()>TheGame.size.getWidth()-width){
			setX((int)(TheGame.size.getWidth()-width-1));
		}
     	this.x += getDx();
     	
     	setDy(getDy() + gravity);
     	this.y += getDy();
     	
     
     	if(Math.abs(getDy()) > 10){
     		if(getDy() >= 0) setDy(10);
     		else setDy(-10);
     	}
     	if(this.y > TheGame.size.getHeight()-105){ 
     		this.y = (float)TheGame.size.getHeight()-105;
     		ground=true;	
     	}
    	
    	rec.setLocation((int)this.x, (int)this.y);
    	top.setLocation((int)x+17 ,(int)y);
    	bottom.setLocation((int)x+17,(int)(y+61));
    	left.setLocation((int)x+17,(int)y+3);
    	right.setLocation((int)(x+17+23),(int)y+3);

    	win.drawImage(currentImage, (int)this.x, (int)this.y, null);
    	
    	
    }
    
    public Rectangle getRec(){
    	return rec;
    }
    
    public Rectangle getTop(){
    	return top;
    }
  
  	public Rectangle getBottom(){
  		return bottom;
  	}
  	
  	public Rectangle getLeft(){
  		return left;
  	}
  	
  	public Rectangle getRight(){
  		return right;
  	}
    
    public int getWidth(){
    	return (int)width;
    }
    
    public int getHeight(){
    	return (int)height;
    }
    
    
    private void doAnim(boolean[] dir){
    	if(dir[14]){
    		
    		if(timer <= 15){
			currentImage = spriteLoader.get(4);
				timer++;
			} else if (timer <= 30){
			currentImage = spriteLoader.get(5);
				timer++;
			} else if (timer <= 45){
			currentImage = spriteLoader.get(6);
				timer++;
			} else if (timer <= 60){
			currentImage = spriteLoader.get(7);
				timer = 0;
			}	
    	} else if(dir[15]){
    		
    			if(timer <= 15){
			currentImage = spriteLoader.get(0);
				timer++;
			} else if (timer <= 30){
			currentImage = spriteLoader.get(1);
				timer++;
			} else if (timer <= 45){
			currentImage = spriteLoader.get(2);
				timer++;
			} else if (timer <= 60){
			currentImage = spriteLoader.get(3);
				timer = 0;
			}	
    	}else{
    		if(currentImage == (spriteLoader.get(0)) || currentImage == (spriteLoader.get(1)) || currentImage == (spriteLoader.get(2)) || currentImage == (spriteLoader.get(3))){
    			currentImage = spriteLoader.get(0);
    			facing = 1;
    		}
    		else{
    			currentImage = spriteLoader.get(4);
    			facing = 0;
    		}
    	}
    }
    
    public void checkSideIntersect(ArrayList<Obstacle> obs){
		for(Obstacle a: obs){
		
			if(a.getLeft().intersects(this.getRight())){
				if(getDx()>0){
					sidecollision=true;
					setDx(0);
					this.x-=1.85f;
				}
			} if (a.getRight().intersects(this.getLeft())){
					sidecollision = true;
					setDx(0);
					this.x+=1.85f;
			}
			
		}
	}
	
	public void checkPlatformIntersect(ArrayList<Obstacle> obs){
		for(Obstacle a: obs){
			if((a.getTop()).intersects(this.getBottom())){
					ground=true;
					setDy(0);
					this.y-=.10f;
					break;
				} else if(!(a.getTop()).intersects(this.getBottom())){ 
						ground = false;
						
						//return ground;		
				} if(a.getBottom().intersects(this.getTop())){
					ground=false;
					setDy(0);
	
					this.y+=.1f;
				}
		}
	}
	
	
    
    public boolean isOnGround(){
			if (this.y >= TheGame.size.getHeight()-105){
				return true;
			}
			else return ground;			
	
		 
	}
		
	public void setDx(float x) {
		dx = x;
	}
	public void setDy(float y) {
		dy = y;
	}
    
    public float getDx() {
		return dx;
	}
	public float getDy() {
		return dy;
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}
	
	public void setX(int x){
		this.x = x; 
	}
	
	public void setY(int y){
		this.y = y;
	}
    
    public boolean isVisible(){
    	return visible;
    }
    
    public void dies(){
    	visible = false;
    }
}
 
