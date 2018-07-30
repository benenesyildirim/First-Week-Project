package com.example.enesyildirim.firstweeksproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enesyildirim.firstweeksproject.adapter.RecAdapter;
import com.example.enesyildirim.firstweeksproject.service.API;
import com.example.enesyildirim.firstweeksproject.adapter.CustomAdapter;
import com.example.enesyildirim.firstweeksproject.R;
import com.example.enesyildirim.firstweeksproject.service.RequestInterface;
import com.example.enesyildirim.firstweeksproject.model.ResponseModel;
import com.example.enesyildirim.firstweeksproject.model.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements CustomAdapter.ItemClickListener {

    RequestInterface requestInterface;
    ListView listView;
    CustomAdapter listAdapter;
    FragmentTransaction transaction;
    boolean oneRowDesign = true;
    LinearLayout firsRowLL, secRowLL;
    View view;
    private List<Items> itemsList;
    Button changeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.mainfragment, container, false);               //View'e inflate ediyoruz ki görünütümüz de fragment gözüksün!!
        listView = view.findViewById(R.id.lv);

        firsRowLL = view.findViewById(R.id.firstRowLL);
        secRowLL = view.findViewById(R.id.secondRowLL);

        requestInterface = API.getClient().create(RequestInterface.class);
        Call<ResponseModel> call = requestInterface.getJSON();

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    //fillGithubItems(response);
                    itemsList = response.body().getItems();                                       //body bizim değerleri tuttuğumuz yer!?!
                    listAdapter = new CustomAdapter(MainFragment.this.getContext(), itemsList, true, MainFragment.this);//true dönmesinin sebebi layout seçimimiz.
                    listView.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Check Internet Connection!!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {//Burası benim actionBar'daki butonum için çağrım yapıyor!!
        inflater.inflate(R.menu.mainmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//ActionBar'daki butonun listenerı

        switch (item.getItemId()) {
            case R.id.changeBtn:
                if (oneRowDesign) {
                    oneRowDesign = false;
                    listAdapter = new CustomAdapter(MainFragment.this.getContext(), itemsList, oneRowDesign, MainFragment.this);
                    listView.setAdapter(listAdapter);
                } else {
                    oneRowDesign = true;
                    listAdapter = new CustomAdapter(MainFragment.this.getContext(), itemsList, oneRowDesign, MainFragment.this);
                    listView.setAdapter(listAdapter);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicker(Items item) {
        ItemDetail itemDetail = new ItemDetail();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        itemDetail.setArguments(bundle);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, android.R.anim.slide_in_left);
        transaction.add(R.id.frame_layout, itemDetail, "ItemDetailFragment");
        transaction.addToBackStack("ItemDetailFragment");
        transaction.commit();
    }
}
