package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LogupActivity extends AppCompatActivity implements Initialize{
    private TextView login_btn;
    private EditText email_info;
    private EditText pwd_info;
    private Button btn;
    FirebaseAuth user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        initialize();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Click logUp_btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_info.getText().toString().trim();
                String pwd = pwd_info.getText().toString().trim();

                user.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LogupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()) {
                                    try {
                                        throw task.getException();
                                    } catch(FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(LogupActivity.this,"비밀번호가 간단합니다." ,Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(LogupActivity.this,"E-mail 형식에 맞지 않습니다." ,Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(LogupActivity.this,"이미 존재하는 E-mail 입니다." ,Toast.LENGTH_SHORT).show();
                                    } catch(Exception e) {
                                        Toast.makeText(LogupActivity.this,"회원가입 오류" ,Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LogupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    public void initialize() {
        login_btn = findViewById(R.id.go_login);
        // 분석하기
        SpannableString content = new SpannableString("로그인");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        login_btn.setText(content);
        // 분석하기

        email_info = findViewById(R.id.email_join);
        pwd_info = findViewById(R.id.pwd_join);
        btn = findViewById(R.id.logIn_btn);
        user = FirebaseAuth.getInstance();
    }
}
