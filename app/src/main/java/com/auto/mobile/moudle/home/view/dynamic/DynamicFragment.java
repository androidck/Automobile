package com.auto.mobile.moudle.home.view.dynamic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;
import com.auto.mobile.moudle.home.view.dynamic.type.FactoryFragment;
import com.auto.mobile.moudle.home.view.dynamic.type.TrendsFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.auto.mobile.common.util.DensityUtil.dip2px;

/**
 * 动态
 */
public class DynamicFragment extends BaseFragment {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private static final int ONE = 1;
    private static final int TWO = 2;
    @BindView(R.id.tv_city_select)
    TextView tvCitySelect;
    @BindView(R.id.im_data)
    ImageView imData;



    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.dynamic_layout, null);
        unbinder = ButterKnife.bind(this, layout);
        initView();
        return layout;
    }

    //初始化控件
    private void initView() {
        tablayout.addTab(tablayout.newTab().setText("动态"));
        tablayout.addTab(tablayout.newTab().setText("汽修厂"));
        reflex(tablayout);
        //创建子页面
        MyPageAdapter adapter = new MyPageAdapter(getChildFragmentManager());
        adapter.addFragment(TrendsFragment.newInstance(ONE), "动态");
        adapter.addFragment(FactoryFragment.newInstance(TWO), "汽修厂");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick({R.id.tv_city_select, R.id.im_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city_select:

                break;
            case R.id.im_data:
                break;
        }
    }





    //tablayout 适配器
    private class MyPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragment = new ArrayList<Fragment>();
        private final List<String> mFragmentTitle = new ArrayList<String>();

        public void addFragment(Fragment fragment, String title) {
            mFragment.add(fragment);
            mFragmentTitle.add(title);
        }

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitle.get(position);
        }
    }

}
