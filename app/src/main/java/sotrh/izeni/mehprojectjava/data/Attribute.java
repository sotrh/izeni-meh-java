package sotrh.izeni.mehprojectjava.data;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class Attribute extends RealmObject implements Serializable {
    String key;
    String value;
}
