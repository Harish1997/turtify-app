package rexzen.turtify;

/**
 * Created by harishananth on 02/02/17.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager=(ViewPager)findViewById(R.id.pager);
        setupViewPager(viewPager);
        tablayout=(TabLayout)findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);

        if(tablayout!=null)
            tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPageAdapter viewpageadapter=new ViewPageAdapter(getSupportFragmentManager());
        viewpageadapter.add(new Fragment_1(),"Get Started");
        viewpageadapter.add(new Fragment_2(),"Report");
        viewpageadapter.add(new Fragment_3(),"View Nests");
        viewpageadapter.add(new Fragment_4(),"Turtle Report");
        viewPager.setAdapter(viewpageadapter);
    }

    private class ViewPageAdapter extends FragmentPagerAdapter
    {
        List<Fragment> fragmentlist= new ArrayList<>();
        List<String> fragmenttitle=new ArrayList<>();
        public ViewPageAdapter(FragmentManager supportFragmentManager)
        {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }

        public void add(Fragment fragment, String title)
        {
            fragmentlist.add(fragment);
            fragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuinfalter=getMenuInflater();
        menuinfalter.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.action_settings)
        {

        }
        if(id==R.id.action_logout){
            SharedPreferences.Editor editor = getSharedPreferences("login result", MODE_PRIVATE).edit();
            editor.putString("result","no");
            editor.putInt("st_value",0);
            editor.apply();
            Intent intent = new Intent(this, login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
