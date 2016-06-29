package com.sealiu.memo.note;

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
import android.widget.EditText;
import android.widget.TextView;

import com.sealiu.memo.R;
import com.sealiu.memo.book.Service.BookService;
import com.sealiu.memo.book.Service.Impl.BookDao;
import com.sealiu.memo.book.model.Book;

public class AddNoteDialogFragment extends DialogFragment {

    private static final String TAG = "AddNoteDialogFragment";

    public interface AddNoteDialogListener {
        void onAddNoteDialogPositiveClick(DialogFragment dialog, String front, String back, String targetBook);

        void onAddNoteDialogNegativeClick(DialogFragment dialog);
    }

    AddNoteDialogListener aListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            aListener = (AddNoteDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddNoteDialogListener");
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

        final EditText frontET = (EditText) view.findViewById(R.id.front);
        final EditText backET = (EditText) view.findViewById(R.id.back);
        final TextView targetBookTV = (TextView) view.findViewById(R.id.target_book);

        // 检测是否已经存在 status == 1 的词库
        BookService bookService = new BookDao(getActivity());
        final Book activeBook = bookService.queryBook(true, null, "status = ?", new String[]{"1"}, null, "1");
        if (activeBook == null) {
            targetBookTV.setText(R.string.no_activeBook_info);
        } else {
            targetBookTV.setText(activeBook.getName());
        }

        // Add action buttons
        builder.setPositiveButton(R.string.positive_btn, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {

                String front = String.valueOf(frontET.getText());
                String back = String.valueOf(backET.getText());

                AddNoteDialogListener listener = (AddNoteDialogListener) getActivity();
                listener.onAddNoteDialogPositiveClick(
                        AddNoteDialogFragment.this,
                        front == null || front.equals("") ? "" : front,
                        back == null || back.equals("") ? "" : back,
                        activeBook == null ? "" : activeBook.getUuid()
                );
            }
        }).setNegativeButton(R.string.negative_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddNoteDialogListener listener = (AddNoteDialogListener) getActivity();
                listener.onAddNoteDialogNegativeClick(AddNoteDialogFragment.this);
            }
        });

        return builder.create();
    }
}
