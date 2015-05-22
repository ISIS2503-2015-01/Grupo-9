package grupo9.arquisoft.migrainetrackingmobile.extras;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by henryfvargas on 21/05/15.
 */
public class Utils
{
    private static final String PROTOCOLO="https:";
    private static final String URLENDPOINT="//172.24.99.178:80/webresources";
    private static SSLSocketFactory sslSocketFactory;
    private static Pinning pinning;
    private static final boolean SSL=true;

    public static String getPath()
    {
        return PROTOCOLO+URLENDPOINT;
    }

    public static SSLSocketFactory getSSL()
    {
        if(pinning==null)
        {
            pinning = new Pinning(MigraineTrackingMobile.getAppContext());
        }
        if(sslSocketFactory==null)
        {
            sslSocketFactory=pinning.getPinnedCertSslSocketFactory();
        }
        return (SSL?sslSocketFactory:null);
    }
}
