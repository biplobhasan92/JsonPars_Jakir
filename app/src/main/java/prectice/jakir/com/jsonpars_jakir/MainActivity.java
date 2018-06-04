package prectice.jakir.com.jsonpars_jakir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    Button btn_go_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view);
        Button buttonPrse = findViewById(R.id.btn_prse);
        btn_go_save =findViewById(R.id.btn_go_save);

        mQueue = Volley.newRequestQueue(this);

        buttonPrse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonPare();
            }
        });

        btn_go_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SaveActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void jsonPare(){
        String url = "https://api.myjson.com/bins/kp9wz";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("employees");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject student = jsonArray.getJSONObject(i);

                        String firstname = student.getString("firstname");
                        String mail      = student.getString("mail");
                        int age          = student.getInt("age");
                        mTextViewResult.append(firstname+", "+String.valueOf(age)+", "+mail+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
