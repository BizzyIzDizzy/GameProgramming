package info.bizzyizdizzy.graphics.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	private static Logger logger = Logger.getLogger(GamePanel.class);
	
	private static final int PWIDTH = 500;
	private static final int PHEIGHT = 400;
	
	private Thread animator;
	private boolean running = false;
	private static final int FPS = 100;
	private long period = 1000/FPS;
	private static final int NO_DELAYS_PER_YIELD = 16;	
	private static final int MAX_FRAME_SKIPS = 5;
	
	private boolean gameOver = false;
	private boolean isPaused = false;
	
	private Graphics dbg;
	private Image dbImage = null;
	
	public GamePanel(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		
		setFocusable(true);
		requestFocus();
		readyForTermination();
		//TODO create game components
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//TODO mouse actions
			}
		});
	}
	
	private void readyForTermination(){
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				int kCode = e.getKeyCode();
				if(kCode == KeyEvent.VK_ESCAPE ||
				   kCode == KeyEvent.VK_Q ||
				   kCode == KeyEvent.VK_END ||
				   (kCode == KeyEvent.VK_C && e.isControlDown())){
					running = false;
				}
			}
		});
	}
	
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	
	private void startGame(){
		if(animator == null || !running){
			animator = new Thread(this);
			animator.start();
		}
	}
	
	public void stopGame(){
		running = false;
	}
	
	public void pauseGame(){
		isPaused = true;
	}
	
	public void resumeGame(){
		isPaused = false;
	}
	
	@Override
	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;
		
		beforeTime = System.nanoTime();
		
		running = true;
		
		while(running){
			gameUpdate(); //game state is updated
			gameRender(); //render to buffer
			paintScreen(); //draw buffer to screen
			
			// calculate time to sleep - same game speed on every computer.
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime; //time left in this loop
			
			if(sleepTime > 0){ 
				// some time left in this cycle
				try{
					Thread.sleep(sleepTime/1000000L); // nanoseconds -> milliseconds
				}catch(InterruptedException e){
					logger.error("Error while sleeping!",e);
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			}else{
				//sleep took longer than the period
				excess -= sleepTime;
				overSleepTime = 0L;
				if(++noDelays >= NO_DELAYS_PER_YIELD){
					Thread.yield(); // give another thread a chance to run
					noDelays = 0;
				}
			}
			beforeTime = System.nanoTime();
			
			/* If frame animation is taking too long, update the game state
			 * without rendering it, to get the updates/sec nearer to the required FPS. */
			int skips = 0;
			while((excess > period) && (skips < MAX_FRAME_SKIPS)){
				excess -= period;
				gameUpdate(); //update state but don't render the game
				skips++;
			}
		}
		
		System.exit(0);
	}
	
	private void paintScreen(){
		Graphics g;
		try{
			g = this.getGraphics();
			if(g!=null && dbImage != null){
				g.drawImage(dbImage, 0, 0, null);
			}
			g.dispose();
		}catch(Exception e){
			logger.error("Graphics context error!",e);
		}
	}
	
	private void gameUpdate(){
		if(!isPaused && !gameOver){
			
		}
	}
	
	private void gameRender(){
		if(dbImage == null){
			dbImage = createImage(PWIDTH, PHEIGHT);
			if(dbImage == null){
				logger.error("Double buffer image can not be created!");
				return;
			}else{
				dbg = dbImage.getGraphics();
			}
			
			// clear the background
			dbg.setColor(Color.white);
			dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
			
			//TODO draw game elements
			
			if(gameOver){
				gameOverMessage(dbg);
			}
		}
	}
	
	private void gameOverMessage(Graphics g){
		g.drawString("Game over!", 50, 50);
	}
}
