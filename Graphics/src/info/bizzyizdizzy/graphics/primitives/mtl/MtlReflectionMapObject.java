package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Material reflection map</b><br>
 * A reflection map is an environment that simulates reflections in<br>
 * specified objects. The environment is represented by a color texture<br>
 * file or procedural texture file that is mapped on the inside of an<br>
 * infinitely large, space. Reflection maps can be spherical or cubic. A<br>
 * spherical reflection map requires only one texture or image file, while<br>
 * a cubic reflection map requires six.<br>
 * <br>
 * Each material description can contain one reflection map statement that<br>
 * specifies a color texture file or a color procedural texture file to<br>
 * represent the environment. The material itself must be assigned an<br>
 * illumination model of 3 or greater.<br>
 * <br>
 * The reflection map statement in the .mtl file defines a local<br>
 * reflection map. That is, each material assigned to an object in a scene<br>
 * can have individual reflection map. You can assign a global reflection<br>
 * map to an object and specify the orientation of the reflection<br>
 * map. Rotating the reflection map creates the effect of<br>
 * animating reflection independently of object motion. When you replace<br>
 * a global reflection map with a local reflection map, the local<br>
 * reflection map inherits the transformation of the global reflection map.<br>
 * <br>
 * <b>Syntax</b><br>
 * Syntax is described in each individual class that extends this class.<br>
 * All describes the reflection map statement for .mtl files.
 * @author Uroš Marolt
 *
 */
public abstract class MtlReflectionMapObject extends MtlObject{
		
	public static Map<String, Class<? extends MtlReflectionMapObject>> REFLECTION_MAP_OBJECTS = new HashMap<String,Class<? extends MtlReflectionMapObject>>();
	static{
		REFLECTION_MAP_OBJECTS.put("refl", MtlRefl.class);
	}
		
	/**
	 * @see MtlObject#getStatementName()
	 * @param statementName
	 */
	public MtlReflectionMapObject(String statementName){
		super(statementName);
	}
	
	protected abstract void formatParams(List<String> params);
	
	public String toString(){
		String returnString = super.toString()+"\n";
		for(String s : REFLECTION_MAP_OBJECTS.keySet()){
			returnString += s+"\n";
		}
		
		return returnString;
	}
}
