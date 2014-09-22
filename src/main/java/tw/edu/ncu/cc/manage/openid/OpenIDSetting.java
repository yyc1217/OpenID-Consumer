package tw.edu.ncu.cc.manage.openid;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Set;

public class OpenIDSetting {
	private Properties prop;
	public OpenIDSetting(Properties properties) {
		this.prop=properties;
	}
	
	public URL getURL(){
		String endpoint = getURLString();
		URL url=null;
		try {
			url=new URL(endpoint);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	public String getURLString(){
		String endpoint = prop.getProperty("openid.op_endpoint")+"?";
		Set<String> names=prop.stringPropertyNames();
		String[] keys=names.toArray(new String[names.size()]);
		return addParameters(endpoint, keys);
	}

	private String addParameters(String url,String parameters[]){
		try {
			for(int i=0;i<parameters.length-1;i++){			
				url+= parameters[i]+ "=" + URLEncoder.encode(prop.getProperty(parameters[i]),"UTF-8")+"&";		
				url+= parameters[parameters.length-1]+ "=" + URLEncoder.encode(prop.getProperty(parameters[parameters.length-1]),"UTF-8");			
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
}
