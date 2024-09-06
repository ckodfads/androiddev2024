package vn.edu.usth.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.graphics.Color; // for background colors


public class ForecastFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate an empty layout
        View view = inflater.inflate(R.layout.forecast_fragment, container, false);

        // Set background color dynamically
        // You can choose one of the colors (#20FF0000, #2000FF00, or #200000FF)
        view.setBackgroundColor(Color.parseColor("#20FF0000")); // Red with transparency

        return view;
    }
}

