package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Write_frag extends Fragment implements Initialize, View.OnClickListener {
    private final int DYNAMIC_VIEW_ID = 0x8000;
    private View view; // activity_write의 View 객체 변수
    private LinearLayout conLinear;
    private ImageButton back_btn, trans_btn, add_btn;
    private LinearLayout item;
    private int cnt_title = 1;
    private TextView big_title;
    private TextView this_title, this_contents;
    private TextView n_title, n_contents;
    View write_sub;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_write_frag, container, false);
        initialize();

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
        conLinear = (LinearLayout) view.findViewById(R.id.item_con);
        back_btn = view.findViewById(R.id.wBack_btn);
        trans_btn = view.findViewById(R.id.wTrans_btn);
        add_btn = view.findViewById(R.id.wAdd_item);
        item = view.findViewById(R.id.wItem);
        big_title = view.findViewById(R.id.wTitle);
        this_title = view.findViewById(R.id.wN_title);
        this_contents = view.findViewById(R.id.wN_contents);
        intent = new Intent(getContext(), DetailWriteActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wBack_btn:
                view.setVisibility(View.INVISIBLE);
                break;

            case R.id.wTrans_btn:
                startActivity(new Intent(getContext(), Translate2Activity.class));
                break;

            case R.id.wAdd_item:
                createItem();
                break;

            case R.id.wItem:
            case R.id.wN_title:
            case R.id.wN_contents:
                startActivity(intent);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void createItem(){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layout_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700);
        layout_lp.setMargins(65, 40, 60, 50);
        layout.setLayoutParams(layout_lp);
        layout.setPadding(10, 10, 10, 10);
        layout.setElevation(20.0f);
        layout.setBackgroundResource(R.color.white);

        TextView n_title = new TextView(getContext());
        LinearLayout.LayoutParams n_title_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        n_title_lp.setMargins(50, 55, 60, 0);
        n_title.setLayoutParams(n_title_lp);
        n_title.setEms(10);
        n_title.setPadding(10, 15, 15, 15);
        n_title.setTextSize(13);
        n_title.setHint("항목을 적어주세요.");
        n_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        TextView n_contents = new TextView(getContext());
        LinearLayout.LayoutParams n_contents_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        n_contents_lp.setMargins(50, 55, 60, 0);
        n_contents.setLayoutParams(n_contents_lp);
        n_contents.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        n_contents.setEms(10);
        n_contents.setPadding(10, 5, 15, 15);
        n_contents.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        n_contents.setTextSize(13);
        n_contents.setHint("내용을 적어주세요.");
        n_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        layout.addView(n_title);
        layout.addView(n_contents);

        conLinear.addView(layout);
    }
}
