package com.otssso.samimchala.flick.utils

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otssso.samimchala.flick.viewModels.CatalogueViewModel
import com.otssso.samimchala.flick.views.CatalogueAdapter


object BindingAdapters {

    @BindingAdapter("bindCatalogueViewAdapter")
    @JvmStatic
    fun bindCatalogueViewAdapter(view: View, viewModel: CatalogueViewModel) {
        val layoutManager = LinearLayoutManager(view.context)
        if (view is RecyclerView) {
            val adapter = CatalogueAdapter(viewModel)
            view.layoutManager = layoutManager
            view.setHasFixedSize(false)
            view.adapter = adapter
        }
    }

    /*
    nice way to avoid long elvis operators in xml
    was going to use it but change of plan!
     */
    @BindingAdapter("visibleOrGone")
    @JvmStatic
    fun visibleOrGone(view: View, visible: Boolean?) {
        if (visible == null)
            return
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    /**
     * setQuantity
     * @param editText
     */
    @BindingAdapter("quantity")
    @JvmStatic
    fun setQuantity(editText: EditText, value: Int) {
        //need validation making sure we are not inputting for e.g.: `.` or `10.`
        editText.setText(value.toString())
    }

    /**
     * getQuantity
     * @param editText
     */
    @InverseBindingAdapter(attribute = "quantity", event = "android:textAttrChanged")
    @JvmStatic
    fun getQuantity(editText: EditText) =
        editText.text?.toString()?.toIntOrNull()?.let { return it } ?: 0

    /**
     * setQuantity
     * @param editText
     */
    @BindingAdapter("quantityTextView")
    @JvmStatic
    fun setQuantityTextView(textView: TextView, value: Int) {
        textView.setText(value.toString())
    }

}