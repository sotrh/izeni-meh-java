package sotrh.izeni.mehprojectjava.data;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class Story extends RealmObject implements Serializable {
    public String title;
    public String body;
}
