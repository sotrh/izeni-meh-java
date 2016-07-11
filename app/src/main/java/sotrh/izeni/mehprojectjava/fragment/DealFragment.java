package sotrh.izeni.mehprojectjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

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
import sotrh.izeni.mehprojectjava.service.MehService;

/**
 * Created by izeni on 7/11/16.
 */
public class DealFragment extends Fragment {
    public static final String TAG = "DealFragment";

    public static DealFragment newInstance() {
        DealFragment fragment = new DealFragment();
        // todo: add arguments if needed
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

        // load deal data using retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MehService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(RealmString.class, new RealmStringTypeAdapter())
                        .create()))
                .build();

        MehService service = retrofit.create(MehService.class);
        Call<ResponseWrapper> call = service.getCurrentDeal();
        call.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                updateUI(response.body());
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                // todo: create an alert dialog
            }
        });
    }

    private void updateUI(ResponseWrapper responseWrapper) {
        View view = getView();
        Deal deal = responseWrapper.deal;

        // set the text views first
        ((TextView) view.findViewById(R.id.title)).setText(deal.title);

        // fill the recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.items);
        recyclerView.setAdapter(new ItemAdapter(deal.items));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
