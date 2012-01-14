package info.bizzyizdizzy.graphics.core.formats;

/**
 * # Material file format definitions:
 * # <- comment
 * 
 * # define material with name materialName
 * newmtl	materialName
 * 
 * # ambient color definition	(colors are in RGB, values are within [0,1]
 * Ka	r	g	b
 * 
 * # diffuse color definition	(colors are in RGB, values are within [0,1]
 * Kd	r	g	b
 * 
 * # specular color definition	(colors are in RGB, values are within [0,1]
 * Ks	r	g	b
 * 
 * # coefficient for specular color weightening, values are within [0,1000]
 * Ns	value
 * 
 * # dissolved definition
 * d	value
 * # or
 * Tr	value
 * 
 * # multiple illumination models are available per material:
 * #	0	color on and ambient off
 * #	1	color on and ambient on
 * #	2	highlight on
 * #	3	reflection on and ray trace on
 * #	4	transparency: glass on, reflection:	ray trace on
 * #	5	reflection:	fresnel on and ray trace on
 * #	6	transparency:	refraction on, reflection:	fresnel off and ray trace on
 * #	7	transparency:	refraction on, reflection:	fresnel on and ray trace on
 * #	8	reflection on and ray trace off
 * #	9	transparency:	glass on, reflection:	ray trace off
 * #	10	casts shadows onto invisible shadows
 * # definition:
 * illum	illumModelNr
 * 
 * # ambient texture map definition
 * map_Ka	filename
 * 
 * # diffuse texture map definition	(most of the time it will be the same as the ambient texture map)
 * map_Kd	filename
 * 
 * # specular texture map definition
 * map_Ks	filename
 * 
 * # alpha texture map definition
 * map_d	filename
 * 
 * # bump map definition
 * map_bump	filename
 * # or
 * bump	filename
 * 
 * 
 * @author uros
 *
 */
public class MtlFile {

}
