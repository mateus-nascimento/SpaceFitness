package ledare.com.br.spacefitness.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledare.com.br.spacefitness.R;


public class InicialFragment extends Fragment {

    public InicialFragment() {
        // Required empty public constructor
    }

    public static InicialFragment newInstance() {
        InicialFragment fragment = new InicialFragment();
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
