package in.vin.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import in.vin.main.R;
import in.vin.main.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

    private Context context;
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        if (view == null) { // Do not create View multiple times...
            view = inflater.inflate(getLayout(), container, false);
            onCreateView();
        }
        return view;
    }

    public void loadFragment(BaseFragment fragment,boolean addToBackstack){
        BaseActivity baseActivity = (BaseActivity)getActivity();
        baseActivity.loadFragment(fragment,addToBackstack);
    }

    protected abstract void onCreateView();

    protected abstract int getLayout();
}
