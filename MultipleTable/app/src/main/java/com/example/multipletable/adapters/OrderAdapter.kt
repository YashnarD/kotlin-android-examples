package uz.mobiler.multipletablesg2122.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multipletable.databinding.ItemOrderBinding
import com.example.multipletable.models.Order

class OrderAdapter(var list: List<Order>) : RecyclerView.Adapter<OrderAdapter.Vh>() {

    inner class Vh(var itemOrderBinding: ItemOrderBinding) :
        RecyclerView.ViewHolder(itemOrderBinding.root) {

        fun onBind(order: Order) {
            itemOrderBinding.apply {
                tv1.text = order.customer.address
                tv2.text = order.employee.name
                tv3.text = order.orderDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}