import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.lang.Object;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class bat extends enemy{
	
	private Rectangle rec;
	private int width,height, d,timer;
	private int t = 0;
	private Image image;
	private static BufferedImage currentImage,bat1,bat2,bat3,bat4,bat5,bat6;
	private ArrayList<BufferedImage> spriteLoader;

    public bat(int x, int y, int dir) {
    	super(x,y);
    	ImageIcon ii = new ImageIcon(this.getClass().getResource("images/bat/bat1.png"));
    	width = ii.getIconWidth();
	    height = ii.getIconHeight();
	   	spriteLoader = new ArrayList<BufferedImage>();
    	loadImages();
    	currentImage = spriteLoader.get(0);
    	rec = new Rectangle((int)this.x, (int)this.y, width, height);
    }
    
    public void loadImages(){
    	try{
    		bat1 = ImageIO.read(this.getClass().getResource("images/bat/bat1.png"));
    		bat2 = ImageIO.read(this.getClass().getResource("images/bat/bat2.png"));
    		bat3 = ImageIO.read(this.getClass().getResource("images/bat/bat3.png"));
    		bat4 = ImageIO.read(this.getClass().getResource("images/bat/bat4.png"));
    		bat5 = ImageIO.read(this.getClass().getResource("images/bat/bat5.png"));
    		bat6 = ImageIO.read(this.getClass().getResource("images/bat/bat6.png"));
    		
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    	spriteLoader.add(bat1);
    	spriteLoader.add(bat2);
    	spriteLoader.add(bat3);
    	spriteLoader.add(bat4);
    	spriteLoader.add(bat5);
    	spriteLoader.add(bat6);
    	
    }
    
    private void doAnim(){
    	if(d==0){
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
				timer = 0;
			}	
    	} else if(d==1){
    		if(timer <= 15){
				currentImage = spriteLoader.get(3);
				timer++;
			} else if (timer <= 30){
				currentImage = spriteLoader.get(4);
				timer++;
			} else if (timer <= 45){
				currentImage = spriteLoader.get(5);
				timer++;
			} else if (timer <= 60){
				timer = 0;
			}
    	}
    }
    
    public void getDirection(Protag p){
    	if(p.getX()>=getX()){
    		d=1;
    		setDx(.5f);
    	} else if(p.getX()<getX()){
    		d=0;
    		setDx(-.5f);
    	}
    	if(p.getY()>getY()){
    		setDy(.5f);
    	} else if(p.getY()<getY()){
    		setDy(-.5f);
    	}
    }
    
    public void movedraw(Graphics2D win){
    	doAnim();

    	
    	if(getX()<-1*width){
    		d=1;
    	} else if(getX()>TheGame.size.getWidth()){
    		d=0;
    	}
    	
    	x+=dx;
    	y+=dy;

		

    	rec.setLocation((int)x,(int)y);
    	win.drawImage(currentImage, (int)this.x, (int)this.y, null);
    	
    }
    
    public Rectangle getRec(){
    	return rec;
    }
    
    public int getWidth(){
    	return width;
    }
    
    public int getHeight(){
    	return height;
    }
    
}