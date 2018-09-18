package org.example.framework;

public class MyUtil {

	public MyUtil() {
		// TODO Auto-generated constructor stub
	}

	public static final String getWebModuleName(String packageName, final String webTieName) {
		if(null == packageName || null == webTieName) 
			return null;
		String name = packageName.replace('.', ',');
		String[] tmp = name.split(",");
		String moduleName = null;
		for(int i=0; i<tmp.length; i++) {
			if(webTieName.equals(tmp[i])) {
				moduleName = (i-1) >= 0 ? "/" + tmp[i-1] + "/" : null;
				break;
			}
		}
		return moduleName;
	}
}
