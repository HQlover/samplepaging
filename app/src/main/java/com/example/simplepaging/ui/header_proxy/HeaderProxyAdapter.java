package com.example.simplepaging.ui.header_proxy;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;
import com.example.simplepaging.ui.viewholder.FooterViewHolder;
import com.example.simplepaging.ui.viewholder.HeaderViewHolder;
import com.example.simplepaging.ui.viewholder.StudentViewHolder;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderProxyAdapter extends PagedListAdapter<Student, RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_HEADER = 99;
    private static final int ITEM_TYPE_FOOTER = 100;
    private static DiffUtil.ItemCallback<Student> studentItemCallback = new DiffUtil.ItemCallback<Student>() {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };
    public HeaderProxyAdapter( ) {
        super(studentItemCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE_HEADER){
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false));
        }else if(viewType==ITEM_TYPE_FOOTER){
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer,parent,false));
        }else{
            return new StudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  HeaderViewHolder){
            ((HeaderViewHolder) holder).bindsHeader();
        }else if(holder instanceof  StudentViewHolder) {
            ((StudentViewHolder) holder).bindTo(getItem(position-1));
        }else if(holder instanceof FooterViewHolder ){
            ((FooterViewHolder) holder).bindsFooter();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE_HEADER;
        }else if(position==getItemCount()-1){
            return  ITEM_TYPE_FOOTER;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+2;
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(new AdapterDataObserverProxy(observer,1));
    }
}
