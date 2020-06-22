package com.example.simplepaging.ui.header_simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;
import com.example.simplepaging.ui.viewholder.HeaderViewHolder;
import com.example.simplepaging.ui.viewholder.StudentViewHolder;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderSimpleAdapter extends PagedListAdapter<Student, RecyclerView.ViewHolder> {
    private static final int  ITEM_TYPE_HEADER = 99;
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
    protected HeaderSimpleAdapter( ) {
        super( studentItemCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE_HEADER){
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false));
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
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE_HEADER;
        }else{
            return super.getItemViewType(position);
        }

    }

    // 这种多类型的Adapter存在很大的问题
    // 1.展示不全，因为第一个item展示了Header, 因此数据只展示了n-1条
    // 2.如果重写getItemCount()方法，指定item数量 +1，则界面刷新出现问题
    @Override
    public int getItemCount() {
        return super.getItemCount()+1;
    }
}
