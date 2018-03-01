package shrikant.com.quizapp.Admin;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import shrikant.com.quizapp.Config.WebConfig;
import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.R;
import shrikant.com.quizapp.Student.StudentDahboard;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void Login(View view)
    {
        EditText mail=(EditText)findViewById(R.id.mail);
        EditText pass=(EditText)findViewById(R.id.pass);
        String Mail=mail.getText().toString();
        String Pass=pass.getText().toString();
        if (Mail.isEmpty()||Pass.isEmpty())
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        else LoginVolly(Mail,Pass);
    }
    public void Register(View view)
    {
        startActivity(new Intent(this,Registration.class));
    }
    private void LoginVolly(final String Mail, final String Pass)
    {
        Toast.makeText(this, "Wait..", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, new WebConfig().getLogin(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")) {
                            Toast.makeText(LoginPage.this, "Username or Password wrong", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for(int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                                        new SharePref(getApplicationContext()).setLogin(jsonObj.getString("Id"),jsonObj.getString("FirstName"),
                                                jsonObj.getString("LastName"),jsonObj.getString("Mail"),jsonObj.getString("Address"),
                                                jsonObj.getString("Type"));
                                    if(jsonObj.getString("Type").equals("Admin"))
                                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                    else if(jsonObj.getString("Type").equals("Student"))
                                        startActivity(new Intent(getApplicationContext(),StudentDahboard.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LoginPage.this, "Error try again", Toast.LENGTH_SHORT).show();
                            }
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
                params.put("mail",Mail);
                params.put("pass",Pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
