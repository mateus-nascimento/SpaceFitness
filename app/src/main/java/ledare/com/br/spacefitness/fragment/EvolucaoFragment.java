package ledare.com.br.spacefitness.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ledare.com.br.spacefitness.R;
import ledare.com.br.spacefitness.adapter.EvolucaoAdapter;
import ledare.com.br.spacefitness.database.EvolucaoDAO;
import ledare.com.br.spacefitness.model.Evolucao;

public class EvolucaoFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Evolucao> evolucaoList;
    private EvolucaoDAO mDatabase;

    public static EvolucaoFragment newInstance() {
        EvolucaoFragment fragment = new EvolucaoFragment();
        return fragment;
    }

    public EvolucaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = new EvolucaoDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_evolucao, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerEvolucao);
        recyclerView.setAdapter(new EvolucaoAdapter(evolucaoList));

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.floatEvolucao);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Evolucao e = new Evolucao();
                        e.carga="1";
                        e.exercicio="1";
                        e.repeticao="1";
                        mDatabase.inserirEvolucao(e);
                        Log.d("TESTE", "inseriu");
                    }
                }
        );

        return layout;
    }

}
