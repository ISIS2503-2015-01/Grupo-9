package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
/**
 * Created by henryfvargas on 31/03/15.
 */
public class VerPacientesActivity extends ActionBarActivity
{

        private ExpandListAdapter ExpAdapter;
        private ArrayList<ExpandListGroup> ExpListItems;
        private ExpandableListView ExpandList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ver_pacientes);
            ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(VerPacientesActivity.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);
            ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
        }

    private ExpandableListView.OnChildClickListener ExpandList_ItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            // TODO Auto-generated method stub
            ExpandListChild ch =  ExpListItems.get(groupPosition).getItems().get(childPosition);
            //Your code where

            //Creación del intent
            if(ch.getName().startsWith("Identificación:")) {
                Intent intent = new Intent(VerPacientesActivity.this, VerEpisodiosActivity.class);
                Bundle b = new Bundle();
                b.putString("tipo", "PACIENTE");
                b.putString("id", ch.getName().split(": ")[1]);

                intent.putExtras(b);

                startActivity(intent);
            }

            return false;
        }

    };

        public ArrayList<ExpandListGroup> SetStandardGroups() {
            ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
            ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
            ExpandListGroup gru1 = new ExpandListGroup();
            gru1.setName("Paciente: Henry Fabián Vargas");
            ExpandListChild ch1_1 = new ExpandListChild();
            ch1_1.setName("Fecha de nacimiento: 22/08/1995");
            ch1_1.setTag(null);
            list2.add(ch1_1);
            ExpandListChild ch1_2 = new ExpandListChild();
            ch1_2.setName("Identificación: 875353221");
            ch1_2.setTag(null);
            list2.add(ch1_2);
            ExpandListChild ch1_3 = new ExpandListChild();
            ch1_3.setName("Peso: 78");
            ch1_3.setTag(null);
            list2.add(ch1_3);
            ExpandListChild ch1_4 = new ExpandListChild();
            ch1_4.setName("Estatura: 170");
            ch1_4.setTag(null);
            list2.add(ch1_4);
            gru1.setItems(list2);
            list2 = new ArrayList<ExpandListChild>();

            ExpandListGroup gru2 = new ExpandListGroup();
            gru2.setName("Paciente: Pedro Otoya");
            ExpandListChild ch2_1 = new ExpandListChild();
            ch2_1.setName("Fecha de nacimiento: 24/09/1990");
            ch2_1.setTag(null);
            list2.add(ch2_1);
            ExpandListChild ch2_2 = new ExpandListChild();
            ch2_2.setName("Identificación: 1006745893");
            ch2_2.setTag(null);
            list2.add(ch2_2);
            ExpandListChild ch2_3 = new ExpandListChild();
            ch2_3.setName("Peso: 86");
            ch2_3.setTag(null);
            list2.add(ch2_3);
            ExpandListChild ch2_4 = new ExpandListChild();
            ch2_4.setName("Estatura: 192");
            ch2_4.setTag(null);
            list2.add(ch2_4);
            gru2.setItems(list2);
            list.add(gru1);
            list.add(gru2);

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
}
