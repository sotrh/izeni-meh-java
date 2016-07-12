package sotrh.izeni.mehprojectjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sotrh.izeni.mehprojectjava.R;
import sotrh.izeni.mehprojectjava.adapter.PreviousDealsAdapter;

/**
 * Created by izeni on 7/11/16.
 */
public class PreviousDealsFragment extends Fragment {
    public static final String TAG = "PreviousDealsFragment";

    public static PreviousDealsFragment newInstance() {
        return new PreviousDealsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_previous_deals, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PreviousDealsAdapter(getActivity()));
    }
}
