package ledare.com.br.spacefitness.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import ledare.com.br.spacefitness.model.Evolucao;

public class EvolucaoAdapter extends RecyclerView.Adapter{

    private List<Evolucao> evolucaoList;

    public EvolucaoAdapter(List<Evolucao> evolucaoList){
        this.evolucaoList = evolucaoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return evolucaoList.size();
    }
}
