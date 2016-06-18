package com.sealiu.memo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sealiu.memo.book.AddBookDialogFragment;
import com.sealiu.memo.note.AddNoteDialogFragment;

/**
 * Created by root
 * on 6/18/16.
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FloatingActionButton libFab = (FloatingActionButton) view.findViewById(R.id.lib_add_fab);
        libFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddBookDialogFragment()
                        .show(getFragmentManager(), "addBook_dialog_fragment");
            }
        });

        // FloatingActionButton
        FloatingActionButton noteFab = (FloatingActionButton) view.findViewById(R.id.note_add_fab);
        noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteDialogFragment().show(getFragmentManager(), "addNote_dialog_fragment");
            }
        });

        Button moreBtn = (Button) view.findViewById(R.id.allMemoBooks_btn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WordsBookList.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
