package com.melinda.lindashop

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import com.melinda.lindashop.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_simple_intent.setOnClickListener {
           val simpleIntent = Intent(this@MainActivity, SimpleActivity::class.java)
            startActivity(simpleIntent)
        }
        btn_intent_with_data.setOnClickListener{
            val dataIntent = Intent(this@MainActivity, ExplicitIntentActivity::class.java)
            dataIntent.putExtra(ExplicitIntentActivity.EXTRA_NAME,"Melinda")
            dataIntent.putExtra(ExplicitIntentActivity.EXTRA_AGE,"20 Tahun")
            dataIntent.putExtra(ExplicitIntentActivity.EXTRA_EMAIL,"lmelinda672@gmail.com")
            startActivity(dataIntent)
        }
        btn_intent_parcelable.setOnClickListener {
            val parcelIntent = Intent(this@MainActivity, ParcleActivity::class.java)
            val user = User("Melinda", "lmelinda672@gmail.com", "20 Tahun")
            parcelIntent.putExtra(ParcleActivity.EXTRA_USER, user)
            startActivity(parcelIntent)
        }
        btn_implicit_intent.setOnClickListener {
            val phoneNumber ="085845216569"
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
            intent.putExtra("sms_body", "Hallo Selamat Malam Semangat Begadang")
            startActivity(intent)
        }
        btn_intent_result.setOnClickListener {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==200){
            val color = data?.getStringExtra(ResultActivity.EXTRA_COLOR)
            view_result.setBackgroundColor(Color.parseColor(color))
        }
    }
}