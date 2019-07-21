package com.eComm.utility.adapter;/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The of the ViewDataBinding
 */
public abstract class DataBoundListAdapter<T, V extends ViewDataBinding>
        extends Adapter<DataBoundViewHolder<V>> {

    @Nullable
    public List<T> items;
    protected int position;
    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private int dataVersion = 0;

    @Override
    public final DataBoundViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        V binding = createBinding(parent, viewType);
        return new DataBoundViewHolder<V>(binding);
    }

    protected abstract V createBinding(ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(DataBoundViewHolder<V> holder, int position) {
        //noinspection ConstantConditions
        setPosition(position);
        if (position < items.size())
            bind(holder.binding, items.get(position));
        else
            bind(holder.binding, null);
        holder.binding.executePendingBindings();
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(List<T> update) {
        dataVersion++;

        if (items == null) {
            if (update == null) {
                return;
            }
            items = update;
            notifyDataSetChanged();
        } else if (update == null) {
            int oldSize = items.size();
            items = null;
            notifyItemRangeRemoved(0, oldSize);
        } else {
            final int startVersion = dataVersion;
            final List<T> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            try {
                                T oldItem = oldItems.get(oldItemPosition);
                                T newItem = update.get(newItemPosition);
                                return DataBoundListAdapter.this.areItemsTheSame(oldItem, newItem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return false;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            try {
                                T oldItem = oldItems.get(oldItemPosition);
                                T newItem = update.get(newItemPosition);
                                return DataBoundListAdapter.this.areContentsTheSame(oldItem, newItem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion) {
                        // ignore update
                        return;
                    }
                    items = update;
                    diffResult.dispatchUpdatesTo(DataBoundListAdapter.this);
                    onDataUpdated();

                }
            }.execute();
        }
    }

    protected void onDataUpdated() {

    }

    public void notifyOnlyItem(int position, T addInviteResponseBean) {
        notifyItemChanged(position, addInviteResponseBean);
    }


    protected abstract void bind(V binding, T item);

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    protected abstract boolean areContentsTheSame(T oldItem, T newItem);

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void add(List<T> update) {
        int oldSize = 0;
        int updateSize = 0;
        if (items != null){
            oldSize = items.size();
            if (update != null) {
                updateSize = update.size();
                items.addAll(update);
                notifyItemRangeInserted(oldSize,updateSize);
            }
        }else {
            replace(update);
        }

    }
}
