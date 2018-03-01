package shrikant.com.quizapp.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import shrikant.com.quizapp.Config.WebConfig;
import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;

public class Descriptive_Question extends AppCompatActivity {
    EditText que,ans;
    Spinner cat1,cat2;
    ProgressBar pb;
    String Url="";
    Button submit;
    String Id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptive__question);
        que=(EditText)findViewById(R.id.question);
        ans=(EditText)findViewById(R.id.answer);
        cat1=(Spinner) findViewById(R.id.category1);
        cat2=(Spinner) findViewById(R.id.category2);
        submit=(Button)findViewById(R.id.submit);
        pb=(ProgressBar)findViewById(R.id.progressbar);
        if (pb.getVisibility()==View.VISIBLE)
            pb.setVisibility(View.INVISIBLE);
            UpdateQuestion();
    }

    private void UpdateQuestion()
    {
        if (!(getIntent().getSerializableExtra("question") ==null))
        {   submit.setText("Update");
            Url=new WebConfig().getUpdateDescQue();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("Update Descriptive Que");
            findViewById(R.id.submit);
            QueList q = (QueList) getIntent().getSerializableExtra("question");
            que.setText(q.getQue());
            ans.setText(q.getAns());
            Id= String.valueOf(q.getId());

        }
        else {
            Url=new WebConfig().getAddDescQue();
        }


    }

    public void Submit(View view)
    {
        String Que=que.getText().toString();
        String Ans=ans.getText().toString();
        if (Que.isEmpty()||Ans.isEmpty())
        {
            Toast.makeText(this, "Fill All Field", Toast.LENGTH_SHORT).show();
        }
        else
        {
            submitQuestionAsyn(Que,Ans,Id);
            pb.setVisibility(View.VISIBLE);
        }
    }
    private void  submitQuestionAsyn(final String que, final String ans, final String id)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pb.setVisibility(View.GONE);
                        if (response.equals("1"))
                        {
                            Toast.makeText(Descriptive_Question.this, "Done", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(Descriptive_Question.this, "Try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pb.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Try Again" +error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("que",que);
                params.put("ans",ans);
                params.put("cat1","lll");
                params.put("cat2","lll");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
