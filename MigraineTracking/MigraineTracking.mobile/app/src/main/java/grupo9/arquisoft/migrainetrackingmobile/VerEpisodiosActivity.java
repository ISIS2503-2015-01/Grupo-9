package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class VerEpisodiosActivity extends ActionBarActivity {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_episodios);
        ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(VerEpisodiosActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setOnChildClickListener(ExpandList_ItemClicked);
    }

    private ExpandableListView.OnChildClickListener ExpandList_ItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            ExpandListChild ch =  ExpListItems.get(groupPosition).getItems().get(childPosition);
            //Creación del intent

            Intent intent= new Intent(VerEpisodiosActivity.this, VerAnalisisActivity.class);
            Bundle b = new Bundle();
            b.putString("tipo","EPISODIO");
            b.putString("id",ch.getName());

            intent.putExtras(b);

            startActivity(intent);


            return false;
        }

    };

    public ArrayList<ExpandListGroup> SetStandardGroups() {
        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Episodio 1");
        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setName("Fecha: 22/08/2014 11:29:30");
        ch1_1.setTag(null);
        list2.add(ch1_1);
        ExpandListChild ch1_2 = new ExpandListChild();
        ch1_2.setName("Localización: Frente");
        ch1_2.setTag(null);
        list2.add(ch1_2);
        ExpandListChild ch1_3 = new ExpandListChild();
        ch1_3.setName("Intensidad: 5");
        ch1_3.setTag(null);
        list2.add(ch1_3);
        ExpandListChild ch1_4 = new ExpandListChild();
        ch1_4.setName("Horas de sueño: 5");
        ch1_4.setTag(null);
        list2.add(ch1_4);
        gru1.setItems(list2);
        list2 = new ArrayList<ExpandListChild>();

        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("Episodio 2");
        ExpandListChild ch2_1 = new ExpandListChild();
        ch2_1.setName("Fecha: 24/09/2014 07:58:09");
        ch2_1.setTag(null);
        list2.add(ch2_1);
        ExpandListChild ch2_2 = new ExpandListChild();
        ch2_2.setName("Localización: Nuca");
        ch2_2.setTag(null);
        list2.add(ch2_2);
        ExpandListChild ch2_3 = new ExpandListChild();
        ch2_3.setName("Intensidad: 8");
        ch2_3.setTag(null);
        list2.add(ch2_3);
        ExpandListChild ch2_4 = new ExpandListChild();
        ch2_4.setName("Horas de sueño: 7");
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
}
