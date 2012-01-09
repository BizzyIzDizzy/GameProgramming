package info.bizzyizdizzy.animationframework.lwjgl.samples;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class FullscreenExample {
	
	float x = 400, y = 300;
	
	float rotation = 0;
	
	long lastFrame;
	
	int fps;
	
	long lastFPS;
	
	boolean vsync;
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL();
		getDelta();
		lastFPS = getTime();
		
		while(!Display.isCloseRequested()){
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public void update(int delta){
		rotation += 0.15f * delta;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			x -= 0.35f * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			x += 0.35f * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			y -= 0.35f * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			y += 0.35f * delta;
		}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_F){
					setDisplayMode(800,600,!Display.isFullscreen());
					System.out.println("Fullscreen set to "+Display.isFullscreen());
				}else if(Keyboard.getEventKey() == Keyboard.KEY_V){
					vsync = !vsync;
					Display.setVSyncEnabled(vsync);
					System.out.println("VSync set to "+vsync);
				}
			}
		}
		
		if(x < 0){
			x = 0;
		}
		if(x > 800){
			x = 800;
		}
		if(y < 0){
			y = 0;
		}
		if(y > 600){
			y = 600;
		}
		
		updateFPS();
	}
	
	public void setDisplayMode(int width, int height, boolean fullscreen){
		if((Display.getDisplayMode().getWidth() == width) &&
			(Display.getDisplayMode().getHeight() == height) &&
			(Display.isFullscreen() == fullscreen)){
				return;
		}
		
		try{
			DisplayMode targetDisplayMode = null;
			
			if(fullscreen){
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				
				for(int i = 0; i<modes.length; i++){
					DisplayMode current = modes[i];
					
					if((current.getWidth() == width) && (current.getHeight() == height)){
						if((targetDisplayMode == null) || (current.getFrequency() >= freq)){
							if((targetDisplayMode == null || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))){
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
						if((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
								(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())){
							targetDisplayMode = current;
							break;
						}
					}
					
				}
			}else{
				targetDisplayMode = new DisplayMode(width, height);
			}
			
			if(targetDisplayMode == null){
				System.out.println("Failed to find value mode: "+width+"x"+height+" f="+fullscreen);
				return;
			}
			
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
		}catch(LWJGLException e){
			System.out.println("Unable to set up mode "+width+"x"+height+" fullscreen="+fullscreen);
		}		
	}
	
	public int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void updateFPS(){
		if(getTime() - lastFPS > 1000){
			Display.setTitle("FPS "+fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	public void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void renderGL(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(0.5f, 0.5f, 1.0f);
		
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			glRotatef(rotation, 0f, 0f, 1f);
			glTranslatef(-x, -y, 0);
			
			glBegin(GL_QUADS);
			{
				glVertex2f(x-50, y-50);
				glVertex2f(x+50, y-50);
				glVertex2f(x+50, y+50);
				glVertex2f(x-50, y+50);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public static void main(String[] args){
		FullscreenExample fullscreenExample = new FullscreenExample();
		fullscreenExample.start();
	}
}
