package com.example.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import com.example.fragment.R
import com.example.fragment.databinding.FragmentFirstBinding
import com.example.fragments.models.User

private const val ARG_PARAM1 = "param1"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var mParam1: User? = null

    companion object {

        fun newInstance(user: User): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, user)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = (requireArguments().getSerializable(ARG_PARAM1)) as User
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.closeBtn.text = mParam1?.username + " " + mParam1?.password

        binding.closeBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
            parentFragmentManager.popBackStack()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.add -> Toast.makeText(requireContext(), "Menu Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}