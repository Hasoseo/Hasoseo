package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Write_frag extends Fragment implements Initialize, View.OnClickListener{
    private View view; // activity_write의 View 객체 변수
    private List_frag lf;
    private LinearLayout conLinear;
    private ImageButton back_btn, trans_btn, add_btn;
    private LinearLayout item;
    private int cnt_title=1;
    private TextView big_title, this_title, this_contents;
    View write_sub;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_write_frag, container, false);
        initialize();

        // onClick 구현
        back_btn.setOnClickListener(this);
        trans_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);
        item.setOnClickListener(this);
        this_title.setOnClickListener(this);
        this_contents.setOnClickListener(this);

        return view;
    }

    @Override
    public void initialize() {
        lf = new List_frag();
        conLinear = (LinearLayout)view.findViewById(R.id.item_con);
        back_btn = view.findViewById(R.id.wBack_btn);
        trans_btn = view.findViewById(R.id.wTrans_btn);
        add_btn = view.findViewById(R.id.wAdd_item);
        item = view.findViewById(R.id.wItem);
        big_title = view.findViewById(R.id.wTitle);
        this_title = view.findViewById(R.id.wN_title);
        this_contents = view.findViewById(R.id.wN_contents);
        intent = new Intent(getContext(),DetailWriteActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.wBack_btn:
                ((MainActivity)getActivity()).replaceFragment(lf);
                break;

            case R.id.wTrans_btn:
                startActivity(new Intent(getContext(), Translate2Activity.class));
                break;

            case R.id.wAdd_item:
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                write_sub = inflater.inflate(R.layout.activity_write__sub, conLinear, true);
                //동적 뷰에 id 부여하기
                TextView n_title = write_sub.findViewById(R.id.wN_title);
                TextView n_contents = write_sub.findViewById(R.id.wN_contents);
                //밑에 n_title과 n_contents id가 원래 있던 레이아웃부터 적용됨.... 그래서 2부터 들어감...
                //   n_title.setId(cnt_title);
                //   n_title.setText(Integer.toString(cnt_title));
                //   n_contents.setId(cnt_title+20);
                //   n_contents.setText(Integer.toString(cnt_title+20));
                cnt_title++;
                break;

            case R.id.wItem: case R.id.wN_title: case R.id.wN_contents:
                startActivity(intent);
                break;
        }
    }
}
