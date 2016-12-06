package ledare.com.br.spacefitness.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledare.com.br.spacefitness.R;


public class TreinoFragment extends Fragment {

    public TreinoFragment() {
        // Required empty public constructor
    }

    public static TreinoFragment newInstance() {
        TreinoFragment fragment = new TreinoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_treino, container, false);
    }

}
