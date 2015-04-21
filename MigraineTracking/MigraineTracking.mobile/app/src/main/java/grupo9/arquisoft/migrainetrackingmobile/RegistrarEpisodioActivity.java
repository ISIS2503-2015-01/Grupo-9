package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;


public class RegistrarEpisodioActivity extends ActionBarActivity {

    MultiSelectionSpinner spinCatalizadores;
    MultiSelectionSpinner spinMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_episodio);
        Intent intent = getIntent();
        String[] catalizadores = {"Catalizador 1","Catalizador 2","Catalizador 3"};
        spinCatalizadores = (MultiSelectionSpinner) findViewById(R.id.spinCatalizadores);
        spinCatalizadores.setItems(catalizadores);
        //ArrayList<MedicamentoDTO> medica = MainActivity.medicamentos;
        ArrayList<String> medicamentos = new ArrayList<String>();
        medicamentos.add("Medicamento 1");
        medicamentos.add("Medicamento 2");
        medicamentos.add("Medicamento 3");
        //for(int i=1;i<medica.size();i++)
        //{
        //    String nombre = medica.get(i).getNombre();
        //    medicamentos.add(nombre);
        //}
        spinMedicamentos = (MultiSelectionSpinner) findViewById(R.id.spinMedicamentos);
        spinMedicamentos.setItems(medicamentos);
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

    public void agregarSintoma(View view)
    {
        Intent intent = new Intent(this,AgregarSintomaActivity.class);
        EditText loca = (EditText) findViewById(R.id.localizacion_edit);
        EditText fech = (EditText) findViewById(R.id.fecha_edit);
        EditText horas = (EditText) findViewById(R.id.horas_edit);
        EditText inten = (EditText) findViewById(R.id.intensidad_edit);
        String localizacion = loca.getText().toString();
        try {
            int horasSueno = Integer.parseInt(horas.getText().toString());
            int intensidad = Integer.parseInt(inten.getText().toString());
            Date fecha = new Date();
            fecha.setYear(Integer.parseInt(fech.getText().toString().substring(0, 3)));
            fecha.setMonth(Integer.parseInt(fech.getText().toString().substring(5, 6)));
            fecha.setDate(Integer.parseInt(fech.getText().toString().substring(8, 9)));
            EpisodioDolorDTO nuevo = new EpisodioDolorDTO(fecha, localizacion, horasSueno, intensidad);
        }
        catch(Exception e)
        {

        }
        startActivity(intent);
    }

    public void agregarCatalizadores(View view)
    {
        Intent intent = new Intent(this,AgregarCatalizadorActivity.class);
        startActivity(intent);
    }

    public void agregarMedicamentosEpisodioDolor(View view)
    {
        Intent intent = new Intent(this,AgregarMedicamentosEpisodio.class);
        startActivity(intent);
    }

    public void registrarEpisodioDolor(View view)
    {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        EditText loca = (EditText) findViewById(R.id.localizacion_edit);
        EditText fech = (EditText) findViewById(R.id.fecha_edit);
        EditText horas = (EditText) findViewById(R.id.horas_edit);
        EditText inten = (EditText) findViewById(R.id.intensidad_edit);
        String localizacion = loca.getText().toString();
        try {
            int horasSueno = Integer.parseInt(horas.getText().toString());
            int intensidad = Integer.parseInt(inten.getText().toString());
            Date fecha = new Date();
            fecha.setYear(Integer.parseInt(fech.getText().toString().substring(0, 3)));
            fecha.setMonth(Integer.parseInt(fech.getText().toString().substring(5, 6)));
            fecha.setDate(Integer.parseInt(fech.getText().toString().substring(8, 9)));
            EpisodioDolorDTO nuevo = new EpisodioDolorDTO(fecha, localizacion, horasSueno, intensidad);
        }
        catch(Exception e)
        {

        }
        startActivity(intent);
    }



}
