package info.bizzyizdizzy.graphics.primitives.obj;

import info.bizzyizdizzy.graphics.primitives.Vertex4f;

import java.util.LinkedList;
import java.util.List;

public class ObjFace {
	public List<Vertex4f> vertices;
	public List<Vertex4f> normals;
	
	
	public ObjFace(List<Vertex4f> vertices, List<Vertex4f> normals){
		this.vertices = vertices;
		this.normals = normals;
	}
	public ObjFace(List<Vertex4f> vertices){
		this(vertices, new LinkedList<Vertex4f>());
	}
}
