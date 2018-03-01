package shrikant.com.quizapp.Student;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import shrikant.com.quizapp.Adapter.DescType_ViewPager;
import shrikant.com.quizapp.Adapter.MultiQue_ViewPager;
import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;

public class ViewDescQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Descriptive Questions");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<QueList> m =  (ArrayList<QueList>)getIntent().getSerializableExtra("question");

        viewPager.setAdapter(new DescType_ViewPager(this,m,viewPager));
        viewPager.setCurrentItem(getIntent().getExtras().getInt("position"));
    }
}
