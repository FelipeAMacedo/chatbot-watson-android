package com.example.vmac.WatBot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vmac.WatBot.dao.STTDAO;
import com.example.vmac.WatBot.model.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 25/08/17.
 */

public class FragmentSTT extends Fragment {
    private RecyclerView rvStt;
    private ListView lvStt;
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_configuration_stt, container, false);

        initComponents();
        initListeners();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        populateListView();
    }

    private void initComponents() {
//        rvStt = root.findViewById(R.id.rvStt);
        lvStt = root.findViewById(R.id.lvStt);
    }

    private void initListeners() {

    }

    private void populateListView() {
        List<Service> lista = new ArrayList<>();
        STTDAO sttDao = new STTDAO(this.getContext());

        lista.addAll(sttDao.findAll());


        ArrayAdapter<Service> adapter = new ArrayAdapter<Service>(this.getContext(), android.R.layout.simple_list_item_1, lista);
        lvStt.setAdapter(adapter);


    }
}
