package kr.hs.emirim.yensoft02.hasoseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Translate_frag extends Fragment implements Initialize{
    private View view; // activity_translate의 View 객체 변수
    private Intent intent;
    private TextView box_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.translate_frag, container, false); // false는 뷰를 수신하는 모든 터치 이벤트도 상위 뷰로 전송하는 것등 무시
        // inflater로 view 생성 (인플레이트`라는 것은 xml 파일을 통해서 객체화)
        initialize();

        // 안됨
        box_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }
    public void initialize() {
        box_btn = (TextView) view.findViewById(R.id.transBox);
        intent = new Intent(getContext(), Translate2Activity.class);
    }
}
