package com.app.drylining.fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.drylining.R;
import com.app.drylining.Util;
import com.app.drylining.adapter.AddedOffersAdapter;
import com.app.drylining.adapter.MyJobAdepter;
import com.app.drylining.adapter.RecentlyAddedJobAdepter;
import com.app.drylining.adapter.SearchedOffersAdapter;
import com.app.drylining.custom.AdminBar;
import com.app.drylining.custom.AdminPanel;
import com.app.drylining.custom.AppDebugLog;
import com.app.drylining.custom.CustomAutoCompleteListener;
import com.app.drylining.custom.MessageCntSetListener;
import com.app.drylining.data.AppConstant;
import com.app.drylining.data.ApplicationData;
import com.app.drylining.data.Offer;
import com.app.drylining.model.MyJobModel;
import com.app.drylining.model.RecentlyAddedJobModel;
import com.app.drylining.retrofit.ApiClient;
import com.app.drylining.retrofit.ApiInterface;
import com.app.drylining.ui.AddNewOfferActivity;
import com.app.drylining.ui.DashboardActivity;
import com.app.drylining.ui.SearchNewOfferActivity;
import com.app.drylining.util.PaginationScrollListenerLinear;
import com.app.drylining.util.PaginationScrollListenerLineartest;
import com.app.drylining.utils.AppInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AddNewOfferFragment extends Fragment {
    private static final String TAG = "AddNewOfferFragment";
    private static final int PAGE_START = 0;
    private static final int PAGE_STARTMyJob = 0;
    RecentlyAddedJobAdepter recentlyAddedJobAdepter;
    MyJobAdepter myJobAdepter;
    LinearLayoutManager linearLayoutManager, linearLayoutManagerMyjob;
    RecyclerView recyclerViewRecentlyjob, recyclerViewMyJob;
    ProgressBar progressBar;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private boolean isLoadingmyjob = false;
    private boolean isLastPageMyjob = false;
    private int TOTAL_PAGES;
    private int TOTAL_PAGESMyJob;
    private int currentPage = PAGE_START;
    private int currentPageMyJob = PAGE_STARTMyJob;
    private ApiInterface apiInterface;
    Button txtMyJob, txtRecentrlyAddJob, btn_new_search;
    private ApplicationData appData;
    private DashboardActivity activity;
    private View view = null;
    private TextView lblError, msgError;
    private RecyclerView recyclerView, recyclerView_new;
    private AddedOffersAdapter adapter;
    private SearchedOffersAdapter adapter1;
    private ArrayList<Offer> offerList, offerList1;
    private ProgressDialog pdialog;
    private String location;
    private TextView msg_success, txt_none_added_offer, txt_none_offers_new;
    private Button btnAddNewOffer;
    private AdminBar adminbar;

    private AdminPanel adminPanel;
    private LinearLayout adminLayout;

    private Animation animShow, animHide;

    private CountDownTimer lTimer;

    private boolean backPressedOneTime = false;

    private CustomAutoCompleteListener listener;

    private MessageCntSetListener messageCntSetListener;

    @SuppressLint("ValidFragment")
    public AddNewOfferFragment() {
        // Required empty public constructor
    }

    public void tabChanged() {
        AppDebugLog.println("In tabChanged of AddOfferByAddressFragment : ");
    }

    public void tabUnSelected() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppDebugLog.println("In onCreateView of AddOfferByAddressFragment : ");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_add_new_offer, container, false);
            SharedPreferences pref = getActivity().getSharedPreferences("BackStack", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("Back", "Recent Job");
            editor.clear();// Storing string
            editor.apply();
            adminLayout = (LinearLayout) view.findViewById(R.id.adminBar);
            msg_success = (TextView) view.findViewById(R.id.msg_success);
            txt_none_added_offer = (TextView) view.findViewById(R.id.txt_none_offers);
            txt_none_offers_new = (TextView) view.findViewById(R.id.txt_none_offers_new);
            txtMyJob = (Button) view.findViewById(R.id.txtMyJob);
            txtRecentrlyAddJob = (Button) view.findViewById(R.id.txtRecentrlyAddJob);
            btnAddNewOffer = (Button) view.findViewById(R.id.btn_add_offer);
            messageCntSetListener = (MessageCntSetListener) this.getActivity();
            recyclerViewRecentlyjob = view.findViewById(R.id.recyclerView_new);
            recyclerViewMyJob = view.findViewById(R.id.recyclerView);

            setVisibilityView();
            initialize();
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            recentlyAddedJobAdepter = new RecentlyAddedJobAdepter(getActivity());
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerViewRecentlyjob.setLayoutManager(linearLayoutManager);
            recyclerViewRecentlyjob.setAdapter(recentlyAddedJobAdepter);
            recyclerViewRecentlyjob.addOnScrollListener(new PaginationScrollListenerLinear(linearLayoutManager) {
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





            btnAddNewOffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnAddNewOffer.getText().equals("Post Job")) {
                        OnNewOfferClicked();
                    } else {
                        OnNewSearchClicked();
                    }
                }
            });
        }
        if (AppInfo.getInstance().jobType() == 202) {
            /*recyclerViewMyJob.setLayoutManager(new LinearLayoutManager(getActivity()) {
                @Override
                public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate) {
                    return false;
                }
            });*/
            txtMyJob.performClick();
        }
        loadFirstPage();

        return view;

    }

    private void setVisibilityView() {
        txtMyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPageMyJob=0;
                AppInfo.getInstance().setJobType(102);
                recyclerViewRecentlyjob.setVisibility(View.GONE);
                recyclerViewMyJob.setVisibility(View.VISIBLE);
                txtRecentrlyAddJob.setBackgroundColor(getResources().getColor(R.color.white));
                txtMyJob.setBackgroundResource(R.drawable.app_board_with_padding);
                btnAddNewOffer.setText("Post Job");
                setMyJobData();
            }
        });

        txtRecentrlyAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage=0;
                recyclerViewMyJob.setVisibility(View.GONE);
                recyclerViewRecentlyjob.setVisibility(View.VISIBLE);
                txtMyJob.setBackgroundColor(getResources().getColor(R.color.white));
                txtRecentrlyAddJob.setBackgroundResource(R.drawable.app_board_with_padding);
                btnAddNewOffer.setText("New Search");
            }
        });
    }

    private void setMyJobData() {
        myJobAdepter = new MyJobAdepter(getActivity());
        linearLayoutManagerMyjob = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewMyJob.setLayoutManager(linearLayoutManagerMyjob);
        recyclerViewMyJob.setAdapter(myJobAdepter);
        recyclerViewMyJob.addOnScrollListener(new PaginationScrollListenerLinear(linearLayoutManagerMyjob) {
            @Override
            protected void loadMoreItems() {
                isLoadingmyjob = true;
                currentPageMyJob += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPageMyJob();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGESMyJob;
            }

            @Override
            public boolean isLastPage() {
                return isLastPageMyjob;
            }

            @Override
            public boolean isLoading() {
                return isLoadingmyjob;
            }
        });
        loadFirstPageMyJob();
    }

    private void loadFirstPageMyJob() {
        showProgressDialog();
        Call<MyJobModel> modelCall = apiInterface.getMyJobList(Integer.parseInt(appData.getUserId()), currentPageMyJob);
        modelCall.enqueue(new Callback<MyJobModel>() {
            @Override
            public void onResponse(Call<MyJobModel> call, Response<MyJobModel> response) {

                txt_none_offers_new.setVisibility(View.GONE);
                TOTAL_PAGESMyJob = response.body().getTotalpages();
                List<MyJobModel.ResultBean> results = response.body().getResult();
                myJobAdepter.addAll(results);

                if (currentPageMyJob <= TOTAL_PAGESMyJob)
                    myJobAdepter.addLoadingFooter();
                else isLastPageMyjob = true;
                cancelProgressDialog();
            }

            @Override
            public void onFailure(Call<MyJobModel> call, Throwable t) {
                cancelProgressDialog();
                t.printStackTrace();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFirstPage() {
        showProgressDialog();
        Call<RecentlyAddedJobModel> modelCall = apiInterface.getRecentryAddedJob(Integer.parseInt(appData.getUserId()), currentPage);
        modelCall.enqueue(new Callback<RecentlyAddedJobModel>() {
            @Override
            public void onResponse(Call<RecentlyAddedJobModel> call, Response<RecentlyAddedJobModel> response) {

                txt_none_offers_new.setVisibility(View.GONE);
                TOTAL_PAGES = response.body().getTotalpages();
                List<RecentlyAddedJobModel.ResultBean> results = response.body().getResult();
                recentlyAddedJobAdepter.addAll(results);

                if (currentPage <= TOTAL_PAGES)
                    recentlyAddedJobAdepter.addLoadingFooter();
                else isLastPage = true;
                cancelProgressDialog();
            }

            @Override
            public void onFailure(Call<RecentlyAddedJobModel> call, Throwable t) {
                cancelProgressDialog();
                t.printStackTrace();
            }
        });

    }


    private void loadNextPageMyJob() {
        Call<MyJobModel> modelCall = apiInterface.getMyJobList(Integer.parseInt(appData.getUserId()), currentPageMyJob);
        modelCall.enqueue(new Callback<MyJobModel>() {
            @Override
            public void onResponse(Call<MyJobModel> call, Response<MyJobModel> response) {
                myJobAdepter.removeLoadingFooter();
                isLoadingmyjob = false;

                List<MyJobModel.ResultBean> results = response.body().getResult();
                myJobAdepter.addAll(results);

                if (currentPageMyJob != TOTAL_PAGESMyJob)
                    myJobAdepter.addLoadingFooter();
                else isLastPageMyjob = true;
            }

            @Override
            public void onFailure(Call<MyJobModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void loadNextPage() {

        Call<RecentlyAddedJobModel> modelCall = apiInterface.getRecentryAddedJob(Integer.parseInt(appData.getUserId()), currentPage);
        modelCall.enqueue(new Callback<RecentlyAddedJobModel>() {
            @Override
            public void onResponse(Call<RecentlyAddedJobModel> call, Response<RecentlyAddedJobModel> response) {
                recentlyAddedJobAdepter.removeLoadingFooter();
                isLoading = false;

                List<RecentlyAddedJobModel.ResultBean> results = response.body().getResult();
                recentlyAddedJobAdepter.addAll(results);

                if (currentPage != TOTAL_PAGES)
                    recentlyAddedJobAdepter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<RecentlyAddedJobModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    private void initialize() {
        msg_success.setVisibility(View.GONE);
        Intent intent = getActivity().getIntent();
        try {
            String isp = intent.getStringExtra("offerAdded");
            if (isp.equals("1"))
                msg_success.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            msg_success.setVisibility(View.GONE);
        }

        if (intent.getIntExtra("OFFER_REMOVED", 0) == 1) {
            msg_success.setVisibility(View.VISIBLE);
            msg_success.setText("YOUR OFFER HAS BEEN REMOVED");
        }

        if (intent.getIntExtra("offerUpdated", 0) == 1) {
            msg_success.setVisibility(View.VISIBLE);
            msg_success.setText("YOUR OFFER HAS BEEN MODIFIED");
        }
        appData = ApplicationData.getSharedInstance();
        activity = (DashboardActivity) getActivity();

        location = AppConstant.NULL_STRING;
        offerList = new ArrayList<Offer>();
        offerList1 = new ArrayList<Offer>();

        //setToolbar();

        animShow = AnimationUtils.loadAnimation(this.getActivity(), R.anim.view_show);
        animHide = AnimationUtils.loadAnimation(this.getActivity(), R.anim.view_hide);
    }

    public void OnNewOfferClicked() {
        if (appData.getConnectionDetector().isConnectingToInternet()) {
            startActivity(new Intent(activity, AddNewOfferActivity.class));
            activity.finish();
        } else {
            Util.showNoConnectionDialog(activity);
        }
    }
    private void OnNewSearchClicked() {
        if (appData.getConnectionDetector().isConnectingToInternet()) {
            startActivity(new Intent(this.getActivity(), SearchNewOfferActivity.class));
        } else {
            Util.showNoConnectionDialog(this.getActivity());
        }
    }


    private void showProgressDialog() {
        if (!activity.isFinishing() && pdialog == null) {
            pdialog = ProgressDialog.show(activity, getResources().getString(R.string.alert_title_loading), getResources()
                    .getString(R.string.alert_body_wait));
        }
    }

    private void cancelProgressDialog() {
        if (pdialog != null && !activity.isFinishing()) {
            pdialog.dismiss();
            pdialog = null;
        }
    }
}
