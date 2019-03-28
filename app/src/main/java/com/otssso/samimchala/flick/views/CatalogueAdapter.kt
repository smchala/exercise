package com.otssso.samimchala.flick.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.otssso.samimchala.flick.BR
import com.otssso.samimchala.flick.R
import com.otssso.samimchala.flick.viewModels.CatalogueViewModel

class CatalogueAdapter(val viewModel: CatalogueViewModel) :
    RecyclerView.Adapter<CatalogueAdapter.CatalogueAdapterViewHolder>() {

    class CatalogueAdapterViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
        var viewDataBinding: ViewDataBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueAdapterViewHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.catalogue_item,
            parent,
            false
        )

        return CatalogueAdapterViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = viewModel.getCinemaTickets().movies.size

    override fun onBindViewHolder(holder: CatalogueAdapterViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.viewModel, viewModel)
        holder.viewDataBinding.setVariable(BR.position, position)
    }
}