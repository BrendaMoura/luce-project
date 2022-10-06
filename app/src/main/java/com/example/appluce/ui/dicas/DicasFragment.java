package com.example.appluce.ui.dicas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.appluce.DicaEscolhida;
import com.example.appluce.R;
import java.util.ArrayList;
import java.util.List;

public class DicasFragment extends Fragment {
    Dicas dica = new Dicas();
    List<Dicas> dicas = new ArrayList<Dicas>();

    private DicasViewModel dicasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dicasViewModel =
                ViewModelProviders.of(this).get(DicasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dicas, container, false);
        final GridView gridview = root.findViewById(R.id.gridDica);

        Adicionar();
        AdaptadorDicas<Dicas> adapter = new AdaptadorDicas<>(getContext(), R.layout.item_grid, dicas);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DicaEscolhida.url = dicas.get(position).getLink();
                Intent intent = new Intent(getContext(), DicaEscolhida.class);
                startActivity(intent);
            }

        });
        return root;
    }

    public void Adicionar(){
        int i;
        String [] titulo = {"Guia de Estimulação","Comunicação","Estimulação"};
        Integer [] id = {R.drawable.dica,R.drawable.dica,R.drawable.dica};
        String [] link = {"http://www.movimentodown.org.br/desenvolvimento/guia-de-estimulacao-para-criancas-com-sindrome-de-down/",
                "http://www.movimentodown.org.br/desenvolvimento/fala-e-comunicacao/",
                "http://www.movimentodown.org.br/desenvolvimento/estimulacao-precoce/"};
        dicas.clear();
        for(i=0;i<titulo.length;i++){
            dica = new Dicas(titulo[i], id[i], link[i]);
            dicas.add(dica);
        }

    }
}