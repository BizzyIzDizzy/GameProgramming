package info.bizzyizdizzy.animationframework.lwjgl.samples;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class QuadExample {
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			// clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			//set the color of the quade (RGBA)
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
			//draw quad
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex2f(800/2-100, 600/2-100);
				GL11.glVertex2f(800/2+100, 600/2-100);
				GL11.glVertex2f(800/2+100, 600/2+100);
				GL11.glVertex2f(800/2-100, 600/2+100);
			}
			GL11.glEnd();
			
			Display.update();
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args){
		QuadExample quadExample = new QuadExample();
		quadExample.start();
	}
}
