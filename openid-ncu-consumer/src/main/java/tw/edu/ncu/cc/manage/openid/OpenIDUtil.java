package tw.edu.ncu.cc.manage.openid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OpenIDUtil {

    public static String buildURLWithParameters( String url, Map< String, String > parameters ) {
        StringBuilder builder = new StringBuilder( url + "?" );
        try {
            for ( Map.Entry< String, String > parameter : parameters.entrySet() ) {
                builder.append( parameter.getKey() )
                        .append( "=" )
                        .append( urlEncode( parameter.getValue() ) )
                        .append( "&" );
            }
        } catch ( UnsupportedEncodingException e ) {
            throw new RuntimeException( "cannot build url", e );
        }
        return builder.deleteCharAt( builder.length()-1 ).toString();
    }

    private static String urlEncode( String s ) throws UnsupportedEncodingException {
        return URLEncoder.encode( s, OpenIDContants.CHARSET );
    }

    public static Map< String, String > convertToStringMap( Properties properties ) {
        Map< String, String > map = new HashMap<>();
        for( String key : properties.stringPropertyNames() ) {
            map.put( key, properties.getProperty( key ) );
        }
        return map;
    }

    //ONLY USE ON MAP FROM SERVLET REQUEST
    public static Map< String, String > convertToStringMap( Map map ) {
        Map< String, String > stringMap = new HashMap<>();
        for ( Object key : map.keySet() ) {
            stringMap.put( ( String ) key, singleValue( map, key ) );
        }
        return stringMap;
    }

    private static String singleValue( Map map, Object key ) {
        return ( ( String[] ) map.get( key ) )[ 0 ];
    }

}
