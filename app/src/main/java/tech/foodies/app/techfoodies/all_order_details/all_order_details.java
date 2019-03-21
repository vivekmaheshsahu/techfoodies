package tech.foodies.app.techfoodies.all_order_details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.inscripts.ins_armman.techfoodies.R;

import tech.foodies.app.techfoodies.data.model.all_order_model;

import java.util.List;

public class all_order_details extends AppCompatActivity implements Iall_order_details_view,Details_orderFormAdapter.ClickListener  {

    Iall_order_details_presenter iall_order_presenter;
    ProgressBar mProgressBar;
    Details_orderFormAdapter mcompleteFormAdapter;
    RelativeLayout emptyLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Complete Order");
        setContentView(R.layout.activity_all_order);
        mProgressBar = findViewById(R.id.child_list_progress_bar);
        emptyLayout = findViewById(R.id.empty_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(all_order_details.this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        iall_order_presenter = new all_order_details_presenter();
        iall_order_presenter.attachView(this);

        Intent intent = getIntent();

        String unique_id = intent.getStringExtra("id");
        String created = intent.getStringExtra("createdOn");
        iall_order_presenter.getListCompleteForm(unique_id,created);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(List<all_order_model> mWomenList) {
        mProgressBar.setVisibility(View.GONE);

        if (mWomenList == null || mWomenList.size() < 1) {
            emptyLayout.setVisibility(View.VISIBLE);
            TextView emptyTextView = findViewById(R.id.text_empty_list);
            emptyTextView.setText(R.string.Reg_women_com);
            return;
        }if(mWomenList != null) {
            mcompleteFormAdapter = new Details_orderFormAdapter(getContext(), mWomenList);
            mRecyclerView.setAdapter(mcompleteFormAdapter);
            mcompleteFormAdapter.setClickListener(this);
        } else {
            mcompleteFormAdapter.swapDataList(mWomenList);
            mcompleteFormAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemClicked(View view, int position) {}

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iall_order_presenter.detch();
    }

}
