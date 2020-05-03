package kr.hs.emirim.yensoft02.hasoseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends AppCompatActivity {
    private TextView box_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        initialize();

        // 안됨
        box_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TranslateActivity.this, "into onClick Method", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TranslateActivity.this, Translate2Activity.class));
                finish();
            }
        });
    }

    public void initialize() {
        box_btn = (TextView) findViewById(R.id.transBox);
    }
}
