package grupo9.arquisoft.migrainetrackingmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListAdapter;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListChild;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListGroup;
import grupo9.arquisoft.migrainetrackingmobile.extras.GetHttp;

public class VerEpisodiosActivity extends ActionBarActivity {

    public final static String TAG="grupo9.migraintracking";

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;
    private ArrayList<ExpandListGroup> list;
    private ArrayList<ExpandListChild> list2;
    private List<EpisodioDolorDTO> listaEpisodios;
    private long idUsuario;
    private String token;
    private Gson gson;
    ProgressDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_episodios);
        gson = new Gson();
        SharedPreferences preferences=getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        idUsuario=preferences.getLong("CEDULA",0);
        token=preferences.getString("token","");
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        String tipo=bundle.getString("tipo");
        ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);

        if(tipo.equals("CEDULA"))
        {
            new pedirEpisodios().execute("https://migraine-services.herokuapp.com/webresources/pacientes/episodios/" + idUsuario);
        }
        else if(tipo.equals("CEDULA-FECHAS"))
        {

        }
    }

    private ExpandableListView.OnChildClickListener ExpandList_ItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            ExpandListChild ch =  ExpListItems.get(groupPosition).getItems().get(childPosition);
            return false;
        }

    };

    private ArrayList<ExpandListGroup> SetStandardGroups() {

        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        for(int i=0;i<listaEpisodios.size();i++)
        {
            EpisodioDolorDTO actual=listaEpisodios.get(i);
            ExpandListGroup group = new ExpandListGroup();
            group.setName("Episodio "+(i+1));

            ExpandListChild paciente=new ExpandListChild();
            paciente.setName("Paciente : " + actual.getCedulaPaciente());
            paciente.setTag(null);
            list2.add(paciente);

            ExpandListChild fecha=new ExpandListChild();
            Date fechita=new Date();
            fechita.setTime(actual.getFecha());
            fecha.setName("Fecha: "+fechita.toString());
            fecha.setTag(null);
            list2.add(fecha);

            ExpandListChild localizacion=new ExpandListChild();
            localizacion.setName("Localización : "+actual.getLocalizacion());
            localizacion.setTag(null);
            list2.add(localizacion);

            ExpandListChild intensidad=new ExpandListChild();
            intensidad.setName("Intensidad : " + actual.getIntensidad());
            intensidad.setTag(null);
            list2.add(intensidad);

            ExpandListChild horas=new ExpandListChild();
            horas.setName("Horas de sueño : " + actual.getHoursSlept());
            horas.setTag(null);
            list2.add(horas);

            group.setItems(list2);
            list2= new ArrayList<ExpandListChild>();
            list.add(group);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_episodios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class pedirEpisodios extends AsyncTask<String, Long, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialogo=ProgressDialog.show(VerEpisodiosActivity.this,"Espera un momento...","Cargando...",true);
            dialogo.setCancelable(true);
        }
        protected String doInBackground(String... urls)
        {
            try
            {
                Map<String, String> headers=new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X_rest_user", token);
                headers.put("Accept", "application/json");
                Response response=new GetHttp().run(urls[0],headers);
                String respuesta=response.body().string();
                List<EpisodioDolorDTO> resp=obtenerEpisodios(respuesta);
                if(resp!=null)
                   listaEpisodios=resp;
                else
                   listaEpisodios=new ArrayList<EpisodioDolorDTO>();
                return respuesta;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(VerEpisodiosActivity.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);
            ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
            dialogo.dismiss();
        }
    }

    private List<EpisodioDolorDTO> obtenerEpisodios(String json)
    {
        Type type=new TypeToken<ArrayList<EpisodioDolorDTO>>(){}.getType();
        try
        {
            List<EpisodioDolorDTO> resp = gson.fromJson(json, type);
            return resp;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
