package com.example.ga_mlsdiscovery.viewmodelproject2.view.main_activity.vm_fragment_1;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ga_mlsdiscovery.viewmodelproject2.R;
import com.example.ga_mlsdiscovery.viewmodelproject2.model.Foto;
import com.example.ga_mlsdiscovery.viewmodelproject2.view_model.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class fotosRecyclerAdapter extends RecyclerView.Adapter<fotosRecyclerAdapter.FotoViewHolder>{

    private final List<Foto> list = new ArrayList<>();

    public fotosRecyclerAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getFotos().observe(lifecycleOwner, new Observer<List<Foto>>() {
            @Override
            public void onChanged(@Nullable List<Foto> fotos) {
                list.clear();
                if(fotos != null){
                    list.addAll(fotos);
                    notifyDataSetChanged();
                }
            }
        });

        /*
        Indicates whether each item in the data set can be represented with a unique identifier
         */
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foto_item,
                parent, false);
        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if(list == null){return 0;}
        else {return list.size();}
    }

    @Override
    public long getItemId(int position) {
        if(list != null){
            return list.get(position).getId();
        } else {
            return 0;
        }
    }

    class FotoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.thumbnail)ImageView thumbnail;
        @BindView(R.id.title_text)TextView titleText;
        @BindView(R.id.url_text)TextView urlText;

        //The pojo that we will bind with this layout class
        private Foto foto;

        FotoViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
            v.setOnClickListener(view ->{
                if(foto != null){
                    Timber.d("FotoViewHolder item Clicked!!!");
                }
            });
        }

        void bind(Foto foto){
            this.foto = foto;
            titleText.setText(foto.getTitle());
            urlText.setText(foto.getUrl());
        }
    }
}
