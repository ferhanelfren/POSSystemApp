package com.example.pos;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragmentFirstPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragmentFirstPage extends Fragment {

    EditText firstname_EditText, lastname_EditText,midname_EditText;
    Button savebutton, updatebutton, deletebutton, viewdatabutton;
    DBClass DB;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CategoryFragmentFirstPage() {
        // Required empty public constructor

       // insert();

    }






    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragmentFirstPage.
     */





    // TODO: Rename and change types and number of parameters
    public static CategoryFragmentFirstPage newInstance(String param1, String param2) {
        CategoryFragmentFirstPage fragment = new CategoryFragmentFirstPage();
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
        View v = inflater.inflate(R.layout.fragment_category_first_page, container, false);





        savebutton = (Button) v.findViewById(R.id.save);
        lastname_EditText = (EditText) v.findViewById(R.id.lname);
        firstname_EditText = (EditText) v.findViewById(R.id.fname);
        midname_EditText = (EditText) v.findViewById(R.id.mname);

        savebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
            }

        });






        return v;
    }








}


