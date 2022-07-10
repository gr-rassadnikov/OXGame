package com.example.oxgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


open class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var m = 4
        var n = 4
        var k = 4
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val SettingsButton1_2: Button = findViewById(R.id.buttonSettings1_2)
        val SettingsButton1_3: Button = findViewById(R.id.buttonSettings1_3)
        val SettingsButton1_4: Button = findViewById(R.id.buttonSettings1_4)
        val SettingsButton1_5: Button = findViewById(R.id.buttonSettings1_5)
        val SettingsButton1_6: Button = findViewById(R.id.buttonSettings1_6)
        val SettingsButton1_7: Button = findViewById(R.id.buttonSettings1_7)

        val SettingsButton2_2: Button = findViewById(R.id.buttonSettings2_2)
        val SettingsButton2_3: Button = findViewById(R.id.buttonSettings2_3)
        val SettingsButton2_4: Button = findViewById(R.id.buttonSettings2_4)
        val SettingsButton2_5: Button = findViewById(R.id.buttonSettings2_5)
        val SettingsButton2_6: Button = findViewById(R.id.buttonSettings2_6)
        val SettingsButton2_7: Button = findViewById(R.id.buttonSettings2_7)

        val buttonStart: Button = findViewById(R.id.buttonStart)
        val listSettingsButton1: MutableList<Button> = mutableListOf(
            SettingsButton1_2,
            SettingsButton1_3,
            SettingsButton1_4,
            SettingsButton1_5,
            SettingsButton1_6,
            SettingsButton1_7
        )
        val listSettingsButton2: MutableList<Button> = mutableListOf(
            SettingsButton2_2,
            SettingsButton2_3,
            SettingsButton2_4,
            SettingsButton2_5,
            SettingsButton2_6,
            SettingsButton2_7
        )
        for (number in 0 until listSettingsButton1.size) {
            listSettingsButton1[number].setOnClickListener {
                listSettingsButton1[number].setBackgroundColor(Color.RED)
                n = number + 1
                m = number + 1
            }
        }

        for (number in 0 until listSettingsButton2.size) {
            listSettingsButton2[number].setOnClickListener {
                listSettingsButton2[number].setBackgroundColor(Color.RED)
                k = number + 1
            }
        }
        buttonStart.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("m", m + 1)
            intent.putExtra("n", n + 1)
            intent.putExtra("k", k + 1)
            startActivity(intent)
            listSettingsButton1[n - 1].setBackgroundColor(Color.GREEN)
            listSettingsButton2[k - 1].setBackgroundColor(Color.GREEN)
        }

    }
}