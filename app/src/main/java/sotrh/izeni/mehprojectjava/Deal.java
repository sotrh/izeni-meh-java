package sotrh.izeni.mehprojectjava;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class Deal extends RealmObject {
    public String id;
    public String title;
    public String specifications;
    public String url;

    public RealmList<Item> items;
    public RealmList<RealmString> photos;

    public Story story;
    public Theme theme;
}
