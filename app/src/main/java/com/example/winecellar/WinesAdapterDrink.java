package com.example.winecellar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WinesAdapterDrink extends RecyclerView.Adapter<WinesAdapterDrink.ViewHolder> {

    Context context;
    List<Wine> wineList;
    RecyclerView rvWines;
    DrinkActivity drinkActivity;
    final View.OnClickListener onClickListener = new MyOnClickListener();
    private List<Wine>wineListFull;


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

    public WinesAdapterDrink(Context context, List<Wine> wineList, RecyclerView rvWines){
        this.context = context;
        this.wineList = wineList;
        wineListFull = new ArrayList<>(wineList);
        this.rvWines = rvWines;
    }

    @NonNull
    @Override
    public WinesAdapterDrink.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_item, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WinesAdapterDrink.ViewHolder viewHolder, int position) {
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



    public Filter getDrinkFilter() {
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
        DatabaseHelper myDb;
        @Override
        public void onClick(View v) {

            int itemPosition = rvWines.getChildLayoutPosition(v);
            int id = wineList.get(itemPosition).getId();
            int itemPosition2 = rvWines.getChildLayoutPosition(v);
            int amount = wineList.get(itemPosition2).getAmount();

            myDb = new DatabaseHelper(context);
            boolean response = myDb.drinkWine(id, amount);




            if (response == true){
                Toast.makeText(context, "Enjoy!", Toast.LENGTH_LONG).show();



            } else{
                Toast.makeText(context, "Was your last bottle!", Toast.LENGTH_LONG).show();

            }





        }
    }
}
