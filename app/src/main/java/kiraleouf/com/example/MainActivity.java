package kiraleouf.com.example;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

import kiraleouf.com.example.network.request.ServiceManager;

public class MainActivity extends Activity{
    ServiceManager.ServiceResponseListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createListener();
        initData();
    }

    private void initData() {
        try {
            ServiceManager.getInstance().getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attach the receiver when activity start or restart
     */
    @Override
    protected void onStart() {
        super.onStart();
        ServiceManager.getInstance().registerListener(mListener);
    }

    /**
     * Detach the receiver when activity closed to avoid errors
     */
    @Override
    protected void onStop() {
        super.onStop();
        ServiceManager.getInstance().unregisterListener(mListener);
    }

    /**
     * Initialize ServiceResponseListener
     * and implements callbacks
     */
    private void createListener() {
        mListener = new ServiceManager.ServiceResponseListener() {
            @Override
            public void onResponse(Object response) {
                //callback de l'appel , interpreter le résultat ici
                if(response != null && response instanceof String){
                    //Here String is json
                }
            }
        };
    }
}
