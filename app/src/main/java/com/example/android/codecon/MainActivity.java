package com.example.android.codecon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    int x;
    int e;
    int q=0;
    String set="";
    RadioButton en;
    RadioButton de;
    RadioGroup rgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=findViewById(R.id.reset);
        rgroup=(RadioGroup) findViewById(R.id.radiogroup);
        b.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        rgroup.clearCheck();
        EditText clr=(EditText)findViewById(R.id.textField);
        clr.setText("");
        EditText a=(EditText)findViewById(R.id.el);
        a.setText("");
        a.setHint("Thank You for using our app!");
        a.setTextColor(getResources().getColor(R.color.bg));
        e=0;
        q=0;
        TextView r=(TextView)findViewById(R.id.outputArea);
        r.setText("ENCODED/DECODED Text to be displayed here");
    }

    public void submit(View view)
    {
        q=q+1;
        if(q==2)
        {
            EditText ab=(EditText)findViewById(R.id.el);
            ab.setHint("Enter 7 digit encryption code");
            ab.setTextColor(this.getResources().getColor(R.color.border));
        }
        if(q>2)
        {
            set="";
            String done = conversion();
            TextView conv = (TextView) findViewById(R.id.outputArea);
            conv.setText(""+done);
        }
    }


    public void clear(View view)
    {
        EditText clr=(EditText)findViewById(R.id.textField);
        clr.setText("");
    }

    public int el()
    {
        EditText a=(EditText)findViewById(R.id.el);
        String v=a.getText().toString();
        int len=v.length();
        for(int i=0;i<len;i++)
        {
            char c = v.charAt((i+1)/2);
            e=Integer.parseInt(String.valueOf(c));
        }
        for(int i=0;i<e;i++)
        {
            if (i >= 4)
            {
                e=e-(i+1);
                break;
            }
        }
        return e;
    }

    public String conversion()
    {
        en=(RadioButton)findViewById(R.id.ENCODE);
        de=(RadioButton)findViewById(R.id.DECODE);
        boolean ch1=en.isChecked();
        boolean ch2=de.isChecked();
        if(ch1==true)
        {
            set=encode(set);
        }
        else if(ch2==true)
        {
            set=decode(set);
        }
        return set;
    }


    public String encode(String enc)
    {
        int e=el();
        EditText tv=(EditText)findViewById(R.id.textField);
        String a=tv.getText().toString();
        int l=a.length();

        for (int i = 0; i < l; i++)
        {
            char c = a.charAt(i);
            int asc = (int) c;
            if (asc == 32)
            {
                x = asc + 2;
            }
            else if (asc >= 33 && asc <= 170)
            {
                x = asc + (int) Math.pow(2, e);
            }
            char fnl = (char) x;
            enc = enc + fnl;
        }
        return enc;
    }


    public String decode(String dec)
    {
        int e=el();
        EditText tv=(EditText) findViewById(R.id.textField);
        String g=tv.getText().toString();
        int l=g.length();
        for (int i = 0; i < l; i++)
        {
            char c = g.charAt(i);
            int asc = (int) c;
            if (asc == 34)
            {
                x = asc - 2;
            }
            else if (asc >= 33 && asc <= 170)
            {
                x = asc - (int) Math.pow(2, e);
            }
            char fnl = (char) x;
            dec = dec + fnl;
        }
        return dec;
    }
}