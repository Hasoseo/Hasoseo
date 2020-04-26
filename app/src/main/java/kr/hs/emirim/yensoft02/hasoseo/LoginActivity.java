package kr.hs.emirim.yensoft02.hasoseo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView findIdPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        // 분석하기
        SpannableString content = new SpannableString("아이디/비밀번호 찾기");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        findIdPassword.setText(content);
        // 분석하기
    }

    protected void initialize() {
        findIdPassword = findViewById(R.id.id_passwordFind);
    }
}
