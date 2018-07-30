package com.example.enesyildirim.firstweeksproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.enesyildirim.firstweeksproject.adapter.CustomAdapter;
import com.example.enesyildirim.firstweeksproject.fragment.MainFragment;

public class FirstWeekActivity extends AppCompatActivity /*implements RecAdapter.OnItemClickListener, AdapterView.OnItemClickListener*/ {

    //RequestInterface requestInterface;
    //private RecAdapter adapter;
    //CustomAdapter tempAdapter;
    FragmentTransaction transaction;
    private CustomDialog.OnItemClickListener onItemClickListener;
    boolean oneRowDesign = true;

    public boolean isOneRowDesign() {
        return oneRowDesign;
    }

    public void setOneRowDesign(boolean oneRowDesign) {
        this.oneRowDesign = oneRowDesign;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_week);

        // Burada biz en başta açılacak olan MainFragment'ı framelayout'a atıyoruz.
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
        transaction.add(R.id.frame_layout, new MainFragment(), "MainFragment");
        transaction.addToBackStack("MainFragment");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();// Geri dönebilebilmek için pop ediyoruz.
        } else {// Burada çıkmak için emin misin ekranını yazdığım customDialog üzerinden soruyorum.
            CustomDialog cd = new CustomDialog(this, R.string.tittle, R.string.desc, new CustomDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int buttonId, CustomDialog customDialog) {//TODO Burada neden else yazmadık!! yani neden sadec yes var!!
                    if (buttonId == R.id.yes_button) {
                        FirstWeekActivity.this.finish();
                    }
                }
            });
            cd.show();
        }
    }

    public void gereksiz() {
        //listView.setOnItemClickListener(this); Bu kısım onCreate içindeydi!!!!
    /*  requestInterface = API.getClient().create(RequestInterface.class);
        Call<ResponseModel> call = requestInterface.getJSON();

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    //fillGithubItems(response);

                    tempAdapter = new CustomAdapter(FirstWeekActivity.this, response.body().getItems());
                    listView.setAdapter(tempAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });*/

        /*private void fillGithubItems(Response<ResponseModel> response) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecAdapter(response.body().getItems());
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }*/

    /*@Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, ItemDetail.class);
        Items item = adapter.getItem(position);
        i.putExtra("item", item);
        startActivity(i);
    }*/

    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent i = new Intent(this, ItemDetail.class);
        i.putExtra("item", item);
        startActivity(i);

        ItemDetail itemDetail = new ItemDetail();
        Items item = tempAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        itemDetail.setArguments(bundle);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, itemDetail, "ItemDetailFragment");
        transaction.addToBackStack("ItemDetailFragment");
        transaction.commit();
    }*/
    }
}