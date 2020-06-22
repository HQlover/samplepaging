package com.example.simplepaging.ui.header_proxy;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDataObserverProxy extends RecyclerView.AdapterDataObserver {
    private RecyclerView.AdapterDataObserver observer;
    private int headerCount;

    public AdapterDataObserverProxy(RecyclerView.AdapterDataObserver observer, int headerCount) {
        this.observer = observer;
        this.headerCount = headerCount;
    }

    @Override
    public void onChanged() {
        observer.onChanged();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        observer.onItemRangeChanged(positionStart + headerCount, itemCount);
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
        observer.onItemRangeChanged(positionStart + headerCount, itemCount, payload);
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        observer.onItemRangeInserted(positionStart + headerCount, itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        observer.onItemRangeRemoved(positionStart + headerCount, itemCount);
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        observer.onItemRangeMoved(fromPosition + headerCount, toPosition + headerCount, itemCount);
    }
}
