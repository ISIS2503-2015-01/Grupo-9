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

import javax.net.ssl.SSLSocketFactory;

import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListAdapter;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListChild;
import grupo9.arquisoft.migrainetrackingmobile.extras.ExpandListGroup;
import grupo9.arquisoft.migrainetrackingmobile.extras.GetHttp;
import grupo9.arquisoft.migrainetrackingmobile.extras.Pinning;


public class VerPacientesActivity extends ActionBarActivity {
    private Gson gson;
    private String token;
    private ExpandableListView ExpandList;
    private ArrayList<ExpandListGroup> ExpListItems;
    private List<PacienteDTO> listaPacientes;
    ProgressDialog dialogo;
    private ExpandListAdapter ExpAdapter;
    private SSLSocketFactory ssl;
    private long id;
    private boolean todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pacientes);
        GetHttp.createInstance();
        Pinning pin = new Pinning(VerPacientesActivity.this);
        ssl = pin.getPinnedCertSslSocketFactory();
        gson = new Gson();
        SharedPreferences preferences = getSharedPreferences(MainActivity.TAG, MODE_PRIVATE);
        token = preferences.getString("token", "");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        todos = bundle.getBoolean("todos");
        id = preferences.getLong("ID", -1);
        new pedirPacientes().execute("https://migraine-services.herokuapp.com/webresources/pacientes/");
    }

    private ExpandableListView.OnChildClickListener ExpandList_ItemClicked = new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            ExpandListChild ch = ExpListItems.get(groupPosition).getItems().get(childPosition);
            return false;
        }

    };

    private ArrayList<ExpandListGroup> SetStandardGroups(boolean todos) {

        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        for (int i = 0; i < listaPacientes.size(); i++) {
            PacienteDTO actual = listaPacientes.get(i);
            if (actual.getDoctorid() == id && !todos) {
                ExpandListGroup group = new ExpandListGroup();
                group.setName("Paciente " + (i + 1));

                ExpandListChild paciente = new ExpandListChild();
                paciente.setName("Nombre : " + actual.getName());
                paciente.setTag(null);
                list2.add(paciente);

                ExpandListChild fecha = new ExpandListChild();
                Date fechita = new Date();
                fechita.setTime(actual.getBirthdate());
                fecha.setName("Fecha de nacimiento: " + fechita.toString());
                fecha.setTag(null);
                list2.add(fecha);

                ExpandListChild cedula = new ExpandListChild();
                cedula.setName("Cédula : " + actual.getCedula());
                cedula.setTag(null);
                list2.add(cedula);

                group.setItems(list2);
                list2 = new ArrayList<ExpandListChild>();
                list.add(group);
            } else {
                ExpandListGroup group = new ExpandListGroup();
                group.setName("Paciente " + (i + 1));

                ExpandListChild paciente = new ExpandListChild();
                paciente.setName("Nombre : " + actual.getName());
                paciente.setTag(null);
                list2.add(paciente);

                ExpandListChild fecha = new ExpandListChild();
                Date fechita = new Date();
                fechita.setTime(actual.getBirthdate());
                fecha.setName("Fecha de nacimiento: " + fechita.toString());
                fecha.setTag(null);
                list2.add(fecha);

                ExpandListChild cedula = new ExpandListChild();
                cedula.setName("Cédula : " + actual.getCedula());
                cedula.setTag(null);
                list2.add(cedula);

                ExpandListChild idDoctor = new ExpandListChild();
                idDoctor.setName("ID del doctor : " + actual.getDoctorid());
                idDoctor.setTag(null);
                list2.add(idDoctor);

                group.setItems(list2);
                list2 = new ArrayList<ExpandListChild>();
                list.add(group);
            }
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_pacientes, menu);
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

    private class pedirPacientes extends AsyncTask<String, Long, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogo = ProgressDialog.show(VerPacientesActivity.this, "Espera un momento...", "Cargando...", true);
            dialogo.setCancelable(true);
        }

        protected String doInBackground(String... urls) {
            try {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X_rest_user", token);
                headers.put("Accept", "application/json");
                Response response = GetHttp.run(urls[0], headers, null);
                String respuesta = response.body().string();
                System.out.println(respuesta);
                System.out.println("-------------------");
                List<PacienteDTO> resp = obtenerEpisodios(respuesta);
                if (resp != null)
                    listaPacientes = resp;
                else
                    listaPacientes = new ArrayList<PacienteDTO>();
                return respuesta;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            ExpListItems = SetStandardGroups(todos);
            ExpAdapter = new ExpandListAdapter(VerPacientesActivity.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);
            ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
            dialogo.dismiss();
        }
    }

    private List<PacienteDTO> obtenerEpisodios(String json) {
        Type type = new TypeToken<ArrayList<PacienteDTO>>() {
        }.getType();
        try {
            List<PacienteDTO> resp = gson.fromJson(json, type);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
