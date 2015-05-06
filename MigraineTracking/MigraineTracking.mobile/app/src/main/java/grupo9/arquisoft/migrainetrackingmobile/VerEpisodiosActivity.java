package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;


public class VerEpisodiosActivity extends ActionBarActivity {

    public final static String TAG="grupo9.migraintracking";

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;
    private ArrayList<ExpandListGroup> list;
    private ArrayList<ExpandListChild> list2;
    private List<EpisodioDolorDTO> listaEpisodios;
    private String idUsuario;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_episodios);
        SharedPreferences preferences=getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        idUsuario=preferences.getString("USUARIO","");
        token=preferences.getString("token","");
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();

        String tipo=bundle.getString("tipo");
        String id=bundle.getString("id");

        if(tipo.equals("CEDULA"))
        {
            ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
            new pedirEpisodios().execute("https://migraine-services.herokuapp.com/pacientes/episodios/"+id);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(VerEpisodiosActivity.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);
            ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
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

    public ArrayList<ExpandListGroup> SetStandardGroups() {

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

    private class pedirEpisodios extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            RestClient restClient = new RestClient(urls[0]);
            restClient.AddHeader("Accept", "application/json");
            restClient.AddHeader("x_rest_user",token);
            restClient.AddParam(idUsuario);
            System.out.println(idUsuario);
            System.out.println(token);
            try {
                restClient.Execute(RestClient.RequestMethod.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(restClient.getResponse());
            listaEpisodios=obtenerEpisodios(restClient.getResponse());
            return restClient.getResponse();
        }

        protected void onPostExecute(String response) {


        }
    }

    private List<EpisodioDolorDTO> obtenerEpisodios(String json)
    {
        Gson gson = new Gson();
        Type type=new TypeToken<ArrayList<EpisodioDolorDTO>>(){}.getType();
        return gson.fromJson(json,type);
    }
}
