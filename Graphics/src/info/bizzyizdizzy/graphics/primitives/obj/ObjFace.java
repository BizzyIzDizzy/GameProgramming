package info.bizzyizdizzy.graphics.primitives.obj;

import info.bizzyizdizzy.graphics.primitives.Vertex4f;

import java.util.List;

public class ObjFace {
	public List<Vertex4f> vertices;
	
	public ObjFace(List<Vertex4f> vertices){
		this.vertices = vertices;
	}
}
