package sotrh.izeni.mehprojectjava.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.RealmList;
import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.data.Item;
import sotrh.izeni.mehprojectjava.data.MehTheme;

/**
 * Created by izeni on 7/11/16.
 */
public class ItemAdapter extends RecyclerView.Adapter {
    private final RealmList<Item> items;
    private int color;
    private Context context;

    public ItemAdapter(RealmList<Item> items, MehTheme theme) {
        this.items = items;
        this.color = Color.BLACK;
        if (theme.foreground.equals("light")) this.color = Color.WHITE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        // set the text views first
        viewHolder.condition.setText(item.condition);
        viewHolder.condition.setTextColor(color);
        viewHolder.price.setText("Price: $" + item.price);
        viewHolder.price.setTextColor(color);

        // download the photo for the item asychronously
        Picasso.with(context).load(item.photo).into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            condition = (TextView) itemView.findViewById(R.id.condition);
            price = (TextView) itemView.findViewById(R.id.price);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public TextView condition;
        public TextView price;
        public ImageView photo;
    }
}
