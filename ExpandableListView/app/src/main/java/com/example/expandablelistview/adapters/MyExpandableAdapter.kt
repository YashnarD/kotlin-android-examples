package com.example.expandablelistview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ItemChildBinding
import com.example.expandablelistview.databinding.ItemGroupBinding

class MyExpandableAdapter(
    var map: Map<String, ArrayList<String>>,
    var titleList: ArrayList<String>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = titleList.size

    override fun getChildrenCount(p0: Int): Int = map[titleList[p0]]!!.size

    override fun getGroup(p0: Int): Any = titleList[p0]

    override fun getChild(p0: Int, p1: Int): Any = map[titleList[p0]]!![p1]

    override fun getGroupId(p0: Int): Long = p0.toLong()

    override fun getChildId(p0: Int, p1: Int): Long = p1.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val itemGroupBinding: ItemGroupBinding
        if (convertView == null) {
            itemGroupBinding =
                ItemGroupBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            itemGroupBinding = ItemGroupBinding.bind(convertView)
        }
        itemGroupBinding.groupTv.text = titleList[groupPosition]

        if (isExpanded) {
            itemGroupBinding.image.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
        } else {
            itemGroupBinding.image.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
        }

        return itemGroupBinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val itemChildBinding: ItemChildBinding
        if (convertView == null) {
            itemChildBinding =
                ItemChildBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            itemChildBinding = ItemChildBinding.bind(convertView)
        }
        itemChildBinding.childTv.text = map[titleList[groupPosition]]!![childPosition]
        return itemChildBinding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = true
}