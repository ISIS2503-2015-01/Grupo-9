/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.Init;

import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.tenant.Tenant;

/**
 *
 * @author Personal
 */
public class Stormpath {

    private static Client clnt;
    private static Application app;
    
    public static Client createStormPathClient() {
        if (clnt == null) {
            String path = "C:/Users/Personal/Documents/LDAP/StormPath/apiKey.properties";
            ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
            clnt = Clients.builder().setApiKey(apiKey).build();
        }
        return clnt;
    }

    public static Application getStormPathApp() {
        if (app == null) {
            Tenant tenant = clnt.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(
                    Applications.where(Applications.name().eqIgnoreCase("My Application"))
            );

            app = applications.iterator().next();
        }
        return app;
    }
}
