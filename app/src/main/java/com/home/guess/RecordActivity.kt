package com.home.guess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER", -1)
        txt_count.text = count.toString()
        val nickname = ed_nickname.text.toString()
        btn_save.setOnClickListener { view ->
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nickname)
                .apply()
        }
    }
}
