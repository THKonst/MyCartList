package com.thvkonst.mycartlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{

    private List<Record> data = new ArrayList<>();

    public ItemsAdapter() {
        createData();
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {

        Record record = data.get(position);
        holder.applyData(record);


    }

    @Override
    public int getItemCount() {return data.size();}

    private void createData() {
        data.add(new Record("Хлеб",22.0));
        data.add(new Record("Молоко",35.50));
        data.add(new Record("Сметана",60.0));
        data.add(new Record("Каша овсяная",34.0));
        data.add(new Record("Каша гречневая",555.0));
        data.add(new Record("Макароны Макфа",6.0));
        data.add(new Record("Супер сковорода которая никогда не пригорает",1000.0));
        data.add(new Record("Йогурт",120.0));
        data.add(new Record("Пиво",34.78));
        data.add(new Record("Чипсы",108.90));
        data.add(new Record("Чипсы2",108.90));
        data.add(new Record("Чипсы3",108.90));
        data.add(new Record("Чипсы4",108.90));
        data.add(new Record("Чипсы555",108.90));
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
        }

        public void applyData(Record record){
            title.setText(record.getTitle());
            price.setText(String.valueOf(record.getPrice()));
            //Spannable spannabletext = new SpannableString(String.valueOf(record.getPrice())+"@string/symbol_rbl");

            // spannabletext.setSpan(new  );

        }
    }
}

