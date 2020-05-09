package kr.hs.emirim.yensoft02.hasoseo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class List_frag extends Fragment {

    private View view; // activity_write의 View 객체 변수
    private List<String> mData;
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;
    private Write_frag wf;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_frag, container, false);
        wf = new Write_frag();

        ImageButton add_btn = (ImageButton)view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(wf);
            }
        });
        //1. 데이터 준비
        initData();

        //2. Adapter 준비
        initAdapter();

        //3. ListView에 Adapter 장착
        initListView();

        return view;
    }

    private void initData()
    {
        mData = new ArrayList<>();
        for(int i=1; i<=15; i++)
        {
            mData.add("자기소개서"+i);
        }
    }

    private void initAdapter()
    {
        mAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,mData);

    }

    private void initListView()
    {
        mListView = (ListView)view.findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);
    }

}
