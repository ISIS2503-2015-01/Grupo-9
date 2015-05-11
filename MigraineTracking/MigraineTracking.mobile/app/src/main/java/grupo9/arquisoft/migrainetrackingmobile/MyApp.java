package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Context;

/**
 * Created by henryfvargas on 11/05/15.
 */
public class MyApp extends android.app.Application {

    private static MyApp instance;

    public MyApp() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

}
