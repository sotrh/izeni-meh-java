package sotrh.izeni.mehprojectjava.data;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class Item extends RealmObject implements Serializable {
    String id;
    public String condition;
    public String photo;
    public float price;

    RealmList<Attribute> attributes;
}
