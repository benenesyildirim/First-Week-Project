package com.example.enesyildirim.firstweeksproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enesyildirim.firstweeksproject.service.CircleTransform;
import com.example.enesyildirim.firstweeksproject.R;
import com.example.enesyildirim.firstweeksproject.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Items> list;
    private OnItemClickListener onItemClickListener;
    private ItemHolder itemHolder;
    private HeaderHolder headerHolder;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    RecAdapter(List<Items> list,OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {//Bunun customAdapterdaki ile ne farkı var ve bunu kullanmasak dışa yazsak olmaz mı?
        if (position == 0) {
            LayoutInflater systemService = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = systemService.inflate(R.layout.cardview_design, null);
            return new HeaderHolder(view);
        } else {
            LayoutInflater systemService = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = systemService.inflate(R.layout.line_design, null);
            return new ItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (position == 0) {
            headerHolder = (HeaderHolder) viewHolder;
        } else {
            itemHolder = (ItemHolder) viewHolder;

            Picasso.get().load(list.get(position).getOwner().getAvatar_url()).transform(new CircleTransform()).into(itemHolder.imageView);
            itemHolder.fullname_tv.setText(list.get(position).getFull_name());
            itemHolder.language_tv.setText(list.get(position).getLanguage());
            itemHolder.type_tv.setText(list.get(position).getOwner().getType());
            itemHolder.watchers_tv.setText(String.valueOf(list.get(position).getWatchers()));

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView fullname_tv, language_tv, type_tv, watchers_tv;
        ImageView imageView;

        public ItemHolder(View v) {
            super(v);
            fullname_tv = v.findViewById(R.id.fullname_tv);
            language_tv = v.findViewById(R.id.language);
            type_tv = v.findViewById(R.id.type_tv);
            watchers_tv = v.findViewById(R.id.watchers_tv);
            imageView = v.findViewById(R.id.imageView);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        TextView title;
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);

        }

    }

    public Items getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
