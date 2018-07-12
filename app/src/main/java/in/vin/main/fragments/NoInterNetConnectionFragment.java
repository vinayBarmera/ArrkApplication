package in.vin.main.fragments;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import in.vin.main.R;

public class NoInterNetConnectionFragment extends BaseFragment {


    private String msg1;
    private String msg2;
    private String msg3;

    public NoInterNetConnectionFragment(){

    }

    @SuppressLint("ValidFragment")
    public NoInterNetConnectionFragment(String msg1, String msg2){
        this.msg1 = msg1;
        this.msg2 = msg2;
    }

    @Override
    protected void onCreateView() {
        TextView msg1TextView = (TextView) view.findViewById(R.id.msg1);
        msg1TextView.setText(msg1);

        TextView msg2TextView = (TextView) view.findViewById(R.id.msg2);
        msg2TextView.setText(msg2);

        TextView retryTextView = (TextView) view.findViewById(R.id.retryTextView);
        retryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                loadFragment(homeFragment,false);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_no_internet_connection;
    }
}
