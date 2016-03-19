package alin.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.AbstractMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LoginActivity";
    public HashMap<String, String> map = new HashMap<>();
    SharedPreferences shP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_button).setOnClickListener(this);
        map.put("alin.corodescu@gmail.com", "123456");
        map.put("dianarioana@gmail.com", "654321");

        shP = getSharedPreferences("users", 0);
        String savedUser = shP.getString("username", null);
        if (savedUser != null)
            ((EditText) findViewById(R.id.etmail)).setText(savedUser);

        String savedPass = shP.getString("password", null);
        if (savedPass != null)
            ((EditText) findViewById(R.id.etpass)).setText(savedPass);

    }

    public void onClick(View v) {
        String email = ((EditText) findViewById(R.id.etmail)).getText().toString();
        String pass = ((EditText) findViewById(R.id.etpass)).getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(LoginActivity.this, "Email or password empty", Toast.LENGTH_LONG).show();
        } else {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            dialogBuilder.setTitle(getResources().getString(R.string.dialog_title));
            dialogBuilder.setMessage(getResources().getString(R.string.dialog_message));
            dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (checkInput() == true) {
                        SharedPreferences.Editor editor = shP.edit();
                        String email = ((EditText) findViewById(R.id.etmail)).getText().toString();
                        String pass = ((EditText) findViewById(R.id.etpass)).getText().toString();
                        editor.putString("username", email);
                        editor.putString("password", pass);
                        editor.commit();
                        setResult(RESULT_OK);
                        finish();
                    } else
                        dialog.dismiss();
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialogBuilder.show();
        }
    }

    public boolean checkInput() {
        String email = ((EditText) findViewById(R.id.etmail)).getText().toString();
        String pass = ((EditText) findViewById(R.id.etpass)).getText().toString();
        if (!isEmail(email)) {
            Toast.makeText(LoginActivity.this, "Invalid email format", Toast.LENGTH_LONG).show();
            return false;
        }
        if (map.containsKey(email) && map.get(email).equals(pass))
            return true;
        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
        return false;
    }

    public boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
        Matcher matcher = pattern.matcher(email.toUpperCase());
        if (matcher.matches())
            return true;
        return false;
    }
}