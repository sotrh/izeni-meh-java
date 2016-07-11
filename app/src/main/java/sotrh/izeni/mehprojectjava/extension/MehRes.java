package sotrh.izeni.mehprojectjava.extension;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by izeni on 7/11/16.
 */
public class MehRes extends Resources {
    public MehRes(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
    }
}
