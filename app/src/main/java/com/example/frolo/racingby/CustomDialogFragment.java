package com.example.frolo.racingby;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

public class CustomDialogFragment extends DialogFragment {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Диалоговое окно")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.fragment_custom_dialog)
                .create();
    }
}
