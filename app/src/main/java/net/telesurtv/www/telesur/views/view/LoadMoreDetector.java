package net.telesurtv.www.telesur.views.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jhordan on 27/08/15.
 */
public class LoadMoreDetector extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager layoutManager;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLoading() {
        return loading;
    }

    private boolean loading;
    private Listener listener;

    public LoadMoreDetector(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.enabled = true;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();



        if (enabled && !loading) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                if (listener != null) {
                    listener.onLoadMore();
                    loading = true;
                }
            }
        }
    }

    public interface Listener {
        void onLoadMore();
    }
}