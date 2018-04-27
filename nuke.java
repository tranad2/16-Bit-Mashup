//Don't bother using this.
//Unusable
import java.awt.*;
import javax.swing.*;

public class nuke extends Sprite{

	protected Image image;
	private int x,y,width,height;
	protected Rectangle rec;

    public nuke(int x, int y) {
    	
    	super(x,y);
    	this.x = x;
    	this.y = y;
    	ImageIcon ii = new ImageIcon(this.getClass().getResource("nuke.png"));
    	image = ii.getImage();
    	width = ii.getIconWidth();
    	height = ii.getIconHeight();
    	rec = new Rectangle(x,y,width,height);
    }
    
    public Rectangle getRec(){
    	return rec;
    }
  	
  	public int getHeight(){
  		return height;
  	}
  	
  	public int getWidth(){
  		return width;
  	}
        
    public void draw(Graphics2D gg){
    	gg.drawImage(image,x,y,null);
    		
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
    
}