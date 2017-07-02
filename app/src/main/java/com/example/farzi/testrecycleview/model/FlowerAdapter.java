package com.example.farzi.testrecycleview.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farzi.testrecycleview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farzi on 26/06/2017.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<FlowerResponse> mFlowerList;
    private FlowerClickListener mListener;
    private List<FlowerResponse> mFilterFlowerList;

    public FlowerAdapter(FlowerClickListener listener,LayoutInflater inflater) {
        mInflater = inflater;
        mListener = listener;
        mFlowerList = new ArrayList<>();
        mFilterFlowerList = mFlowerList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_flower,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FlowerResponse currFlower = mFlowerList.get(position);

        holder.mName.setText(mFilterFlowerList.get(position).getName());
        //holder.mPrice.setText("$"+currFlower.getPrice());
        Glide.with(holder.itemView.getContext()).load("http://services.hanselandpetal.com/photos/"+mFilterFlowerList.get(position).getPhoto()).into(holder.mPhoto);

      /*  holder.mName.setText(currFlower.getName());
        holder.mPrice.setText("$"+currFlower.getPrice());
        Glide.with(holder.itemView.getContext()).load("http://services.hanselandpetal.com/photos/"+currFlower.getPhoto()).into(holder.mPhoto);
    */
    }

    @Override
    public int getItemCount() {
        return mFilterFlowerList.size();
    }

    public void addFlower( List<FlowerResponse> flowerResponses) {
        mFlowerList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

       public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPhoto;
        private TextView mName, mPrice;
        public Holder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.flowerPhoto);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // mListener.onClick(getLayoutPosition(),mFlowerList.get(getAdapterPosition()).getName());
            mListener.onClick(getLayoutPosition(),mFilterFlowerList.get(getAdapterPosition()).getName());
        }
    }

    public interface FlowerClickListener {
        void onClick(int position, String name);
    }

    public void filter(String query) {
        mFilterFlowerList = new ArrayList<>();
        for (FlowerResponse flowers : mFlowerList) {
            if(flowers.getName().toLowerCase().contains(query.toLowerCase())){
                mFilterFlowerList.add(flowers);
            }
        }
        notifyDataSetChanged();
    }
}
