package com.example.testaop

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aopclick.AopClick
import kotlinx.android.synthetic.main.activity_single_click.*

class SingleClick : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("ShowToast")
    @AopClick(3000)
    override fun onClick(v: View?) {
        Toast.makeText(this, "", Toast.LENGTH_LONG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_click)
        singleClick.setOnClickListener(this)
    }
}
