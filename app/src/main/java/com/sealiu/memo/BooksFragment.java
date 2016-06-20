package com.sealiu.memo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.sealiu.memo.book.BookDetailActivity;
import com.sealiu.memo.book.Service.BookService;
import com.sealiu.memo.book.Service.Impl.BookDao;
import com.sealiu.memo.book.modle.Book;

import java.util.List;

/**
 * Created by root
 * on 6/18/16.
 */
public class BooksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.books_toolbar_title);

        View view = inflater.inflate(R.layout.fragment_books, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.books_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new MyAdapter(getActivity(), getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private String[][] getDataSet() {
        BookService bookService = new BookDao(getActivity());
        List<Book> books = bookService.queryBookList(true, null, null, null, "status DESC", null);

        int bookSize = books.size();

        String[][] DataSet = new String[bookSize][3];
        int i = 0;
        for (Book book : books) {
            DataSet[i][0] = book.getName().equals("") ? "UNDEFINE" : book.getName();
            DataSet[i][1] = book.getDesc().equals("") ? "UNDEFINE" : book.getDesc();
            DataSet[i][2] = String.valueOf(book.getStatus());
            i++;
        }
        return DataSet;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[][] mDataSet;
        private Context context;

        public MyAdapter(Context context, String[][] mDataSet) {
            this.context = context;
            this.mDataSet = mDataSet;
        }

        /*
        Provide a reference to the views for each data item
        Complex data items may need more than one view per item, and
        you provide access to all the view for a data item in a view holder;
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            TextView subTitle;
            Switch activeSwitch;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                title = (TextView) itemView.findViewById(R.id.book_title);
                subTitle = (TextView) itemView.findViewById(R.id.book_sub_title);
                activeSwitch = (Switch) itemView.findViewById(R.id.active_switch);
            }

            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BookDetailActivity.class));
            }
        }

        // Create new views (it will be invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (it will be invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            holder.title.setText(mDataSet[position][0]);
            holder.subTitle.setText(mDataSet[position][1]);
            boolean isCheck = mDataSet[position][2].equals("1");
            holder.activeSwitch.setChecked(isCheck);
            holder.activeSwitch.setClickable(false);
        }

        @Override
        public int getItemCount() {
            return mDataSet.length;
        }
    }
}
