package shrikant.com.quizapp.Admin;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shrikant.com.quizapp.Adapter.Desc_AdapterClass;
import shrikant.com.quizapp.Adapter.Multi_AdapterClass;
import shrikant.com.quizapp.Config.WebConfig;
import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;
import shrikant.com.quizapp.Student.ViewDescQuestion;
import shrikant.com.quizapp.Student.ViewMultiQuestions;

public class ViewMultiList extends Fragment
{
    ListView viewQue;
    ProgressBar pb;
    ArrayList<QueList> result;
    Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_view_desc_list, container, false);
        c=getActivity();
        viewQue= v.findViewById(R.id.list);
        pb= v.findViewById(R.id.progressbar);
        if (pb.getVisibility()== View.VISIBLE)pb.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        viewQue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (new SharePref(getActivity()).getType().equals("Admin"))
                {
                    startActivity(new Intent(getActivity(),MultichoiceQuestion.class)
                            .putExtra("question",result.get(position)));
                }else {
                    startActivity(new Intent(getActivity(), ViewMultiQuestions.class)
                            .putExtra("position", position)
                            .putExtra("question", result));
                }
            }
        });
        getQueVolly();
        return  v;
    }
    private void getQueVolly()
    {
        Toast.makeText(c, "Wait..", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, new WebConfig().getAllMultiQue(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pb.setVisibility(View.GONE);
                        if(response.equals("0")||response.equals("2"))
                        {
                            Toast.makeText(c, "getting Data error", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            QueList model;
                            result=new ArrayList<>();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for(int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                                    model=new QueList();
                                    model.setId(jsonObj.getString("Id"));
                                    model.setQue(jsonObj.getString("Question"));
                                    model.setAns(jsonObj.getString("Answer"));
                                    model.setOpt1(jsonObj.getString("Option1"));
                                    model.setOpt2(jsonObj.getString("Option2"));
                                    model.setOpt3(jsonObj.getString("Option3"));
                                    model.setOpt4(jsonObj.getString("Option4"));
                                    model.setCat1(jsonObj.getString("Category1"));
                                    model.setCat2(jsonObj.getString("Category2"));
                                    result.add(model);
                                }
                                viewQue.setAdapter(new Multi_AdapterClass(c,result));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(c, "Error try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pb.setVisibility(View.GONE);
                        Toast.makeText(c,"Try Again" +error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(stringRequest);
    }

}