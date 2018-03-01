package shrikant.com.quizapp.Adapter;

/**
 * Created by ShrikantIngole on 23-02-2018.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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

public class DescType_ViewPager extends  PagerAdapter
{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<QueList> result;
    private ViewPager vp;

    public DescType_ViewPager(Context context, ArrayList<QueList> data, ViewPager viewPager) {
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
        View view = layoutInflater.inflate(R.layout.activity_view_desc_question, null);
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
        TextView ans = (TextView) view.findViewById(R.id.answer);
        TextView cat1 = (TextView) view.findViewById(R.id.cat1);
        TextView cat2 = (TextView) view.findViewById(R.id.cat2);
        final ExpandableLinearLayout slide = (ExpandableLinearLayout) view.findViewById(R.id.expandableLayout);
        CardView showans = view.findViewById(R.id.shownas);
        showans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        slide.toggle();
            }
        });

        que.setText((position+1)+"] "+result.get(position).getQue());
                ans.setText(result.get(position).getAns());
                cat1.setText("Main Category : "+result.get(position).getCat1());
                cat2.setText("Sub Category : "+result.get(position).getCat2());

        ImageView next = view.findViewById(R.id.next);
        ImageView pre = view.findViewById(R.id.pre);


        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                vp.arrowScroll(View.FOCUS_LEFT);
            }
        });
        next.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (position+1==getCount()||position==getCount())
                Toast.makeText(context, "Last question", Toast.LENGTH_SHORT).show();
                vp.arrowScroll(View.FOCUS_RIGHT);}
        });
    }
}