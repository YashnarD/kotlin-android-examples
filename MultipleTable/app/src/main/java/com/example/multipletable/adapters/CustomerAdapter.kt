package uz.mobiler.multipletablesg2122.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.multipletable.databinding.ItemCustomerBinding
import com.example.multipletable.models.Customer

class CustomerAdapter(var list: List<Customer>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Customer = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemCustomerBinding: ItemCustomerBinding
        if (convertView == null) {
            itemCustomerBinding =
                ItemCustomerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            itemCustomerBinding = ItemCustomerBinding.bind(convertView)
        }
        itemCustomerBinding.tv.text = list[position].address
        return itemCustomerBinding.root
    }
}