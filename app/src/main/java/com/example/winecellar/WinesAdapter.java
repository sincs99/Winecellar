package com.example.winecellar;

import android.app.MediaRouteButton;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class WinesAdapter extends RecyclerView.Adapter<WinesAdapter.ViewHolder> implements Filterable {


    Context context;
    List<Wine> wineList;
    private List<Wine>wineListFull;
    RecyclerView rvWines;
    final View.OnClickListener onClickListener = new MyOnClickListener();



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowId;
        TextView rowName;
        TextView rowGrape;
        TextView rowPrice;
        TextView rowAmount;
        TextView rowAge;
        TextView rowType;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId = itemView.findViewById(R.id.item_id);
            rowName = itemView.findViewById(R.id.item_name);
            rowGrape = itemView.findViewById(R.id.item_grape);
            rowPrice = itemView.findViewById(R.id.item_price);
            rowAmount = itemView.findViewById(R.id.item_amount);
            rowAge = itemView.findViewById(R.id.item_age);
            rowType = itemView.findViewById(R.id.item_type);





        }
    }

    public WinesAdapter(Context context, List<Wine> wineList, RecyclerView rvWines){
        this.context = context;
        this.wineList = wineList;
        wineListFull = new ArrayList<>(wineList);
        this.rvWines = rvWines;
    }

    @NonNull
    @Override
    public WinesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_item, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WinesAdapter.ViewHolder viewHolder, int position) {
        Wine wine = wineList.get(position);



        viewHolder.rowId.setText(""+wine.getId());
        viewHolder.rowName.setText(wine.getName());
        viewHolder.rowGrape.setText(wine.getGrape());
        viewHolder.rowPrice.setText("Price: " + String.format("%.2f", wine.getPrice()));
        viewHolder.rowAmount.setText("Amount: " + wine.getAmount());
        viewHolder.rowAge.setText("Age: " + wine.getAge());
        viewHolder.rowType.setText(wine.getType());


    }



    @Override
    public int getItemCount() {
        return wineList.size();
    }

    @Override
    public Filter getFilter() {
        return wineFilter;
    }
    private Filter wineFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Wine> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(wineListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Wine item : wineListFull){
                    if (item.getType().toLowerCase().contains(filterPattern) || item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            wineList.clear();
            wineList.addAll((List)results.values);
            notifyDataSetChanged();


        }
    };

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvWines.getChildLayoutPosition(v);
            String item = wineList.get(itemPosition).getName();
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();
        }
    }
}
