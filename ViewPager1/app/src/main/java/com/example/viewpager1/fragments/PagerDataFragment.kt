package com.example.viewpager1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewpager1.R
import com.example.viewpager1.databinding.FragmentPagerDataBinding
import com.example.viewpager1.models.PagerData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PagerDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: PagerData? = null
    private lateinit var binding: FragmentPagerDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as PagerData
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerDataBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.image.setImageResource(param1?.image!!)
        binding.titleTv.text = param1?.title
        binding.descTv.text = param1?.description
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagerDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: PagerData) =
            PagerDataFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}