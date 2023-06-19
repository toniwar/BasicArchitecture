package ru.otus.basicarchitecture.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otus.basicarchitecture.DISABLED_ITEM
import ru.otus.basicarchitecture.ENABLED_ITEM
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.domain.models.HobbyItem
import ru.otus.basicarchitecture.presentation.viewmodels.Fragment3ViewModel



class HobbyListAdapter(
    private val vm:Fragment3ViewModel
): RecyclerView.Adapter<HobbyListViewHolder>() {

    private val itemList = mutableListOf<HobbyItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyListViewHolder {
        val layoutRes = when(viewType){
            ENABLED_ITEM -> R.layout.enabled_hobby_item
            else -> R.layout.hobby_item
        }
        val itemView = LayoutInflater.from(parent.context).inflate(layoutRes, parent,false)
        return HobbyListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HobbyListViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            Log.d("VMAction", item.toString())
            vm.changeEnableState(item)
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val isEnabled = itemList[position].enabled
        return if(isEnabled) ENABLED_ITEM else DISABLED_ITEM
    }

    fun setItemList(list: List<HobbyItem>){
        itemList.clear()
        for(i in list.indices){
            itemList.add(list[i])
        }
    }

}