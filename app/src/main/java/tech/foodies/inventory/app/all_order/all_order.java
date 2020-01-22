package tech.foodies.inventory.app.all_order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tech.foodies.inventory.app.R;
import tech.foodies.inventory.app.data.model.all_order_model;

public class all_order extends AppCompatActivity implements Iall_order_view, OrderFormAdapter.ClickListener {

    Iall_order_presenter iall_order_presenter;
    ProgressBar mProgressBar;
    OrderFormAdapter mcompleteFormAdapter;
    RelativeLayout emptyLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Customer List");
        setContentView(R.layout.activity_all_order);
        mProgressBar = findViewById(R.id.child_list_progress_bar);
        emptyLayout = findViewById(R.id.empty_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        iall_order_presenter = new all_order_presenter();
        iall_order_presenter.attachView(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(all_order.this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iall_order_presenter.detch();
    }

    @Override
    public void setAdapter(List<all_order_model> womenList) {
        mProgressBar.setVisibility(View.GONE);

        if (womenList == null || womenList.size() < 1) {
            emptyLayout.setVisibility(View.VISIBLE);
            TextView emptyTextView = findViewById(R.id.text_empty_list);
            emptyTextView.setText(R.string.Reg_women_com);
            return;
        }
        if (womenList != null) {
            mcompleteFormAdapter = new OrderFormAdapter(getContext(), womenList);
            mRecyclerView.setAdapter(mcompleteFormAdapter);
            mcompleteFormAdapter.setClickListener(this);
        } else {
            mcompleteFormAdapter.swapDataList(womenList);
            mcompleteFormAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemClicked(View view, int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        iall_order_presenter.getListCompleteForm();
    }

}
