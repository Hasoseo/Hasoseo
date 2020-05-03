package kr.hs.emirim.yensoft02.hasoseo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Translate_frag extends Fragment {
    private View view; // activity_translate의 View 객체 변수

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_translate, container, false); // false는 뷰를 수신하는 모든 터치 이벤트도 상위 뷰로 전송하는 것등 무시
        // inflater로 view 생성 (인플레이트라는 것은 xml 파일을 통해서 객체화)

        return view;
    }
}
