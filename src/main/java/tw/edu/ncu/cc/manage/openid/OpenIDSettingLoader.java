package tw.edu.ncu.cc.manage.openid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class OpenIDSettingLoader {

    public OpenIDSetting getSetting( String filePath ) throws OpenIDException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try ( InputStream inputStream = classloader.getResourceAsStream( filePath ) ) {
            Properties properties = new Properties();
            properties.load( inputStream );
            return new OpenIDSetting( properties );
        } catch ( IOException e ) {
            throw new OpenIDException( "reading openid setting error" );
        }
    }

}
