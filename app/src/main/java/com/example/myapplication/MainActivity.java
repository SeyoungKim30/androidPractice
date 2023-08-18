package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    TextView textView;
    GestureDetector gd;
    String name;
    EditText editText;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textView =findViewById(R.id.textView);
       View view= findViewById(R.id.view);
       view.setOnTouchListener(new View.OnTouchListener(){
           public boolean onTouch(View v, MotionEvent event){
               int action = event.getAction();
               float curX = event.getX();
               float curY = event.getY();
               if(action == MotionEvent.ACTION_DOWN){
                   println("손가락 눌림 : "+ curX + " , "+curY);
               } else if (action == MotionEvent.ACTION_MOVE) {
                   println("손가락 move : "+ curX + " , "+curY);
               }else if(action == MotionEvent.ACTION_UP){
                   println("손가락 up : "+ curX + " , "+curY);
               }
               return true;
           }
       });

       gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {
           @Override
           public boolean onDown(@NonNull MotionEvent motionEvent) {
               println("onDown 호출");
               return true;
           }

           @Override
           public void onShowPress(@NonNull MotionEvent motionEvent) {
               println("show press");
           }

           @Override
           public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
               return false;
           }

           @Override
           public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
               println("on scroll");
               return true;
           }

           @Override
           public void onLongPress(@NonNull MotionEvent motionEvent) {
               println("on long press");
           }

           @Override
           public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
               println("on fling : "+v +" , "+ v1);
               return true;
           }
       });

       View view2=findViewById(R.id.view2);
       view2.setOnTouchListener(new View.OnTouchListener(){
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               gd.onTouchEvent(motionEvent);
               return true;
           }
       });
       showToast("onCreate 호출");
       editText=findViewById(R.id.editTextText);
       textView2 = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);
        if(button!=null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editText != null) {
                        name = editText.getText().toString();
                        showToast("사용자 입력값을 name 변수에 할당");
                    }
                }
            });
        }
        if(savedInstanceState!=null){
            if(textView2!=null){
                name=savedInstanceState.getString("name");
                textView2.setText(name);
                showToast("값을 복원했습니다.name="+name);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name : ",name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestory 호출");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            println("back 눌림");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void println(String data){
        textView.append(data + "\n");
    }

    public void onButton1Clicked(View v){
        Toast.makeText(this,"been clicked",Toast.LENGTH_LONG).show();
    }

    public void onButton2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
        startActivity(intent);
    }

    public void onButton3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-6380-9098"));
        startActivity(intent);
    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}