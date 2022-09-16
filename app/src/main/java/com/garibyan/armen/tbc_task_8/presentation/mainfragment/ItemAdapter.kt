package com.garibyan.armen.tbc_task_8.presentation.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.garibyan.armen.tbc_task_8.R
import com.garibyan.armen.tbc_task_8.data.remote.dto.ResponseModel
import com.garibyan.armen.tbc_task_8.databinding.RvItemBinding

class ItemAdapter: ListAdapter<ResponseModel, ItemAdapter.ItemViewHolder>(ItemCallBack()) {

    inner class ItemViewHolder(private val binding: RvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(responseModel: ResponseModel) = with(binding){
            image.load(responseModel.cover)
            //image.background.setTint(R.drawable.round_corners)
            tvPrice.text = responseModel.price
            tvTitle.text = responseModel.title
            responseModel.liked?.let { isFavorite(it) }
        }

        private fun isFavorite(isFavorite: Boolean){
            when(isFavorite){
                true -> binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                false -> binding.imgFavorite.setImageResource(R.drawable.ic_not_favorite)
            }
        }
    }



    class ItemCallBack: DiffUtil.ItemCallback<ResponseModel>(){
        override fun areItemsTheSame(oldItem: ResponseModel, newItem: ResponseModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ResponseModel, newItem: ResponseModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}