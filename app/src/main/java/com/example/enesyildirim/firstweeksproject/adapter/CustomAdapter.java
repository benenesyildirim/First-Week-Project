package com.example.enesyildirim.firstweeksproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enesyildirim.firstweeksproject.service.CircleTransform;
import com.example.enesyildirim.firstweeksproject.R;
import com.example.enesyildirim.firstweeksproject.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Items> items;
    private boolean oneRowDesign;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClicker(Items item);
    }

    public CustomAdapter(Context activity, List<Items> items, boolean oneRowDesign, ItemClickListener itemClickListener) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.oneRowDesign = oneRowDesign;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        if (oneRowDesign)
            return items.size();
        else
            return items.size() / 2;
    }

    @Override
    public Items getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {                              //TODO Tür long olmasına rağmen neden int dönüyor ve hata almıyoruz?!?!?!?
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        GithubRowItem githubRowItem;

        if (oneRowDesign) {//Eğer true ise bu
            v = inflater.inflate(R.layout.line_design, null);
            final Items item = getItem(i);
            githubRowItem = new GithubRowItem(((TextView) v.findViewById(R.id.fullname_tv)), ((TextView) v.findViewById(R.id.language)), ((ImageView) v.findViewById(R.id.imageView)), ((LinearLayout) v.findViewById(R.id.lineDesignLL)), ((TextView) v.findViewById(R.id.watchers_tv)), ((TextView) v.findViewById(R.id.type_tv)));
            fillGithubItem(githubRowItem,item);
        } else {//Eğer false ise bu
            v = inflater.inflate(R.layout.cardview_design, null);
            int val = 2 * i;
            final Items item = getItem(val);
            githubRowItem = new GithubRowItem(((TextView) v.findViewById(R.id.firstTittleTV)), ((TextView) v.findViewById(R.id.firstDescTV)), ((ImageView) v.findViewById(R.id.firstAvatarImage)), ((LinearLayout) v.findViewById(R.id.firstRowLL)), null, null);
            fillGithubItem(githubRowItem, item);

            val = val + 1;
            final Items item1 = getItem(val);
            if (item1 != null) {
                githubRowItem = new GithubRowItem(((TextView) v.findViewById(R.id.secTittleTV)), ((TextView) v.findViewById(R.id.secDescTV)), ((ImageView) v.findViewById(R.id.secAvatarImage)), ((LinearLayout) v.findViewById(R.id.secondRowLL)), null, null);
                fillGithubItem(githubRowItem, item1);
            }
        }

        return v;
    }

    private void fillGithubItem(GithubRowItem githubRowItem, final Items item) {
        if (oneRowDesign) {
            githubRowItem.type.setText(item.getOwner().getType());
            githubRowItem.watchers.setText(String.valueOf(item.getWatchers()));
        }

        githubRowItem.rowLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClicker(item);
                }
            }
        });

        Picasso.get().load(item.getOwner().getAvatar_url()).transform(new CircleTransform()).into(githubRowItem.imageView);
        githubRowItem.fullName.setText(item.getName());
        githubRowItem.language.setText(item.getLanguage());
    }

    class GithubRowItem {
        TextView fullName, language, type, watchers;
        ImageView imageView;
        LinearLayout rowLL;

        GithubRowItem(TextView fullNameTv, TextView languageTv, ImageView avatarIV, LinearLayout rowLL, TextView watchersTV, TextView typeTV) {
            this.fullName = fullNameTv;
            this.language = languageTv;
            this.watchers = watchersTV;
            this.type = typeTV;
            this.imageView = avatarIV;
            this.rowLL = rowLL;
        }
    }

}