package com.chaev.newdebts.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaev.newdebts.databinding.ItemDebtListBinding
import com.chaev.newdebts.domain.models.Debts
import java.time.format.DateTimeFormatter

class DebtsCreditsAdapter : RecyclerView.Adapter<DebtsCreditsAdapter.ViewHolder>() {
    var items = emptyList<Debts>()


    class ViewHolder(private val binding: ItemDebtListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Debts) {
            if (item.userCreditor) {
                binding.username.text = item.debtor.username
                binding.iconText.text = item.debtor.username
                    .first()
                    .toString()
                    .uppercase()
            } else {
                binding.username.text = item.creditor.username
                binding.iconText.text = item.creditor.username
                    .first()
                    .toString()
                    .uppercase()
            }
            binding.dateText.text = item.created.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
            binding.sumText.text = "${item.money} ₽"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDebtListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}