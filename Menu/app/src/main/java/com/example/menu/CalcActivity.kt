package com.example.menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.menu.databinding.ActivityCalcBinding


class CalcActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCalcBinding

    var number1 = ""
    var number2 = ""
    var isHave = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clearTv.setOnClickListener(this)
        binding.plusMinusTv.setOnClickListener(this)
        binding.percentTv.setOnClickListener(this)
        binding.divTv.setOnClickListener(this)

        binding.sevenTv.setOnClickListener(this)
        binding.eightTv.setOnClickListener(this)
        binding.nineTv.setOnClickListener(this)
        binding.multTv.setOnClickListener(this)

        binding.fourTv.setOnClickListener(this)
        binding.fiveTv.setOnClickListener(this)
        binding.sixTv.setOnClickListener(this)
        binding.minusTv.setOnClickListener(this)

        binding.oneTv.setOnClickListener(this)
        binding.twoTv.setOnClickListener(this)
        binding.threeTv.setOnClickListener(this)
        binding.plusTv.setOnClickListener(this)

        binding.zeroTv.setOnClickListener(this)
        binding.dotTv.setOnClickListener(this)
        binding.equalTv.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id: Int = p0!!.getId()
        when (id) {
            R.id.clear_tv -> {
                binding.edit.setText("")
                number1 = ""
                number2 = ""
                isHave = false
            }
            R.id.one_tv -> if (isHave) {
                number2 += "1"
                binding.edit.setText(number2)
            } else {
                number1 += "1"
                binding.edit.setText(number1)
            }
            R.id.two_tv -> if (isHave) {
                number2 += "2"
                binding.edit.setText(number2)
            } else {
                number1 += "2"
                binding.edit.setText(number1)
            }
            R.id.three_tv -> if (isHave) {
                number2 += "3"
                binding.edit.setText(number2)
            } else {
                number1 += "3"
                binding.edit.setText(number1)
            }
            R.id.plus_tv -> {
                isHave = true
                binding.edit.setText("")
            }
            R.id.equal_tv -> {
                val result = number1.toInt() + number2.toInt()
                binding.edit.setText(result.toString())
            }
        }
    }
}