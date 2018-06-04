package prectice.jakir.com.jsonpars_jakir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SaveActivity extends AppCompatActivity {

    Button btn_back, btn_save, btn_reset;
    EditText stu_name, stu_email, age;
    String insertURL = "http://192.168.0.108/php_android/inserData.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        btn_back = (Button)findViewById(R.id.btn_bk);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_reset = (Button)findViewById(R.id.btn_reset);

        stu_name = (EditText)findViewById(R.id.stu_name);
        stu_email = (EditText)findViewById(R.id.stu_email);
        age  = (EditText)findViewById(R.id.age);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // save data by URL in Server





        // Btn Back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnBtn = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(returnBtn);
                System.out.print(stu_name.getText().toString());
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(stu_name.getText().toString(), "############################");
                Log.w(stu_email.getText().toString(), "############################");
                Log.w(age.getText().toString(), "############################");
                insertData();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearField();
            }
        });
    }


    private void insertData(){
        StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters  = new HashMap<String, String>();
                parameters.put("stu_name",stu_name.getText().toString());
                parameters.put("stu_email",stu_email.getText().toString());
                parameters.put("age", age.getText().toString());
                return parameters;
            }
        };
        requestQueue.add(request);
    }


    private void clearField(){
        stu_name.setText("");
        stu_email.setText("");
        age.setText("");
    }
}
