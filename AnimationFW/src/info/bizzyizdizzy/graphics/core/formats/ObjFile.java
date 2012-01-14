package info.bizzyizdizzy.graphics.core.formats;

/**
 * Wavefront object file format definitions:
 * # <- comment
 * 
 * # vertex definition	(v4 is optional)
 * v	v1	v2	v3	v4
 * 
 * # texture coordinate definition	(vt4 is optional)
 * vt	vt1	vt2	vt3	vt4
 * 
 * # normal definition	(might not be unit)
 * vn	vn1	vn2	vn3
 * 
 * # face vertex definition
 * f	v1	v2	v3	...	v_n
 * 
 * # face vertex/texture coordinate definition
 * f	v1/vt1	v2/vt2	v3/vt3	...	v_n/vt_n
 * 
 * # face vertex/texture coordinate/normal definition
 * f	v1/vt1/vn1	v2/vt2/vn2	v3/vt3/vn3	...	v_n/vt_n/vn_n
 * 
 * # face vertex/normal definition
 * f	v1//vn1	v2//vn2	v3//vn3	...	v_n//vn_n
 * 
 * # loading external material file
 * mtllib	[filename]
 * 
 * # TAGS (aplied to all faces following until another tag of the same type appears, 
 * # the texture coordinate can be left out if the current material definition does not include a texture)
 * 
 * # name object
 * o	[objectName]
 * 
 * # name group
 * g	[groupName]
 * 
 * # material group	(materialName is defined in external material file
 * usemtl	[materialname]
 * 
 * # smooth shading across polygons is enabled by smoothing groups
 * s	1
 * 
 * # disable smooth shading
 * s	off
 * 
 * 
 * @author uros
 *
 */
public class ObjFile {
}
