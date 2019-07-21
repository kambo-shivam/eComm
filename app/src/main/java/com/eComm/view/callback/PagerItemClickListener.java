package com.eComm.view.callback;

import android.view.View;

public interface PagerItemClickListener {

    void onPagerItemClick(int pos, int itemId);

    void onPagerItemDoubleClick(int pos, View view);
}
