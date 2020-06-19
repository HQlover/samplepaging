package com.example.simplepaging.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private TextView item;
    private Student student;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.name);
    }

    public void bindTo(Student student) {
        this.student = student;
        if (student != null) {
            item.setText(student.getName());
        }
    }


}
