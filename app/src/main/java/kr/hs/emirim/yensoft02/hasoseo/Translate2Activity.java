package kr.hs.emirim.yensoft02.hasoseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Translate2Activity extends AppCompatActivity implements Initialize{
    private ImageButton back_btn, cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate2);

        initialize();

        ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.tBack_btn:
                        finish();
                        break;

                    case R.id.tCancel_btn:
                        // 번역내용 삭제 코드 삽입필요.
                        break;
                }
            }
        };

        back_btn.setOnClickListener(onClickListener);
        cancel_btn.setOnClickListener(onClickListener);
    }

    @Override
    public void initialize() {
        back_btn = findViewById(R.id.tBack_btn);
        cancel_btn = findViewById(R.id.tCancel_btn);
    }
}
