package com.thvkonst.mycartlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemsFragment extends Fragment {
    private static final String TAG = "ItemsFragment";

    private static final int TYPE_UNKNOWN = -1;

    public static final int TYPE_NEED = 1;
    public static final int TYPE_DONE = 2;
    public static final int TYPE_BALANCE = 3;

    public static final String TYPE_KEY = "type";
    public static final String TYPE_KEY_1 = "type_1";
    public static final String TYPE_KEY_2= "type_2";
    private int type;

    public static final int ADD_ITEM_REQUEST_CODE = 123;

    public static ItemsFragment createItemsFragment(int type){
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_NEED);
        bundle.putInt(ItemsFragment.TYPE_KEY_1, ItemsFragment.TYPE_DONE);
        bundle.putInt(ItemsFragment.TYPE_KEY_2, ItemsFragment.TYPE_BALANCE);

        fragment.setArguments(bundle);
        return fragment;
    }



    private RecyclerView recycler;
    private FloatingActionButton fab;
    private ItemsAdapter adapter;
    private SwipeRefreshLayout refresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();
        adapter.setListener(new AdapterListener());

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

        refresh=view.findViewById(R.id.refresh);
        refresh.setColorSchemeColors(Color.BLUE, Color.CYAN, Color.GREEN);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);

            }
        });
        


//        loadItems();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            Record item= (Record) data.getSerializableExtra("item");
            adapter.addItem(item);



        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    /*        ACTION MODE      */
    private ActionMode actionMode1 = null;
    private void removeSelectedItems(){
        for (int i=adapter.getSelectedItems().size()-1; i>=0; i--){
            adapter.remove(adapter.getSelectedItems().get(i));
        }
        actionMode1.finish();
    }


    private class AdapterListener implements ItemsAdapterListener{

        @Override
        public void OnItemClick(Record record, int position) {
            if(isInActionMode()){
                toggleSelection(position);
            }

        }

        @Override
        public void OnItemLongClick(Record record, int position) {
            if (isInActionMode()){
                return;
            }
            actionMode1 = ((AppCompatActivity)getActivity()).startSupportActionMode(actionModeCallback);
            toggleSelection(position);
        }

        private boolean isInActionMode(){
            return actionMode1!= null;
        }

        private void toggleSelection(int position){
            adapter.toggleSelection(position);
        }
    }
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = new MenuInflater(getContext());
            inflater.inflate(R.menu.items_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case (R.id.remove):
                    showDialog();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            adapter.clearSelections();
            actionMode1 = null;
        }
    };
    private void showDialog(){
        ConfirmationDialog dialog = new ConfirmationDialog();
        dialog.show(getFragmentManager(),"ConfirmationDialog");

    }
}




