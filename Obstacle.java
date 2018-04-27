import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.lang.Object;
import java.awt.*;


public class Obstacle extends Sprite{

	protected Rectangle bottom,top,left,right,rec;
	private int x,y,width,height;
	private float dx,dy;
	private Image image;
	private boolean visible=true;
	

    
    public Obstacle(int xPos, int yPos) {
    	x=xPos;
    	y=yPos;
    	ImageIcon i = new ImageIcon(this.getClass().getResource("images/block/block.png"));
    	width = i.getIconWidth();
	    height = i.getIconHeight();
	    image = i.getImage();
    	rec = new Rectangle(x,y,width,height);
    	top = new Rectangle(x ,y, width,3);
    	bottom = new Rectangle(x,y+height,width,3);
    	left = new Rectangle(x,y+3,3,height-3);
    	right = new Rectangle(x+width+3,y+3,3,height-3);
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
  	
  	public int getHeight(){
  		return height;
  	}
  	
  	public int getWidth(){
  		return width;
  	}
        
    public void draw(Graphics2D gg){
    	rec.setLocation(x,y);
    	top.setLocation(x,y);
    	bottom.setLocation(x,y+height);
    	left.setLocation(x,y+2);
    	right.setLocation(x+width,y+2);
    	gg.drawImage(image,x,y,null);
    		
    }
    
    public float getDx(){
    	return dx;
    }
    
    public float getDy(){
    	return dy;
    }
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public void setX(int x){
    	this.x=x;
    }
    
    public void setY(int y){
    	this.y=y;
    }
    
    public void setDx(float dx){
    	this.dx=dx;
    }
    
    public void setDy(float dy){
    	this.dy=dy;
    }
    
    public boolean isVisible(){
    	return visible;
    }
}