package alin.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "MainActivity";
    static final int RESULT_LOGIN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    public void onClick(View v) {

        Log.d(TAG, "onClick");
        switch( v.getId() )
        {
            case R.id.login:
                Log.d(TAG,"Login");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent,RESULT_LOGIN);
                break;
            case R.id.register:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                Log.d(TAG,"Register");
                break;
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (requestCode == RESULT_LOGIN && resultCode == RESULT_OK)
        {
            Intent mainPageIntent = new Intent(MainActivity.this, MainPageActivity.class);
            startActivity(mainPageIntent);
            finish();
        }
    }
}
