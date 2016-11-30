package com.blindingdark.geektrans.activity.settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.blindingdark.geektrans.R;
import com.blindingdark.geektrans.adapter.EngineInfoRecyclerViewAdapter;
import com.blindingdark.geektrans.bean.TransEngineInfo;
import com.blindingdark.geektrans.global.SeqMainSettings;
import com.blindingdark.geektrans.global.StringMainSettings;
import com.blindingdark.geektrans.global.TransEngineInfoFactory;
import com.blindingdark.geektrans.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private RecyclerView recyclerViewEngines;
    EngineInfoRecyclerViewAdapter engineInfoRecyclerViewAdapter;
    private List<TransEngineInfo> transEngineInfos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();


        Set<String> defaultTransSet = SeqMainSettings.getDefaultEngines();
        // 得到当前已添加的引擎列表

        final HashSet<String> nowTransEngines = (HashSet<String>) preferences.getStringSet(StringMainSettings.NOW_ENGINE_LIST, defaultTransSet);

        for (String transEPackageName : nowTransEngines) {
            transEngineInfos.add(TransEngineInfoFactory.getTransEngineInfo(transEPackageName));
        }

        recyclerViewEngines = (RecyclerView) findViewById(R.id.recyclerViewEngines);

        recyclerViewEngines.setLayoutManager(new LinearLayoutManager(this));
        engineInfoRecyclerViewAdapter = new EngineInfoRecyclerViewAdapter(this, transEngineInfos);
        recyclerViewEngines.setAdapter(engineInfoRecyclerViewAdapter);

        // 设置分割线
        recyclerViewEngines.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        // 设置添加动画
        recyclerViewEngines.setItemAnimator(new DefaultItemAnimator());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                Set<String> allEnginesTemp = SeqMainSettings.getAllEngines();
                //Set<String> defaultTransSet = SeqMainSettings.getDefaultEngines();
                // 得到当前已添加的引擎列表
                //final HashSet<String> nowTransEngines = (HashSet<String>) preferences.getStringSet(StringMainSettings.NOW_ENGINE_LIST, defaultTransSet);

                allEnginesTemp.removeAll(nowTransEngines);

                final List<TransEngineInfo> addableEInfoList = new ArrayList<>();
                List<String> showAddList = new ArrayList<>();
                for (String enginePackageName : allEnginesTemp) {
                    TransEngineInfo info = TransEngineInfoFactory.getTransEngineInfo(enginePackageName);
                    addableEInfoList.add(info);
                    showAddList.add(info.getEngineName());
                }


                builder.setItems(showAddList.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        nowTransEngines.add(addableEInfoList.get(arg1).getEnginePackageName());
                        editor.putStringSet(StringMainSettings.NOW_ENGINE_LIST, nowTransEngines);
                        editor.commit();
                        transEngineInfos.add(addableEInfoList.get(arg1));
                        arg0.dismiss();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        engineInfoRecyclerViewAdapter.notifyItemInserted(nowTransEngines.size());
                    }
                });
                builder.show();
/*                transEngineInfos.add(TransEngineInfoFactory.getTransEngineInfo(StringMainSettings.BAIDU_TRANS_ENGINE));

                engineInfoRecyclerViewAdapter.notifyDataSetChanged();*/

/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


        //0则不执行拖动或者滑动
        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            /**
             * @param viewHolder 拖动的ViewHolder
             * @return
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                nowTransEngines.remove(transEngineInfos.get(position).getEnginePackageName());

                editor.putStringSet(StringMainSettings.NOW_ENGINE_LIST, nowTransEngines);
                editor.commit();

                transEngineInfos.remove(position);
                engineInfoRecyclerViewAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //滑动时改变Item的透明度
                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);
                }
            }


        };
        // 添加触摸事件支持
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewEngines);
    }

}
