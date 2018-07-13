# Infinix-Indicator

Android view pager indicator library for infinite pages (>10) as used in Gradeup School and Facebook App

![](https://gs-post-images.grdp.co/2018/7/20180713144042-7c011ad592-img1531482075155-55-rs.gif)

![](https://gs-post-images.grdp.co/2018/7/20180713141153-7c011ad592-img1531480513566-29-rs.gif)

# Gradle Dependency
Add this line to your app level build.gradle file

```compile 'com.grappes:Infinix-Indicator:1.0.2'```


# Usage

Add InfinixIndicator view to your xml :

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ffffff"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context=".ExampleActivity">

   <com.grappes.infinixindicator.InfiniXIndicator
       android:layout_width="match_parent"
       android:layout_height="22dp"
       android:id="@+id/infinix_indicator"
       android:layout_marginBottom="40dp"
       app:x_background_color="@color/sample_bg"
       app:x_dot_unselected_color="@color/sample_dot_light"
       app:x_dot_selected_color="@color/sample_dot_dark"
       app:layout_constraintLeft_toLeftOf="parent"
       app:layout_constraintRight_toRightOf="parent"
       app:layout_constraintTop_toTopOf="parent"/>

   <android.support.v4.view.ViewPager
       android:id="@+id/view_pager"
       android:layout_width="match_parent"
       android:layout_height="0dp"
       app:layout_constraintLeft_toLeftOf="parent"
       app:layout_constraintRight_toRightOf="parent"
       app:layout_constraintBottom_toBottomOf="parent"
       app:layout_constraintTop_toBottomOf="@+id/infinix_indicator"
       tools:layout_editor_absoluteX="0dp"></android.support.v4.view.ViewPager>



</android.support.constraint.ConstraintLayout>
```

Now link InfinixIndicator to your view pager

```
ViewPager viewPager = findViewById(R.id.view_pager);
InfiniXIndicator infiniXIndicator = findViewById(R.id.infinix_indicator);

YourAdapter adapter = new YourAdapter(getSupportFragmentManager());
viewPager.setAdapter(adapter);
infiniXIndicator.setViewPager(viewPager);
```

NOTE : Now use InfinixIndicator's OnPageSelected listener instead of viewpager's

```
infiniXIndicator.setOnPageChangeListener(new InfiniXIndicator.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
```

# Customizations

```
app:x_background_color="@color/sample_bg"
app:x_dot_unselected_color="@color/sample_dot_light"
app:x_dot_selected_color="@color/sample_dot_dark"
```
