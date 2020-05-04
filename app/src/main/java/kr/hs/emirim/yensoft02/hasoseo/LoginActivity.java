package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextView findIdPassword;
    private TextView goLogup;
    private TextView email_info;
    private TextView pwd_info;
    private Button btn;
    private FirebaseAuth user;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        // Click Logup Button
        goLogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LogupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Click Find Id&Password button
        findIdPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        // Click Login button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin(email_info.getText().toString().trim(), pwd_info.getText().toString().trim());
            }
        });

    }

    protected void initialize() {
        findIdPassword = findViewById(R.id.find_idPwd);
        // 분석하기
        SpannableString content = new SpannableString("아이디/비밀번호 찾기");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        findIdPassword.setText(content);
        // 분석하기

        goLogup = findViewById(R.id.go_logup);
        // 분석하기
        SpannableString content2 = new SpannableString("회원가입");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        goLogup.setText(content2);
        // 분석하기

        email_info = findViewById(R.id.email_in);
        pwd_info = findViewById(R.id.pwd_in);
        btn = findViewById(R.id.logIn_btn);
        user = FirebaseAuth.getInstance();
    }

    public void startLogin(String email, String pwd){
        user.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(LoginActivity.this,"user. onComplete 함수" ,Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(LoginActivity.this,"존재하지 않는 id 입니다." ,Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(LoginActivity.this,"이메일 형식이 맞지 않습니다." ,Toast.LENGTH_SHORT).show();
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(LoginActivity.this,"Firebase NetworkException" ,Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this,"Exception" ,Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            currentUser = user.getCurrentUser();

                            Toast.makeText(LoginActivity.this, "로그인 성공" + "/" + currentUser.getEmail() + "/" + currentUser.getUid() ,Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });
    }
}
