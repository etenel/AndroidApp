package com.androidapp.MainActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidapp.MainActivity.presenter.IMainPresenter;
import com.androidapp.MainActivity.presenter.MainPresenter;
import com.androidapp.MainActivity.view.IMainActivity;
import com.androidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    @BindView(R.id.fragment)
    FrameLayout fragment;
    private IMainPresenter mainPresenter;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.right)
    ImageButton right;
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

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                break;
            case R.id.right:
                break;
        }
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
