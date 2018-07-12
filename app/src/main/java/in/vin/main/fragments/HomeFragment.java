package in.vin.main.fragments;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.vin.main.R;
import in.vin.main.adapter.HomeAdaptor;
import in.vin.main.constants.ApplicationConstants;
import in.vin.main.constants.ServerConstants;
import in.vin.main.listeners.PaginationScrollListener;
import in.vin.main.pogo.JsonParser;
import in.vin.main.pogo.People;
import in.vin.main.webService.VollyWebService;

public class HomeFragment extends BaseFragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    private HomeAdaptor adapter;
    private String nextUrl;
    private String previousUrl;
    private int currentPage = 0;
    private boolean isLastPage;
    private boolean isLoading;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreateView() {

        nextUrl = ServerConstants.getHomeWebServiceURL();
        isLastPage = false;

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.container);
        layoutManager = new GridLayoutManager(getActivity(), 1);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdaptor(getActivity());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                loadNextPage();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadNextPage();
    }

    private void loadNextPage() {
        if (!isLastPage){
            if(nextUrl != null && !nextUrl.isEmpty() && !nextUrl.equalsIgnoreCase("null")){

                People people = new People();
                people.type = ApplicationConstants.TYPE_LOADING;
                adapter.add(adapter.getItemCount(),people);

                currentPage++;
                isLoading = true;
                loadData(nextUrl);
            } else {
                isLastPage = true;
            }
        }
    }

    private void loadData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,
                        null, this,this);
        VollyWebService.getInstance().addRequest(jsonObjectRequest);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    /**
     * Called when a response is received.
     *
     * @param response
     */
    @Override
    public void onResponse(JSONObject response) {
        if(isLoading) {
            adapter.remove(adapter.getItemCount()-1);
        }
        try {
            if(response.has("next")){
                this.nextUrl = response.getString("next");
            }

            if(response.has("previous")){
                this.previousUrl = response.getString("previous");
            }

            if(response.has("results")){
                JSONArray results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    People people = JsonParser.getInstance().getPeopleFromJson(results.getJSONObject(i));
                    adapter.add(adapter.getItemCount(),people);
                }
            }
            isLoading = false;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @SuppressLint("LongLogTag")
    @Override
    public void onErrorResponse(VolleyError error) {

        String msg1 = "Error Found !!";
        String msg2 = "Unknown Error Found";
        if (error instanceof NoConnectionError) {
            //This indicates that the reuest has either time out or there is no connection
            msg1 = ApplicationConstants.NO_CONNECTION_ERROR_TITLE;
            msg2 = ApplicationConstants.NO_CONNECTION_ERROR_MESSAGE;
        } else if (error instanceof TimeoutError ) {
            //This indicates that the reuest has either time out or there is no connection
            msg1 = ApplicationConstants.SERVER_TIME_OUT_TITLE;
            msg2 = ApplicationConstants.SERVER_TIME_OUT_MESSAGE;
        } else if (error instanceof AuthFailureError) {
            // Error indicating that there was an Authentication Failure while performing the request
            msg1 = ApplicationConstants.AUTH_ERROR_TITLE;
            msg2 = ApplicationConstants.AUTH_ERROR_MESSAGE;
        } else if (error instanceof ServerError) {
            //Indicates that the server responded with a error response
            msg1 = ApplicationConstants.SERVER_ERROR_TITLE;
            msg2 = ApplicationConstants.SERVER_ERROR_MESSAGE;
        } else if (error instanceof NetworkError) {
            //Indicates that there was network error while performing the request
            msg1 = ApplicationConstants.NETWORK_ERROR_TITLE;
            msg2 = ApplicationConstants.NETWORK_ERROR_MESSAGE;
        } else if (error instanceof ParseError) {
            // Indicates that the server response could not be parsed
            msg1 = ApplicationConstants.PARSE_ERROR_TITLE;
            msg2 = ApplicationConstants.PARSE_ERROR_MESSAGE;
        }
        NoInterNetConnectionFragment fragment = new NoInterNetConnectionFragment(msg1,msg2);
        loadFragment(fragment,false);
    }
}
