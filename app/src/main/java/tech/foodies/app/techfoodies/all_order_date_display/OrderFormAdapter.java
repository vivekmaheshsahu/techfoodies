package tech.foodies.ins_armman.techfoodies.all_order_date_display;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.foodies.ins_armman.techfoodies.R;
import tech.foodies.ins_armman.techfoodies.all_order_details.all_order_details;
import tech.foodies.ins_armman.techfoodies.data.model.all_order_model;

import java.util.List;


public class OrderFormAdapter extends RecyclerView.Adapter<OrderFormAdapter.ViewHolder> {

    private Context mContext;
  private ClickListener clickListener;
    private List<all_order_model> mWomenList;

    public OrderFormAdapter(Context mContext, List<all_order_model> womenList) {
        this.mContext = mContext;
        this.mWomenList = womenList;

    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_incomplete_list, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderFormAdapter.ViewHolder holder, int i) {
        holder.bindData(mWomenList.get(i));
    }

    @Override
    public int getItemCount() {
        return mWomenList.size();
    }

    public void swapDataList(List<all_order_model> womenList) {
        this.mWomenList = womenList;
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;
        ConstraintLayout constraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewName = itemView.findViewById(R.id.textview_name);
            constraintLayout = itemView.findViewById(R.id.constraint_layout_root);
        }

        private void bindData(final all_order_model listModel) {
            if (listModel != null) {
                textViewName.setText(listModel.getSname());
            }
        }

        @Override
        public void onClick(View v) {

                Intent intent = new Intent(mContext, all_order_details.class);
                intent.putExtra("id",mWomenList.get(getPosition()).getName());
                intent.putExtra("createdOn",mWomenList.get(getPosition()).getSname());
                mContext.startActivity(intent);

        }
    }
}