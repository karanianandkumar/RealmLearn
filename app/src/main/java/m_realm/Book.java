package m_realm;

import io.realm.RealmObject;

/**
 * Created by Anand on 11/26/2016.
 */

public class Book extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
