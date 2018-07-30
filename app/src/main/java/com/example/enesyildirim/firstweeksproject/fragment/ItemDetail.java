package com.example.enesyildirim.firstweeksproject.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enesyildirim.firstweeksproject.R;
import com.example.enesyildirim.firstweeksproject.service.CircleTransform;
import com.example.enesyildirim.firstweeksproject.model.Items;
import com.squareup.picasso.Picasso;

public class ItemDetail extends Fragment {
    private TextView fullnameTV, languageTV, idTV, watchersTV;
    private ImageView imageView;
    private Items item;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//TODO gelen değere göre dolum yapılıyor değil mi?!?!?
        // item = (Items) getIntent().getExtras().getSerializable("item");
        view = inflater.inflate(R.layout.activity_item_detail, container, false);
        fillBlankLines();
        return view;
    }

    private void fillBlankLines() {
        fullnameTV = view.findViewById(R.id.fullname_tv);
        languageTV = view.findViewById(R.id.language_tv);
        idTV = view.findViewById(R.id.id_tv);
        watchersTV = view.findViewById(R.id.watchers_tv);
        imageView = view.findViewById(R.id.imageview);

        item = (Items) getArguments().getSerializable("item");
        fullnameTV.setText(item.getFull_name());
        languageTV.setText(item.getLanguage());
        idTV.setText(String.valueOf(item.getId()));
        watchersTV.setText(String.valueOf(item.getWatchers()));
        Picasso.get().load(item.getOwner().getAvatar_url()).transform(new CircleTransform()).into(imageView);

    }


}
