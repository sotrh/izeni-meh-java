package sotrh.izeni.mehprojectjava.data;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by izeni on 7/11/16.
 */
public class MehTheme extends RealmObject implements Serializable {
    public String accentColor;
    public String backgroundColor;
    public String backgroundImage;
    public String foreground;
}
