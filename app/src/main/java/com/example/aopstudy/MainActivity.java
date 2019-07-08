package com.example.aopstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView toastClick=findViewById(R.id.toastClick);
        toastClick.setOnClickListener(this);
    }
    @AopClick(3000)
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.toastClick:
              Toast.makeText(this, "love 欢欢", Toast.LENGTH_SHORT).show();
              break;
      }
    }
}
