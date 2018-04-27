import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;

public class Background {
	
	protected int x,y,bgXpos;
	protected float dx,Protagdx;
	protected BufferedImage background,background2,bg1,bg2;
	protected ArrayList<BufferedImage> bgLoader;
	protected boolean bg1On;
	
    public Background() {

    	bgLoader=new ArrayList<BufferedImage>();
    	loadBackground();
    	bg1On=true;
    	background=bgLoader.get(0);
    	background2=bgLoader.get(1);
    }
    
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public float getDx(){
    	return dx;
    }
    
    public void setX(int bgX){
    	x=bgX;
    }
    
    public void setY(int bgY){
    	y=bgY;
    }
    
    public void setDx(float bgDx){
    	dx=bgDx;
    }
    
    public float getProtagDx(){
    	return Protagdx;
    }
    
    public void loadBackground(){
	    	try{
	    		bg1 = ImageIO.read(this.getClass().getResource("images/background/city.jpg"));
	    		bg2 = ImageIO.read(this.getClass().getResource("images/background/city.jpg"));
	    		
	    	}
	    	catch(IOException e){
	    		System.out.println(e);
	    	}
	    	
	    	bgLoader.add(bg1);
	    	bgLoader.add(bg2);
	}
    
    public void drawBG(Graphics2D win){
    	win.drawImage(background, 640, 0, null);
    	win.drawImage(background2,0,0,null);
    }
    
}