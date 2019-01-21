package lakercompany.adventure_war.Activnosty;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;

import lakercompany.adventure_war.Fragments.MapsActivity;
import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.Fragments.Magazin;
import lakercompany.adventure_war.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MapsActivity mapsActivity;
    private Magazin magazin;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;
    private SupportMapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        mapsActivity = new MapsActivity();
        mMapFragment = SupportMapFragment.newInstance();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.main_cont,mapsActivity,mapsActivity.TAG);
        mMapFragment = SupportMapFragment.newInstance();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();

        magazin = new Magazin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        fragmentTransaction = manager.beginTransaction();
        if (id == R.id.menu_maps) {
            if(manager.findFragmentByTag(magazin.TAG) != null)
                fragmentTransaction.remove(magazin);
            fragmentTransaction.show(mapsActivity);
        }
        else {
            if (id == R.id.menu_swards) {
                new UserShmot().ts = 1;
            } else if (id == R.id.menu_weapon) {
                new UserShmot().ts = 2;
            } else if (id == R.id.menu_amulet) {
                new UserShmot().ts = 3;
            }
            if(manager.findFragmentByTag(magazin.TAG) != null) {
                fragmentTransaction.remove(magazin);
                magazin = new Magazin();
            }
            fragmentTransaction.hide(mapsActivity);
            fragmentTransaction.add(R.id.main_cont, magazin,magazin.TAG);
        }

        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}