package info.bizzyizdizzy.graphics.lwjgl.tutorials;

import static org.lwjgl.opengl.GL11.*;
import info.bizzyizdizzy.graphics.core.loaders.ObjFileLoader;
import info.bizzyizdizzy.graphics.primitives.Vertex4f;
import info.bizzyizdizzy.graphics.primitives.obj.ObjFace;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class BasicShapes {
	private List<ObjFace> cylinder;
	private List<ObjFace> cone;
	private List<ObjFace> monkey;
	int width = 800;
	int height = 600;
	
	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight; 
	private FloatBuffer lModelAmbient;
	
	float angle = 30f;
	float cameraAngle = 0f;
	boolean shift = false;
	
	long lastFrame;
	int fps;
	long lastFPS;
		
	public void start(){
		cylinder = ObjFileLoader.loadObjFile("cylinder.obj");
		cone = ObjFileLoader.loadObjFile("cone.obj");
		monkey = ObjFileLoader.loadObjFile("monkey.obj");
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create(new PixelFormat(24, 8, 24, 0, 0));
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
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f); // sets background to grey
		glClearDepth(1.0f); // clear depth buffer
		glEnable(GL_DEPTH_TEST); // Enables depth testing
		glDepthFunc(GL_LEQUAL); // sets the type of test to use for depth testing
		glMatrixMode(GL_PROJECTION); // sets the matrix mode to project
		
		float fovy = 45.0f;
		float aspect = width / height;
		float zNear = 0.1f;
		float zFar = 100.0f;
		GLU.gluPerspective(fovy, aspect, zNear, zFar);
		
		glMatrixMode(GL_MODELVIEW);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
		
		//----------- Variables & method calls added for Lighting Test -----------//
		initLightArrays();
		glShadeModel(GL_SMOOTH);
		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);				// sets specular material color
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);					// sets shininess
		
		glLight(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);					// sets diffuse light to white
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);		// global ambient light 
		
		glEnable(GL_LIGHTING);										// enables lighting
		glEnable(GL_LIGHT0);										// enables light0
		
		glEnable(GL_COLOR_MATERIAL);								// enables opengl to use glColor3f to define material color
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			// tell opengl glColor3f effects the ambient and diffuse properties of material
		//----------- END: Variables & method calls added for Lighting Test -----------//
	}
	
	private void initLightArrays(){
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(2.0f).put(1.0f).put(1.0f).put(0.0f).flip();
		
		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(0.1f).put(0.1f).put(0.1f).put(0.01f).flip();
		
		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
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
	    for(ObjFace face : cylinder){
	    	glBegin(GL_POLYGON);
    		{
    			if(face.vertices.size() == face.normals.size()){
    				for(int x = 0; x<face.vertices.size(); x++){
        				Vertex4f vertex = face.vertices.get(x);
        				Vertex4f normal = face.normals.get(x);
        				glNormal3f(normal.x, normal.y, normal.z);
        				glVertex3f(vertex.x, vertex.y, vertex.z);
        			}
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
    			if(face.vertices.size() == face.normals.size()){
    				for(int x = 0; x<face.vertices.size(); x++){
        				Vertex4f vertex = face.vertices.get(x);
        				Vertex4f normal = face.normals.get(x);
        				glNormal3f(normal.x, normal.y, normal.z);
        				glVertex3f(vertex.x, vertex.y, vertex.z);
        			}
    			}
    		}
    		glEnd();	    	
	    }	   
	    glPopMatrix();
	    
	    glPushMatrix();
	    glTranslatef(-1f,1f,0f);
	    glRotatef(angle, 1.0f,2.0f,3.0f);
	    glRotatef(110,1f,0f,0f);
	    glColor3f(0f, 0f, 1f);
	    glScalef(0.7f,0.7f,0.7f);
	    for(ObjFace face : monkey){
	    	glBegin(GL_POLYGON);
    		{
    			if(face.vertices.size() == face.normals.size()){
    				for(int x = 0; x<face.vertices.size(); x++){
        				Vertex4f vertex = face.vertices.get(x);
        				Vertex4f normal = face.normals.get(x);
        				glNormal3f(normal.x, normal.y, normal.z);
        				glVertex3f(vertex.x, vertex.y, vertex.z);
        			}
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
