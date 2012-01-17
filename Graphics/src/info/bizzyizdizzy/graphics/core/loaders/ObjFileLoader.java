package info.bizzyizdizzy.graphics.core.loaders;

import info.bizzyizdizzy.graphics.primitives.Vertex4f;
import info.bizzyizdizzy.graphics.primitives.obj.ObjFace;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ObjFileLoader {
	private static Logger logger = Logger.getLogger(ObjFileLoader.class);
	
	public static List<ObjFace> loadObjFile(String fileName){
		URL fileUrl = ObjFileLoader.class.getClassLoader().getResource("models/"+fileName);
		File file = new File(fileUrl.getFile());
		
		if(file.exists()){
			try {
				List<String> lines = FileUtils.readLines(file);
				List<Vertex4f> vertices = new LinkedList<Vertex4f>();
				List<Vertex4f> normals = new LinkedList<Vertex4f>();
				List<ObjFace> faces = new LinkedList<ObjFace>();
				for(String line : lines){
					if(line.startsWith("#")){
						continue;
					}
					if(line.startsWith("v ")){
						// remove statement name
						line = line.substring(1);
						// trim trailing spaces
						line = line.trim();
						// split into parts
						String[] parts = line.split("[ ]");
						// init variables
						float x = 1f,y = 1f,z = 1f,w = 1f;
						// parse variables
						for(int i = 0; i<parts.length; i++){
							if(i == 0){
								x = Float.parseFloat(parts[i]);
							}else if(i == 1){
								y = Float.parseFloat(parts[i]);
							}else if(i == 2){
								z = Float.parseFloat(parts[i]);
							}else if(i == 3){
								w = Float.parseFloat(parts[i]);
							}
						}
						// create vertex object
						Vertex4f vertex = new Vertex4f(x,y,z,w);
						// add it to list
						vertices.add(vertex);
					}else if(line.startsWith("vn ")){
						// remove statement name
						line = line.substring(2);
						// trim trailing spaces
						line = line.trim();
						// split into parts
						String[] parts = line.split("[ ]");
						// init variables
						float x = 1f,y = 1f,z = 1f,w = 1f;
						// parse variables
						for(int i = 0; i<parts.length; i++){
							if(i == 0){
								x = Float.parseFloat(parts[i]);
							}else if(i == 1){
								y = Float.parseFloat(parts[i]);
							}else if(i == 2){
								z = Float.parseFloat(parts[i]);
							}else if(i == 3){
								w = Float.parseFloat(parts[i]);
							}
						}
						// create vertex object
						Vertex4f normal = new Vertex4f(x,y,z,w);
						// add it to list
						normals.add(normal);
					}else if(line.startsWith("f ")){
						// remove statement name
						line = line.substring(1);
						// trim trailing spaces
						line = line.trim();
						// split into parts
						String[] parts = line.split("[ ]");
						// init variables
						List<Vertex4f> faceVertices = new LinkedList<Vertex4f>();
						List<Vertex4f> faceNormals = new LinkedList<Vertex4f>();
						// parse variables
						for(String part : parts){
							String[] faceParts = part.split("[/]");
							if(faceParts.length==3){
								//vertices
								if(faceParts[0].length()>0){
									int index = Integer.parseInt(faceParts[0]);
									faceVertices.add(vertices.get(--index));
								}
								//texture coords
								if(faceParts[1].length()>0){
									
								}
								//normals
								if(faceParts[2].length()>0){
									int index = Integer.parseInt(faceParts[2]);
									faceNormals.add(normals.get(--index));
								}
							}else if(faceParts.length == 2){
								
							}else if(faceParts.length == 1){
								int index = Integer.parseInt(faceParts[0]);
								faceVertices.add(vertices.get(--index));
							}							
						}
						faces.add(new ObjFace(faceVertices, faceNormals));
					}else{
						logger.warn("obj file format statement not supported! Statement: "+line);
					}
				}
				return faces;	
			} catch (IOException e) {
				logger.error("Error while loading obj file: "+fileName, e);
				return null;
			}
		}else{
			logger.error("obj file does not exists! File: "+fileName);
			return null;
		}
		
	}
}
