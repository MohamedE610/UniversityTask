package com.university.listing.presentation.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.university.core.extension.gone
import com.university.core.extension.show
import com.university.core.extension.viewBinding
import com.university.core.entity.University
import com.university.listing.databinding.ItemUniversityBinding

class UniversityAdapter(
    private val onItemClicked: (University) -> Unit
) : Adapter<UniversityViewHolder>() {
    val data: ArrayList<University> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<University>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        return UniversityViewHolder(
            binding = parent.viewBinding(ItemUniversityBinding::inflate),
            onItemClicked = onItemClicked
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(item = data[position], showDivider = position < itemCount - 1)
    }
}

class UniversityViewHolder(
    private val binding: ItemUniversityBinding,
    private val onItemClicked: (University) -> Unit
) : ViewHolder(binding.root) {
    fun bind(item: University, showDivider: Boolean) {
        with(binding) {
            tvName.text = item.name
            tvState.text = item.stateProvince ?: item.country
            itemView.setOnClickListener { onItemClicked(item) }
            takeIf { showDivider }?.let {
                divider.show()
            } ?: divider.gone()
        }
    }
}