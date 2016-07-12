package sotrh.izeni.mehprojectjava.extension;

import android.content.res.Resources;
import android.graphics.Color;

import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.data.MehTheme;

/**
 * Created by izeni on 7/11/16.
 */
public class MehRes extends Resources {

    int colorAccent;
    int colorPrimary;
    int colorPrimaryDark;
    int textColor;

    public MehRes(Resources original) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
        init(original);
    }

    private void init(Resources original) {
        colorAccent = original.getColor(R.color.colorAccent);
        colorPrimary = original.getColor(R.color.colorPrimary);
        colorPrimaryDark = original.getColor(R.color.colorPrimaryDark);
        textColor = original.getColor(R.color.textColor);
    }

    public void setTheme(MehTheme theme) {
        colorAccent = Color.parseColor(theme.accentColor);
        colorPrimary = Color.parseColor(theme.backgroundColor);
        switch (theme.foreground) {
            case "light":
                textColor = Color.WHITE;
                break;
            default:
                textColor = Color.BLACK;
                break;
        }
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        switch (id) {
            case R.color.colorAccent:
                return colorAccent;
            case R.color.colorPrimary:
                return colorPrimary;
            case R.color.colorPrimaryDark:
                return colorPrimaryDark;
            case R.color.textColor:
                return textColor;
        }
        return super.getColor(id);
    }

    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        switch (id) {
            case R.color.colorAccent:
                return colorAccent;
            case R.color.colorPrimary:
                return colorPrimary;
            case R.color.colorPrimaryDark:
                return colorPrimaryDark;
            case R.color.textColor:
                return textColor;
        }
        return super.getColor(id, theme);
    }
}
