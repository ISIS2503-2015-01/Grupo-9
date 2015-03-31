package grupo9.arquisoft.migrainetrackingmobile;

import java.util.ArrayList;

/**
 * Created by henryfvargas on 30/03/15.
 */
public class ExpandListGroup
{
    private String Name;
    private ArrayList<ExpandListChild> Items;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public ArrayList<ExpandListChild> getItems() {
        return Items;
    }
    public void setItems(ArrayList<ExpandListChild> Items) {
        this.Items = Items;
    }
}
