package shrikant.com.quizapp.Adapter;

/**
 * Created by ShrikantIngole on 23-02-2018.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.ArrayList;

import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;

public class MultiQue_ViewPager extends  PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<QueList> result;
    private ViewPager vp;

    public MultiQue_ViewPager(Context context, ArrayList<QueList> data, ViewPager viewPager) {
        this.context = context;
        result=data;
        vp=viewPager;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_view_multi_question, null);
            onCreateData(view,position);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
    private void onCreateData(final View view, final int position)
    {
        TextView que = (TextView) view.findViewById(R.id.question);
        TextView cat1 = (TextView) view.findViewById(R.id.category1);
        TextView cat2 = (TextView) view.findViewById(R.id.category2);
        Button submit = (Button) view.findViewById(R.id.submit);
        RadioButton opt1=view.findViewById(R.id.a);
        RadioButton opt2=view.findViewById(R.id.b);
        RadioButton opt3=view.findViewById(R.id.c);
        RadioButton opt4=view.findViewById(R.id.d);
        opt1.setText(result.get(position).getOpt1());
        opt2.setText(result.get(position).getOpt2());
        opt3.setText(result.get(position).getOpt3());
        opt4.setText(result.get(position).getOpt4());
                que.setText((position+1)+"] "+result.get(position).getQue());
                cat1.setText("Main Category : "+result.get(position).getCat1());
                cat2.setText("Sub Category : "+result.get(position).getCat2());
        final RadioGroup radioGroup = view.findViewById(R.id.ragiogroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView ans = (TextView) view.findViewById(R.id.answer);
                ans.setText("Answer is : "+result.get(position).getAns());
                int id=radioGroup.getCheckedRadioButtonId();
                if (id==-1){
                    Toast.makeText(context, "Select Answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioButton opt;
                    opt = view.findViewById(id);

                    if (result.get(position).getAns().equals(opt.getHint()))
                        opt.setTextColor(Color.GREEN);
                    else
                        opt.setTextColor(Color.RED);
                    ExpandableLinearLayout el = view.findViewById(R.id.expandableLayout);
                    if (!el.isExpanded()) el.expand();
                }
            }
        });
        ImageView next = view.findViewById(R.id.next);
        ImageView pre = view.findViewById(R.id.pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                vp.arrowScroll(View.FOCUS_LEFT);
            }
        });
 next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (position+1==getCount()||position==getCount())
                Toast.makeText(context, "Last question", Toast.LENGTH_SHORT).show();
                vp.arrowScroll(View.FOCUS_RIGHT);
            }
        });





    }
}