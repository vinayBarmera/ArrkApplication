package in.vin.main.webService;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

public class VollyWebService {
    private static VollyWebService ourInstance = null;
    private Context context;
    private RequestQueue requestQueue;

    public static VollyWebService getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new VollyWebService(context);
        }
        return ourInstance;
    }

    public static VollyWebService getInstance() {
        return ourInstance;
    }

    private VollyWebService(Context context) {
        this.context = context;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void addRequest(StringRequest request){
        requestQueue.add(request);
    }

    public void addRequest(JsonObjectRequest request){
        requestQueue.add(request);
    }


}
