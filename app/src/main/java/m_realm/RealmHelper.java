package m_realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Anand on 11/26/2016.
 */

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm=realm;
    }

    //WRITE
    public void save(final Book book){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book b=realm.copyToRealm(book);
            }
        });
    }
    //Retrieval

    public ArrayList<String> retrieve(){

        ArrayList<String> booksList=new ArrayList();
        RealmResults<Book> books=realm.where(Book.class).findAll();

        for(Book b:books){
            booksList.add(b.getName());
        }

        return booksList;

    }

}
