package com.example.schoolbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CustomDialogClass extends AppCompatDialogFragment {
    public Dialog d;
    public TextView bad, reallyBad, good,stable, cancel,save;

   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

       LayoutInflater inflater = getActivity().getLayoutInflater();
       View view = inflater.inflate(R.layout.custom_focus_on_settings_dialog,null);

       builder.setView(view);

       bad = view.findViewById(R.id.BadChoice);
       reallyBad = view.findViewById(R.id.ReallyBadChoice);
       good = view.findViewById(R.id.GoodChoice);
       stable = view.findViewById(R.id.StableChoice);
       cancel = view.findViewById(R.id.Cancel);
       save = view.findViewById(R.id.Save);


       return  builder.create();
   }
}

//TODO:finish dialog

/*
 public Activity c;
    public Dialog d;
    public TextView bad, reallyBad, good,stable, cancel,save;

    public CustomDialogClass(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_focus_on_settings_dialog);
        bad = findViewById(R.id.BadChoice);
        reallyBad = findViewById(R.id.ReallyBadChoice);
        good = findViewById(R.id.GoodChoice);
        stable = findViewById(R.id.StableChoice);
        cancel = findViewById(R.id.Cancel);
        save = findViewById(R.id.Save);

        bad.setOnClickListener(this);
        reallyBad.setOnClickListener(this);
        good.setOnClickListener(this);
        stable.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

 */