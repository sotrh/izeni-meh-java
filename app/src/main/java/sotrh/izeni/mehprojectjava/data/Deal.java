package sotrh.izeni.mehprojectjava.data;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sotrh.izeni.mehprojectjava.RealmString;

/**
 * Created by izeni on 7/11/16.
 */
public class Deal extends RealmObject implements Serializable {
    @PrimaryKey
    public String id;
    public String title;
    public String specifications;
    public String features;
    public String url;

    public RealmList<Item> items;
    public RealmList<RealmString> photos;

    public Story story;
    public MehTheme theme;
}
