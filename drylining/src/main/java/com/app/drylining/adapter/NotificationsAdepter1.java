package com.app.drylining.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.drylining.R;
import com.app.drylining.custom.AppDebugLog;
import com.app.drylining.data.AppConstant;
import com.app.drylining.data.ApplicationData;
import com.app.drylining.data.Conversation;
import com.app.drylining.data.Offer;
import com.app.drylining.model.NotificationReadModel;
import com.app.drylining.model.NotificationsModel;
import com.app.drylining.network.RequestTask;
import com.app.drylining.network.RequestTaskDelegate;
import com.app.drylining.retrofit.ApiClient;
import com.app.drylining.retrofit.ApiInterface;
import com.app.drylining.ui.ActivityConversationsLessee;
import com.app.drylining.ui.ActivityConversationsRenter;
import com.app.drylining.ui.AddedOfferDetailActivity;
import com.app.drylining.ui.SearchedOfferDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsAdepter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RequestTaskDelegate {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<NotificationsModel.MessagesBean> dataBean;
    private Context mContext;
    private static final String TAG = "NotificationsAdepter1";
    private boolean isLoadingAdded = false;


    NotificationsModel.MessagesBean result;
    private ApplicationData appData;
    private ProgressDialog pdialog;
    private ArrayList<Conversation> arrayList;
    private int selectedClientId, selectedOfferId;                //use in click process
    private Offer selectedOffer;
    private String selectedNotificationType, selectedNotificationState;

    public NotificationsAdepter1(Context mContext) {
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
        View v1 = inflater.inflate(R.layout.cell_notification, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        result = dataBean.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;

                movieVH.mainLayout.setTag(result);

                movieVH.txtTimeAgo.setText(calcTime(result.getTime()));

                movieVH.txtContent.setText(result.getContent());
                if (result.getRead().equals("0")) {
                    movieVH.mainLayout.setBackgroundResource(R.color.light_gray);
                } else {
                    movieVH.mainLayout.setBackgroundResource(R.color.app_bg_color);
                }
                String type = result.getIsRead();
                if (type.equals("LtoR") || type.equals("RtoL")) {
                    movieVH.iconImg.setImageResource(R.drawable.ic_mail);

                    String state = result.getIsRead();
                    if (state.equals("unread")) {
                        movieVH.mainLayout.setBackgroundColor(mContext.getResources().getColor(R.color.primary_color));
                    } else if (state.equals("read")) {
                        movieVH.mainLayout.setBackgroundColor(mContext.getResources().getColor(R.color.color_gray_99));
                    }
                } else if (type.equals("interest") || type.equals("confirm_interest")) {
                    //holder.iconImg.setImageResource(R.drawable.ic_thumb_white);
                    //holder.mainLayout.setBackgroundColor(mContext.getResources().getColor(R.color.color_gray_4d));
                } else if (type.equals("favorite")) {
                    movieVH.iconImg.setImageResource(R.drawable.ic_favourite_white);
                    movieVH.mainLayout.setBackgroundColor(mContext.getResources().getColor(R.color.color_success));
                } else if (type.equals("criteria")) {
                    movieVH.iconImg.setImageResource(R.drawable.ic_star_white);
                    movieVH.mainLayout.setBackgroundColor(mContext.getResources().getColor(R.color.color_success));
                }
                movieVH.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notificationClicked(dataBean.get(position));
                    }
                });
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


    public void add(NotificationsModel.MessagesBean r) {
        dataBean.add(r);
        notifyItemInserted(dataBean.size() - 1);
    }

    public void addAll(List<NotificationsModel.MessagesBean> Results) {
        for (NotificationsModel.MessagesBean result : Results) {
            add(result);
        }
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new NotificationsModel.MessagesBean());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataBean.size() - 1;
        NotificationsModel.MessagesBean result = getItem(position);

        if (result != null) {
            dataBean.remove(position);
            notifyItemRemoved(position);
        }
    }

    public NotificationsModel.MessagesBean getItem(int position) {
        return dataBean.get(position);
    }


    protected class MovieVH extends RecyclerView.ViewHolder {
        ImageView imgQuotes;
        private RelativeLayout mainLayout;
        private TextView txtContent, txtTimeAgo;
        private ImageView iconImg;

        public MovieVH(View itemView) {
            super(itemView);
            mainLayout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            txtContent = (TextView) itemView.findViewById(R.id.txt_message);
            txtTimeAgo = (TextView) itemView.findViewById(R.id.txt_time_ago);
            iconImg = (ImageView) itemView.findViewById(R.id.img_icon);

        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    private void notificationClicked(NotificationsModel.MessagesBean messagesBean) {
        Log.e(TAG, "notificationClicked: ");

        selectedClientId = messagesBean.getSender_id();
        selectedOfferId = messagesBean.getOffer_id();
        selectedNotificationType = messagesBean.getMessage_type();
        selectedNotificationState = messagesBean.getIsRead();

        if (selectedNotificationState.equals("unread")) {
            // showProgressDialog();
            RequestTask requestTask = new RequestTask(AppConstant.UPDATE_NOTIFICATIONS, AppConstant.HttpRequestType.UpdateNotification);
            requestTask.delegate = NotificationsAdepter1.this;
            requestTask.execute(AppConstant.UPDATE_NOTIFICATIONS + messagesBean.getId());
        } else if (selectedNotificationState.equals("read")) {
            clickProc(selectedNotificationType);
        }
        readNotifaction(messagesBean.getNotification_id(), messagesBean.getInterest_id(), messagesBean.getConfirm_id());
    }

    private void readNotifaction(String notification_id, String interest_id, String confirm_id) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final Call<NotificationReadModel> readModelCall = apiService.getNotificationBit(notification_id, interest_id, confirm_id);
        readModelCall.enqueue(new Callback<NotificationReadModel>() {
            @Override
            public void onResponse(Call<NotificationReadModel> call, Response<NotificationReadModel> response) {
                Log.e(TAG, "onResponse: " + response);
            }

            @Override
            public void onFailure(Call<NotificationReadModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
    }

    private void clickProc(String messageType) {
        if (appData.getUserId().equals(String.valueOf(selectedClientId))) {
            Intent detailIntent = new Intent(mContext, AddedOfferDetailActivity.class);
            detailIntent.putExtra("OfferID", selectedOfferId);
            detailIntent.putExtra("BackActivityName", "Notifation");

            mContext.startActivity(detailIntent);
        } else {
            if (messageType.equals("LtoR")) {
                // showProgressDialog();
                RequestTask requestTask = new RequestTask(AppConstant.GET_PROPERTY_INFO_RENTER, AppConstant.HttpRequestType.getPropertyInfo);
                requestTask.delegate = NotificationsAdepter1.this;
                requestTask.execute(AppConstant.GET_PROPERTY_INFO_RENTER + selectedOfferId);

            } else if (messageType.equals("RtoL")) {
                //    showProgressDialog();
                RequestTask requestTask = new RequestTask(AppConstant.GET_PROPERTY_INFO_LESSEE, AppConstant.HttpRequestType.getPropertyInfo);
                requestTask.delegate = NotificationsAdepter1.this;
                String params = "propertyId=" + selectedOfferId + "&userId=" + appData.getUserId();
                requestTask.execute(AppConstant.GET_PROPERTY_INFO_LESSEE + params);
            } else if (messageType.equals("interest") || messageType.equals("favorite")) {
                Intent detailIntent = new Intent(mContext, SearchedOfferDetailActivity.class);
                detailIntent.putExtra("OfferID", selectedOfferId);
                detailIntent.putExtra("BackActivityName", "Notifation");
                mContext.startActivity(detailIntent);
                //mContext.finish();
            } else if (messageType.equals("confirm_interest") || messageType.equals("criteria")) {
                Intent intent = new Intent(mContext, AddedOfferDetailActivity.class);
                intent.putExtra("OfferID", selectedOfferId);
                intent.putExtra("BackActivityName", "Notifation");
                mContext.startActivity(intent);
                //mContext.finish();
            }
        }
    }

    @Override
    public void backgroundActivityComp(String response, AppConstant.HttpRequestType completedRequestType) {
        // cancelProgressDialog();
        AppDebugLog.println("Response is " + response);
        JSONObject request_response = null;
        try {
            request_response = new JSONObject(response);

            if (completedRequestType == AppConstant.HttpRequestType.UpdateNotification) {
                String status = request_response.getString("status");
                if (status.equals("success"))
                    clickProc(selectedNotificationType);
            } else if (completedRequestType == AppConstant.HttpRequestType.getPropertyInfo) {
                if (selectedNotificationType.equals("LtoR")) {
                    JSONObject propObj = request_response.getJSONObject("result");
                    JSONObject offerObj = propObj.getJSONObject("info");

                    String propName = offerObj.getString("name");
                    String proproomType = offerObj.getString("room_type");
                    String propPrice = offerObj.getString("price");

                    Offer selectedOffer = new Offer(selectedOfferId, propName, proproomType, propPrice, 0);

                    Intent intent = new Intent(mContext, ActivityConversationsRenter.class);
                    intent.putExtra("SELECTED_OFFER", selectedOffer);
                    intent.putExtra("CLIENT_ID", selectedClientId);
                    mContext.startActivity(intent);
                    // mContext.finish();
                } else if (selectedNotificationType.equals("RtoL")) {
                    boolean isFavorited = false;
                    JSONObject propObj = request_response.getJSONObject("result");
                    JSONObject offerObj = propObj.getJSONObject("info");

                    int propId = Integer.parseInt(offerObj.getString("id").toString());
                    String propName = offerObj.getString("name");
                    String proproomType = offerObj.getString("room_type");
                    String price = offerObj.getString("price");
                    String propPrice = offerObj.getString("price") + " EUR/month";
                    double distance = offerObj.getDouble("distance");
                    selectedOffer = new Offer(propId, propName, proproomType, price, distance);

                    int isFavorite = request_response.getInt("isFavorite");
                    if (isFavorite != 0)
                        isFavorited = true;

                    Intent intent = new Intent(mContext, ActivityConversationsLessee.class);
                    intent.putExtra("SELECTED_OFFER", selectedOffer);
                    intent.putExtra("IS_FAVORITED", isFavorited);
                    mContext.startActivity(intent);
                    // mContext.finish();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void timeOut() {

    }

    @Override
    public void codeError(int code) {

    }

    @Override
    public void percentageDownloadCompleted(int percentage, Object record) {

    }

    private String calcTime(long agoTime) {
        String res;
        int days = (int) (agoTime / (3600 * 24));
        if (days > 0) {
            res = String.valueOf(days) + " days";
            return res;
        }

        int hours = (int) (agoTime / 3600);
        if (hours > 0) {
            res = String.valueOf(hours) + " h ";
            return res;
        }


        int reminder = (int) (agoTime - hours * 3600);
        int mins = reminder / 60;

        res = String.valueOf(mins) + " min";
        return res;
    }
}