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
import com.app.drylining.data.ApplicationData;
import com.app.drylining.data.Offer;
import com.app.drylining.model.RecentlyAddedJobModel;
import com.app.drylining.ui.AddedOfferDetailActivity;
import com.app.drylining.ui.SearchedOfferDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RecentlyAddedJobAdepter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ApplicationData appData;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    RecentlyAddedJobModel.ResultBean result;
    private List<RecentlyAddedJobModel.ResultBean> dataBean;
    private Context mContext;

    private boolean isLoadingAdded = false;

    public RecentlyAddedJobAdepter(Context mContext) {
        appData = ApplicationData.getSharedInstance();
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
        View v1 = inflater.inflate(R.layout.cell_searched_offers, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        result = dataBean.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;

                //     movieVH.txtQoutes.setText(result.getQuotes());


                //Offer offer = arrayList.get(position);

                movieVH.txtNotifications.setVisibility(View.VISIBLE);

                if (result != null) {
                    movieVH.mainLayout.setTag(result);
                    movieVH.txtOfferName.setText(result.getName());
                    movieVH.txtRoomType.setText(result.getWork_type());
                    movieVH.txtPrice.setText(result.getCurrency_type() + " " + result.getPrice());
                    movieVH.txtCity.setText(result.getPostcity());
                    movieVH.txtInterested.setText(result.getMy_state());

                    if (result.getConversation_count() > 0) {
                        // holder.txtNotifications.setText(String.valueOf(offer.getConversations()));
                        if (0 > 0) {
                            movieVH.imgMsgIcon.setImageResource(R.drawable.ic_mail_dark);
                            movieVH.txtNotifications.setTextColor(mContext.getResources().getColor(R.color.black));
                        } else {
                            movieVH.imgMsgIcon.setImageResource(R.drawable.ic_mail_gray_cc);
                            movieVH.txtNotifications.setTextColor(mContext.getResources().getColor(R.color.color_gray_99));
                        }
                    } else {
                        movieVH.txtNotifications.setVisibility(View.GONE);
                        movieVH.imgMsgIcon.setVisibility(View.GONE);
                    }

                    movieVH.mainLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ClickJob(dataBean.get(position));
                        }
                    });
                }
                break;

            case LOADING:
                break;
        }

    }

    private void ClickJob(RecentlyAddedJobModel.ResultBean resultBean) {
   //     Offer offer = (Offer) view.getTag();
     //   int offerId = offer.getId();

        if (resultBean.getUser_id().equals(appData.getUserId())) {
            Intent intent = new Intent(mContext, AddedOfferDetailActivity.class);
            intent.putExtra("OfferID", resultBean.getId());
            intent.putExtra("BackActivityName", "SearchAcivity");
            AppDebugLog.println("Search OfferID" + resultBean.getId());
            mContext.startActivity(intent);
        } else {
            Intent intent = new Intent(mContext, SearchedOfferDetailActivity.class);
            intent.putExtra("OfferID", resultBean.getId());
            intent.putExtra("BackActivityName", "SearchAcivity");
            AppDebugLog.println("Search OfferID" + resultBean.getId());
            mContext.startActivity(intent);
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


    public void add(RecentlyAddedJobModel.ResultBean r) {
        dataBean.add(r);
        notifyItemInserted(dataBean.size() - 1);
    }


    public void addAll(List<RecentlyAddedJobModel.ResultBean> Results) {
        for (RecentlyAddedJobModel.ResultBean result : Results) {
            add(result);
        }

    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new RecentlyAddedJobModel.ResultBean());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataBean.size() - 1;
        RecentlyAddedJobModel.ResultBean result = getItem(position);

        if (result != null) {
            dataBean.remove(position);
            notifyItemRemoved(position);
        }
    }

    public RecentlyAddedJobModel.ResultBean getItem(int position) {
        return dataBean.get(position);
    }


    protected class MovieVH extends RecyclerView.ViewHolder {
        private ItemClickListener clickListener;
        private RelativeLayout mainLayout;
        private TextView txtOfferName, txtRoomType, txtPrice, txtCity, txtInterested, txtNotifications;
        private ImageView propertyImage, imgMsgIcon;

        public MovieVH(View v) {
            super(v);

            mainLayout = (RelativeLayout) v.findViewById(R.id.mainLayout);
            txtOfferName = (TextView) v.findViewById(R.id.offer_name);
            txtRoomType = (TextView) v.findViewById(R.id.txt_offer_type);
            txtPrice = (TextView) v.findViewById(R.id.txt_price);
            txtCity = (TextView) v.findViewById(R.id.txt_city);
            txtInterested = (TextView) v.findViewById(R.id.txt_interest);
            txtNotifications = (TextView) v.findViewById(R.id.txt_notification);
            imgMsgIcon = (ImageView) v.findViewById(R.id.img_msg_marker);

        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}