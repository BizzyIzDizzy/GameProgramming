package info.bizzyizdizzy.utils.system;

public class SystemInfo {
	
	/**
	 * Returns String with default system path separator.
	 * Unix - /
	 * Windows - \
	 * OSX - /
	 * 
	 * @return String with default system path separator.
	 */
	public static String getDefaultSystemPathSeparator(){
		return System.getProperty("file.separator");
	}
}
