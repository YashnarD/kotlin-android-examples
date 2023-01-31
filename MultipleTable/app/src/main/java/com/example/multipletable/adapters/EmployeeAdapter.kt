package uz.mobiler.multipletablesg2122.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.multipletable.databinding.ItemEmployeeBinding
import com.example.multipletable.models.Employee
class EmployeeAdapter(var list: List<Employee>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Employee = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemEmployeeBinding: ItemEmployeeBinding
        if (convertView == null) {
            itemEmployeeBinding =
                ItemEmployeeBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            itemEmployeeBinding = ItemEmployeeBinding.bind(convertView)
        }
        itemEmployeeBinding.tv.text = list[position].name
        return itemEmployeeBinding.root
    }
}