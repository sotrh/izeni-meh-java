package sotrh.izeni.mehprojectjava.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmList;
import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.data.Item;

/**
 * Created by izeni on 7/11/16.
 */
public class ItemAdapter extends RecyclerView.Adapter {
    private final RealmList<Item> items;

    public ItemAdapter(RealmList<Item> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            condition = (TextView) itemView.findViewById(R.id.condition);
            price = (TextView) itemView.findViewById(R.id.price);
        }

        TextView condition;
        TextView price;
    }
}
