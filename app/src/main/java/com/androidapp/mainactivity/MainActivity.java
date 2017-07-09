package com.androidapp.mainactivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.androidapp.R;
import com.androidapp.mainactivity.presenter.IMainPresenter;
import com.androidapp.mainactivity.presenter.MainPresenter;
import com.androidapp.mainactivity.view.IMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    @BindView(R.id.fragment)
    FrameLayout fragment;
    private IMainPresenter mainPresenter;
    @BindView(R.id.rb_product)
    RadioButton rbProduct;
    @BindView(R.id.rb_magazine)
    RadioButton rbMagazine;
    @BindView(R.id.rb_talent)
    RadioButton rbTalent;
    @BindView(R.id.rb_share)
    RadioButton rbShare;
    @BindView(R.id.rb_persion)
    RadioButton rbPersion;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        mainPresenter = new MainPresenter(this, this);
        initListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        rgGroup.setOnCheckedChangeListener((radioGroup, id) -> mainPresenter.onCheckedChanged(fragmentManager, fragment, id));
        rgGroup.check(R.id.rb_product);
    }

    @Override
    public void showToast() {

    }
}
