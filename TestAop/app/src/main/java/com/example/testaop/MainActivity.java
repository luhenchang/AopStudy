package com.example.testaop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aopclick.AopClick;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this,SingleClick.class));
      TextView tvClick= findViewById(R.id.singleClick);
      tvClick.setOnClickListener(this);
    }
    @AopClick(12000)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singleClick:
            Log.e("llll", "sjldlsdjls");
            Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
            break;
        }
    }
}
