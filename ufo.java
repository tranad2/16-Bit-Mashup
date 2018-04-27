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

public class ufo extends enemy{
	
	private Rectangle rec;
	private int width,height, d, timer;
	private Image image;
	private static BufferedImage currentImage,ufo1, ufo2;
	private ArrayList<BufferedImage> spriteLoader;

    public ufo(int x, int y, int dir) {
    	super(x,y);
    	ImageIcon ii = new ImageIcon(this.getClass().getResource("images/ufo/ufo1.png"));
    	width = ii.getIconWidth();
	    height = ii.getIconHeight();
	   	spriteLoader = new ArrayList<BufferedImage>();
    	loadImages();
    	currentImage = spriteLoader.get(0);
    	rec = new Rectangle((int)this.x, (int)this.y, width, height);
    	timer = 0;
    	setDy(1.5f);
    }
    
    public void loadImages(){
    	try{
    		ufo1 = ImageIO.read(this.getClass().getResource("images/ufo/ufo1.png"));
    		ufo2 = ImageIO.read(this.getClass().getResource("images/ufo/ufo2.png"));
    		
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    	spriteLoader.add(ufo1);
    	spriteLoader.add(ufo2);
    	
    }
    
    public void movedraw(Graphics2D win){
    	if(d==1){
    		setDx(2.25f);
    	} else if(d==0){
    		setDx(-2.25f);
    	}
    	if(getX()<-2*width){
    		d=1;
    	} else if(getX()>TheGame.size.getWidth()+width){
    		d=0;
    	}
    	x+=dx;
		y+=dy;
		if(timer%400==100) setDx(-dx);
		if(timer%300 == 100) setDy(-dy);
		
		if(this.y<0 || this.y>410) setDy(-dy);

    	rec.setLocation((int)x,(int)y);
    	if(d==0){
    		currentImage=spriteLoader.get(0);
    	} else if(d==1){
    		currentImage=spriteLoader.get(1);
    	} else
    		currentImage=spriteLoader.get(0);
    	win.drawImage(currentImage, (int)this.x, (int)this.y, null);
    	timer++;
    	if(timer>1000) timer = 0;
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