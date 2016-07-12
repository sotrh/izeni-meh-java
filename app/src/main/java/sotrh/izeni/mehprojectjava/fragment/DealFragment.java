package sotrh.izeni.mehprojectjava.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.RealmString;
import sotrh.izeni.mehprojectjava.RealmStringTypeAdapter;
import sotrh.izeni.mehprojectjava.ResponseWrapper;
import sotrh.izeni.mehprojectjava.adapter.ItemAdapter;
import sotrh.izeni.mehprojectjava.data.Deal;
import sotrh.izeni.mehprojectjava.data.MehTheme;
import sotrh.izeni.mehprojectjava.service.MehService;

/**
 * Created by izeni on 7/11/16.
 */
public class DealFragment extends Fragment {
    public static final String TAG = "DealFragment";
    private Call<ResponseWrapper> call;
    private Deal deal;

    public static DealFragment newInstance(Deal deal) {
        DealFragment fragment = new DealFragment();
        Bundle args = new Bundle();
        args.putSerializable("deal", deal);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_deal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deal = (Deal) getArguments().getSerializable("deal");

        if (deal == null) {

            // load deal data using retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MehService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .registerTypeAdapter(RealmString.class, new RealmStringTypeAdapter())
                            .create()))
                    .build();

            MehService service = retrofit.create(MehService.class);
            call = service.getCurrentDeal();
            call.enqueue(new Callback<ResponseWrapper>() {
                @Override
                public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                    updateUI(response.body().deal);
                }

                @Override
                public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                    if (!call.isCanceled()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("An Error Has Occurred")
                                .setMessage(t.getMessage())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        getActivity().finish();
                                    }
                                })
                                .show();
                    }
                }
            });
            setLoading(true);
        } else {
            updateUI(deal);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null) call.cancel();
        if (deal != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(deal);
            realm.commitTransaction();
            getArguments().putSerializable("deal", deal);
        }
    }

    private void setLoading(boolean loading) {
        if (getView() == null) return;
        getView().findViewById(R.id.progress).setVisibility(
                loading ? View.VISIBLE : View.GONE
        );
        getView().findViewById(R.id.launchBrowser).setVisibility(
                loading ? View.GONE : View.VISIBLE
        );
    }

    private void updateUI(Deal deal) {
        // disable the progress bar
        setLoading(false);

        View view = getView();
        this.deal = deal;

        if (this.deal == null) {
            getActivity().finish();
            return;
        }

        // set the background image
        Picasso.with(getContext()).load(this.deal.theme.backgroundImage).into((ImageView) view.findViewById(R.id.background));

        // set the actionbar color
        getActivity().findViewById(R.id.toolbar).setBackgroundDrawable(new ColorDrawable(Color.parseColor(this.deal.theme.backgroundColor)));

        // set the text views first
        setText(R.id.title, this.deal.title, this.deal.theme);
        setText(R.id.story_title, this.deal.story.title, this.deal.theme);
        setText(R.id.story_body, this.deal.story.body, this.deal.theme);
        setText(R.id.features, this.deal.features, this.deal.theme);
        setText(R.id.specifications, this.deal.specifications, this.deal.theme);

        // fill the recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.items);
        recyclerView.setAdapter(new ItemAdapter(this.deal.items, this.deal.theme));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // set the launchBrowser click listener
        view.findViewById(R.id.launchBrowser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DealFragment.this.deal.url));
                startActivity(intent);
            }
        });

    }

    private void setText(int textView, String s, MehTheme theme) {
        TextView textView1 = (TextView) getView().findViewById(textView);
        textView1.setText(s);
        int color = Color.BLACK;
        if (theme.foreground.equals("light")) color = Color.WHITE;
        textView1.setTextColor(color);
    }
}
