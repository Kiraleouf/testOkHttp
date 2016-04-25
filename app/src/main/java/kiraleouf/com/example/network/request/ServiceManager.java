package kiraleouf.com.example.network.request;

import java.io.IOException;
import java.util.ArrayList;

import kiraleouf.com.example.network.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kira on 25/04/2016.
 */
public class ServiceManager implements IServiceManager{

    private static ServiceManager sInstance;
    private ArrayList<ServiceResponseListener> mListeners;


    public ServiceManager(){
        mListeners = new ArrayList<>();
    }

    public void registerListener(ServiceResponseListener listener){
        mListeners.add(listener);
    }

    public void unregisterListener(ServiceResponseListener listener){
        mListeners.remove(listener);
    }

    public static ServiceManager getInstance(){
        if(sInstance==null){
            sInstance = new ServiceManager();
        }
        return sInstance;
    }

    @Override
    public void getObject() throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = Constants.urlWS;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        notifyListener(response);

    }

    private void notifyListener(Object response){
        for (ServiceResponseListener listener:mListeners) {
            listener.onResponse(response);
        }
    }

    public interface ServiceResponseListener{
        void onResponse(Object response);
    }
}
