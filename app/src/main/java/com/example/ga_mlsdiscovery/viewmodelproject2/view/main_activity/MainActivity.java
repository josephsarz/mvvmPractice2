package com.example.ga_mlsdiscovery.viewmodelproject2.view.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ga_mlsdiscovery.viewmodelproject2.R;
import com.example.ga_mlsdiscovery.viewmodelproject2.view.main_activity.vm_fragment_1.VMfragment_1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new VMfragment_1())
                    .addToBackStack("fragment")
                    .commit();
        }
    }
}
