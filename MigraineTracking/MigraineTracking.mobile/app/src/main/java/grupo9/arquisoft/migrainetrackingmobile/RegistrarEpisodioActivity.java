package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import grupo9.arquisoft.migrainetrackingmobile.dtos.CatalizadorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.SintomaDTO;


public class RegistrarEpisodioActivity extends ActionBarActivity {

    MultiSelectionSpinner spinCatalizadores;
    MultiSelectionSpinner spinSintomas;
    private String idUsuario;
    private ArrayList<MedicamentoDTO> medicamentos;
    private ArrayList<CatalizadorDTO> catalizadores;
    private ArrayList<SintomaDTO> sintomas;
    final Context context = this;
    private String jsonRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicamentos= new ArrayList<MedicamentoDTO>();
        setContentView(R.layout.activity_registrar_episodio);
        Intent intent = getIntent();
        Bundle extras=intent.getExtras();
        idUsuario=extras.getString("USUARIO");
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
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Agregar Un Medicamento");

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=dialog.findViewById(R.id.nombre_edit).toString();
                String fecha=dialog.findViewById(R.id.fecha_edit).toString();
                Date fechal=new Date();
                try
                {
                    DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-DD");
                    fechal=dateFormat.parse(fecha);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                MedicamentoDTO medicamentoDTO=new MedicamentoDTO(nombre,fechal);
                medicamentos.add(medicamentoDTO);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void registrarEpisodioDolor(View view)
    {
        llenarListas();
        String fec=findViewById(R.id.fecha_edit).toString();
        String horas=findViewById(R.id.horasSueno_edit).toString();
        String intensi=findViewById(R.id.intensidad_edit).toString();
        String localizacion=findViewById(R.id.localizacion_edit).toString();
        int hora=0;
        int intensidad=0;
        Date fecha=new Date();
        try
        {
            DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-DD");
            fecha=dateFormat.parse(fec);
            hora=Integer.parseInt(horas);
            intensidad=Integer.parseInt(intensi);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        EpisodioDolorDTO episodio=new EpisodioDolorDTO();
        episodio.setId(null);
        episodio.setCatalizadores(catalizadores);
        episodio.setFecha(fecha.getTime());
        if(idUsuario!=null)
        episodio.setCedulaPaciente(Long.parseLong(idUsuario));
        episodio.setHoursSlept(hora);
        episodio.setMedicamentos(medicamentos);
        episodio.setSintomas(sintomas);
        episodio.setIntensidad(intensidad);
        episodio.setLocalizacion(localizacion);

        Gson gson=new Gson();
        jsonRespuesta=gson.toJson(episodio);
        System.out.println(jsonRespuesta);

        new registrar().execute("https://migraine-services.herokuapp.com/episodios");
    }

    public void llenarListas()
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

    private class registrar extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            RestClient restClient = new RestClient(urls[0]);
            restClient.AddHeader("Content-Type", "application/json");
            restClient.AddParam(jsonRespuesta);
            try {
                restClient.Execute(RestClient.RequestMethod.POST);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(restClient.getResponse());
            return restClient.getResponse();
        }

        protected void onPostExecute(String response) {
            new AlertDialog.Builder(RegistrarEpisodioActivity.this).setTitle("Análisis").setMessage(response).setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(RegistrarEpisodioActivity.this,MenuPrincipalActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("USUARIO", idUsuario);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }).show();
        }
    }

}
