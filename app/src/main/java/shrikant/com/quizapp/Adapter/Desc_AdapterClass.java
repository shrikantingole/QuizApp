package shrikant.com.quizapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;

/**
 * Created by ShrikantIngole on 20-02-2018.
 */
public class Desc_AdapterClass extends BaseAdapter
{
    private static ArrayList<QueList> statusDataList;
    Context c;
    private LayoutInflater mInflater;

    public Desc_AdapterClass(Context context, ArrayList<QueList> results)
    {
        c=context;
        statusDataList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return statusDataList.size();
    }

    public Object getItem(int position) {
        return statusDataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.questionlistitem, null);
            holder = new ViewHolder();
            holder.Que= (TextView) convertView.findViewById(R.id.question);
            holder.Cat1= (TextView) convertView.findViewById(R.id.cat1);
            holder.Cat2 = (TextView) convertView.findViewById(R.id.cat2);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        String que= statusDataList.get(position).getQue();
        String cat1= statusDataList.get(position).getCat1();
        String cat2= statusDataList.get(position).getCat2();

        holder.Que.setText(que);
        holder.Cat1.setText("Main Category : "+cat1);
        holder.Cat2.setText("Sub Category : "+cat2);
        return convertView;
    }

    private static class ViewHolder {
        TextView Que,Cat1,Cat2;
    }
}