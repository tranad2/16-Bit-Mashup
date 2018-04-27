import java.awt.event.*;
import static java.lang.Character.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import javax.sound.sampled.*;


public class Board extends GameDriver{
	
	private boolean vunerable = true;
	private Rectangle clear;
	private Protag quote, curly;
	private ArrayList<Bullet> bullets;
	private ArrayList<nuke> nuk;
    private int bulletDelay = 40;
    private Background bg;
    private int gameState;
    private File soundFile;
    private Clip clip;
    private int score,health, deadEnemies, timer2, level;
    private String[] audioclip;
    private SoundDriver sounds;
    private ArrayList<Obstacle> obs,floor;
	private ArrayList<enemy> e;
	private enemy boss;
	private int timer = 0;
	private Random rnd;
	private Font f;
	
    public Board() {
    	gameState=0;
    	bullets = new ArrayList<Bullet>();
    	floor = new ArrayList<Obstacle>();
    	obs = new ArrayList<Obstacle>();
    	e = new ArrayList<enemy>();
    	nuk = new ArrayList<nuke>();
    	audioclip = new String[1];
    	audioclip[0]="Cave Story Last Battle.wav";
    	sounds = new SoundDriver(audioclip);
    	bg = new Background();
    	quote = new Protag((int)(TheGame.size.getWidth()/2),300);
    	health=200;
    	score = 0;
    	buildObs();
    	deadEnemies = 0;
    	rnd = new Random();
    	level=0;
    	sounds.loop(0);
    }
    
