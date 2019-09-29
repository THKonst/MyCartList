package com.thvkonst.mycartlist;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{

    private List<Record> data = new ArrayList<>();
    private ItemsAdapterListener listener = null;
    public void setListener(ItemsAdapterListener listener){
        this.listener = listener;
    }

    public ItemsAdapter() {
        createData();



    }

    public void setData(List<Record> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {

        Record record = data.get(position);
        holder.applyData(record, position, listener, selections.get(position, false));


    }

    @Override
    public int getItemCount() {return data.size();}

    private SparseBooleanArray selections = new SparseBooleanArray();
    public void toggleSelection(int position){
        if (selections.get(position, false)){
            selections.delete(position);

        } else{
            selections.put(position,true);
        }
        notifyItemChanged(position);
    }
    void clearSelections(){
        selections.clear();
        notifyDataSetChanged();
    }
    int getSelectedItemCount(){
        return selections.size();
    }
    List <Integer> getSelectedItems(){
        List<Integer> items = new ArrayList<>(selections.size());
        for (int i=0; i<selections.size(); i++){
            items.add(selections.keyAt(i));
        }
        return items;
    }
    Record remove(int pos){
        final Record record = data.remove(pos);
        notifyItemRemoved(pos);
        return record;
    }

    private void createData() {
        data.add(new Record("Хлеб","22.00","1"));
        data.add(new Record("Молоко","35.50",  "1"));
        data.add(new Record("Сметана","60.0", "1"));
        setData(data);
    }

    public void addItem(Record record) {
        data.add(record);
        notifyItemInserted(data.size());

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
        }

        public void applyData(final Record record, final int position, final ItemsAdapterListener listener, boolean selected){
            title.setText(record.getTitle());
            price.setText(record.getPrice()+" \u20BD");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.OnItemClick(record, position);

                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null){
                        listener.OnItemLongClick(record, position);

                    }
                    return true;
                }
            });
            itemView.setActivated(selected);

        }
    }
}

