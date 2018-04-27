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

public abstract class enemy{
	public static int facing;
	protected float x, y, dy, dx, width, height;
	protected boolean alive =  true;
	private boolean ground = true;
	private int timer = 0;
	private int t2 = 0;
	private static float gravity =.1f;
	private float currentG = 0;
	private boolean visible;
	private Rectangle top,bottom,left,right,rec;
	static BufferedImage currentImage;
	static BufferedImage quote1, quote2, quote3, quote4, quote1L, quote2L, quote3L, quote4L;
	
	ArrayList<BufferedImage> spriteLoader;
	
    public enemy(int xPos, int yPos) {
    	x = (float)xPos;
    	y = (float)yPos;
    	visible = true;
    	setDx(0);
    	setDy(0);
    	
    	
    }

    public abstract void movedraw(Graphics2D win);
    	
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
    
    
    public boolean isOnGround(){
			if(this.y >= 512-2*getHeight()) return true;
			
			return false;
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

    
    public void dies(){
    	visible = false;
    }
    
    public void respawn(){
    	visible = true;
    }
    
    public boolean isVisible(){
    	return visible;
    }
}
 
