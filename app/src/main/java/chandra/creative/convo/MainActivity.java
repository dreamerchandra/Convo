    package chandra.creative.convo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText entered,entered2;
    Spinner enter,e2,result_spinner;
    TextView result;
    TextView info;
    int eid,eid2,rid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoo();

        init();

        foo();
        entered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                Double e1,e2,ans;
                if(editable.length()!=0)
                    e1=ans(Double.parseDouble(editable.toString()),eid,rid);
                else
                    e1=0.0d;
                if(entered2.getText().length()!=0)
                    e2=ans(Double.parseDouble(entered2.getText().toString()),eid,rid);
                else
                    e2=0.0d;
                ans=e1+e2;
                result.setText(ans.toString());
            }
        });
        entered2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                Double e1,e2,ans;
                if(entered.getText().length()!=0)
                    e1=ans(Double.parseDouble(entered.getText().toString()),eid,rid);
                else
                    e1=0.0d;
                if(editable.length()!=0)
                    e2=ans(Double.parseDouble(editable.toString()),eid,rid);
                else
                    e2=0.0d;
                ans=e1+e2;
                result.setText(ans.toString());
            }
        });

    }
    void hoo(){
        this.getSupportActionBar().setTitle("CONVO");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
    }
    void init(){
        entered = findViewById(R.id.num);
        entered2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);
        enter = findViewById(R.id.enter_item);
        result_spinner = findViewById(R.id.result_item);
        e2 = findViewById(R.id.val2);

        ArrayAdapter data = ArrayAdapter.createFromResource(this,
                R.array.unit_array,
                android.R.layout.simple_spinner_item);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enter.setAdapter(data);
        result_spinner.setAdapter(data);
        e2.setAdapter(data);

        enter.setOnItemSelectedListener(this);
        result_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.contains("Cm")){rid=1;}
                else if(item.contains("Ft")){rid=3;}
                else if(item.contains("In")){rid=2;}
                else {rid=4;}
                Double e1,e2,ans;
                if(entered.getText().length()!=0)
                    e1=ans(Double.parseDouble(entered.getText().toString()),eid,rid);
                else
                    e1=0.0d;
                if(entered2.getText().length()!=0)
                    e2=ans(Double.parseDouble(entered2.getText().toString()),eid2,rid);
                else
                    e2=0.0d;
                ans=e1+e2;
                result.setText(ans.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                rid=1;
            }
        });
        e2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.contains("Cm")){eid2=1;}
                else if(item.contains("Ft")){eid2=3;}
                else if(item.contains("In")){eid2=2;}
                else {eid2=4;}
                Double e1,e2,ans;
                if(entered.getText().length()!=0)
                    e1=ans(Double.parseDouble(entered.getText().toString()),eid,rid);
                else
                    e1=0.0d;
                if(entered2.getText().length()!=0)
                    e2=ans(Double.parseDouble(entered2.getText().toString()),eid2,rid);
                else
                    e2=0.0d;
                ans=e1+e2;
                result.setText(ans.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            String item = parent.getItemAtPosition(position).toString();
            if(item.contains("Cm")){eid=1;}
            else if(item.contains("Ft")){eid=3;entered2.setVisibility(View.INVISIBLE);}
            else if(item.contains("In")){eid=2;entered2.setVisibility(View.INVISIBLE);}
            else if(item.contains("M")){eid=4;entered2.setVisibility(View.INVISIBLE);}
            else {eid=5;entered2.setVisibility(View.VISIBLE);eid2=2;}
            Double e1,e2,ans;
            if(entered.getText().length()!=0)
                e1=ans(Double.parseDouble(entered.getText().toString()),eid,rid);
            else
                e1=0.0d;
            if(entered2.getText().length()!=0)
                e2=ans(Double.parseDouble(entered2.getText().toString()),eid2,rid);
            else
                e2=0.0d;
            ans=e1+e2;
            result.setText(ans.toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            eid=1;
        }
        Double ans(Double num,int eid,int rid){
            Double re_num=0.0;
            switch (eid){
                case 1:
                    switch (rid){
                        case 1:
                            re_num = num;
                            break;
                        case 2:
                            re_num=num*0.393701;
                            break;
                        case 3:
                            re_num=num*0.0328084;
                            break;
                        case 4:
                            re_num=num*0.01;
                            break;
                    }
                    break;
                case 2:
                    switch (rid){
                        case 1:
                            re_num=num*2.54;
                            break;
                        case 2:
                            re_num = num*1;
                            break;
                        case 3:
                            re_num=num*0.0833333;
                            break;
                        case 4:
                            re_num=num*0.0254;
                            break;
                    }
                    break;
                case 3:
                    switch (rid){
                        case 1:
                            re_num =num*30.48;
                            break;
                        case 2:
                            re_num =num*12;
                            break;
                        case 3:
                            re_num=num*1;
                            break;
                        case 4:
                            re_num=num*0.3048;
                            break;
                    }
                    break;
                case 4:
                    switch (rid){
                        case 1:
                            re_num =num*100;
                            break;
                        case 2:
                            re_num =num*39.3701;
                            break;
                        case 3:
                            re_num =num*3.28084;
                            break;
                        case 4:
                            re_num=num*1;
                            break;
                    }
                    break;
            }
            return re_num;
        }
        public void foo(){
            info = findViewById(R.id.mail);
            info.setText(R.string.info);
        }
    }
