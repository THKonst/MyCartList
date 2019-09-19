package com.thvkonst.mycartlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsFragment extends Fragment {

    private static final int TYPE_UNKNOWN = -1;

    public static final int TYPE_NEED = 1;
    public static final int TYPE_DONE = 2;
    public static final int TYPE_BALANCE = 3;

    private static final String TYPE_KEY = "type";
    private int type;

    public static ItemsFragment createItemsFragment(int type){
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_NEED);
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_DONE);
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_BALANCE);

        fragment.setArguments(bundle);
        return fragment;
    }



    private RecyclerView recycler;
    private ItemsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        type = bundle.getInt(TYPE_KEY, TYPE_UNKNOWN);

        if (type==TYPE_UNKNOWN){
            throw new IllegalArgumentException("Unknown type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

//        loadItems();
    }

//    private void loadItems(){
//        List<Record> items = new ArrayList<>();
//        items.add(new Record("Хлеб",22.0));
//        items.add(new Record("Молоко",35.50));
//        items.add(new Record("Сметана",60.0));
//
//        adapter.setData(items);

//    }
}




