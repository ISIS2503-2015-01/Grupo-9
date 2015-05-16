package grupo9.arquisoft.migrainetrackingmobile.extras;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by henryfvargas on 12/05/15.
 */
public class GetHttp
{
    OkHttpClient client = new OkHttpClient();

    public Response run(String url, Map<String, String> headers) throws IOException
    {
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
