package co.gradeup.infinixindicator;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    String[] colors = new String[] {"#ffaa22", "#ffea62", "#82a522", "#f1a322", "#4faa22",
            "#3fba22", "#5fca22", "#6fda22", "#7faa43", "#8f6a22",
            "#166a2e", "#4faa2d", "#e5aa2f", "#7da42c"};

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_color, container, false);
        v.setBackgroundColor(Color.parseColor(colors[this.getArguments().getInt("index")]));
        return v;
    }

}
