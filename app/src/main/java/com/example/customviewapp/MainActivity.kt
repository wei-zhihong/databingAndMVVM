package com.example.customviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import com.example.customviewapp.databinding.ActivityMainBinding
import com.example.customviewapp.databinding.ClickHandler
import com.example.customviewapp.databinding.User

class MainActivity : AppCompatActivity() {
    private var name: ObservableField<String> = ObservableField<String>()

    private lateinit var binding: ActivityMainBinding

    companion object {
        @BindingAdapter("image")
        @JvmStatic
        fun bindImageUrl(view: ImageView, url: String) {
            Glide.with(view)
                .load(url)
                .into(view)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        name.set("https://img-blog.csdnimg.cn/img_convert/fd5211d435e68c31fdd76b0bf5b2c42c.png")
        binding.name = name
        binding.user = User("first", "lastNie")
        binding.handler = ClickHandler()
    }
}