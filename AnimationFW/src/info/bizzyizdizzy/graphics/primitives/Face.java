package info.bizzyizdizzy.graphics.primitives;

import java.util.LinkedList;
import java.util.List;


public class Face {
	
	private List<Vertex4f> vertices;
	
	private List<Vertex4f> textureCoords;
	
	private List<Vertex4f> normals;
	
	public Face(List<Vertex4f> vertices, List<Vertex4f> textureCoords, List<Vertex4f> normals){
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
	}
	
	public List<Vertex4f> getVertices(){
		return this.vertices;
	}
	
	public List<Vertex4f> getTextureCoords(){
		return this.textureCoords;
	}
	
	public List<Vertex4f> getNormals(){
		return this.normals;
	}
	
	/**
	 * Returns Face object with initialized lists.
	 * @return Face object
	 */
	public static Face getEmptyFace(){
		return new Face(new LinkedList<Vertex4f>(), new LinkedList<Vertex4f>(), new LinkedList<Vertex4f>());
	}
	
	/**
	 * Returns Face object with vertices lists set.
	 * Other lists are just initialized but left empty.
	 * @param vertices list with vertices
	 * @return Face object
	 */
	public static Face getVertexFace(List<Vertex4f> vertices){
		return new Face(vertices, new LinkedList<Vertex4f>(), new LinkedList<Vertex4f>());
	}
	
	/**
	 * Returns Face object with vertices and texture coordinates lists set.
	 * Normals list is just initialized but left empty.
	 * @param vertices list with vertices
	 * @param textureCoords list with texture coordinates
	 * @return Face object
	 */
	public static Face getVertexTextureCoordFace(List<Vertex4f> vertices, List<Vertex4f> textureCoords){
		return new Face(vertices, textureCoords, new LinkedList<Vertex4f>());
	}
	
	/**
	 * Returns Face object with vertices and normals lists set.
	 * Texture coordinate list is just initialized but left empty.
	 * @param vertices list with vertices
	 * @param normals list with normals
	 * @return Face object
	 */
	public static Face getVertexNormalFace(List<Vertex4f> vertices, List<Vertex4f> normals){
		return new Face(vertices, new LinkedList<Vertex4f>(), normals);
	}
	
	/**
	 * Return Face object with vertices, texture coordinate and normals lists set.
	 * @param vertices list with vertices
	 * @param textureCoords list with texture coordinates
	 * @param normals list with normals
	 * @return Face object
	 */
	public static Face getVertexTextureCoordsNormalFace(List<Vertex4f> vertices, List<Vertex4f> textureCoords, List<Vertex4f> normals){
		return new Face(vertices, textureCoords, normals);
	}
	
	public String toString(){
		return "vertices: "+vertices.size()+
				"\ttextureCoords: "+textureCoords.size()+
				"\tnormals: "+normals.size();
	}
}
