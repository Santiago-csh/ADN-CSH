package com.ceiba.adn_csh.databound

import android.os.AsyncTask
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class DataBoundListAdapter<T, V: ViewDataBinding>: RecyclerView.Adapter<DataBoundViewHolder<V>>() {

    private var items: List<T>? = null
    private var dataVersion = 0

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        val binding: V = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    protected abstract fun bind(binding: V, item: T)

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, items!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    @MainThread
    fun replace(update: List<T>?) {
        dataVersion++
        if (items == null) {
            if (update == null) {
                return
            }
            items = update
            notifyDataSetChanged()
        } else if (update == null) {
            val oldSize = items!!.size
            items = null
            notifyItemRangeRemoved(0, oldSize)
        } else {
            val startVersion = dataVersion
            val oldItems = items

            object : AsyncTask<Void, Void, DiffUtil.DiffResult>() {

                override fun doInBackground(vararg voids: Void): DiffUtil.DiffResult {
                    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                        override fun getOldListSize(): Int {
                            return oldItems!!.size
                        }

                        override fun getNewListSize(): Int {
                            return update.size
                        }

                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            val oldItem = oldItems!![oldItemPosition]
                            val newItem = update[newItemPosition]
                            return this@DataBoundListAdapter.areItemsTheSame(oldItem, newItem)
                        }

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            val oldItem = oldItems!![oldItemPosition]
                            val newItem = update[newItemPosition]
                            return this@DataBoundListAdapter.areContentsTheSame(oldItem, newItem)
                        }
                    })
                }

                override fun onPostExecute(diffResult: DiffUtil.DiffResult) {
                    if (startVersion != dataVersion) {
                        return
                    }
                    items = update
                    diffResult.dispatchUpdatesTo(this@DataBoundListAdapter)
                }
            }.execute()
        }
    }

    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

}