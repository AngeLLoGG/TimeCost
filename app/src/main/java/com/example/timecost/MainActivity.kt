package com.example.timecost

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        val etSalary = findViewById<EditText>(R.id.etMonthlySalary)
        val etHours = findViewById<EditText>(R.id.etMonthlyHours)
        val etPrice = findViewById<EditText>(R.id.etProductPrice)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        val layoutResult = findViewById<View>(R.id.layoutResult)
        val tvResultTime = findViewById<TextView>(R.id.tvResultTime)
        val tvHourlyRate = findViewById<TextView>(R.id.tvHourlyRate)
        val tvAdvice = findViewById<TextView>(R.id.tvAdvice)
        val progressBar = findViewById<ProgressBar>(R.id.progressBarWorkday)
        val tvProgressPercent = findViewById<TextView>(R.id.tvProgressPercent)

        btnCalculate.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            it.clearFocus()

            val salaryStr = etSalary.text.toString().trim()
            val hoursStr = etHours.text.toString().trim()
            val priceStr = etPrice.text.toString().trim()

            if (salaryStr.isEmpty() || hoursStr.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val salary = salaryStr.toDoubleOrNull() ?: 0.0
            val hours = hoursStr.toDoubleOrNull() ?: 0.0
            val price = priceStr.toDoubleOrNull() ?: 0.0

            if (salary <= 0 || hours <= 0 || price <= 0) {
                Toast.makeText(this, "Los valores deben ser mayores a cero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hourlyRate = salary / hours
            val totalHoursNeeded = price / hourlyRate

            val hoursInt = totalHoursNeeded.toInt()
            val minutesInt = ((totalHoursNeeded - hoursInt) * 60).toInt()

            tvHourlyRate.text = String.format("Valor hora estimado: S/ %.2f", hourlyRate)
            tvResultTime.text = "$hoursInt h y $minutesInt min"

            val workdayPercentage = (totalHoursNeeded / 8.0) * 100.0
            val displayProgress = workdayPercentage.toInt().coerceIn(0, 100)

            val colorHex: String
            when {
                totalHoursNeeded < 1.0 -> {
                    tvAdvice.text = "Gasto bajo • Menos de 1h de trabajo"
                    colorHex = "#34C759"
                    tvProgressPercent.text = String.format("%.0f%% de tu día", workdayPercentage)
                }
                totalHoursNeeded in 1.0..8.0 -> {
                    tvAdvice.text = "Gasto medio • Fracción de tu jornada"
                    colorHex = "#FF9500"
                    tvProgressPercent.text = String.format("%.0f%% de tu día", workdayPercentage)
                }
                else -> {
                    val days = String.format("%.1f", totalHoursNeeded / 8.0)
                    tvAdvice.text = "Alerta • Equivale a $days días laborales"
                    colorHex = "#FF3B30"
                    tvProgressPercent.text = String.format("%.0f%% (¡Excede 1 día!)", workdayPercentage)
                }
            }

            val dynamicColor = Color.parseColor(colorHex)
            tvAdvice.setTextColor(dynamicColor)
            tvProgressPercent.setTextColor(dynamicColor)
            progressBar.progressTintList = ColorStateList.valueOf(dynamicColor)

            val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, displayProgress)
            progressAnimator.duration = 650
            progressAnimator.interpolator = DecelerateInterpolator()
            progressAnimator.start()

            if (layoutResult.visibility != View.VISIBLE) {
                layoutResult.alpha = 0f
                layoutResult.translationY = 80f
                layoutResult.visibility = View.VISIBLE
            }

            layoutResult.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(450)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }
}