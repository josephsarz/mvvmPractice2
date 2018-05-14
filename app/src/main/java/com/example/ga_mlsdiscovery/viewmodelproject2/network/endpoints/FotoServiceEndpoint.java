package com.example.ga_mlsdiscovery.viewmodelproject2.network.endpoints;

import com.example.ga_mlsdiscovery.viewmodelproject2.model.Foto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FotoServiceEndpoint {

    @GET("photos")
    Call<List<Foto>> getFotos();
}
