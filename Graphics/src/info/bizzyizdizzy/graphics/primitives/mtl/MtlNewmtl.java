package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.LinkedList;
import java.util.List;


/**
 * <b>Material Name</b><br>
 * The material name statement assigns a name to the material description.<br>

 * <br>
 * <b>Syntax</b><br>
 * The folowing syntax describes the material name statement.<br>
 * <br>
 * <code>newmtl	name</code><br>
 * <i>"name" is the name of the material. Names may be any length but<br>
 * cannot include blanks. Underscores may be used in material names.</i><br>
 * <br>
 * It specifies the start of a material description and assigns a name to the<br>
 * material. An .mtl file must have one newmtl statement at the start of<br>
 * each material description.<br>
 * @author uros
 *
 */
public class MtlNewmtl {
	
	private String name;
	
	private List<MtlColorIllumObject> colorAndIlluminationObjects;
	
	private List<MtlTextureMapObject> textureMapObjects;
	
	private List<MtlReflectionMapObject> reflectionMapObjects;
	
	public MtlNewmtl(List<String> params) throws RuntimeException{
		formatParameters(params);
		this.colorAndIlluminationObjects = new LinkedList<MtlColorIllumObject>();
		this.textureMapObjects = new LinkedList<MtlTextureMapObject>();
		this.reflectionMapObjects = new LinkedList<MtlReflectionMapObject>();
	}
	
	private void formatParameters(List<String> params) throws RuntimeException{
		// there must be only one parameter
		if(params.size() == 1){
			String name = params.get(0);
			// there must be no spaces in the parameter
			if(name.indexOf(" ") == -1){
				this.name = name;
			}else{
				throw new RuntimeException("newmtl parameter should not contain any spaces");
			}
		}else{
			throw new RuntimeException("newmtl statement must have only one parameter!");
		}
	}
	
	/**
	 * Returns String with the name of this material, <br>
	 * if name is not set return blank String.
	 * @return String with the name of the material.
	 */
	public String getName(){
		if(this.name == null){
			return "";
		}
		return this.name;
	}
	
	public List<MtlColorIllumObject> getColorAndIlluminationObjects(){
		return this.colorAndIlluminationObjects;
	}
	
	public List<MtlTextureMapObject> getTextureMapObjects(){
		return this.textureMapObjects;
	}
	
	public List<MtlReflectionMapObject> getReflectionMapObjects(){
		return this.reflectionMapObjects;
	}
}
