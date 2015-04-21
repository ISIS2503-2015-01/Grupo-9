package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;



public class AgregarMedicamentoActivity extends ActionBarActivity {

    public final static String MEDICAMENTOS = "MedicamentosFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);
        Intent intent = getIntent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_medicamento, menu);
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

    public void volverInicio(View view)
    {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        EditText nombre = (EditText) findViewById(R.id.nombre_edit);
        EditText cantidadVeces = (EditText) findViewById(R.id.dosis_edit);
        EditText horas = (EditText) findViewById(R.id.horas_edit);
        EditText fecha = (EditText) findViewById(R.id.fecha_edit);
        EditText cantidad = (EditText) findViewById(R.id.cantidad_edit);
        String nomb = nombre.getText().toString();
        String cantVeces = cantidadVeces.getText().toString();
        String hor = horas.getText().toString();
        String fech = fecha.getText().toString();
        String cant = cantidad.getText().toString();
        try
        {
            MedicamentoDTO nuevo = new MedicamentoDTO(nomb, Integer.parseInt(cantVeces), Integer.parseInt(hor), Integer.parseInt(cant));
            //MainActivity.medicamentos.add(nuevo);
            Log.i("MyActivity", nomb);
           //Log.i("MyActivity", MainActivity.medicamentos.size()+ " medicamentos");
        }
        catch(Exception e)
        {

        }
        startActivity(intent);
    }
}
