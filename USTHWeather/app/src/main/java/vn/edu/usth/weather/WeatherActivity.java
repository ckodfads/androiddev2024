package vn.edu.usth.weather;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import vn.edu.usth.weather.HomeFragmentAdapter;
import com.google.android.material.tabs.TabLayout;


public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        // Create a new Fragment to be placed in the activity
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
// Add the fragment to the 'container' FrameLayout

        ViewPager viewPager = findViewById(R.id.viewpager);
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

}
