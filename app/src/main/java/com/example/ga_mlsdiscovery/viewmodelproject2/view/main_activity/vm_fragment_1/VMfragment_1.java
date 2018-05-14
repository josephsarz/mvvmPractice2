package com.example.ga_mlsdiscovery.viewmodelproject2.view.main_activity.vm_fragment_1;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ga_mlsdiscovery.viewmodelproject2.R;
import com.example.ga_mlsdiscovery.viewmodelproject2.model.Foto;
import com.example.ga_mlsdiscovery.viewmodelproject2.view_model.ListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class VMfragment_1 extends Fragment {
    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_error)
    TextView textView_error;
    @BindView(R.id.progressbar)
    View progressBar;

    private ListViewModel viewModel;

    public VMfragment_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vmfragment_1, container, false);

        /*
           BINDING RESET
           Fragments have a different view lifecycle than activities. When binding           a fragment in onCreateView, set the views to null in onDestroyView.                Butter Knife returns an Unbinder instance when you call bind to do this            for you. Call its unbind method in the appropriate lifecycle callback.
         */

        //ButterKnife in a fragment needs unbinder
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //get ViewModel
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        observeViewModel();

        /*
        After building pojos, api service, retrofit, view model, adapter class,
        viewholder, finally build recycler view in fragment. This is Last
        Remember to add user permissions internet for Retrofit call
        */
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(new fotosRecyclerAdapter(viewModel, this));
        //add Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private void observeViewModel() {

        //ViewModel's MutableLiveData<List<Foto>> getFotos()
        viewModel.getFotos().observe(this, (List<Foto> fotos) -> {
            if(fotos != null){
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setHasFixedSize(true);
            } else {
                Timber.e("fotos is Null!!!");
            }
        });

        //ViewModel's MutableLiveData<Boolean> getFotoLoadError()
        viewModel.getFotoLoadError().observe(this, (Boolean isError) ->{
            if(isError){
                textView_error.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                textView_error.setText("Error while loading fotos");
            } else {
                textView_error.setVisibility(View.GONE);
                textView_error.setText(null);
            }
        });

        //ViewModel's public MutableLiveData<Boolean> getLoading()
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
