package kr.hs.emirim.yensoft02.hasoseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailWriteActivity extends AppCompatActivity implements Initialize, View.OnClickListener {
    private TextView s_title;
    private TextView content;
    private ImageButton backbtn, transbtn;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_write);

        initialize();

        backbtn.setOnClickListener(this);
        transbtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wd_Backbtn:
                save();
                finish();
                break;
            case R.id.wd_transbtn:
                startActivity(new Intent(DetailWriteActivity.this, Translate2Activity.class));
                break;
        }
    }

    @Override
    public void initialize() {
        backbtn = findViewById(R.id.wd_Backbtn);
        transbtn = findViewById(R.id.wd_transbtn);
        s_title = findViewById(R.id.wS_title);
        content = findViewById(R.id.wd_content);
    }
    public void save() {
//        if((s_title.getText() != null) || (content.getText() != null)) {
//                    DatabaseReference title = database.getReference("s_title");
//                    title.setValue(s_title.getText().toString());
//                    DatabaseReference contents = database.getReference("contents");
//                    contents.setValue(content.getText().toString());
//        }
    }
}
