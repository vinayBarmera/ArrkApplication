package in.vin.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import in.vin.main.R;
import in.vin.main.activities.BaseActivity;
import in.vin.main.constants.ApplicationConstants;
import in.vin.main.fragments.DetailFragment;
import in.vin.main.pogo.People;

public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.ViewHolder> {

    private final String TAG = "HomeAdaptor";
    private ArrayList<People> peoples;
    private Context context;

    public HomeAdaptor(Context context) {
        this.context = context;
        peoples = new ArrayList<People>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nameTextView = (TextView) v.findViewById(R.id.nameTextView);

        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public View layout;

        public LoadingViewHolder(View v) {
            super(v);
            layout = v;
            nameTextView = (TextView) v.findViewById(R.id.loadingTextView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return peoples.get(position).type;
    }

    public void add(int position, People people) {
        peoples.add(position, people);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        peoples.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case ApplicationConstants.TYPE_PEOPLE:
                return new ViewHolder(inflater.inflate(R.layout.home_list_item, parent,
                        false));

            case ApplicationConstants.TYPE_LOADING:
                return new ViewHolder(inflater.inflate(R.layout.loading_view, parent,
                        false));

        }
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final People people = peoples.get(position);
        switch (people.type){
            case ApplicationConstants.TYPE_PEOPLE:
                holder.nameTextView.setText(people.name);
                holder.nameTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DetailFragment detailFragment = new DetailFragment(people);
                        BaseActivity baseActivity = (BaseActivity)context;
                        baseActivity.loadFragment(detailFragment,true);
                    }
                });
                break;
            case ApplicationConstants.TYPE_LOADING:
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return peoples.size();
    }
}