    public void splashPage(Graphics2D win){
    	try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			f = Font.createFont(Font.TRUETYPE_FONT, new File("Font/victor-pixel.ttf"));
			ge.registerFont(f);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		Rectangle end = new Rectangle(1280,480);
    	win.setColor(Color.BLACK);
    	win.fill(end);
    	win.draw(end);
    	win.setColor(Color.WHITE);
		win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,50));
		win.drawString("16-Bit Mashup",((int)TheGame.size.getWidth()/2)-240,(int)TheGame.size.getHeight()/2);
		win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,30));
		win.drawString("By Nicolas Manders and Alex Tran",((int)TheGame.size.getWidth()/2)-350,((int)TheGame.size.getHeight()/2)+30);
		win.drawString("Controls: [LEFT][UP][RIGHT] < Move Your Player",((int)TheGame.size.getWidth()/2)-390,((int)TheGame.size.getHeight()/2)+60);
		win.drawString("[SPACE] < Shoot",((int)TheGame.size.getWidth()/2)-190,((int)TheGame.size.getHeight()/2)+90);
		win.drawString("Goal: Shoot and Survive!",((int)TheGame.size.getWidth()/2)-260,((int)TheGame.size.getHeight()/2)+120);
		win.drawString("Blocks will heal everytime they change!",((int)TheGame.size.getWidth()/2)-340,((int)TheGame.size.getHeight()/2)+150);
		win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,40));
		win.drawString("Hit [SPACE] To Start",((int)TheGame.size.getWidth()/2)-260,((int)TheGame.size.getHeight()/2)+180);
		ImageIcon i = new ImageIcon(this.getClass().getResource("quote.gif"));
		Image iquote = i.getImage();
		ImageIcon ii = new ImageIcon(this.getClass().getResource("curly-brace.gif"));
		Image icurly = ii.getImage();
		ImageIcon iii = new ImageIcon(this.getClass().getResource("images/bat/bat1.png"));
		Image ibat = iii.getImage();
		ImageIcon iv = new ImageIcon(this.getClass().getResource("images/ufo/ufo1.png"));
		Image iufo = iv.getImage();
		ImageIcon v = new ImageIcon(this.getClass().getResource("images/Balrog/balrog1.png"));
		Image ibalrog = v.getImage();
		ImageIcon vi = new ImageIcon(this.getClass().getResource("images/block/block.png"));
		Image iblock = vi.getImage();
		win.drawImage(iquote,20,50,null);
		win.drawImage(icurly,183,50,null);
		win.drawImage(ibat,203*2,50,null);
		win.drawImage(iufo,233*3,50,null);
		win.drawImage(ibalrog,233*4,50,null);
		win.drawImage(iblock,233*5,50,null);
		win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,18));
		win.drawString("You",30,150);
		win.drawString("Coming Soon",155,150);
		win.drawString("Avoid at all cost",203*2-30,150);
		win.drawString("Hard to catch",233*3-20,150);
		win.drawString("Balrog (Squishy Tank)",233*4-70,150);
		win.drawString("I Help (or not)",233*5-50,150);
    	if(keys[11]){
    		gameState=1;
    	}
    }
    
    public void endPage(Graphics2D win){
    	try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			f = Font.createFont(Font.TRUETYPE_FONT, new File("Font/victor-pixel.ttf"));
			ge.registerFont(f);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
    	Rectangle end = new Rectangle(1280,480);
    	win.setColor(Color.BLACK);
    	win.fill(end);
    	win.draw(end);
    	win.setColor(Color.WHITE);
    	win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,50));
    	win.drawString("oh teh noes! You died!",((int)TheGame.size.getWidth()/2)-330,(int)TheGame.size.getHeight()/2);
    	win.setFont(new Font("Victor's Pixel Font",Font.PLAIN,30));
    	win.drawString("Score: "+score,((int)TheGame.size.getWidth()/2)-330,(int)TheGame.size.getHeight()/2+40);
    	win.drawString("Enemies Killed: "+deadEnemies,((int)TheGame.size.getWidth()/2)+30,(int)TheGame.size.getHeight()/2+40);
    	win.drawString("Highest Level: "+level,((int)TheGame.size.getWidth()/2)-190,(int)(TheGame.size.getHeight()/2)+70);
    	win.drawString("Want to play again? Hit [ENTER]",((int)TheGame.size.getWidth()/2)-275,(int)TheGame.size.getHeight()/2+120);
    	ImageIcon i = new ImageIcon(this.getClass().getResource("quote.gif"));
		Image iquote = i.getImage();
		ImageIcon ii = new ImageIcon(this.getClass().getResource("curly-brace2.gif"));
		Image icurly = ii.getImage();
		win.drawImage(iquote,100,100,null);
		win.drawImage(icurly,(int)TheGame.size.getWidth()-158,100,null);
    }
    
    public void buildObs(){

    	for(int obsNum=0;obsNum<10;obsNum++){
	    	obs.add(new Obstacle(obsNum*(int)(TheGame.size.getWidth()/10),390));
	    }
    }
    
    
    
    
    public void draw(Graphics2D win) {
    	Random rng = new Random();
    	if(gameState==0){
    		splashPage(win);
    	}
    	else if(gameState==1){
	    	bg.drawBG(win);
	    	
	    	if(e.size()==0){
	    		level++;
	    		for(int l=0;l<level;l++){
	    			int enemyType = rng.nextInt(2);
	    			if(enemyType==0){
	    				e.add(new ufo(rnd.nextInt(1190),rnd.nextInt(200),rng.nextInt(2)));
	    			} else if(enemyType==1){
	    				e.add(new bat(rnd.nextInt(1190),rnd.nextInt(200),rng.nextInt(2)));
	    			}
	    			
	    		}
	    		for(enemy en: e){
	    				en.dx+=(.1*level);
	    			}
	    		if(level%5==0){
	    			e.add(new Balrog((int)TheGame.size.getWidth()/2,rnd.nextInt(430)));
	    		}
	    		if(level%15==0){
	    			e.add(new Balrog((int)(TheGame.size.getWidth()/(1.5)),rnd.nextInt(430)));
	    		}
	    	}
	    	
	    	for(Obstacle o: obs){
	    		o.draw(win);
	    	}
	    	if(quote.isVisible()){
    			quote.movedrawProtag(win, keys);
    			shoot(win);
    			quote.checkSideIntersect(obs);
    			quote.checkPlatformIntersect(obs);
	    	}
	    	if(health<=0){
	    		quote.dies();
	    		health=0;
	    		gameState=2;
	    	}	    	
	    	
	    	for(enemy en:e){
	    		if(en instanceof bat) ((bat)en).getDirection(quote);
	    		en.movedraw(win);
	    	if(quote.getTop().intersects(en.getRec())||quote.getBottom().intersects(en.getRec())||quote.getLeft().intersects(en.getRec())||quote.getRight().intersects(en.getRec())){
	    			if(vunerable){ 
	    			if(!(en instanceof ufo)) health-=10;
	    				else if(en instanceof bat) health-=2;
	    				else health-=3;
	    			}
	    			vunerable = false;
	    			if(quote.getLeft().intersects(en.getRec()) ){quote.x+=10f;}
	    			if(quote.getRight().intersects(en.getRec())) {quote.x-=10f;}
	    			if(quote.getTop().intersects(en.getRec())&& en instanceof Balrog){quote.dy=-7f; quote.y+=1.1f;}
	    			if(quote.getTop().intersects(en.getRec())){quote.setDy(0); quote.y+=5f;}
	    			if(quote.getBottom().intersects(en.getRec())){quote.y-=5f;}
	    			vunerable = true;
	    		}
	    		
	    	}
	    	for(int n=0;n<nuk.size();n++){
	    		nuk.get(n).draw(win);
	    		if(quote.getTop().intersects(nuk.get(n).getRec())||quote.getBottom().intersects(nuk.get(n).getRec())||quote.getLeft().intersects(nuk.get(n).getRec())||quote.getRight().intersects(nuk.get(n).getRec())){
	    			score+=e.size()*100;
	    			e.clear();
	    			nuk.clear();
	    			
	    		}
	    	}
	    	for(int k = 0; k < bullets.size(); k++) {
	    		Bullet b = (Bullet)bullets.get(k);
				if(b.isVisible()){
					b.move(win);
				 	if (b.getX() > quote.getX()+300||b.getX()<quote.getX()-300) {
						bullets.remove(k);
						k--;
					}
					for(int i = 0; i<e.size();i++){
						enemy en = (enemy)e.get(i);
						if (b.getRec().intersects(en.getRec())){
							if(!(en instanceof Balrog)){
							e.remove(i);
							i--;
							deadEnemies++;
							score+=100;
							}
							else ((Balrog)en).hit();
							if(en instanceof Balrog &&((Balrog)en).health() ==0) {
							e.remove(i);
							i--;
							deadEnemies++;
							score+=300;
							}
							bullets.remove(k);
							k--;
						}
					}
				}
			}
	    	timer++;
	    	if(timer == 1500){
	    		for(Obstacle o : obs){
	    			
	    			o.setY(rnd.nextInt(430));
	    			o.setX(rnd.nextInt(1190));
	    			
	    			health+=5;
	    			if(health>500) health = 500;
	    		}
	    		int r = rnd.nextInt(10);
	    		if(r==5){
	    				nuk.add(new nuke(rnd.nextInt(1000)+50,rnd.nextInt(330)+50));
	    			}
	    		timer = 0;
	    	}
			win.setFont(new Font(Font.SANS_SERIF,2,20));
    		win.drawString("Health: "+health + " Score: " + score + " Level: " + level,100,100);
    	}
    	else if(gameState==2){
    		endPage(win);
    		if(keys[10]){
    		reset(win);
    		}
    	}
    }
    public void reset(Graphics2D win){
    	bullets.clear();
    	floor.clear();
    	obs.clear();
    	buildObs();
    	nuk.clear();
    	e.clear();
    	splashPage(win);
    	quote = new Protag((int)(TheGame.size.getWidth()/2),300);
    	health=200;
    	score = 0;
    	deadEnemies = 0;
    	level=0;
    	gameState=0;
    }
    public void shoot(Graphics2D win){
    	bulletDelay++;
    	float dx = 3.9f;
    	if (keys[11]&&bulletDelay>40) {
    		bulletDelay=0;
    		if(Protag.facing==1){
        		bullets.add(new Bullet(quote.getX(), quote.getY()+quote.getHeight()/2,dx));
    		}
        	else if(Protag.facing==0){
        		bullets.add(new Bullet(quote.getX(), quote.getY()+quote.getHeight()/2,-dx));
        	}
        }else if(keys[12] || (keys[12] &&keys[14])){
        }
    }
}