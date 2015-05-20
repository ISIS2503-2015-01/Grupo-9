package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.squareup.okhttp.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import grupo9.arquisoft.migrainetrackingmobile.dtos.CatalizadorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.SintomaDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.DataSecurity;
import grupo9.arquisoft.migrainetrackingmobile.extras.MultiSelectionSpinner;
import grupo9.arquisoft.migrainetrackingmobile.extras.Pinning;
import grupo9.arquisoft.migrainetrackingmobile.extras.PostHttp;


public class RegistrarEpisodioActivity extends ActionBarActivity {

    MultiSelectionSpinner spinCatalizadores;
    MultiSelectionSpinner spinSintomas;
    private long idUsuario;
    private String token;
    private ArrayList<MedicamentoDTO> medicamentos;
    private ArrayList<CatalizadorDTO> catalizadores;
    private ArrayList<SintomaDTO> sintomas;
    final Context context = this;
    private String jsonRespuesta;
    ProgressDialog dialogo;
    private SSLSocketFactory ssl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pinning pin=new Pinning(RegistrarEpisodioActivity.this);
        ssl=pin.getPinnedCertSslSocketFactory();
        medicamentos= new ArrayList<MedicamentoDTO>();
        setContentView(R.layout.activity_registrar_episodio);
        SharedPreferences preferences=getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        idUsuario=preferences.getLong("CEDULA",0);
        token=preferences.getString("token","");
        String[] catalizadores = {"Estres","Anticonceptivos","Chocolate","Licor","Endulcolorantes artificiales", "Citricos", "Queso curado", "Yogur","Pescado","Salsa de soja","Platanos","Aguacate","Vino tinto","Esfuerzo fisico","Estimulo frio(Ej: helado)","Luces intensas","Tabaco","Olores fuertes"};
        spinCatalizadores = (MultiSelectionSpinner) findViewById(R.id.spinCatalizadores);
        spinCatalizadores.setItems(catalizadores);
        spinSintomas = (MultiSelectionSpinner) findViewById(R.id.spinSintomas);
        String[] sintomas = {"Vomito", "Depresion","Palidez y cambios de temperatura en la cabeza","Ansiedad","Insomnio","Fatiga","Palpitaciones","Aura","Confundido y olvidadiso"};
        spinSintomas.setItems(sintomas);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar_episodio, menu);
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

    public void agregarMedicamento(View view)
    {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_agregar_medicamento);
        dialog.setTitle("Agregar Un Medicamento");

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the dialog_agregar_medicamento dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nom=(EditText)dialog.findViewById(R.id.nombre_edit);
                EditText fec=(EditText)dialog.findViewById(R.id.fecha_edit);
                String nombre=nom.getText().toString();
                String fecha=fec.getText().toString();
                Date fechal=new Date();
                try
                {
                    DateFormat dateFormat=new SimpleDateFormat("dd/mm/yyyy");
                    fechal=dateFormat.parse(fecha);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                MedicamentoDTO medicamentoDTO=new MedicamentoDTO(nombre,fechal+"");
                medicamentos.add(medicamentoDTO);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void registrarEpisodioDolor(View view)
    {
        llenarListas();
        Spinner spinnerDias = (Spinner) findViewById(R.id.spindias);
        String dia = (String) spinnerDias.getSelectedItem();
        int diaI = spinnerDias.getSelectedItemPosition()+1;
        Spinner spinnerMeses = (Spinner) findViewById(R.id.spinmeses);
        int mesI = spinnerMeses.getSelectedItemPosition()+1;
        String mes= "";
        if(mesI<10)
        {
            mes = "0"+mesI;
        }
        else
        {
            mes=mesI+"";
        }
        Spinner spinnerAnios = (Spinner) findViewById(R.id.spinanios);
        String anio = (String) spinnerAnios.getSelectedItem();
        Spinner spinnerSueno = (Spinner) findViewById(R.id.spinnerhoras);
        int hora = spinnerSueno.getSelectedItemPosition() + 1;
        Spinner spinnerIntensidad = (Spinner) findViewById(R.id.spinnerintensidad);
        int intensidad = spinnerIntensidad.getSelectedItemPosition()+1;
        EditText local = (EditText)findViewById(R.id.localizacion_edit);
        String localizacion=local.getText().toString();
        Date fecha=new Date();
        if(((mesI==4 || mesI==6 || mesI==9 || mesI==11) && diaI>30) || (mesI==2&&diaI>29))
        {
            new AlertDialog.Builder(this).setTitle("Error de creación").setMessage("Ha introducido222 una fecha infactible").setNeutralButton("Cerrar", null).show();
            return;
        }
        String fech = dia+"/"+mes+"/"+anio;
        System.out.println(fech);
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try
        {
            fecha=format.parse(fech);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            new AlertDialog.Builder(this).setTitle("Error de creación").setMessage("Ha introducido una fecha infactible").setNeutralButton("Cerrar", null).show();
            return;
        }
        EpisodioDolorDTO episodio=new EpisodioDolorDTO();
        episodio.setId(null);
        episodio.setCatalizadores(catalizadores);
        episodio.setFecha(fecha.getTime());
        episodio.setCedulaPaciente(idUsuario);
        episodio.setHoursSlept(hora);
        episodio.setMedicamentos(medicamentos);
        episodio.setSintomas(sintomas);
        episodio.setIntensidad(intensidad);
        episodio.setLocalizacion(localizacion);

        jsonRespuesta="{\"id\":"+"null"+"," +
                "\"fecha\":"+episodio.getFecha()+"," +
                "\"localizacion\":\""+episodio.getLocalizacion()+"\"," +
                "\"intensidad\":"+episodio.getIntensidad()+"," +
                "\"hoursSlept\":"+episodio.getHoursSlept()+"," +
                "\"cedulaPaciente\":"+episodio.getCedulaPaciente()+ "," +
                "\"sintomas\":[";
                for(int i=0;i<sintomas.size();i++)
                {
                    if(i!=0)
                    {
                        jsonRespuesta+=",";
                    }
                    SintomaDTO sin=sintomas.get(i);
                    jsonRespuesta+="{\"especificacion\":\""+sin.getEspecificacion()+"\"}";
                }
                jsonRespuesta+="],";
                jsonRespuesta+="\"catalizadores\":[";
                for(int i=0;i<catalizadores.size();i++)
                {
                    if(i!=0)
                    {
                        jsonRespuesta+=",";
                    }
                    CatalizadorDTO cat=catalizadores.get(i);
                    jsonRespuesta+="{\"especificacion\":\""+cat.getEspecificacion()+"\"}";
                }
                jsonRespuesta+="],";
                jsonRespuesta+="\"medicamentos\":[";
                for (int i=0;i<medicamentos.size();i++)
                {
                    if(i!=0)
                    {
                        jsonRespuesta+=",";
                    }
                    MedicamentoDTO med=medicamentos.get(i);
                    jsonRespuesta+="{\"referencia\":\""+med.getReferencia()+"\"," +
                            "\"fechaDePrescripcion\":\""+med.getFechaDePrescripcion()+"\"}";
                }
                jsonRespuesta+="]}";
        System.out.println(jsonRespuesta);
        new registrar().execute("https://migraine-services.herokuapp.com/webresources/episodios");
    }

    private void llenarListas()
    {
        List<String> cat=spinCatalizadores.getSelectedStrings();
        catalizadores=new ArrayList<CatalizadorDTO>();
        for(int i=0;i<cat.size();i++)
        {
            CatalizadorDTO nuevo=new CatalizadorDTO(cat.get(i));
            catalizadores.add(nuevo);
        }
        List<String> sin=spinSintomas.getSelectedStrings();
        sintomas=new ArrayList<SintomaDTO>();
        for(int i=0;i<sin.size();i++)
        {
            SintomaDTO nuevo=new SintomaDTO(sin.get(i));
            sintomas.add(nuevo);
        }
    }

    private class registrar extends AsyncTask<String, Long, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialogo= ProgressDialog.show(RegistrarEpisodioActivity.this, "Espera un momento...", "Cargando...", true);
            dialogo.setCancelable(true);
        }
        protected String doInBackground(String... urls)
        {
            try
            {
                String hash= DataSecurity.hashCryptoCode(jsonRespuesta);
                String hashNoSpace=hash.replaceAll("\\s+","");
                Map<String, String> headers=new HashMap<String,String>();
                headers.put("X_rest_user", token);
                headers.put("Data_hash",hashNoSpace);
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");

                Response response=new PostHttp().run(urls[0],jsonRespuesta,headers,null);
                return response.body().string();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "No se conectó al servidor";
            }
        }

        protected void onPostExecute(String response)
        {
            String res=response.split("\"")[3];
            String resp=res.replaceAll("\\s+","");
            dialogo.dismiss();
            new AlertDialog.Builder(RegistrarEpisodioActivity.this).setTitle("Análisis").setMessage(resp).setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(RegistrarEpisodioActivity.this,MenuPacienteActivity.class);
                    startActivity(intent);
                }
            }).show();
        }
    }

}
