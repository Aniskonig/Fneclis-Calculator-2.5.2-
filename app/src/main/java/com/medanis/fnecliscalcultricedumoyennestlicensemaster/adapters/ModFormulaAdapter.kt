package com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.ModFormula

class ModFormulaAdapter(
    private val context: Context,
    private val items: List<ModFormula>,
    private val onItemClick: (ModFormula) -> Unit
) : RecyclerView.Adapter<ModFormulaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvFormula)

        init {
            itemView.setOnClickListener {
                onItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_formula, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].label
    }

    override fun getItemCount(): Int = items.size
}
