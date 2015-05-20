package grupo9.arquisoft.migrainetrackingmobile.extras;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by henryfvargas on 12/05/15.
 */
public class GetHttp
{
    private static OkHttpClient client = new OkHttpClient();

    private static PostHttp instancia;

    public static PostHttp createInstance()
    {
        if(instancia==null)
        {
            instancia=new PostHttp();
        }
        return instancia;
    }

    public static Response run(String url, Map<String, String> headers, SSLSocketFactory ssl) throws IOException
    {
        if(ssl!=null)
        {
            client.setSslSocketFactory(ssl);
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        Iterator i=headers.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry<String,String> key=(Map.Entry<String,String>)i.next();
            builder.addHeader(key.getKey(),key.getValue());
            i.remove();
        }
        Request request=builder.build();

        Response response = client.newCall(request).execute();
        return response;
    }
}
