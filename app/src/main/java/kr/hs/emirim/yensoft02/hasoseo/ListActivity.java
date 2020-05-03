package kr.hs.emirim.yensoft02.hasoseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<String> mData;
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ImageButton add_btn = (ImageButton)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WriteActivity.class);
                startActivity(intent);
            }
        });
        //1. 데이터 준비
        initData();

        //2. Adapter 준비
        initAdapter();

        //3. ListView에 Adapter 장착
        initListView();
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
        mAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,mData);

    }

    private void initListView()
    {
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);
    }

}
