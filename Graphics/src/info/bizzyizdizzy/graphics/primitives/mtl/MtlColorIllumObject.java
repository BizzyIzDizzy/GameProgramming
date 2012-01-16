package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Material color and illumination</b><br>
 * The statements in this section specify color, transparency, and<br>
 * reflectivity values.<br>
 * <br>
 * <b>Syntax</b><br>
 * Syntax is described in each individual class that extends this class.<br>
 * All describes the material color and illumination statements that apply<br>
 * to all .mtl files<br>
 * @author Uroš Marolt
 *
 */
public abstract class MtlColorIllumObject extends MtlObject{
	
	public static Map<String, Class<? extends MtlColorIllumObject> > COLOR_ILLUM_OBJECTS = new HashMap<String, Class<? extends MtlColorIllumObject>>();
	static{
		COLOR_ILLUM_OBJECTS.put("Ka", MtlKa.class);
		COLOR_ILLUM_OBJECTS.put("Kd", MtlKd.class);
		COLOR_ILLUM_OBJECTS.put("Ks", MtlKs.class);
		COLOR_ILLUM_OBJECTS.put("Tf", MtlTf.class);
		COLOR_ILLUM_OBJECTS.put("illum", MtlIllum.class);
		COLOR_ILLUM_OBJECTS.put("d", MtlD.class);
		COLOR_ILLUM_OBJECTS.put("Ns", MtlNs.class);
		COLOR_ILLUM_OBJECTS.put("sharpness", MtlSharpness.class);
		COLOR_ILLUM_OBJECTS.put("Ni", MtlNi.class);
	}
	
	/**
	 * @see MtlObject#getStatementName()
	 * @param statementName
	 */
	public MtlColorIllumObject(String statementName){
		super(statementName);
	}
	
	protected abstract void formatParams(List<String> params);
	
	public String toString(){
		String returnString = super.toString()+"\n";
		for(String s : COLOR_ILLUM_OBJECTS.keySet()){
			returnString += s+"\n";
		}
		
		return returnString;
	}
}
