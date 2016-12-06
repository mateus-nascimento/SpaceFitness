package ledare.com.br.spacefitness.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ledare.com.br.spacefitness.R;

import static ledare.com.br.spacefitness.activity.MainActivity.USER_LOGIN;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ProgressDialog mProgress;

    protected void setupToolbar(String titulo){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(titulo);
        }
    }

    protected void setupNavigation(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if(navigationView != null && drawerLayout != null){
            setupHeader(navigationView);
            //
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            drawerLayout.closeDrawers();
                            onNavigationSelected(menuItem);
                            return true;
                        }
                    }
            );
        }

        //Animação
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.bringToFront();
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    private void setupHeader(NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        TextView mNome = (TextView) headerView.findViewById(R.id.header_nome);
        mNome.setText("Cremosa");
    }

    private void onNavigationSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_item_treino:
                Toast.makeText(this, "Treino", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_item_sair:
                sair();
        }
    }

    private void sair() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean(USER_LOGIN, false).apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    protected void openDrawer(){
        if(drawerLayout != null){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    protected void closeDrawer(){
        if(drawerLayout != null){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    protected void showProgress(){
        if (mProgress == null) {
            mProgress = new ProgressDialog(this);
            mProgress.setCancelable(false);
            mProgress.setMessage("Carregando...");
        }
        mProgress.show();
    }

    protected void hideProgress(){
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

}
