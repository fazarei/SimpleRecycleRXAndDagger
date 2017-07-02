package com.example.farzi.testrecycleview.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farzi.testrecycleview.R;
import com.example.farzi.testrecycleview.applicatopn.FlowerApplication;
import com.example.farzi.testrecycleview.base.FlowerPresenter;
import com.example.farzi.testrecycleview.model.FlowerAdapter;
import com.example.farzi.testrecycleview.model.FlowerResponse;
import com.example.farzi.testrecycleview.service.FlowerService;
import com.example.farzi.testrecycleview.service.FlowerViewInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements FlowerViewInterface,FlowerAdapter.FlowerClickListener {

    @Inject
    FlowerService mService;

    private FlowerPresenter mPresenter;

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.search_flower_name)
    EditText mSearchBox;
    private FlowerAdapter mAdapter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);
        configView();
        mSearchBox.addTextChangedListener(mQueryWatcher);
        mPresenter = new FlowerPresenter(MainActivity.this);
        mPresenter.onCreate();
    }

    private void resolveDependency() {
        ((FlowerApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configView() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FlowerAdapter(this,getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onComplete() {
       mDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchFlowers();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait ...");
        mDialog.show();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFlower(List<FlowerResponse> flowerResponses) {
        mAdapter.addFlower(flowerResponses);
    }

    @Override
    public Observable<List<FlowerResponse>> getFlowers() {
        return mService.getFlower();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext(),"You clicked on" + name, Toast.LENGTH_LONG).show();
    }

    private TextWatcher mQueryWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mAdapter.filter(s.toString());
        }
    };
}
