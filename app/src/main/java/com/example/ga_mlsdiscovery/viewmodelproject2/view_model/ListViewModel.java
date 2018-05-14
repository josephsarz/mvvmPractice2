package com.example.ga_mlsdiscovery.viewmodelproject2.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.ga_mlsdiscovery.viewmodelproject2.model.Foto;
import com.example.ga_mlsdiscovery.viewmodelproject2.network.retrofit_service.RetrofitApiService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ListViewModel extends ViewModel {
    //needed for Recycler view
    private final MutableLiveData<List<Foto>> fotos = new MutableLiveData<List<Foto>>();

    //For retrofit to load error text if error occurs.
    private final MutableLiveData<Boolean> fotoLoadError = new MutableLiveData<>();

    //For retrofit while loading and we want to show the progress bar
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    //our local list of fotos
    private Call<List<Foto>> fotoCall;

    //Make api call from the constructor
    public ListViewModel(){
        fetchFotos();
    }

    public MutableLiveData<List<Foto>> getFotos() {
        return fotos;
    }

    public MutableLiveData<Boolean> getFotoLoadError() {
        return fotoLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    //Retrofit should be created and implemented to use this method
    private void fetchFotos() {
        loading.setValue(true);
        /* use RetrofitApi service instance that creates a retrofit call and
            applies retrofit endpoint getRepositories.
            The result is a <List<foto>>
         */
        fotoCall = RetrofitApiService.getInstance().getFotos();
        fotoCall.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if(response.isSuccessful()){
                    fotoLoadError.setValue(false);
                    fotos.setValue(response.body());
                    loading.setValue(false);
                    fotoCall = null;

                    for(Foto photo:response.body()){
                        Timber.d(String.valueOf(photo));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {
                Timber.e(t, getClass().getSimpleName(), "Error loading repos");
                fotoLoadError.setValue(true);
                loading.setValue(false);
                fotoCall = null;
                t.getMessage();
            }
        });
    }

    //our cleanup method

    @Override
    protected void onCleared() {
        if(fotoCall != null){
            fotoCall.cancel();
            fotoCall = null;
        }
    }
}
