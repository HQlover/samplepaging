package com.example.simplepaging.ui.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;
import com.example.simplepaging.viewmodel.CommonViewModel;

public class BasicUsageActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private CommonViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_usage);

        recyclerView=findViewById(R.id.recyclerView);

        final BasicStudentAdapter adapter=new BasicStudentAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(this).get(CommonViewModel.class);
        viewModel.getRefreshLiveData().observe(this,new Observer<PagedList<Student>>(){
            @Override
            public void onChanged(PagedList<Student> students) {
                adapter.submitList(students);
            }
        });
    }
}