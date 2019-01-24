package com.app.drylining.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.drylining.R;
import com.app.drylining.custom.AdminBar;
import com.app.drylining.custom.AdminPanel;
import com.app.drylining.custom.AppDebugLog;
import com.app.drylining.custom.CustomMainActivity;
import com.app.drylining.custom.CustomViewPager;
import com.app.drylining.data.AppConstant;
import com.app.drylining.data.ApplicationData;
import com.app.drylining.fragment.LoginFragment;
import com.app.drylining.fragment.SignUpFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.quickblox.auth.session.QBSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends CustomMainActivity implements TabLayout.OnTabSelectedListener {
    static final String APP_ID = "69976";
    static final String AUTH_KEY = "KDtgekHTPepMj6D";
    static final String AUTH_SECRET = "jWW9c3AtJZ-bQRO";
    static final String ACCOUNT_KEY = "6Z4aqN3Lvm2LhhpPRDAN";
    private static final String TAG = "SplashActivity";
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1111;

    boolean isTablet;

    private ApplicationData appData;
    private Locale locale;

    private int currentTabIndex;
    private TabLayout tabLayout;

    private AdminBar adminbar;
    private AdminPanel adminPanel;
    private LinearLayout adminLayout;

    private Animation animShow, animHide;

    private CountDownTimer lTimer;

    private CustomViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;

    public static void showConfirmExitAlert(final Context context, String title, String message, final Activity contextToFinish) {
        final Dialog dialog = new Dialog(context);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setContentView(R.layout.dialog_custom_double_button);
        dialog.setTitle(title);

        TextView textView = (TextView) dialog.findViewById(R.id.dialogTxt);
        textView.setText(message);

        dialog.findViewById(R.id.dialogBtnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                contextToFinish.finish();
            }
        });

        dialog.findViewById(R.id.dialogBtnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (CustomViewPager) findViewById(R.id.viewPager);

        initialize();
    }

    private void initialize() {
        appData = ApplicationData.getSharedInstance();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("", "Refreshed token: " + token);
        AppDebugLog.println("FCM Token in onTokenRefresh : " + token);
        appData.setGCMTokenId(token);

        Resources resources = getResources();
        if (appData.getLocale() != null) {
            locale = appData.getLocale();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        }
        ;
        setInitialUI();
    }

    private void setInitialUI() {
        initializeFragments();
        setupViewPager();
    }

    @Override
    public void openAdminPanel() {
        adminLayout.setVisibility(View.VISIBLE);
        adminLayout.startAnimation(animShow);

        lTimer = new CountDownTimer(AppConstant.ADMIN_BAR_OPEN_TIME, 100) {

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
        try {
            if (adminLayout.getVisibility() == View.VISIBLE)
                closeAdminPanel();
            else {
                showConfirmExitAlert(this, "", "Are you sure to exit this app?", this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFragments() {
        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();
    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(signUpFragment, getResources().getString(R.string.tab_title_signup));
        viewPagerAdapter.addFragment(loginFragment, getResources().getString(R.string.tab_title_login));
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
    }

    public void tabChanged(TabLayout.Tab tab) {
        int position = tab.getPosition();
        currentTabIndex = position;

        AppDebugLog.println("In tabChanged : " + currentTabIndex);
        switch (currentTabIndex) {
            case AppConstant.TAB_LOGIN:
                loginFragment.tabChanged();
                break;
            case AppConstant.TAB_SIGNUP:
                signUpFragment.tabChanged();
                break;

            default:
                break;
        }
        viewPager.setCurrentItem(position, true);
    }

    private void tabUnSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        currentTabIndex = position;
        AppDebugLog.println("In tabUnSelected : " + currentTabIndex);
        switch (currentTabIndex) {
            case AppConstant.TAB_LOGIN:
                loginFragment.tabUnSelected();
                break;
            case AppConstant.TAB_SIGNUP:
                signUpFragment.tabUnSelected();
                break;
            default:
                break;
        }

        viewPager.setCurrentItem(position, true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabChanged(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tabUnSelected(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        AppDebugLog.println("In onTabReselected : " + tab.getPosition());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
