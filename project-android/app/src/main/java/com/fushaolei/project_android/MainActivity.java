package com.fushaolei.project_android;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.module.home.HomeFragment;
import com.fushaolei.project_android.module.my.MyFragment;
import com.fushaolei.project_android.module.vedio.VedioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@Route(path = ARouterConstant.MAIN_URL)
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private LinearLayout linearLayout;
    //用于记录上个选择的Fragment
    public static int lastFragment;
    private Fragment fragHome, fragVedio, fragMy;
    private Fragment[] mFragments;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
        getSupportActionBar().hide();
        initView();
        initFragment();
        permissionChecking();

    }
    private void permissionChecking() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
    }

    /**
     * 初始化fragment的展示效果
     */
    private void initFragment() {
        fragHome = new HomeFragment();
        fragVedio = new VedioFragment();
        fragMy = new MyFragment();
        mFragments = new Fragment[]{fragHome, fragVedio, fragMy};
        switch (lastFragment) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragVedio).show(fragVedio).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragMy).show(fragMy).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragHome).show(fragHome).commit();
                break;
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_home:
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                case R.id.tab_vedio:
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                case R.id.tab_my:
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    return true;
            }
            return true;
        });
    }

    /**
     * 切换fragment的展示页面
     */
    private void switchFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastIndex]);
        if (mFragments[index].isAdded() == false) {
            transaction.add(R.id.linear_frag_container, mFragments[index]);
        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        linearLayout = findViewById(R.id.linear_frag_container);
    }

}