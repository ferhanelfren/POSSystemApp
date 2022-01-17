package com.example.pos;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragmentSecondPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragmentSecondPage extends Fragment {

    DataConnect myDB;
    ArrayList<String> items_user= new ArrayList<String>();
    ArrayAdapter adapter;
    ListView userList;
    private Context mContext;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragmentSecondPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragmentSecondPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragmentSecondPage newInstance(String param1, String param2) {
        CategoryFragmentSecondPage fragment = new CategoryFragmentSecondPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_category_second_page, container, false);

        mContext = getActivity();
        myDB = new DataConnect(mContext);

        items_user = new ArrayList < > ();













        return v;
    }

    private void viewData() {
        Cursor cursor = myDB.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                items_user.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter < > (getActivity(), android.R.layout.simple_list_item_1, items_user);
            userList.setAdapter(adapter);
        }
    }



    public void load(){


        myDB = DataConnect.getInstance(getActivity());
        final Cursor c = myDB.viewData();

        //  final Cursor c = DB.rawQuery("select * from usernames_samples", null);
        int id = c.getColumnIndex("id");
        int Lnames = c.getColumnIndex("lastname");
        int Fnames = c.getColumnIndex("firstname");
        int Mnames = c.getColumnIndex("middlename");

        items_user.clear();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items_user);
        userList.setAdapter(adapter);
        final ArrayList<UsernamesSamples> usernamessampless = new ArrayList<UsernamesSamples>();
        if(c.moveToFirst())
        {

            do{

                UsernamesSamples us = new UsernamesSamples();
                us.id = c.getString(id);
                us.lastname = c.getString(Lnames);
                us.firstname = c.getString(Fnames);
                us.middlename = c.getString(Mnames);
                usernamessampless.add(us);
                items_user.add(c.getString(id) + "\t" + c.getString(Lnames) + "\t"+ c.getString(Fnames) + "\t" + c.getString(Mnames) );


            }while(c.moveToNext());
            adapter.notifyDataSetChanged();
            userList.invalidateViews();

        }




    }

}