package com.example.ahmedaldulaimy.mysmartusc;


import android.widget.Button;
import android.app.Notification;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    public String removedItem = "";

   public MyCustomAdapter(){}

//    public MyCustomAdapter(ArrayList<String> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }

    public void SetList(ArrayList<String> list){
       this.list = list;
    }

    public void SetContext(Context context){
       this.context = context;
    }

    public String GetRemovedItem(){
       return removedItem;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    private SQLiteDatabase sqlDb;

    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_list_item, null);
        }

        //sqlDb.execSQL("DELETE FROM " +TABLE_NAME+ " WHERE _id= "+ 1 );
        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //do something

                removedItem = list.get(position);
                // currently just removesfrom the list but doesnt remove from the DB
                list.remove(position); //or some other
                System.out.println("Position is " + position);
                System.out.println("Removed Item is  " + removedItem);
                //sqlDb.execSQL("DELETE FROM " +TABLE_NAME+ " WHERE ID="+ position );

                notifyDataSetChanged();

            }
        });
//        addBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //do something
//                notifyDataSetChanged();
//            }
//        });

        return view;
    }
}
