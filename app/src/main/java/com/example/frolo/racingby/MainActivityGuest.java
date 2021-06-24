package com.example.frolo.racingby;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.frolo.racingby.fragments.AboutFragment;
import com.example.frolo.racingby.fragments.HelpFragment;
import com.example.frolo.racingby.pilots.SergeyKabarginFragment;
import com.example.frolo.racingby.pilots.AndreyPiskarevFragment;
import com.example.frolo.racingby.pilots.DmitriyNagulaFragment;
import com.example.frolo.racingby.fragments.EventsFragment;
import com.example.frolo.racingby.fragments.NewsFragment;
import com.example.frolo.racingby.fragments.PilotsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityGuest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;

    NewsFragment newsFragment;
    EventsFragment eventsFragment;
    PilotsFragment pilotsFragment;
    AboutFragment aboutFragment;
    HelpFragment helpFragment;
    DmitriyNagulaFragment dmitriyNagulaFragment;
    AndreyPiskarevFragment andreyPiskarevFragment;
    SergeyKabarginFragment sergeyKabarginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        newsFragment = new NewsFragment();
        eventsFragment = new EventsFragment();
        pilotsFragment = new PilotsFragment();
        aboutFragment = new AboutFragment();
        helpFragment = new HelpFragment();
        dmitriyNagulaFragment = new DmitriyNagulaFragment();
        andreyPiskarevFragment = new AndreyPiskarevFragment();
        sergeyKabarginFragment = new SergeyKabarginFragment();

        // Получить NavigationView
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        // Получить разметку его Header
        View header = nv.getHeaderView(0);
        // Найти нужные поля и с ними что-то сделать

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        // Получить NavigationView
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        // Получить разметку его Header
        View header = nv.getHeaderView(0);

        if (eventsFragment.isVisible() == true){
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (pilotsFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (dmitriyNagulaFragment.isVisible() == true) {
            ftrans.replace(R.id.container, pilotsFragment);
        } if (andreyPiskarevFragment.isVisible() == true) {
            ftrans.replace(R.id.container, pilotsFragment);
        } if (sergeyKabarginFragment.isVisible() == true) {
            ftrans.replace(R.id.container, pilotsFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_guest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.help_item_guest) {
            ftrans.replace(R.id.container, helpFragment);
        } else if (id == R.id.about_item) {
            ftrans.replace(R.id.container, aboutFragment);
        } else if (id == R.id.leave_item_guest) {
            Intent i = new Intent(MainActivityGuest.this, LoginActivity.class);
            startActivity(i);
            FirebaseAuth.getInstance().signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.news_item_guest) {
            if(newsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.containerGuest, newsFragment);
            }
        } else if (id == R.id.events_item_guest) {
            if (eventsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.containerGuest, eventsFragment);
            }
        } else if (id == R.id.pilots_item_guest) {
            if (pilotsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.containerGuest, pilotsFragment);
            }
        } else if (id == R.id.vk_item_guest) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.vk.com/racingby"));
            startActivity(browserIntent);
        } else if (id == R.id.facebook_item_guest) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/RACINGBY/"));
            startActivity(browserIntent);
        } else if (id == R.id.inst_item_guest) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/racingby/"));
            startActivity(browserIntent);
        }else if (id == R.id.racingby_item_guest) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://racing.by/"));
            startActivity(browserIntent);
        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Дмитрий Нагула
    public void btnDmitriyNagula_Click(View v) {
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.containerGuest, dmitriyNagulaFragment);
        ftrans.commit();
    }
    public void btnDmitriyNagulaVK_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.vk.com/dimanagula"));
        startActivity(browserIntent);
    }
    public void btnDmitriyNagulaInst_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/dimanagula/"));
        startActivity(browserIntent);
    }
    // Андрей Пискарёв
    public void btnAndreyPiskarev_Click(View v){
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.containerGuest, andreyPiskarevFragment);
        ftrans.commit();
    }
    public void btnAndreyPiskarevInst_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/andrey_piskarev/"));
        startActivity(browserIntent);
    }

    // Сергей Кабаргин
    public void btnSergeyKabargin_Click(View v) {
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.containerGuest, sergeyKabarginFragment);
        ftrans.commit();
    }
    public void btnSergeyKabarginSite_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://kabargin.ru/"));
        startActivity(browserIntent);
    }
    public void btnSergeyKabarginVK_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.vk.com/ddkaba"));
        startActivity(browserIntent);
    }
    public void btnSergeyKabarginFB_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/kabargin.drift"));
        startActivity(browserIntent);
    }
    public void btnSergeyKabarginYT_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/c/ddkaba"));
        startActivity(browserIntent);
    }
    public void btnSergeyKabarginInst_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/kabargin.drift/"));
        startActivity(browserIntent);
    }

}
