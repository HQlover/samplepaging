package com.example.simplepaging.ui.header_simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;

import com.example.simplepaging.R;
import com.example.simplepaging.db.Student;
import com.example.simplepaging.ui.basic.BasicStudentAdapter;
import com.example.simplepaging.viewmodel.CommonViewModel;

import java.util.concurrent.TimeUnit;

public class HeaderSimpleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommonViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HeaderSimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_simple);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout);

        adapter = new HeaderSimpleAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        binds();

        viewModel = ViewModelProviders.of(this).get(CommonViewModel.class);
        viewModel.getRefreshLiveData().observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(PagedList<Student> students) {
                adapter.submitList(students);
            }
        });
    }

    private void binds() {
        //模拟下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                Observable.just(0)
                        .delay(2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                swipeRefreshLayout.setRefreshing(false);
                                adapter.submitList(null);
                                viewModel.getRefreshLiveData().observe(HeaderSimpleActivity.this, new Observer<PagedList<Student>>() {
                                    @Override
                                    public void onChanged(PagedList<Student> students) {
                                        adapter.submitList(students);
                                    }
                                });
                            }
                        }).subscribe();
            }
        });
    }
}




