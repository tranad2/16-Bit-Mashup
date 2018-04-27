import java.awt.*;
import java.util.*;


public class Sprite implements hitBox{
	private int x,y;
	private float dx,dy;
	private Rectangle rec,left,right,top,bottom;
	private boolean sidecollision,ground;
	
    public Sprite() {
    }
    
    public Sprite(int x, int y){
    	this.x = x;
    	this.y = y;
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
    	this.dx=x;
    }
    
    public void setDy(float dy){
    	this.dy=y;
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
	
}