package com.example.webview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityFlagQuizBinding
import com.example.webview.models.FlagQuiz
import java.util.*


class FlagQuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFlagQuizBinding
    private lateinit var flagQuizList: ArrayList<FlagQuiz>
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlagQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFlags();

        setQuestion();
    }

    private fun setQuestion() {
        val (image, country) = flagQuizList[index]
        binding.image.setImageResource(image)
        val randomList = getRandomList(country)
        for (i in 0 until randomList.size / 2) {
            val button = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
            )
            button.setText(randomList[i])
            button.setId(i)
            button.setOnClickListener(this)
            button.setLayoutParams(layoutParams)
            binding.layout1.addView(button)
        }
        for (i in randomList.size / 2 until randomList.size) {
            val button = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
            )
            button.setText(randomList[i])
            button.setId(i)
            button.setOnClickListener(this)
            button.setLayoutParams(layoutParams)
            binding.layout2.addView(button)
        }
    }

    fun getRandomList(country: String): List<String> {
        val a = "ABCDEFGHJIKLMNOPQRSTUWVXYZ"
        val b = country.length
        val c = 18 - b
        val d = a.substring(0, c)
        val str = country.uppercase(Locale.getDefault()) + d // China+ABCDEFGHJIKLM
        val list: MutableList<String> = ArrayList()
        for (i in 0 until str.length) {
            list.add(str.substring(i, i + 1))
        }
        Collections.shuffle(list)
        return list
    }

    private fun loadFlags() {
        flagQuizList = ArrayList()
        flagQuizList.add(FlagQuiz(R.drawable.ic_launcher_background, "China"))
        flagQuizList.add(FlagQuiz(R.drawable.ic_launcher_foreground, "USA"))
        flagQuizList.add(FlagQuiz(R.drawable.ic_launcher_background, "Spain"))
        flagQuizList.add(FlagQuiz(R.drawable.ic_launcher_foreground, "England"))
        flagQuizList.add(FlagQuiz(R.drawable.ic_launcher_background, "Germany"))
    }

    override fun onClick(v: View) {
        val button: Button = v as Button
        val s: String = button.getText().toString()
        button.setVisibility(View.INVISIBLE)
        val topButton = Button(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1.0f
        )
        topButton.setLayoutParams(layoutParams)
        topButton.setText(s)
        binding.layout.addView(topButton)
        topButton.setOnClickListener { v1 ->
            binding.layout.removeView(topButton)
            button.setVisibility(View.VISIBLE)
        }
        val childCount = binding.layout.childCount
        val (_, country) = flagQuizList[index]
        if (childCount == country.length) {
            val stringBuilder = StringBuilder()
            for (i in 0 until childCount) {
                val childView: View = binding.layout.getChildAt(i)
                val btn: Button = childView as Button
                val s1: String = btn.getText().toString()
                stringBuilder.append(s1)
            }
            if (stringBuilder.toString() == country.uppercase(Locale.getDefault())) {
                Toast.makeText(this, "Barakalla", Toast.LENGTH_SHORT).show()
                index++
                binding.layout1.removeAllViews()
                binding.layout2.removeAllViews()
                binding.layout.removeAllViews()
                setQuestion()
            } else {
                Toast.makeText(this, "Noto'g'ri", Toast.LENGTH_SHORT).show()
            }
        }
    }
}