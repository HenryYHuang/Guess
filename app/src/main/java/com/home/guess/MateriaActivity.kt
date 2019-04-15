package com.home.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_materia.*
import kotlinx.android.synthetic.main.content_materia.*

class MateriaActivity : AppCompatActivity() {
    var secretNumber = SecretNumber()
    val TAG = MateriaActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setNeutralButton("CANCEL", null)
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secretNumber.reStart()
                    txt_counter.text = secretNumber.count.toString()
                    ed_number.setText("")
                    Log.d(TAG, "Number: ${secretNumber.secret}")
                })
                .show()
        }
        Log.d(TAG, "Number: ${secretNumber.secret}")
        txt_counter.text = secretNumber.count.toString()

    }

    fun check(view : View) {
        val number = ed_number.text.toString().toInt()
        var diff = secretNumber.validate(number)
        var message = getString(R.string.great_you_got_it)
        txt_counter.text = secretNumber.count.toString()

        if (diff > 0 ) {
            message = getString(R.string.bigger)
        } else if (diff < 0) {
            message = getString(R.string.smaller)
        } else if (diff == 0 && secretNumber.count < 3) {
            message = getString(R.string.exellent) + secretNumber.secret
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
                ed_number.text.clear()
            })
            .show()
    }

}
