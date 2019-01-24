package com.app.drylining.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.drylining.R;
import com.app.drylining.custom.AdminBar;
import com.app.drylining.custom.AdminPanel;
import com.app.drylining.custom.AppDebugLog;
import com.app.drylining.custom.CustomMainActivity;
import com.app.drylining.custom.CustomViewPager;
import com.app.drylining.data.AppConstant;
import com.app.drylining.data.ApplicationData;
import com.app.drylining.fragment.SearchOfferByAddressFragment;
import com.app.drylining.network.SearchOfferParameters;

import java.util.ArrayList;
import java.util.List;

public class SearchNewOfferActivity extends CustomMainActivity implements TabLayout.OnTabSelectedListener
{
    private Toolbar toolbar;
    private int currentTabIndex;
    private TabLayout tabLayout;

    private CustomViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private SearchOfferByAddressFragment searchOfferByAddressFragment;

    private SearchOfferParameters previousParam;

    private AdminBar adminbar;
    private AdminPanel adminPanel;
    private LinearLayout adminLayout;
    private ApplicationData appData;

    private Animation animShow, animHide;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_offer);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        adminLayout= (LinearLayout) findViewById(R.id.adminBar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchNewOfferActivity.this.onBackPressed();
            }
        });

        initialize();
    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        Drawable launcher = ContextCompat.getDrawable(this, R.mipmap.app_small_toolbar_icon);
        toolbar.setNavigationIcon(launcher);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent firstActivityIntent = new Intent(SearchNewOfferActivity.this, DashboardActivity.class);
                firstActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SearchNewOfferActivity.this.startActivity(firstActivityIntent);
            }
        });

        adminbar = new AdminBar(this);
        adminbar.setUserName(appData.getUserName());
        adminbar.setUserId(appData.getUserId());
        adminbar.setMessages(appData.getCntMessages());

        adminPanel = new AdminPanel(this);
    }

    @Override
    public void openAdminPanel()
    {
        adminLayout.setVisibility(View.VISIBLE);
        adminLayout.startAnimation( animShow );

        new CountDownTimer(AppConstant.ADMIN_BAR_OPEN_TIME, 100) {

            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                adminbar.imgOpenArrow.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    public void closeAdminPanel()
    {
        adminLayout.setVisibility(View.GONE);
        adminLayout.startAnimation( animHide );

        adminbar.imgOpenArrow.setVisibility(View.GONE);
        recreate();
    }

    @Override
    public void onBackPressed()
    {
        if(adminLayout.getVisibility() == View.VISIBLE)
            closeAdminPanel();
        else {
            super.onBackPressed();
            //startActivity(new Intent(this, SearchedOffersActivity.class));
            finish();
        }
    }

    private void initialize()
    {
        appData = ApplicationData.getSharedInstance();
        previousParam = (SearchOfferParameters)getIntent().getSerializableExtra("searchParam");// from new-> null, edit->available values
        setInitialUI();
    }

    private void setInitialUI()
    {
        initializeFragments();
        setToolbar();

        animShow = AnimationUtils.loadAnimation( this, R.anim.view_show);
        animHide = AnimationUtils.loadAnimation( this, R.anim.view_hide);

        setupViewPager();
    }

    private void initializeFragments() {
        searchOfferByAddressFragment = new SearchOfferByAddressFragment();
    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(searchOfferByAddressFragment, getString(R.string.tab_title_seach_offer_by_address));

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
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

    public void tabChanged(TabLayout.Tab tab) {
        int position = tab.getPosition();
        currentTabIndex = position;

        AppDebugLog.println("In tabChanged : " + currentTabIndex);
        switch (currentTabIndex) {
            case AppConstant.TAB_SEARCH_OFFER_BY_ADDRESS:
                searchOfferByAddressFragment.tabChanged();
                break;
            case AppConstant.TAB_SEARCH_OFFER_PICK_FROM_MAP:
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
            case AppConstant.TAB_SEARCH_OFFER_BY_ADDRESS:
                searchOfferByAddressFragment.tabUnSelected();
                break;
            case AppConstant.TAB_SEARCH_OFFER_PICK_FROM_MAP:
                break;
            default:
                break;
        }

        viewPager.setCurrentItem(position, true);
    }

    public SearchOfferParameters getPreviousParams()
    {
        return previousParam;
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


    public boolean onCreateOptionsMenu(Menu menu){

 //       getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==R.id.action_logout){


            Intent loginIntent=new Intent(getApplicationContext(),MainActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
