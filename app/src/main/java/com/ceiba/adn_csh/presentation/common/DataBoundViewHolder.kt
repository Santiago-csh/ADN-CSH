package com.ceiba.adn_csh.presentation.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)