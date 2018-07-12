package in.vin.main.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import in.vin.main.R;
import in.vin.main.fragments.BaseFragment;
import in.vin.main.utils.SystemUtil;
import in.vin.main.webService.VollyWebService;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(getContentView());
    }

    private void init() {
        VollyWebService.getInstance(getApplicationContext());
        SystemUtil.getInstance(getApplicationContext());
    }

    public void loadFragment(BaseFragment fragment, boolean addToBackStack){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        if(addToBackStack) {
            ft.addToBackStack(fragment.toString());
        }
        ft.commit();
    }


    public abstract int getContentView();
}
