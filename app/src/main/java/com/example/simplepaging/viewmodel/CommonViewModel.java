package com.example.simplepaging.viewmodel;

import android.app.Application;

import com.example.simplepaging.db.Student;
import com.example.simplepaging.db.StudentDao;
import com.example.simplepaging.db.StudentDb;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class CommonViewModel extends AndroidViewModel {
    public static final int PAGE_SIZE = 15;
    public static final boolean ENABLE_PLACEHOLDERS = false;
    private StudentDao studentDao = StudentDb.getDatabase(this.getApplication()).studentDao();

    public CommonViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<Student>> getRefreshLiveData() {
        return new LivePagedListBuilder<Integer, Student>(studentDao.getAllStudent()
                , new PagedList.Config.Builder()
                .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()).build();
    }
}
