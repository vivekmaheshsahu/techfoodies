package tech.foodies.inventory.app.Display_Product;

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
import tech.foodies.inventory.app.data.model.completeFiledForm;

public class showProducts extends AppCompatActivity implements IshowProductsView, completedFormAdapter.ClickListener {

    IshowProductsPresentation ishowProductsPresentation;

    ProgressBar mProgressBar;
    completedFormAdapter mcompleteFormAdapter;
    RelativeLayout emptyLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        setTitle("Product List");
        mProgressBar = findViewById(R.id.child_list_progress_bar);
        emptyLayout = findViewById(R.id.empty_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showProducts.this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        ishowProductsPresentation = new showProductsPresentation();
        ishowProductsPresentation.attachView(this);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ishowProductsPresentation.detch();
    }

    @Override
    public void setAdapter(List<completeFiledForm> womenList) {
        mProgressBar.setVisibility(View.GONE);

        if (womenList == null || womenList.size() < 1) {
            emptyLayout.setVisibility(View.VISIBLE);
            TextView emptyTextView = findViewById(R.id.text_empty_list);
            emptyTextView.setText(R.string.Reg_women_com);
            return;
        }
        if (womenList != null) {
            mcompleteFormAdapter = new completedFormAdapter(getContext(), womenList);
            mRecyclerView.setAdapter(mcompleteFormAdapter);
            mcompleteFormAdapter.setClickListener(this);
        } else {
            mcompleteFormAdapter.swapDataList(womenList);
            mcompleteFormAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ishowProductsPresentation.getListCompleteForm();
    }

    @Override
    public void itemClicked(View view, int position) {

    }

}
