package io.github.sebmerkt.everydayconverterwear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenuItems extends RecyclerView.Adapter<MainMenuItems.RecyclerViewHolder> {

    private ArrayList<MainMenuItem> dataSource = new ArrayList<MainMenuItem>();
    public interface AdapterCallback{
        void onItemClicked(Integer menuPosition);
    }
    private AdapterCallback callback;

    public MainMenuItems(Context context, ArrayList<MainMenuItem> dataArgs, AdapterCallback callback){
        Context context1 = context;
        this.dataSource = dataArgs;
        this.callback = callback;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_item,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout menuContainer;
        TextView menuItem;
        ImageView menuIcon;

        public RecyclerViewHolder(View view) {
            super(view);
            menuContainer = view.findViewById(R.id.menu_container);
            menuItem = view.findViewById(R.id.menu_item);
            menuIcon = view.findViewById(R.id.menu_icon);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        MainMenuItem data_provider = dataSource.get(position);

        holder.menuItem.setText(data_provider.getText());
        holder.menuIcon.setImageResource(data_provider.getImage());
        holder.menuContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(callback != null) {
                    callback.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}

class MainMenuItem {
    private String text;
    private int image;

    public MainMenuItem(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}