package com.sealiu.memo.book;

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
import android.widget.Toast;

import com.sealiu.memo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBookDialogFragment extends DialogFragment {

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
                // print input-info
                EditText bookNameET = (EditText) view.findViewById(R.id.book_name);
                EditText bookDescET = (EditText) view.findViewById(R.id.book_desc);
                CheckBox status = (CheckBox) view.findViewById(R.id.is_active);

                String bookName = String.valueOf(bookNameET.getText());
                String bookDesc = String.valueOf(bookDescET.getText());
                boolean isActive = status.isChecked();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now = sdf.format(new Date());

                BookService bookService = new BookDao(getActivity());
                Object[] params = {
                        bookName,
                        bookDesc,
                        isActive,
                        now,
                        now,
                        ""
                };
                boolean flag = bookService.addBook(params);
                if (flag)
                    Toast.makeText(
                            getActivity(),
                            R.string.add_book_success_toast,
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(
                            getActivity(),
                            R.string.add_book_fail_toast,
                            Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton(R.string.negative_btn, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                AddBookDialogFragment.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

}
