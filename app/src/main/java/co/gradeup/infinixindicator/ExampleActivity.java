package co.gradeup.infinixindicator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grappes.infinixindicator.InfiniXIndicator;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        ViewPager viewPager = findViewById(R.id.view_pager);
        InfiniXIndicator infiniXIndicator = findViewById(R.id.infinix_indicator);

        InfinixAdapter adapter = new InfinixAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        infiniXIndicator.setViewPager(viewPager);
    }
}
