package info.bizzyizdizzy.graphics.primitives;

public class Vertex4f {
	public float x,y,z,w;
	
	public Vertex4f(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vertex4f(float x, float y, float z){
		this(x,y,z,1F);
	}
	
	public Vertex4f(float x, float y){
		this(x,y,0F);
	}
	
	public String toString(){
		return "x="+x+
				"\ty="+y+
				"\tz="+z+
				"\tw="+w;
	}
}
