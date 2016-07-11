package sotrh.izeni.mehprojectjava;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by izeni on 7/11/16.
 */
public class RealmStringTypeAdapter extends TypeAdapter<RealmString> {
    @Override
    public void write(JsonWriter out, RealmString value) throws IOException {
        out.value(value.data);
    }

    @Override
    public RealmString read(JsonReader in) throws IOException {
        RealmString str = new RealmString();
        str.data = in.nextString();
        return str;
    }
}
