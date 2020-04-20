package com.example.schoolbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button MarksBtn, ClassesBtn;
    TextView addColegButton, deleteAllColegiButton, helloTextView;
    ListView colegiListView;
    DatabaseHelper mDatabase = new DatabaseHelper(this);
    ArrayList<coleg> ColegiList = new ArrayList<>();
    RecyclerView FocusOnRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<FocusOnItem> FocusOnList = new ArrayList<>();
    ImageView settingsFocus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String last_name, first_name, email, label;
        int number;

        helloTextView = findViewById(R.id.bunaTextView);
        addColegButton = findViewById(R.id.textView3);
        deleteAllColegiButton = findViewById(R.id.textViewMinus);
        colegiListView=findViewById(R.id.ListView);
        deleteAllColegiButton = findViewById(R.id.textViewMinus);
        MarksBtn = findViewById(R.id.NoteButton);
        ClassesBtn = findViewById(R.id.OrarButton);
        FocusOnRecyclerView = findViewById(R.id.FocusOnRecyclerView);
        settingsFocus =findViewById(R.id.SettingsFocusImageView);

        //Set the helloTextView value
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 12 && hour <= 18) {
            helloTextView.setText(R.string.bună);
            helloTextView.setTextSize(43);
        }
        if (hour >= 18 && hour <= 24) {
            helloTextView.setText(R.string.bunăSeara);
            helloTextView.setTextSize(39);
        }
        if (hour >= 0 && hour <= 12) {
            helloTextView.setText(R.string.bunăDimi);
            helloTextView.setTextSize(36);
        }

        //Focus on settings
        settingsFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsDialog();
            }
        });


        //FocusOnRecyclerView
        //temporary items
        FocusOnList.add(new FocusOnItem("MAT","Good",4,"#FF7E00"));
        FocusOnList.add(new FocusOnItem("BIO","Bad",2,"#B0BF1A"));
        FocusOnList.add(new FocusOnItem("RO","Excellent",5,"#0048BA"));

        LinearLayoutManager layoutHorizontalManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        FocusOnRecyclerView.setLayoutManager(layoutHorizontalManager);
        mAdapter = new RecyclerViewFocusAdapter(FocusOnList);
        FocusOnRecyclerView.setAdapter(mAdapter);


        //fill the ColegiListView
        Cursor data = mDatabase.getData();
        while(data.moveToNext()){
            coleg coleg = new coleg(data.getString(1),data.getString(2),data.getString(3),Integer.parseInt(data.getString(4)));
            ColegiList.add(coleg);
        }

        //Declare ColegiList and set adapter
        final ColegListAdapter adapter = new ColegListAdapter(this, R.layout.list_item_layout, ColegiList);
        colegiListView.setAdapter(adapter);

        //Set the OnItemClick
        colegiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                coleg nameColeg = (coleg) parent.getItemAtPosition(position);
                String name = nameColeg.getName();
                Cursor data = mDatabase.getItemID(name);
                Log.i("You clicked on",name);
                int itemId = -1;
                while (data.moveToNext()){
                    itemId = data.getInt(0);
                }
                Intent intent = new Intent(MainActivity.this, ColegDataEditActivity.class);
                intent.putExtra("Id", itemId);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        //plusButton
        addColegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColegInfoActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        //Declare DeleteAllButton
        deleteAllColegiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.deleteClassmates);
                builder.setPositiveButton(R.string.positiveDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.deleteData();
                        Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton(R.string.negativeDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            }
        });

        MarksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarksActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        ClassesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClassesActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

    }

public void openSettingsDialog(){
        CustomDialogClass dialog = new CustomDialogClass();
        dialog.show(getSupportFragmentManager(),"Settings Dialog");
}

}

