package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView; // 네비게이션 바
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Translate_frag tf; // 교정들어가기 전(교정1) fragment 객체
    private Write_frag wf; // 작성 fragment 객체
    private Setting_frag sf; // 환경설정 fragment 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) { //네비게이션 바 메서드
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView); // id 검색한 컴포넌트 변수에 넣어주기
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            // 버튼 클릭하는 리스너
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.N_translate:
                        setFrag(0);
                        break;
                    case R.id.N_write:
                        setFrag(1);
                        break;
                    case R.id.N_setting:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        tf = new Translate_frag();
        wf = new Write_frag();
        sf = new Setting_frag();
        setFrag(0); // 첫 프래그먼트 화면 지정

    }

    // 프레그먼트 교체
    private void setFrag(int n){
        fm = getSupportFragmentManager(); // 프래그먼트를 추가,삭제 또는 교체등의 작업
        ft = fm.beginTransaction(); // transaction 처음
        switch(n) {
            case 0:
                ft.replace(R.id.MainFrame, tf); // 현재 프레임에서 activity_translate프레임으로 재배치(교정클릭전화면) transaction수정
                ft.commit(); // transaction 커밋
                break;
            case 1:
                ft.replace(R.id.MainFrame, wf);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.MainFrame, sf);
                ft.commit();
                break;
        }
    }
}