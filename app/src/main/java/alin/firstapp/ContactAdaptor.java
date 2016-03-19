package alin.firstapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alinc on 3/19/2016.
 */
public class ContactAdaptor extends BaseAdapter {
    ArrayList<Contact> list;
    Activity context ;
    public ContactAdaptor(ArrayList<Contact> list,Context context)
    {
        this.list = list;
        this.context=(Activity)context;
    }
    public int getCount()
    {
        return list.size();
    }
    public Object getItem(int position)
    {
        return list.get(position);
    }
    public long getItemId(int position)
    {
        return 0;
    }
    public View getView(int position,View convertView,ViewGroup parent)
    {
        LayoutInflater LLi = LayoutInflater.from(context);
        View v = LLi.inflate(R.layout.list_item_layout,parent,false);
        TextView nume = (TextView) v.findViewById(R.id.nume);
        TextView prenume = (TextView) v.findViewById(R.id.prenume);
        TextView numar = (TextView) v.findViewById(R.id.numar_telefon);

        nume.setText(list.get(position).nume);
        prenume.setText(list.get(position).prenume);
        numar.setText(list.get(position).phone);

        return v;
    }

}
