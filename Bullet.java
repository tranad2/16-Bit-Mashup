import java.awt.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class Bullet {

	    private float x, y, bulletSpeed, initialX,width,height;
	    private Image image;
	    protected boolean visible,collision;
	    protected BufferedImage currentImage,bullet1,bullet2,bulletL1,bulletL2;
	    protected ArrayList<BufferedImage> spriteLoader;
	    protected int timer=0;
	    protected Rectangle r;


	    public Bullet(float x, float y, float dx) {
	
	        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/bullet/bullet1.png"));
	        image = ii.getImage();
	        width = ii.getIconWidth();
	        height = ii.getIconHeight();
	        bulletSpeed = dx;
	        visible = true;
	        initialX = x;
	        this.x = x;
	        this.y = y;
	        r = new Rectangle((int)this.x,(int)this.y,(int)width,(int)height);
	        spriteLoader = new ArrayList<BufferedImage>();
    		loadImages();
	        currentImage = spriteLoader.get(0);
	    }
	
	    public int getX() {
	        return (int)x;
	    }
	    
	    private void doAnim(){
	    	if(isVisible()){
	    		if(bulletSpeed>0){
	    			if(timer <= 10){
						currentImage = spriteLoader.get(0);
						timer++;
					} else if (timer <= 20){
						currentImage = spriteLoader.get(1);
						timer++;
			  		} else if (timer <= 30){
			  			timer=0;
			  		}
	    		}
		  		if(bulletSpeed<0){
		  			if(timer <= 10){
						currentImage = spriteLoader.get(2);
						timer++;
					} else if(timer<=20){
						currentImage=spriteLoader.get(3);
						timer++;
					} else if(timer<=30){
						timer=0;
					}
		  		}
	    	}
	    }
	    public void loadImages(){
	    	try{
	    		bullet1 = ImageIO.read(this.getClass().getResource("images/bullet/bullet1.png"));
	    		bullet2 = ImageIO.read(this.getClass().getResource("images/bullet/bullet2.png"));
	    		bulletL1 = ImageIO.read(this.getClass().getResource("images/bullet/bulletL1.png"));
	    		bulletL2 = ImageIO.read(this.getClass().getResource("images/bullet/bulletL2.png"));
	    		
	    	}
	    	catch(IOException e){
	    		System.out.println(e);
	    	}
	    	
	    	spriteLoader.add(bullet1);
	    	spriteLoader.add(bullet2);
	    	spriteLoader.add(bulletL1);
	    	spriteLoader.add(bulletL2);
	    }
	
	    public int getY() {
	        return (int)y;
	    }
	
	    public boolean isVisible() {
	        return visible;
	    }
	
	    public void move(Graphics2D win) {	    		
	    	x += bulletSpeed;
	        visible=true;
	        doAnim();
	        r.setLocation((int)this.x, (int)this.y);
	        win.drawImage(currentImage, (int)this.x, (int)this.y, null);
	    }
	    
	    public Rectangle getRec(){
	    	return new Rectangle((int)x,(int)y,(int)width,(int)height);
	    }
	    
	    public Image getImage(){
	    	return image;
	    }
}
