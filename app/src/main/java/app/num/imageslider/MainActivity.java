package app.num.imageslider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import app.num.imageslider.Header_Image_Slider.Animations.DescriptionAnimation;
import app.num.imageslider.Header_Image_Slider.SliderLayout;
import app.num.imageslider.Header_Image_Slider.SliderTypes.BaseSliderView;
import app.num.imageslider.Header_Image_Slider.SliderTypes.TextSliderView;
import app.num.imageslider.Header_Image_Slider.Tricks.ViewPagerEx;

/**
 * When you want to make your own slider view, you must implements from BaseSliderView and ViewPagerEx class.
 */

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.banner);
        file_maps.put("Big Bang Theory",R.drawable.banner2);
        file_maps.put("House of Cards",R.drawable.banner3);
        file_maps.put("Game of Thrones", R.drawable.banner);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(5000);
        imageSlider.addOnPageChangeListener(this);


    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        imageSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
