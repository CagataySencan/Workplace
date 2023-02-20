package com.csencan.sepetim.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.csencan.sepetim.R

class StartScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        waitAndStartLoginActivity()
    }

    private fun waitAndStartLoginActivity() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                val intent = Intent(this@StartScreenActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}