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
import com.app.drylining.model.RecentToolModel;
import com.app.drylining.ui.ActivityRemoveTool;
import com.app.drylining.ui.AddedToolDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecentToolAdepter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private ApplicationData appData;
    private List<RecentToolModel.ResultBean> dataBean;
    private Context mContext;

    private boolean isLoadingAdded = false;

    public RecentToolAdepter(Context mContext) {
        this.mContext = mContext;
        dataBean = new ArrayList<>();
        appData = ApplicationData.getSharedInstance();
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
        View v1 = inflater.inflate(R.layout.cell_added_tools, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final RecentToolModel.ResultBean result = dataBean.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;


                movieVH.txtNotifications.setVisibility(View.VISIBLE);

                if (result != null) {
                    movieVH.mainLayout.setTag(result);
                    movieVH.btnRemoveOffer.setTag(result);
                    movieVH.txtOfferName.setText(result.getName());
                    movieVH.txtRoomType.setText(result.getTool_type());
                    movieVH.txtPrice.setText(result.getCurrency_type() + " " + result.getPrice());

                    if (result.getUser_id().equals(appData.getUserId())) {
                        movieVH.btnRemoveOffer.setVisibility(View.VISIBLE);
                    } else {
                        movieVH.btnRemoveOffer.setVisibility(View.GONE);
                    }

                    if (0 > 0) {
                        movieVH.txtNotifications.setText(String.valueOf("0"));

                        if (0 > 0) {
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

                    Picasso.get()
                            .load(result.getImage_path())
                            .resizeDimen(R.dimen.cell_offer_image_size, R.dimen.cell_offer_image_size)
                            .centerCrop()
                            .into(movieVH.propertyImage);

                    movieVH.mainLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(mContext, AddedToolDetailActivity.class);
                            intent.putExtra("ToolID", result.getId());
                            AppDebugLog.println("ToolID" + result.getId());
                            mContext.startActivity(intent);
                        }
                    });

                    movieVH.btnRemoveOffer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent removeIntent = new Intent(mContext, ActivityRemoveTool.class);
                            removeIntent.putExtra("SELECTED_TOOL_ID", result.getId());
                            mContext.startActivity(removeIntent);
                        }
                    });
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


    public void add(RecentToolModel.ResultBean r) {
        dataBean.add(r);
        notifyItemInserted(dataBean.size() - 1);
    }

    public void addAll(List<RecentToolModel.ResultBean> Results) {
        for (RecentToolModel.ResultBean result : Results) {
            add(result);
        }
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new RecentToolModel.ResultBean());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataBean.size() - 1;
        RecentToolModel.ResultBean result = getItem(position);

        if (result != null) {
            dataBean.remove(position);
            notifyItemRemoved(position);
        }
    }

    public RecentToolModel.ResultBean getItem(int position) {
        return dataBean.get(position);
    }


    protected class MovieVH extends RecyclerView.ViewHolder {

        private ItemClickListener clickListener;
        private RelativeLayout mainLayout;
        private TextView txtOfferName, txtRoomType, txtPrice, txtNotifications;
        private ImageView propertyImage, btnRemoveOffer, imgMsgIcon;

        public MovieVH(View v) {
            super(v);
            mainLayout = (RelativeLayout) v.findViewById(R.id.mainLayout);
            txtOfferName = (TextView) v.findViewById(R.id.offer_name);
            txtRoomType = (TextView) v.findViewById(R.id.txt_offer_type);
            txtPrice = (TextView) v.findViewById(R.id.txt_price);
            txtNotifications = (TextView) v.findViewById(R.id.txt_distance);
            imgMsgIcon = (ImageView) v.findViewById(R.id.img_msg_marker);
            propertyImage = (ImageView) v.findViewById(R.id.ic_offer);
            btnRemoveOffer = (ImageView) v.findViewById(R.id.btn_delete);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}