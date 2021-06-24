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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frolo.racingby.fragments.AboutFragment;
import com.example.frolo.racingby.fragments.HelpFragment;
import com.example.frolo.racingby.pilots.SergeyKabarginFragment;
import com.example.frolo.racingby.pilots.AndreyPiskarevFragment;
import com.example.frolo.racingby.pilots.DmitriyNagulaFragment;
import com.example.frolo.racingby.fragments.EventsFragment;
import com.example.frolo.racingby.fragments.GalleryFragment;
import com.example.frolo.racingby.fragments.NewsFragment;
import com.example.frolo.racingby.fragments.OnlineChatFragment;
import com.example.frolo.racingby.fragments.PilotsFragment;
import com.example.frolo.racingby.fragments.RulesFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private WebView webView;

    NewsFragment newsFragment;
    EventsFragment eventsFragment;
    GalleryFragment galleryFragment;
    OnlineChatFragment onlineChatFragment;
    PilotsFragment pilotsFragment;
    RulesFragment rulesFragment;
    AboutFragment aboutFragment;
    HelpFragment helpFragment;
    DmitriyNagulaFragment dmitriyNagulaFragment;
    AndreyPiskarevFragment andreyPiskarevFragment;
    SergeyKabarginFragment sergeyKabarginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        newsFragment = new NewsFragment();
        eventsFragment = new EventsFragment();
        galleryFragment = new GalleryFragment();
        onlineChatFragment = new OnlineChatFragment();
        pilotsFragment = new PilotsFragment();
        rulesFragment = new RulesFragment();
        aboutFragment = new AboutFragment();
        helpFragment = new HelpFragment();
        dmitriyNagulaFragment = new DmitriyNagulaFragment();
        andreyPiskarevFragment = new AndreyPiskarevFragment();
        sergeyKabarginFragment = new SergeyKabarginFragment();

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://drift.by/");

        // Получить NavigationView
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        // Получить разметку его Header
        View header = nv.getHeaderView(0);
        // Найти нужные поля и с ними что-то сделать
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        final TextView textView = (TextView)header.findViewById(R.id.textViewHead);
        firebaseAuth = FirebaseAuth.getInstance();
        textView.setText(user.getEmail());

        nv.getMenu().getItem(0).setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();

        MenuItem Gallery = menu.findItem(R.id.gallery_item);
        MenuItem Rules = menu.findItem(R.id.rules_item);
        MenuItem OnlineChat = menu.findItem(R.id.online_chat_item);
        MenuItem Vlog = menu.findItem(R.id.vlog_item);

        if (user.isEmailVerified() == false){
            OnlineChat.setVisible(false);
            Gallery.setVisible(false);
            Rules.setVisible(false);
            Vlog.setVisible(false);
            Toast.makeText(MainActivity.this, "Адрес эл. почты не подтверждён!", Toast.LENGTH_LONG).show();
        } else {
            OnlineChat.setVisible(true);
            Gallery.setVisible(true);
            Rules.setVisible(true);
            Vlog.setVisible(true);
        }
    }


    @Override
    public void onBackPressed() {

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        // Получить NavigationView
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        // Получить разметку его Header
        View header = nv.getHeaderView(0);

        if(aboutFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (eventsFragment.isVisible() == true){
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (galleryFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (onlineChatFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (rulesFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (pilotsFragment.isVisible() == true) {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(0).setChecked(true);
        } if (galleryFragment.isVisible() == true) {
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.settings_item) {
            Intent i = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(i);
        } else if (id == R.id.help_item) {
                ftrans.replace(R.id.container, helpFragment);
        } else if (id == R.id.about_item) {
            if (aboutFragment.isVisible() == true) {
                ftrans.replace(R.id.container, newsFragment);
            } else {
                ftrans.replace(R.id.container, aboutFragment);
            }
        } else if (id == R.id.leave_item) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            FirebaseAuth.getInstance().signOut();
        } ftrans.commit();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.news_item) {
            if(newsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.container, newsFragment);
            }
        } else if (id == R.id.events_item) {
            if (eventsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.container, eventsFragment);
            }
        } else if (id == R.id.gallery_item) {
            if (galleryFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.container, galleryFragment);
            }
        } else if (id == R.id.vlog_item) {
            Intent i = new Intent(MainActivity.this, VideoYTActivity.class);
            startActivity(i);
        } else if (id == R.id.rules_item) {
            if (rulesFragment.isVisible() == true){
            } else {
                ftrans.replace(R.id.container, rulesFragment);
            }
        } else if (id == R.id.pilots_item) {
            if (pilotsFragment.isVisible() == true) {
            } else {
                ftrans.replace(R.id.container, pilotsFragment);
            }
        } else if (id == R.id.online_chat_item) {
              ftrans.replace(R.id.container, onlineChatFragment);
        } else if (id == R.id.vk_item) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.vk.com/racingby"));
            startActivity(browserIntent);
        } else if (id == R.id.facebook_item) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/RACINGBY/"));
            startActivity(browserIntent);
        } else if (id == R.id.inst_item) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/racingby/"));
            startActivity(browserIntent);
        }else if (id == R.id.racingby_item) {
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
         ftrans.replace(R.id.container, dmitriyNagulaFragment);
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
        ftrans.replace(R.id.container, andreyPiskarevFragment);
        ftrans.commit();
    }
    public void btnAndreyPiskarevInst_Click(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.instagram.com/andrey_piskarev/"));
        startActivity(browserIntent);
    }

    // Сергей Кабаргин
    public void btnSergeyKabargin_Click(View v) {
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container, sergeyKabarginFragment);
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

    public void LeaveChat_Click(View v){
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        // Получить NavigationView
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        // Получить разметку его Header
        View header = nv.getHeaderView(0);
        // Найти нужные поля и с ними что-то сделать

        if(newsFragment.isVisible() == true) {
        } else {
            ftrans.replace(R.id.container, newsFragment);
            nv.getMenu().getItem(6).setChecked(false);
            nv.getMenu().getItem(0).setChecked(true);
        } ftrans.commit();
    }
}
