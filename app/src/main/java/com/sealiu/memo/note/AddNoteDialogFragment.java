package com.sealiu.memo.note;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sealiu.memo.R;

public class AddNoteDialogFragment extends DialogFragment {

    private static final String TAG = "AddNoteDialogFragment";

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow()
                    .setLayout((int) (dm.widthPixels * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_add_note, null);

        builder.setView(view);
        // Add action buttons
        builder.setPositiveButton(R.string.positive_btn, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                // print input-info
                EditText frontET = (EditText) view.findViewById(R.id.front);
                EditText backET = (EditText) view.findViewById(R.id.back);

                String front = String.valueOf(frontET.getText());
                String back = String.valueOf(backET.getText());

                Log.i(TAG, front + " " + back);
            }
        })
                .setNegativeButton(R.string.negative_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddNoteDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
