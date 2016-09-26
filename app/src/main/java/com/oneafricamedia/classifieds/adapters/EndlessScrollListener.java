package com.oneafricamedia.classifieds.adapters;

import android.widget.AbsListView;

/**
 * Created by brad on 2016/09/26.
 */

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    // The minimum number of items to have below your current scroll position
    // before loading more
    private int visibleThreshold = 5;

    //The current offset index of data you have loaded
    private int currentPage = 0;

    //Tthe total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;

    //True if we are still waiting for the last set of data to load
    private boolean loading = true;

    //Sets the starting page index
    private int startingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startingPageIndex) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startingPageIndex;
        this.currentPage = startingPageIndex;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //Don't take any action on changed
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // If the total item count is zero and the previous ins't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex;
            previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;

        }

        //If it isn't currently loading we check to see if we have breached
        //the visibleThreshold and need to reload more data
        //If we do need to reload some more data, we execute onLoadMore to fetch the data
        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
            loading = onLoadMore(currentPage + 1, totalItemCount);
        }
    }

    //Deine the process for actually loading more data based on page
    //Returns true if more data is being loaded; returns false if there is no more.
    public abstract boolean onLoadMore(int page, int totalItemsCount);

}
