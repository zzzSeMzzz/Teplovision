package ru.sem.teplovision.ui.photo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_photo_view.*
import ru.sem.teplovision.App
import ru.sem.teplovision.R
import java.io.File

class PhotoViewActivity : AppCompatActivity() {

    open fun showHomeButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setHomeButtonEnabled(show)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        showHomeButton(true)

        val img = intent.getStringExtra(App.EXTRA_IMG)
        Picasso.get().load(File(img)).into(imageView)

        btnBack.setOnClickListener{
            finish()
        }
        btnMore.setOnClickListener{
            val intent = Intent();
            intent.putExtra(App.EXTRA_MORE, true)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
