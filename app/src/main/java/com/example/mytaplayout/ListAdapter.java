package com.example.mytaplayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private final ArrayList<Model> arrayList ;

    public ListAdapter(ArrayList<Model> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_user, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Model model = arrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(model.getAvaterUrl())
                .circleCrop()
                .into(holder.imgAvatar);
        holder.tvName.setText(model.getName());
        holder.tvUsername.setText(model.getType());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvUsername;
        ImageView imgAvatar;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvUsername = itemView.findViewById(R.id.tv_username);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
