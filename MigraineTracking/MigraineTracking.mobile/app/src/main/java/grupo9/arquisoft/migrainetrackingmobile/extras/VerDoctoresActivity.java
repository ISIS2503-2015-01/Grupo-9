package grupo9.arquisoft.migrainetrackingmobile.extras;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grupo9.arquisoft.migrainetrackingmobile.MainActivity;
import grupo9.arquisoft.migrainetrackingmobile.R;
import grupo9.arquisoft.migrainetrackingmobile.dtos.DoctorDTO;

public class VerDoctoresActivity extends ActionBarActivity {

    private Gson gson;
    private String token;
    private ExpandableListView ExpandList;
    private ArrayList<ExpandListGroup> ExpListItems;
    private List<DoctorDTO> listaDoctores;
    ProgressDialog dialogo;
    private ExpandListAdapter ExpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_doctores);
        gson = new Gson();
        SharedPreferences preferences = getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        token = preferences.getString("token","");
        System.out.println(token);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
        new pedirDoctores().execute("https://migraine-services.herokuapp.com/webresources/doctores/");
    }

    private ExpandableListView.OnChildClickListener ExpandList_ItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            ExpandListChild ch =  ExpListItems.get(groupPosition).getItems().get(childPosition);
            return false;
        }

    };

    private ArrayList<ExpandListGroup> SetStandardGroups()
    {
        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        for(int i=0; i<listaDoctores.size();i++)
        {
            DoctorDTO doctor = listaDoctores.get(i);
            ExpandListGroup group = new ExpandListGroup();
            group.setName("Doctor: " + doctor.getName());
            ExpandListChild username = new ExpandListChild();
            username.setName("Nombre de Usuario: " + doctor.getUsername());
            username.setTag(null);
            list2.add(username);
            ExpandListChild identificacion = new ExpandListChild();
            identificacion.setName("Identificacion: " + doctor.getId());
            identificacion.setTag(null);
            list2.add(identificacion);
            group.setItems(list2);
            list2 = new ArrayList<ExpandListChild>();
            list.add(group);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_doctores, menu);
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

    private class pedirDoctores extends AsyncTask<String,Long,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialogo = ProgressDialog.show(VerDoctoresActivity.this,"Espera un momento...","Cargando",true);
            dialogo.setCancelable(true);
        }

        protected String doInBackground(String... urls)
        {
            try
            {
                Map<String,String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("x_rest_user",token);
                //headers.put("Accept", "aplication/json");
                Response response = new GetHttp().run(urls[0],headers);
                String respuesta = response.body().string();
                System.out.println(respuesta);
                System.out.println("-------------------");
                List<DoctorDTO> resp = obtenerDoctores(respuesta);
                if(resp!=null)
                    listaDoctores = resp;
                else
                    listaDoctores = new ArrayList<DoctorDTO>();
                return respuesta;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(VerDoctoresActivity.this,ExpListItems);
            ExpandList.setAdapter(ExpAdapter);
            ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
            dialogo.dismiss();
        }
    }

    private List<DoctorDTO> obtenerDoctores(String json)
    {
        Type type = new TypeToken<ArrayList<DoctorDTO>>(){}.getType();
        try
        {
            List<DoctorDTO> response = gson.fromJson(json,type);
            return response;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
