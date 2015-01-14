package tw.edu.ncu.cc.manage.openid;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OpenIDManager {

    private OpenIDSetting setting;

    public OpenIDManager() throws OpenIDException {
        this( "openidSetting.properties" );
    }

    public OpenIDManager( String path ) throws OpenIDException {
        setting = new OpenIDSettingLoader().getSetting( path );
    }

    public OpenIDManager( Properties properties ) {
        setting = new OpenIDSetting( properties );
    }

    public String getURLString() {
        return setting.getURLString();
    }

    public boolean isValid( HttpServletRequest request ) {
        try {
            String checkUrl = createCheckURL( request );
            String result = getResultFromURL( new URL( checkUrl ) );
            return isResultCorrect( result );
        } catch ( IOException e ) {
            throw new RuntimeException( "cannot check validation of openid response", e );
        }
    }

    public String getIdentity( HttpServletRequest request ) {
        Map map = request.getParameterMap();
        if( map == null ) {
            return null;
        } else {
            Map< String, String > parameters = OpenIDUtil.convertToStringMap( map );
            String openidIdentity = parameters.get( OpenIDContants.IDENTITY );
            return openidIdentity.split( "user/" )[ 1 ];
        }
    }

    private String createCheckURL( HttpServletRequest request ) throws UnsupportedEncodingException {

        Properties properties = setting.getProperties();
        String endpoint = properties.getProperty( OpenIDContants.END_POINT );
        String realm    = properties.getProperty( OpenIDContants.REALM );

        Map< String, String > parameters = getRequiredParameterMap( request );
        parameters.put( OpenIDContants.MODE, OpenIDContants.CHECK_AUTHENTICATION );
        parameters.put( OpenIDContants.REALM, realm );

        return  OpenIDUtil.buildURLWithParameters( endpoint, parameters );
    }

    private Map< String, String > getRequiredParameterMap( HttpServletRequest request ) {
        Map map = request.getParameterMap();
        if( map == null ) {
            return new HashMap<>();
        } else {
            Map< String, String > stringMap = OpenIDUtil.convertToStringMap( request.getParameterMap() );
            stringMap.remove( OpenIDContants.NAME_SPACE );
            stringMap.remove( OpenIDContants.MODE );
            return stringMap;
        }
    }

    private boolean isResultCorrect( String text ) {
        return text.trim().equals( OpenIDContants.CORRECT_RESPONSE );
    }

    private String getResultFromURL( URL url ) throws IOException {
        HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        connection.setRequestMethod( "GET" );
        try ( InputStream inputStream = connection.getInputStream() ) {
            return IOUtils.toString( inputStream, OpenIDContants.CHARSET );
        }
    }

}
