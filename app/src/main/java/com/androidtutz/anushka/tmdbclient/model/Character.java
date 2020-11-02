package com.androidtutz.anushka.tmdbclient.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.util.DiffUtil;
import android.widget.ImageView;

import com.androidtutz.anushka.tmdbclient.R;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Character extends BaseObservable {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    private String posterPath;
    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imageURL){

        Glide.with(imageView.getContext())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static final DiffUtil.ItemCallback<Character> CALLBACK=new DiffUtil.ItemCallback<Character>() {
        @Override
        public boolean areItemsTheSame(Character oldItem, Character newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(Character oldItem, Character newItem) {
            return true;
        }
    };
}