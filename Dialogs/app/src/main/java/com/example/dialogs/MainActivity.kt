package com.example.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogs.databinding.ActivityMainBinding
import com.example.dialogs.databinding.DialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Bu yerda title")
            builder.setMessage("Bu yerda message")
            builder.setCancelable(false)

            builder.setPositiveButton("Positive", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Positive button clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            })

            builder.setNegativeButton("Negative", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Negative button clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            })

            builder.setNeutralButton("Neutral", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Neutral button clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            })

            builder.show()
        }

        binding.alertListBtn.setOnClickListener { v ->
            val builder =
                AlertDialog.Builder(this)
            val a =
                arrayOf("Kotlin", "Java", "Android", "Flutter")
            val b = booleanArrayOf(false, false, false, false)
            builder.setMultiChoiceItems(
                a, b
            ) { dialog, which, isChecked ->
                if (isChecked) {
                    Toast.makeText(this@MainActivity, a[which], Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        binding.alertSingleBtn.setOnClickListener { v ->
            val builder =
                AlertDialog.Builder(this)
            val a =
                arrayOf("Portugaliya", "Fransiya", "Belgiya", "Gollandiya")
            builder.setSingleChoiceItems(
                a, 1
            ) { dialog, which ->
                Toast.makeText(this@MainActivity, a[which], Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        binding.customBtn.setOnClickListener { v ->
            val builder =
                AlertDialog.Builder(this)
            val dialogBinding: DialogBinding = DialogBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.getRoot())
            val alertDialog = builder.create()
            alertDialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogBinding.okButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
            })
            alertDialog.show()
        }

        binding.dateBtn.setOnClickListener { v ->
            val calendar: Calendar = Calendar.getInstance()
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            val month: Int = calendar.get(Calendar.MONTH) + 1
            val year: Int = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(
                    this,
                    { view, year, month, dayOfMonth ->
                        Toast.makeText(
                            this@MainActivity,
                            "$dayOfMonth.$month.$year",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, year, month, day
                )
            datePickerDialog.show()
        }

        binding.timeBtn.setOnClickListener { v ->
            val calendar: Calendar = Calendar.getInstance()
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)
            val timePickerDialog =
                TimePickerDialog(
                    this,
                    { view, hourOfDay, minute ->
                        Toast.makeText(
                            this@MainActivity,
                            "$hourOfDay:$minute",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, hour, minute, true
                )
            timePickerDialog.show()
        }

        binding.bottomSheetBtn.setOnClickListener { v ->
            val bottomSheetDialog = BottomSheetDialog(this)
            val dialogBinding: DialogBinding = DialogBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(dialogBinding.getRoot())
            bottomSheetDialog.show()
        }

        binding.snackbarBtn.setOnClickListener { v ->
            val snackbar = Snackbar.make(v, "hello android", Snackbar.LENGTH_LONG)
            snackbar.setAction("Undo", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Undo", Toast.LENGTH_SHORT).show()
                }
            })
            snackbar.show()
        }
    }
}