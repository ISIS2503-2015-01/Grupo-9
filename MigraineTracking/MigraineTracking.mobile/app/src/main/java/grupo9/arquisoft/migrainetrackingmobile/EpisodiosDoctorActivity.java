package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;


public class EpisodiosDoctorActivity extends ActionBarActivity {

    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodios_doctor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_episodios_doctor, menu);
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

    public void buscar(View v)
    {
        EditText info = (EditText) findViewById(R.id.paciente_edit);
        cedula = info.getText().toString();
        try
        {
            Long.parseLong(cedula);
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Ingrese una cédula válida").setNeutralButton("Cerrar", null).show();
            return;
        }
        Intent intent = new Intent(this,EpisodiosPaciente.class);
        intent.putExtra("identificacion",cedula);
        intent.putExtra("fechas",false);
        startActivity(intent);
    }

    public void buscarFechas(View v)
    {
        EditText info = (EditText) findViewById(R.id.paciente_edit);
        cedula = info.getText().toString();
        try
        {
            Long.parseLong(cedula);
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Ingrese una cédula válida").setNeutralButton("Cerrar", null).show();
            return;
        }
        Spinner spinnerDias = (Spinner) findViewById(R.id.spindias);
        int dia = spinnerDias.getSelectedItemPosition()+1;
        Spinner spinnerMeses = (Spinner) findViewById(R.id.spinmeses);
        int mes = spinnerMeses.getSelectedItemPosition()+1;
        Spinner spinnerAnios = (Spinner) findViewById(R.id.spinanios);
        int anio = spinnerAnios.getSelectedItemPosition() + 2013;
        Date fecha=new Date();
        if(((mes==4 || mes==6 || mes==9 || mes==11) && dia>30) || (mes==2&&dia>29))
        {
            new AlertDialog.Builder(this).setTitle("Error de creación").setMessage("Ha introducido una fecha infactible").setNeutralButton("Cerrar", null).show();
            return;
        }
        fecha.setDate(dia);
        fecha.setMonth(mes);
        fecha.setYear(anio);
        Spinner spinnerDias2 = (Spinner) findViewById(R.id.spindias2);
        int dia2 = spinnerDias2.getSelectedItemPosition()+1;
        Spinner spinnerMeses2 = (Spinner) findViewById(R.id.spinmeses2);
        int mes2 = spinnerMeses2.getSelectedItemPosition()+1;
        Spinner spinnerAnios2 = (Spinner) findViewById(R.id.spinanios2);
        int anio2 = spinnerAnios2.getSelectedItemPosition() + 2013;
        Date fecha2=new Date();
        if(((mes2==4 || mes2==6 || mes2==9 || mes2==11) && dia2>30) || (mes2==2&&dia2>29))
        {
            new AlertDialog.Builder(this).setTitle("Error de creación").setMessage("Ha introducido una fecha infactible").setNeutralButton("Cerrar", null).show();
            return;
        }
        fecha2.setDate(dia2);
        fecha2.setMonth(mes2);
        fecha2.setYear(anio2);
        System.out.println("Esta es la fecha 1");
        System.out.println(fecha.getDate());
        System.out.println(fecha.getMonth());
        System.out.println(fecha.getYear());
        System.out.println("Esta es la fecha 2");
        System.out.println(fecha2.getDate());
        System.out.println(fecha2.getMonth());
        System.out.println(fecha2.getYear());
        Intent intent = new Intent(this,EpisodiosPaciente.class);
        intent.putExtra("identificacion",cedula);
        intent.putExtra("fechas",true);
        intent.putExtra("fecha1",fecha.getTime());
        intent.putExtra("fecha2",fecha2.getTime());
        startActivity(intent);
    }
}
