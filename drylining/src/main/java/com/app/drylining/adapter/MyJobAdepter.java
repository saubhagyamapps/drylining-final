package com.app.drylining.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.drylining.R;
import com.app.drylining.custom.AppDebugLog;
import com.app.drylining.custom.ItemClickListener;
import com.app.drylining.model.MyJobModel;
import com.app.drylining.ui.ActivityRemoveOffer;
import com.app.drylining.ui.AddedOfferDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MyJobAdepter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<MyJobModel.ResultBean> dataBean;
    private Context mContext;

    private boolean isLoadingAdded = false;

    public MyJobAdepter(Context mContext) {
        this.mContext = mContext;
        dataBean = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.cell_added_offers, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MyJobModel.ResultBean result = dataBean.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;


                movieVH.txtNotifications.setVisibility(View.VISIBLE);

                if (result != null) {
                    movieVH.mainLayout.setTag(result);
                    movieVH.btnRemoveOffer.setTag(result);
                    movieVH.txtOfferName.setText(result.getName());
                    movieVH.txtRoomType.setText(result.getJob_type());
                    movieVH.txtPrice.setText(result.getCurrency_type() + " " + result.getPrice());
                    movieVH.txtInterest.setText(result.getCountInterest());

                    if (result.getJob_status() > 0) {
                        movieVH.btnRemoveOffer.setVisibility(View.GONE);
                    }

                    if (result.getConversation_count() > 0) {
                        movieVH.txtNotifications.setText(String.valueOf(result.getConversation_count()));

                        if (result.getConversation_count() > 0) {
                            movieVH.imgMsgIcon.setImageResource(R.drawable.ic_mail_dark);
                            movieVH.txtNotifications.setTextColor(mContext.getResources().getColor(R.color.black));
                        } else {
                            movieVH.imgMsgIcon.setImageResource(R.drawable.ic_mail_gray_cc);
                            movieVH.txtNotifications.setTextColor(mContext.getResources().getColor(R.color.color_gray_cc));
                        }
                    } else {
                        movieVH.txtNotifications.setVisibility(View.INVISIBLE);
                        movieVH.imgMsgIcon.setVisibility(View.INVISIBLE);
                    }

                    movieVH.btnRemoveOffer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent removeIntent = new Intent(mContext, ActivityRemoveOffer.class);
                            removeIntent.putExtra("SELECTED_OFFER_ID", result.getId());
                            mContext.startActivity(removeIntent);
                        }
                    });
                    movieVH.mainLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(mContext, AddedOfferDetailActivity.class);
                            intent.putExtra("OfferID", result.getId());
                            intent.putExtra("BackActivityName", "MyJobAcivity");
                            AppDebugLog.println("OfferID" + result.getId());
                            mContext.startActivity(intent);
                        }
                    });

            /*Picasso.with(mContext)
                    .load(offer.getImage_path())
                    .resizeDimen(R.dimen.cell_offer_image_size, R.dimen.cell_offer_image_size)
                    .centerCrop()
                    .into(holder.propertyImage);*/
                }

                break;

            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dataBean == null ? 0 : dataBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == dataBean.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public void add(MyJobModel.ResultBean r) {
        dataBean.add(r);
        notifyItemInserted(dataBean.size() - 1);
    }

    public void addAll(List<MyJobModel.ResultBean> Results) {
        for (MyJobModel.ResultBean result : Results) {
            add(result);
        }
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new MyJobModel.ResultBean());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataBean.size() - 1;
        MyJobModel.ResultBean result = getItem(position);

        if (result != null) {
            dataBean.remove(position);
            notifyItemRemoved(position);
        }
    }

    public MyJobModel.ResultBean getItem(int position) {
        return dataBean.get(position);
    }


    protected class MovieVH extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ItemClickListener clickListener;
        private RelativeLayout mainLayout;
        private TextView txtOfferName, txtRoomType, txtPrice, txtNotifications, txtInterest;
        private ImageView propertyImage, btnRemoveOffer, imgMsgIcon;

        public MovieVH(View v) {
            super(v);

            mainLayout = (RelativeLayout) v.findViewById(R.id.mainLayout);
            txtOfferName = (TextView) v.findViewById(R.id.offer_name);
            txtRoomType = (TextView) v.findViewById(R.id.txt_offer_type);
            txtPrice = (TextView) v.findViewById(R.id.txt_price);
            txtNotifications = (TextView) v.findViewById(R.id.txt_distance);
            imgMsgIcon = (ImageView) v.findViewById(R.id.img_msg_marker);
            txtInterest = (TextView) v.findViewById(R.id.txt_interest);
            //propertyImage = (ImageView) v.findViewById(R.id.ic_offer);
            btnRemoveOffer = (ImageView) v.findViewById(R.id.btn_delete);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}