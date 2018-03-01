package shrikant.com.quizapp.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import shrikant.com.quizapp.R;

public class Registration extends AppCompatActivity {
EditText fname,lname,mail,pass,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fname=(EditText)findViewById(R.id.Fname);
        lname=(EditText)findViewById(R.id.Lname);
        mail=(EditText)findViewById(R.id.Mail);
        pass=(EditText)findViewById(R.id.Password);
        address=(EditText)findViewById(R.id.Address);
    }

    public void Register(View view)
    {
        String Fname=fname.getText().toString().trim();
        String Lname=lname.getText().toString().trim();
        String Mail=mail.getText().toString().trim();
        String Pass=pass.getText().toString().trim();
        String Address=address.getText().toString().trim();
        if (Fname.isEmpty()||Lname.isEmpty()||Mail.isEmpty()||Pass.isEmpty()||Address.isEmpty())
        {
            Toast.makeText(this, "Fille All Field", Toast.LENGTH_SHORT).show();
        }
        else
            {
                        RegisterVolly(Fname,Lname,Mail,Pass,Address);
                Toast.makeText(this, "Wait...", Toast.LENGTH_SHORT).show();
        }
    }
    private void RegisterVolly(final String Fname, final String Lanme, final String Mail, final String Pass, final String Address)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, new WebConfig().getRegigtration(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")){
                            finish();
                        Toast.makeText(Registration.this, "Success", Toast.LENGTH_SHORT).show();}
                        else if (response.equals("2"))
                            Toast.makeText(Registration.this, "Already register", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(Registration.this, "Error try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),"Try Again" +error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("fname",Fname);
                params.put("lname",Lanme);
                params.put("mail",Mail);
                params.put("pass",Pass);
                params.put("address",Address);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
