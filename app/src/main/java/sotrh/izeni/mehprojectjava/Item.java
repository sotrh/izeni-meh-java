package sotrh.izeni.mehprojectjava;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class Item extends RealmObject {
    String id;
    String condition;
    String photo;
    float price;

    RealmList<Attribute> attributes;
}
