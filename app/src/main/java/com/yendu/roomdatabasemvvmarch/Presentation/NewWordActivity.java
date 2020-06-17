package com.yendu.roomdatabasemvvmarch.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.yendu.roomdatabasemvvmarch.R;

public class NewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        EditText editText=findViewById(R.id.editext);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wo=editText.getText().toString();
                if(wo.isEmpty()){
                    Toast.makeText(NewWordActivity.this,"Input text",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent();
                    intent.putExtra("s",wo);
                    setResult(2,intent);
                    finish();
                }
            }
        });
    }
}
