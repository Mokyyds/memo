package com.sealiu.memo.book;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sealiu.memo.R;

public class AddBookDialogFragment extends DialogFragment {

    public interface AddBookDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String name, String desc, boolean status);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    AddBookDialogListener aListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            aListener = (AddBookDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddBookDialogListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow()
                    .setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_add_book, null);

        builder.setView(view);

        // Add action buttons
        builder.setPositiveButton(R.string.positive_btn, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText bookNameET = (EditText) view.findViewById(R.id.book_name);
                EditText bookDescET = (EditText) view.findViewById(R.id.book_desc);
                CheckBox status = (CheckBox) view.findViewById(R.id.is_active);

                String bookName = String.valueOf(bookNameET.getText());
                String bookDesc = String.valueOf(bookDescET.getText());
                boolean isActive = status.isChecked();

                AddBookDialogListener listener = (AddBookDialogListener) getActivity();
                listener.onDialogPositiveClick(
                        AddBookDialogFragment.this,
                        bookName,
                        bookDesc,
                        isActive
                );
            }
        }).setNegativeButton(R.string.negative_btn, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                AddBookDialogListener listener = (AddBookDialogListener) getActivity();
                listener.onDialogNegativeClick(AddBookDialogFragment.this);
            }
        });
        return builder.create();
    }

}
