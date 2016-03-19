package alin.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by alinc on 3/17/2016.
 */
public class MainPageActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        ArrayList<Contact> list = new ArrayList<>();
        list.add(new Contact("Corodescu","Alin","0747497321"));
        list.add(new Contact("skmdk","Adsan","1234"));
        list.add(new Contact("Csamfka","safa","055"));

        ( (ListView)  findViewById(R.id.lista) ).setAdapter(new ContactAdaptor(list,MainPageActivity.this));
    }
}
