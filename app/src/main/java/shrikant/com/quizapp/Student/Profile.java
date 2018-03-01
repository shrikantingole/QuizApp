package shrikant.com.quizapp.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.R;

public class Profile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView name= (TextView) findViewById(R.id.name);
        TextView mail= (TextView) findViewById(R.id.mail);
        TextView address= (TextView) findViewById(R.id.address);
        TextView type= (TextView) findViewById(R.id.type);
        SharePref data=new SharePref(this);
        type.setText(data.getType());
        name.setText(data.getFname()+" "+data.getLname());
        mail.setText(data.getMail());
        address.setText(data.getAddress());


    }
}
