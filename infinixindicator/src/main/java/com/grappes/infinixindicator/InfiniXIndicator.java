package com.grappes.infinixindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;


public class InfiniXIndicator extends RelativeLayout {

    LayoutInflater mInflater;
    int lastPageAccessed = 0, lpa = 0;
    View slidingDot;
    LinearLayout sliderContainer;
    Context context;
    ViewPager viewPager;
    OnPageChangeListener onPageChangeListener;
    int backgroundColor;
    int dotUnselectedColor;
    int dotSelectedColor;

    public InfiniXIndicator(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init(context, null);
    }

    public InfiniXIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init(context, attrs);
    }

    public InfiniXIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        this.context = context;
        View v = mInflater.inflate(R.layout.infinix_container, this, true);
        slidingDot = v.findViewById(R.id.filled_dot);
        sliderContainer = v.findViewById(R.id.container);
        View parent = v.findViewById(R.id.parent);
        View leftParkingView = v.findViewById(R.id.left_cover_view);
        View rightParkingView = v.findViewById(R.id.right_cover_view);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.InfiniXIndicator,
                0, 0);

        try {
            backgroundColor = a.getColor(R.styleable.InfiniXIndicator_x_background_color, context.getResources().getColor(R.color.infinix_background));
            dotUnselectedColor = a.getColor(R.styleable.InfiniXIndicator_x_dot_unselected_color, context.getResources().getColor(R.color.infinix_dot_light_color));
            dotSelectedColor = a.getColor(R.styleable.InfiniXIndicator_x_dot_selected_color, context.getResources().getColor(R.color.infinix_dot_color));

            GradientDrawable shapeDrawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.dot_solid);
            shapeDrawable.setColor(dotSelectedColor);

            parent.setBackgroundColor(backgroundColor);
            leftParkingView.setBackgroundColor(backgroundColor);
            rightParkingView.setBackgroundColor(backgroundColor);
            slidingDot.setBackgroundDrawable(shapeDrawable);

        } finally {
            a.recycle();
        }

    }

    public void setViewPager(ViewPager vPager) {

        this.viewPager = vPager;

        if(viewPager==null)
        {
            throw new NullPointerException("View Pager cannot be null");
        }

        final PagerAdapter pagerAdapter = viewPager.getAdapter();
        generatePagerIndicator(pagerAdapter);
        if(pagerAdapter!=null)
        {
            if(pagerAdapter.getCount()>=10)
            {
                ((RelativeLayout.LayoutParams)slidingDot.getLayoutParams()).leftMargin = pxFromDp(context, 10);
            }
            else if(pagerAdapter.getCount() % 2 == 0)
            {
                ((RelativeLayout.LayoutParams) slidingDot.getLayoutParams()).leftMargin = (int) (pxFromDp(context, 10) * (5 - pagerAdapter.getCount()/2 + 1));
            }
            else if(pagerAdapter.getCount() % 2 == 1)
            {
                ((RelativeLayout.LayoutParams) slidingDot.getLayoutParams()).leftMargin = (int) (pxFromDp(context, 10) * (5 - pagerAdapter.getCount()/2 + 0.5));
            }
            slidingDot.requestLayout();
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(onPageChangeListener!=null)
                {
                    onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                boolean movingForward = false;
                boolean firstSet = false;

                if (lpa < position) {
                    movingForward = true;
                }

                lpa = position;

                if (!firstSet) {

                    if (((movingForward && position < 9) || (!movingForward && position < 8)) || (movingForward && position == pagerAdapter.getCount() - 1) || (!movingForward && position == pagerAdapter.getCount() - 2)) {

                        int index = position;
                        if(movingForward && position == (pagerAdapter.getCount() - 1) && pagerAdapter.getCount()>9)
                        {
                            index = 9;
                        }
                        else if((!movingForward && position == pagerAdapter.getCount() - 2) && pagerAdapter.getCount()>9)
                        {
                            index = 8;
                        }

                        slidingDot.setTranslationX((index*pxFromDp(context, 10)) + (positionOffsetPixels * 1.0f / getScreenWidth()) * pxFromDp(context, 10));
                    } else {
                        sliderContainer.setTranslationX((movingForward ? 1 : -1) * (positionOffsetPixels * 1.0f / getScreenWidth()) * pxFromDp(context, 10));
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

                if (Math.abs(position - lastPageAccessed) != 1) {

                    int index = position;
                    if (position == 9 && pagerAdapter.getCount() == 10) {
                        index = 9;
                    } else if (position >= 9 && pagerAdapter.getCount() > 10) {
                        index = 8;
                    }
                    slidingDot.setTranslationX(0);
                    slidingDot.animate().translationXBy(pxFromDp(context, 10) * index);
                }


                lastPageAccessed = position;

                if(onPageChangeListener!=null)
                {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(onPageChangeListener!=null)
                {
                    onPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        });
    }

    private void generatePagerIndicator(PagerAdapter pagerAdapter) {
        if (pagerAdapter.getCount() <= 1) {
            sliderContainer.setVisibility(View.GONE);
            slidingDot.setVisibility(View.GONE);
            return;
        }
        sliderContainer.setVisibility(View.VISIBLE);
        slidingDot.setVisibility(View.VISIBLE);


        int itemsToShow = pagerAdapter.getCount() >= 10 ? 14 : pagerAdapter.getCount();
        sliderContainer.removeAllViews();
        for (int i = 0; i < itemsToShow; i++) {
            View view = View.inflate(context, R.layout.dot, null);

            GradientDrawable shapeDrawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.dot_light);
            shapeDrawable.setColor(dotUnselectedColor);
            view.findViewById(R.id.dot_parent).setBackgroundDrawable(shapeDrawable);

            sliderContainer.addView(view);
        }
    }

    public static int pxFromDp(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener)
    {
        this.onPageChangeListener = onPageChangeListener;
    }

    public interface OnPageChangeListener {

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
