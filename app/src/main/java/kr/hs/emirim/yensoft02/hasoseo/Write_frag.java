package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Write_frag extends Fragment {
    private View view; // activity_write의 View 객체 변수
    private LinearLayout conLinear;
    private ImageButton add_item;
    private LinearLayout item;
    private int cnt_title=1;
    private TextView this_title, this_contents;
    View write_sub;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_write_frag, container, false);
        conLinear = (LinearLayout)view.findViewById(R.id.item_con);
        add_item = view.findViewById(R.id.add_item);
        item = view.findViewById(R.id.item);
        this_title = view.findViewById(R.id.n_title);
        this_contents = view.findViewById(R.id.n_contents);
        intent = new Intent(getContext(),DetailWriteActivity.class);

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 write_sub = inflater.inflate(R.layout.activity_write__sub,conLinear,true);
                 //동적 뷰에 id 부여하기
                 TextView n_title = write_sub.findViewById(R.id.n_title);
                 TextView n_contents = write_sub.findViewById(R.id.n_contents);
                 //밑에 n_title과 n_contents id가 원래 있던 레이아웃부터 적용됨.... 그래서 2부터 들어감...
//                 n_title.setId(cnt_title);
//                 n_title.setText(Integer.toString(cnt_title));
//                 n_contents.setId(cnt_title+20);
//                 n_contents.setText(Integer.toString(cnt_title+20));
                 cnt_title++;
            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        this_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        this_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }
}
