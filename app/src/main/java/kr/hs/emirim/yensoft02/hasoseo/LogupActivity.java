package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kr.hs.emirim.yensoft02.hasoseo.model.User;

public class LogupActivity extends AppCompatActivity implements Initialize, View.OnClickListener{
    private TextView login_btn;
    private EditText nickname_info;
    private EditText email_info;
    private EditText pwd_info;
    private EditText pwd_check;
    private Button btn;

    private FirebaseAuth user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ArrayList<String> nicknameData = new ArrayList<>();
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        initialize();

        // Button setOnClick
        login_btn.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void initialize() {
        login_btn = findViewById(R.id.go_login);
        // 분석하기
        SpannableString content = new SpannableString("로그인");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        login_btn.setText(content);
        // 분석하기

        email_info = findViewById(R.id.email_join);
        pwd_info = findViewById(R.id.pwd_join);
        pwd_check = findViewById(R.id.check_password);
        nickname_info = findViewById(R.id.nickname);
        btn = findViewById(R.id.logUp_btn);

        user = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_login:
                Intent intent = new Intent(LogupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.logUp_btn:
                doLogup(nickname_info.getText().toString().trim(), email_info.getText().toString().trim(), pwd_info.getText().toString().trim(), pwd_check.getText().toString().trim());
                break;
        }
    }

    private void doLogup(final String nickname, final String email, final String pwd, final String check_pwd) {
        if (pwd.equals(check_pwd)) {
            Toast.makeText(LogupActivity.this,"pwdequal",Toast.LENGTH_LONG).show();
            checkNickname(nickname, user.getUid(), email, pwd);
        } else {
            Toast.makeText(LogupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkNickname(final String newNickname, final String userUid, final String email, final String pwd){
        DatabaseReference ref = reference.child("users").getRef();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User load = snapshot.getValue(User.class);
                    assert load != null;
                    nicknameData.add(load.nickname);
                }
                if(!(nicknameData.contains(newNickname))){
                    Toast.makeText(LogupActivity.this, "writeNewUser True 들어옴", Toast.LENGTH_SHORT).show();
                    user.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(LogupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            Toast.makeText(LogupActivity.this, "비밀번호가 간단합니다.", Toast.LENGTH_SHORT).show();
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            Toast.makeText(LogupActivity.this, "E-mail 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            Toast.makeText(LogupActivity.this, "이미 존재하는 E-mail 입니다.", Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(LogupActivity.this, "회원가입 오류", Toast.LENGTH_SHORT).show();
                                        }
                                        // onComplete task if문
                                    } else {
                                        User user = new User(email, newNickname);
                                        reference.child("users").child(userUid).setValue(user);
                                        Intent intent = new Intent(LogupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } // onComplete task else 문
                                }// onComplete 함수
                            }); // OnCompleteListener
                    // 닉네임 비교 if문
                } else{
                    Toast.makeText(LogupActivity.this, "닉네임이 존재합니다.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });
    }
}
