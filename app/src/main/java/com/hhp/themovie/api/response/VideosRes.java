package com.hhp.themovie.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VideosRes implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<Result> listResults;
    public static class Result {
        @SerializedName("iso_639_1")
        public String languageCode;
        @SerializedName("iso_3166_1")
        public String countryCode;
        @SerializedName("key")
        public String key;
        @SerializedName("name")
        public String name;
        @SerializedName("site")
        public String site;
        @SerializedName("size")
        public int size;
        @SerializedName("type")
        public String type;
        @SerializedName("official")
        public boolean official;
        @SerializedName("published_at")
        public String publishedAt;
        @SerializedName("id")
        public String id;

        @Override
        public String toString() {
            return "Result{" +
                    "languageCode='" + languageCode + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", key='" + key + '\'' +
                    ", name='" + name + '\'' +
                    ", site='" + site + '\'' +
                    ", size=" + size +
                    ", type='" + type + '\'' +
                    ", official=" + official +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VideosRes{" +
                "id=" + id +
                ", listResults=" + listResults +
                '}';
    }
}
