package com.example.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebalivedata.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.view_frame, FragmentMVVM.newInstance(), FragmentMVVM::class.java.simpleName)
            .addToBackStack(FragmentMVVM::class.java.name)
            .commit()
    }


}
