package in.vin.main.fragments;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import in.vin.main.R;
import in.vin.main.pogo.People;

public class DetailFragment extends BaseFragment {

    private People people;

    @SuppressLint("ValidFragment")
    public DetailFragment(People people) {
        super();
        this.people = people;
    }

    public DetailFragment() {
        super();
    }


    @Override
    protected void onCreateView() {
        TextView valueName = (TextView) view.findViewById(R.id.valueName);
        valueName.setText(people.name);
        TextView valueHeight = (TextView) view.findViewById(R.id.valueHeight);
        valueHeight.setText(people.height+" Meters");
        TextView valueMass = (TextView) view.findViewById(R.id.valueMass);
        valueMass.setText(people.mass +" KG");
        TextView valueDateAndTime = (TextView) view.findViewById(R.id.valueDateTime);
        valueDateAndTime.setText(people.createdDate);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_detail;
    }
}
