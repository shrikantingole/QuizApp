package shrikant.com.quizapp.Admin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
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
import shrikant.com.quizapp.Model.QueList;
import shrikant.com.quizapp.R;

public class MultichoiceQuestion extends Activity {
    EditText question,opt1,opt2,opt3,opt4;
    RadioGroup option;
    RadioButton ans;
    Spinner category1,category2;
    ProgressBar pb;
    String Id="0";
    Button submit;
    String Url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichoice_question);
        Initial();
        UpdateQuestions();
    }

    private void UpdateQuestions()
    {
        if (!(getIntent().getSerializableExtra("question") ==null))
        {   submit.setText("Update");
            Url=new WebConfig().getUpdateMultique();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           // setSupportActionBar(toolbar);
            toolbar.setTitle("Update MultiChoice Que");
            QueList q = (QueList) getIntent().getSerializableExtra("question");
            question.setText(q.getQue());
            opt1.setText(q.getOpt1());
            opt2.setText(q.getOpt2());
            opt3.setText(q.getOpt3());
            opt4.setText(q.getOpt4());
            RadioButton Ans;
            switch (q.getAns())
            {
                case "A":
                    Ans=(RadioButton)findViewById(R.id.a);
                    Ans.setChecked(true);
                    break;
                case "B":
                    Ans=(RadioButton)findViewById(R.id.b);
                    Ans.setChecked(true);
                    break;
                case "C":
                    Ans=(RadioButton)findViewById(R.id.c);
                    Ans.setChecked(true);
                    break;
                case "D":
                    Ans=(RadioButton)findViewById(R.id.d);
                    Ans.setChecked(true);
                    break;
            }
            Id= String.valueOf(q.getId());

        }
        else {
            Url=new WebConfig().getAddMultique();
        }

    }

    private void Initial()
    {
        question=(EditText)findViewById(R.id.question);
        opt1=(EditText)findViewById(R.id.option1);
        opt2=(EditText)findViewById(R.id.option2);
        opt3=(EditText)findViewById(R.id.option3);
        opt4=(EditText)findViewById(R.id.option4);
        option=(RadioGroup)findViewById(R.id.ragiogroup);
        category1=(Spinner)findViewById(R.id.category1);
        category2=(Spinner)findViewById(R.id.category2);
        pb=(ProgressBar)findViewById(R.id.progressbar);
        submit=(Button)findViewById(R.id.submit);
    }

    public void submitQuestion(View view)
    {
                String que=question.getText().toString();
                String Opt1=opt1.getText().toString();
                String Opt2=opt2.getText().toString();
                String Opt3=opt3.getText().toString();
                String Opt4=opt4.getText().toString();
                    if (que.isEmpty())
                    {
                        Toast.makeText(this,"Enter Question...",Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                            int id=option.getCheckedRadioButtonId();
                            ans=(RadioButton)findViewById(id);
                            pb.setVisibility(View.VISIBLE);
                            submitQuestionAsyn(que,Opt1,Opt2,Opt3,Opt4, String.valueOf(ans.getText()));
                        }




    }
    private void  submitQuestionAsyn(final String que, final String opt1, final String opt2, final String opt3, final String opt4, final String ans)
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
                           Toast.makeText(MultichoiceQuestion.this, "Done", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                       else {
                           Toast.makeText(MultichoiceQuestion.this, "Try again...", Toast.LENGTH_SHORT).show();
                       }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {pb.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Try Again" +error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",Id);
                params.put("que",que);
                params.put("opt1",opt1);
                params.put("opt2",opt2);
                params.put("opt3",opt3);
                params.put("opt4",opt4);
                params.put("ans",ans);
                params.put("cat1",que);
                params.put("cat2",que);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
