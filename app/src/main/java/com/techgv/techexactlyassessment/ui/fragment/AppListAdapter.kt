package com.techgv.techexactlyassessment.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.techgv.techexactlyassessment.data.remote.dto.App
import com.techgv.techexactlyassessment.databinding.ItemViewBinding

class AppListAdapter : RecyclerView.Adapter<AppListAdapter.AppListViewHolder>() {

    private val filterList: MutableList<App> = mutableListOf()
    private val masterList: MutableList<App> = mutableListOf()

    private var queryText = ""

    inner class AppListViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(app: App, position: Int) {
            binding.textView.text = app.appName
            binding.imageView.load(app.appIcon)
            binding.itemSwitch.isChecked = app.isEnabled()

            binding.itemSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    filterList[position] = filterList[position].copy(status = "active")
                    masterList.mapIndexed { index, app ->
                        if (app.appId == filterList[position].appId) {
                            masterList[index] = filterList[position]
                        }
                    }
                } else {
                    filterList[position] = filterList[position].copy(status = "disable")
                    masterList.mapIndexed { index, app ->
                        if (app.appId == filterList[position].appId) {
                            masterList[index] = filterList[position]
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: AppListViewHolder, position: Int) {
        holder.bind(filterList[position], position)
    }

    override fun getItemId(position: Int): Long {
        return filterList[position].appId.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun clearRecyclerView() {
        filterList.clear()
        notifyDataSetChanged()
    }

    fun filter(searchText: String) {
        queryText = searchText
        val filteredList = mutableListOf<App>()
        if (searchText.isEmpty()) {
            filteredList.addAll(masterList)
        } else {
            for (item in masterList) {
                if (item.appName.contains(searchText, true)) {
                    filteredList.add(item)
                }
            }
        }
        clearRecyclerView()
        internalUpdateList(filteredList)
    }

    fun restList() {
        queryText = ""
        clearRecyclerView()
        internalUpdateList(masterList)
    }

    private fun internalUpdateList(newList: List<App>) {
        val diffCallback = ListDiffCallback(filterList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        filterList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun masterUpdateList(newList: List<App>) {
        val diffCallback = ListDiffCallback(filterList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        filterList.addAll(newList)
        masterList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ListDiffCallback(
        private val oldList: List<App>,
        private val newList: List<App>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition].appId == newList[newItemPosition].appId)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition] == newList[newItemPosition])
        }

    }

}
