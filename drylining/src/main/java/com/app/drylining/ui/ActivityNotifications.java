package com.app.drylining.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.drylining.R;
import com.app.drylining.adapter.NotificationAdapter;
import com.app.drylining.adapter.NotificationsAdepter1;
import com.app.drylining.custom.AdminBar;
import com.app.drylining.custom.AdminPanel;
import com.app.drylining.custom.CustomMainActivity;
import com.app.drylining.data.AppConstant;
import com.app.drylining.data.ApplicationData;
import com.app.drylining.data.Conversation;
import com.app.drylining.model.MSGCountModel;
import com.app.drylining.model.NotificationsModel;
import com.app.drylining.retrofit.ApiClient;
import com.app.drylining.retrofit.ApiInterface;
import com.app.drylining.util.PaginationScrollListenerLinear;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.drylining.ui.DashboardActivity.toolbar_txt_messages_new;

/**
 * Created by Panda on 6/15/2017.
 */

public class ActivityNotifications extends CustomMainActivity {
    private static final String TAG = "ActivityNotifications";
    private String userId, userType;
    private TextView btnRemoveAll;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ApplicationData appData;
    private ArrayList<Conversation> conList;
    private NotificationAdapter adapter;
    private ProgressDialog pdialog;
    private AdminBar adminbar;
    private static final int PAGE_START = 0;
    LinearLayoutManager linearLayoutManager;
    private AdminPanel adminPanel;
    private LinearLayout adminLayout;
    private TextView txtNotifications;
    private Animation animShow, animHide;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES;
    private int currentPage = PAGE_START;
    NotificationsAdepter1 notificationsAdepter1;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        appData = ApplicationData.getSharedInstance();
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        adminLayout = (LinearLayout) findViewById(R.id.adminBar);
        txtNotifications = (TextView) findViewById(R.id.txt_header);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        notificationsAdepter1 = new NotificationsAdepter1(getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notificationsAdepter1);

       // adminbar.setMessages(cnt_notifications);

        recyclerView.addOnScrollListener(new PaginationScrollListenerLinear(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
 /*       btnRemoveAll = (TextView) findViewById(R.id.txt_remove_all);

        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllClicked();
            }
        });*/
        loadFirstPage();
    }

    private void loadFirstPage() {
        showProgressDialog();
        Log.d(TAG, "loadFirstPage: ");
        Call<NotificationsModel> modelCall = apiInterface.getNotifacationList(currentPage, "L", appData.getUserId());
        modelCall.enqueue(new Callback<NotificationsModel>() {
            @Override
            public void onResponse(Call<NotificationsModel> call, Response<NotificationsModel> response) {
                cancelProgressDialog();
                TOTAL_PAGES = response.body().getTotalpages();
                List<NotificationsModel.MessagesBean> results = response.body().getMessages();
                // progressBar.setVisibility(View.GONE);
                notificationsAdepter1.addAll(results);

                if (currentPage <= TOTAL_PAGES) notificationsAdepter1.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<NotificationsModel> call, Throwable t) {
                cancelProgressDialog();
                t.printStackTrace();
            }
        });

    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);
        Call<NotificationsModel> modelCall = apiInterface.getNotifacationList(currentPage, "L", appData.getUserId());
        modelCall.enqueue(new Callback<NotificationsModel>() {
            @Override
            public void onResponse(Call<NotificationsModel> call, Response<NotificationsModel> response) {
                notificationsAdepter1.removeLoadingFooter();
                isLoading = false;

                List<NotificationsModel.MessagesBean> results = response.body().getMessages();
                notificationsAdepter1.addAll(results);

                if (currentPage != TOTAL_PAGES) notificationsAdepter1.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<NotificationsModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            onCreate(savedInstanceState);
        } catch (Exception e) {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = null;

        if (conList != null)
            conList.clear();

        initialize();
    }

    private void countApiCall() {
        appData = ApplicationData.getSharedInstance();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<MSGCountModel> countModelCall = apiService.getCountBit("0", appData.getUserId());
        countModelCall.enqueue(new Callback<MSGCountModel>() {
            @Override
            public void onResponse(Call<MSGCountModel> call, Response<MSGCountModel> response) {
                try {
                    toolbar_txt_messages_new.setText("");
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<MSGCountModel> call, Throwable t) {

            }
        });

    }

    private void initialize() {
        appData = ApplicationData.getSharedInstance();

        setToolbar();

        animShow = AnimationUtils.loadAnimation(this, R.anim.view_show);
        animHide = AnimationUtils.loadAnimation(this, R.anim.view_hide);
        countApiCall();
        conList = new ArrayList<Conversation>();
        adminbar.setMessages("1");
        adminbar.setUserName(appData.getUserName());
        adminbar.setUserId(appData.getUserId());
        // sendGetNotificationsRequest();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        Drawable launcher = ContextCompat.getDrawable(this, R.mipmap.app_small_toolbar_icon);
        toolbar.setNavigationIcon(launcher);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent firstActivityIntent = null;
                String userType = appData.getUserType();
                if (userType.equals("R")) {
                    firstActivityIntent = new Intent(ActivityNotifications.this, DashboardActivity.class);
                    finish();
                } else if (userType.equals("L")) {
                    firstActivityIntent = new Intent(ActivityNotifications.this, DashboardActivity.class);
                    finish();
                } else
                    return;

                firstActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ActivityNotifications.this.startActivity(firstActivityIntent);
            }
        });

        adminbar = new AdminBar(this);

        adminPanel = new AdminPanel(this);
    }

    @Override
    public void openAdminPanel() {
        adminLayout.setVisibility(View.VISIBLE);
        adminLayout.startAnimation(animShow);

        new CountDownTimer(AppConstant.ADMIN_BAR_OPEN_TIME, 100) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                adminbar.imgOpenArrow.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    public void closeAdminPanel() {
        adminLayout.setVisibility(View.GONE);
        adminLayout.startAnimation(animHide);

        adminbar.imgOpenArrow.setVisibility(View.GONE);
        recreate();
    }

    @Override
    public void onBackPressed() {
        if (adminLayout.getVisibility() == View.VISIBLE)
            closeAdminPanel();
        else {
            super.onBackPressed();
            if (appData.getUserType().equals("R"))
                startActivity(new Intent(this, DashboardActivity.class));
            else if (appData.getUserType().equals("L"))
                startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }


    private void showProgressDialog() {
        if (!this.isFinishing() && pdialog == null) {
            pdialog = ProgressDialog.show(this, getResources().getString(R.string.alert_title_loading), getResources()
                    .getString(R.string.alert_body_wait));
        }
    }

    private void cancelProgressDialog() {
        if (pdialog != null && !this.isFinishing()) {
            pdialog.dismiss();
            pdialog = null;
        }
    }
}
