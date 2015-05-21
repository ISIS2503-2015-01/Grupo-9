package grupo9.arquisoft.migrainetrackingmobile.extras;

import android.app.Application;
import android.content.Context;

/**
 * Created by henryfvargas on 21/05/15.
 */
public class MigraineTrackingMobile extends Application
{
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MigraineTrackingMobile.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MigraineTrackingMobile.context;
    }
}
