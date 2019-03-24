package com.example.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {
    List<PortalObject> portalObjects;

    public PortalAdapter(List<PortalObject> portalObjects){
        this.portalObjects = portalObjects;
    }
    @NonNull
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewholder_layout
                , viewGroup,false);
        return new PortalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder viewHolder, int i) {
        PortalObject portalObject = portalObjects.get(i);
        viewHolder.textViewPortal.setText(portalObject.getTitle());





    }

    @Override
    public int getItemCount() {
        return portalObjects.size();
    }
    public void swapList (List<PortalObject> newList) {
        portalObjects = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewPortal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPortal = itemView.findViewById(R.id.text_view_portal);

        }
    }
}
