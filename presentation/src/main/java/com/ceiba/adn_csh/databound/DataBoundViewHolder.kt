package com.ceiba.adn_csh.databound

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)