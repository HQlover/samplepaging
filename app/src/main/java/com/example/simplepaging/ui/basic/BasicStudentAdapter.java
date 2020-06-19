package com.example.simplepaging.ui.basic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;
import com.example.simplepaging.ui.viewholder.StudentViewHolder;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class BasicStudentAdapter extends PagedListAdapter<Student, StudentViewHolder> {

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

    protected BasicStudentAdapter() {
        super(studentItemCallback);
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }
}
