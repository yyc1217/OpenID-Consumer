package tw.edu.ncu.cc.manage.openid;

import java.util.Map;
import java.util.Properties;

public class OpenIDSetting {

    private Properties properties;

    public OpenIDSetting( Properties properties ) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties( Properties properties ) {
        this.properties = properties;
    }

    public String getURLString() {
        Map< String, String > params = OpenIDUtil.convertToStringMap( properties );
        String baseURLString = properties.getProperty( OpenIDContants.END_POINT );
        return OpenIDUtil.buildURLWithParameters( baseURLString, params );
    }

}
