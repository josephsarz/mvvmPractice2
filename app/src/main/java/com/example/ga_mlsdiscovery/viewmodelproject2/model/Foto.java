package com.example.ga_mlsdiscovery.viewmodelproject2.model;

import com.squareup.moshi.Json;

public class Foto {
    @Json(name = "albumId")
    private Integer albumId;
    @Json(name = "id")
    private Integer id;
    @Json(name = "title")
    private String title;
    @Json(name = "url")
    private String url;
    @Json(name = "thumbnailUrl")
    private String thumbnailUrl;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
