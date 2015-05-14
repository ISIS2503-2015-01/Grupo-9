package grupo9.arquisoft.migrainetrackingmobile;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by henryfvargas on 12/05/15.
 */
public class PostHttp
{
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    Response run(String url, String body, Map<String, String> headers) throws IOException
    {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, body));
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
