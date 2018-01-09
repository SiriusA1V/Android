package kr.ac.yjc.wdj.cyh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button button, button2, button3;
    TextView textView;
    LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        button = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView30);
        button2 = (Button)findViewById(R.id.button6);
        button3 = (Button)findViewById(R.id.button7);
        lin = (LinearLayout)findViewById(R.id.lin);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button.getId()){
            String str = editText.getText().toString();

            if("1111".compareTo(str) == 0){
                button.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
            }else{
                finish();
            }
        }else if(v.getId() == button2.getId()){
            lin.setVisibility(View.VISIBLE);
        }else if(v.getId() == button3.getId()){
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
        }
    }
}
