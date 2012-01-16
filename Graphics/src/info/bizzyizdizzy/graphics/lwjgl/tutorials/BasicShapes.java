package info.bizzyizdizzy.graphics.lwjgl.tutorials;

import java.util.List;

import info.bizzyizdizzy.graphics.core.loaders.ObjFileLoader;
import info.bizzyizdizzy.graphics.primitives.Vertex4f;
import info.bizzyizdizzy.graphics.primitives.obj.ObjFace;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class BasicShapes {
	private List<ObjFace> cube;
	private List<ObjFace> cone;
	private List<ObjFace> monkey;
	int width = 800;
	int height = 600;
	
	float angle = 30f;
	float cameraAngle = 0f;
	boolean shift = false;
	
	long lastFrame;
	int fps;
	long lastFPS;
		
	public void start(){
		cube = ObjFileLoader.loadObjFile("cube.obj");
		cone = ObjFileLoader.loadObjFile("cone.obj");
		monkey = ObjFileLoader.loadObjFile("monkey.obj");
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
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
			Display.sync(5000);
		}
		
		Display.destroy();
	}
	
	public void update(int delta){		
		if(shift){
			cameraAngle -= 0.01*delta;
			angle -= 0.1*delta;
		}else{
			cameraAngle += 0.01*delta;
			angle += 0.1*delta;
		}
		
		// if cameraAngle is to big start to decrease it
		if(cameraAngle > 10){
			shift = true;
		}
		// if cameraAngle is to small start to increase it
		if(cameraAngle < -10){
			shift = false;
		}
		
		updateFPS();
	}
	
	private void initGL(){
		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(45.0f, (float)width/height, 1.0f, 200.0f);
		glEnable(GL_DEPTH_TEST);
	}
	
	private void renderGL(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glMatrixMode(GL_MODELVIEW); //Switch to the drawing perspective
	    glLoadIdentity(); //Reset the drawing perspective
	    glRotatef(-cameraAngle,0f,1f,0f);
	    glTranslatef(0f, 0f, -5.0f); 
	    
	    glPushMatrix();
	    glTranslatef(0f,-1f,0f);
	    glRotatef(angle, 1.0f,2.0f,3.0f);
	    glColor3f(1f, 0f, 0f);
	    glScalef(0.5f,0.5f,0.5f);
	    for(ObjFace face : cube){
	    	glBegin(GL_POLYGON);
    		{
    			for(Vertex4f vertex : face.vertices){
    				glVertex3f(vertex.x, vertex.y, vertex.z);
    			}
    		}
    		glEnd();	    	
	    }	    
	    glEnd(); //End quadrilateral coordinates
	    glPopMatrix();
	    
	    glPushMatrix();
	    glTranslatef(1f,1f,0f);
	    glRotatef(angle, 1.0f,2.0f,3.0f);
	    glColor3f(0f, 1f, 0f);
	    glScalef(0.7f,0.7f,0.7f);
	    for(ObjFace face : cone){
	    	glBegin(GL_POLYGON);
    		{
    			for(Vertex4f vertex : face.vertices){
    				glVertex3f(vertex.x, vertex.y, vertex.z);
    			}
    		}
    		glEnd();	    	
	    }	   
	    glPopMatrix();
	    
	    glPushMatrix();
	    glTranslatef(-1f,1f,0f);
	    glRotatef(angle, 1.0f,2.0f,3.0f);
	    glColor3f(0f, 0f, 1f);
	    glScalef(0.7f,0.7f,0.7f);
	    for(ObjFace face : monkey){
	    	glBegin(GL_POLYGON);
    		{
    			for(Vertex4f vertex : face.vertices){
    				glVertex3f(vertex.x, vertex.y, vertex.z);
    			}
    		}
    		glEnd();	    	
	    }	   
	    glPopMatrix();	
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
	
	public static void main(String[] args){
		BasicShapes bs = new BasicShapes();
		bs.start();
	}
}
