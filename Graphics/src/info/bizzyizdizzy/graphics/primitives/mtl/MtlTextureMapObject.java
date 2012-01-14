package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * <b>Material texture map</b><br>
 * Texture map statements modify the material parameters of a surface by<br>
 * associating an image or texture file with material parameters that can<br>
 * be mapped. By modifying existing parameters instead of replacing them,<br>
 * texture maps provide great flexibility in changing the appearance of an<br>
 * object's surface.<br>
 * <br>
 * Image files and texture files can be used interchangeably. If you use<br>
 * an image file, that file is converted to a texture in memory and is<br>
 * discarded after rendering.<br>
 * <br>
 * <code><b>Tip</b><br>
 * Using images instead of textures saves disk space and setup time,<br>
 * however, it introduces a small computational cost at the beginning of a<br>
 * render.<br></code>
 * <br>
 * The material parameters that can be modified by a texture map are:<br>
 * - Ka (color)<br>
 * - Kd (color)<br>
 * - Ks (color)<br>
 * - Ns (scalar)<br>
 * - d (scalar)<br>
 * <br>
 * In addition to the material parameters, the surface normal can be modified.<br>
 * <br>
 * <br>
 * Image file types<br>
 * You can link any image file type that is currently supported.<br>
 * Image file type is supported if corresponding loader exists in <br>
 * info.bizzyizdizzy.graphics.core.loaders package.<br>
 * <br>
 * <br>
 * Texture file types<br>
 * Texture file type is considered as supported if corresponding loader<br>
 * exists in info.bizzyizdizzy.graphics.core.loaders package.<br>
 * The texture file types are:<br>
 * - mip-mapped texture files (.mpc, .mps, .mpb)<br>
 * - compiled procedural texture files (.cxc, .cxs, .cxb)<br>
 * <br>
 * Mip-mapped texture files<br>
 * There are three types of texture files:<br>
 * - color texture files (.mpc)<br>
 * - scalar texture files (.mps)<br>
 * - bump texture files (.mpb)<br>
 * <br>
 * Color textures. Color texture files are designated by an extension of<br>
 * ".mpc" in the filename, such as "chrome.mpc". Color textures modify the<br>
 * material color as follows:<br>
 * - Ka - material ambient is multiplied by the texture value<br>
 * - Kd - material deffuse is multiplied by the texture value<br>
 * - Ks - materual specular is multiplied by the texture value<br>
 * <br>
 * Scalar textures. Scalar texture files are designated by an extension<br>
 * of ".mps" in the filename, such as "wisp.mps". Scalar textures modify<br>
 * the material scalar values as follows:<br>
 * - Ns - material specular exponent is multiplied by the texture value<br>
 * - d - material dissolve is multiplied by the texture value<br>
 * - decal - uses a scalar value to deform the surface of an object to<br>
 * create surface rougness<br>
 * <br>
 * Bump textures. Bump texture files are designated by an extension of<br>
 * ".mpb" in the filename, such as "sand.mpb". Bump textures modify<br>
 * surface normals. The image used for a bump texture represents the<br>
 * topology or height of the surface relative to the average surface. Dark<br>
 * areas are depressions and light areas are high points. The effect is<br>
 * like embossing the surface with texture.<br>
 * <br>
 * Procedural texture files<br>
 * <br>
 * Procedural texture files are mathematical formulas to calculate sample<br>
 * values of the texture.<br>
 * <br>
 * <br>
 * <b>Syntax</b><br>
 * Syntax is described in each individual class that extends this class.<br>
 * All describes the texture map statements that apply to<br>
 * .mtl files. These statements can be used alone or with any combination<br>
 * of options. The options and their arguments are inserted between the<br>
 * keyword and the "filename",<br>
 * 
 * @author Uroš Marolt
 *
 */
public class MtlTextureMapObject {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static Map<String, Class<? extends MtlTextureMapObject>> TEXTURE_MAP_OBJECTS = new HashMap<String, Class<? extends MtlTextureMapObject>>();
	static{
		TEXTURE_MAP_OBJECTS.put("map_Ka", MtlMapka.class);
		TEXTURE_MAP_OBJECTS.put("map_Kd", MtlMapkd.class);
		TEXTURE_MAP_OBJECTS.put("map_Ks", MtlMapks.class);
		TEXTURE_MAP_OBJECTS.put("map_Ns", MtlMapns.class);
		TEXTURE_MAP_OBJECTS.put("map_d", MtlMapd.class);
		TEXTURE_MAP_OBJECTS.put("disp", MtlDisp.class);
		TEXTURE_MAP_OBJECTS.put("decal", MtlDecal.class);
		TEXTURE_MAP_OBJECTS.put("bump", MtlBump.class);
	}
	
	private String statementName;
	
	public MtlTextureMapObject(String statementName){
		this.statementName = statementName;
	}
	
	public String getStatementName(){
		if(this.statementName == null){
			logger.warn("statementName is null!");
			return "";
		}
		return this.statementName;
	}
	
	public void formatParameters(List<String> params){
		logger.error("You can not format parameters of this base class!");
	}
}
