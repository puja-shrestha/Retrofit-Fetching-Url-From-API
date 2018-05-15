package com.example.puza.retrofitfetchingurlfromapi.adapter;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.puza.retrofitfetchingurlfromapi.R;
import com.example.puza.retrofitfetchingurlfromapi.model.Item;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> itemList;

    private static int currentPosition = 0;

    public RecyclerViewAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, realname, team, firstappearance, createdby, publisher, bio;
        public ImageView imageurl;

        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.name);
            realname = (TextView)view.findViewById(R.id.realname);
            team = (TextView)view.findViewById(R.id.team);
            firstappearance = (TextView) view.findViewById(R.id.firstappearance);
            createdby = (TextView) view.findViewById(R.id.createdby);
            publisher = (TextView)view.findViewById(R.id.publisher);
            imageurl = (ImageView) view.findViewById(R.id.imageurl);
            bio = (TextView) view.findViewById(R.id.bio);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_content, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        Item item = itemList.get(position);

        holder.name.setText(item.getName());
        holder.realname.setText("real name " + item.getRealname());
        holder.team.setText(item.getTeam());
        holder.firstappearance.setText(item.getFirstappearance());
        holder.createdby.setText(item.getCreatedby());
        holder.publisher.setText(item.getPublisher());
        holder.bio.setText(item.getBio().trim());

        Glide.with(mContext).load(item.getImageurl()).into(holder.imageurl);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        //Log.v("size", itemList.size()+"");
        return itemList.size();
    }
}
