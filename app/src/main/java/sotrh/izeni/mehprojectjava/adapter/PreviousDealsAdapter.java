package sotrh.izeni.mehprojectjava.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;
import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.data.Deal;
import sotrh.izeni.mehprojectjava.fragment.DealFragment;

/**
 * Created by izeni on 7/12/16.
 */
public class PreviousDealsAdapter extends RecyclerView.Adapter {

    private final RealmResults<Deal> deals;
    private final FragmentActivity activity;

    public PreviousDealsAdapter(FragmentActivity activity) {
        super();
        Realm realm = Realm.getDefaultInstance();
        this.deals = realm.where(Deal.class).findAll();
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_deal, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Deal deal = deals.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        // deal with the text first
        viewHolder.title.setText(deal.title);
        viewHolder.price.setText("$" + deal.items.first().price);

        // now load the image
        Picasso.with(viewHolder.photo.getContext()).load(deal.items.first().photo).into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView photo;
        public final TextView title;
        public final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            this.photo = (ImageView) itemView.findViewById(R.id.photo);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.price = (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, DealFragment.newInstance(deals.get(getAdapterPosition())))
                            .commit();
                }
            });
        }
    }
}
