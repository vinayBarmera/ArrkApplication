package in.vin.main.activities;

import android.os.Bundle;
import android.widget.Toast;

import in.vin.main.R;
import in.vin.main.fragments.HomeFragment;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFragment(new HomeFragment(),true);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count > 1){
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        }
    }

}
