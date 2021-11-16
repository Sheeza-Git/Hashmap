package com.maxworth.mespl.android.brs.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.maxworth.mespl.android.brs.R;
import com.maxworth.mespl.android.brs.databinding.ActivityListBinding;
import com.maxworth.mespl.android.brs.pojo.TrolleyDetail;
import com.maxworth.mespl.android.brs.viewmodels.ListModel;

/**
 * Created by mars on 24/05/18.
 */

public class ListActivity extends MesplBaseActivity {

    public String flightId;
    public ListModel listModel;
    public TrolleyDetail selectedTrolley;
    public ActivityListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        listModel = new ListModel(this);
        binding.setListModel(listModel);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if(getIntent().hasExtra("flightId")){
            flightId = getIntent().getExtras().getString("flightId");
        }


        if(getIntent().hasExtra("selectedTrolley")){
            selectedTrolley = new Gson().fromJson(getIntent().getExtras().getString("selectedTrolley"),TrolleyDetail.class);

            if(getIntent().getAction()!=null){
                if(getIntent().getAction().equalsIgnoreCase("SCAN")){
                    listModel.getBagtagList(flightId,selectedTrolley.getTrolleyNo(),"SCAN");
                }else  if(getIntent().getAction().equalsIgnoreCase("OFFLOAD")){
                    listModel.getBagtagList(flightId,selectedTrolley.getTrolleyNo(),"OFFLOAD");
                }else  if(getIntent().getAction().equalsIgnoreCase("TOTAL")){
                    listModel.getBagtagList(flightId,"","TOTAL");
                }
            }else{
                listModel.getBagtagList(flightId,"","TOTAL");
                //show some alerts
            }//End of Action
        }else{
            listModel.getBagtagList(flightId,"","TOTAL");
            //some some alerts
        }//End of selected trolley


    }



    public void leftClick(View view){
        finish();
    }


    public void rightClick(View view){

    }

}
