package vn.edu.usth.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private static final int PERMISSION_REQUEST_CODE = 1;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ViewPager viewPager = findViewById(R.id.viewpager);
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // Kiểm tra quyền truy cập bộ nhớ ngoài và phát audio
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền nếu chưa được cấp
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            // Nếu đã có quyền, phát file MP3 từ bộ nhớ ngoài
            playAudioFromExternalStorage();
        }
    }

    // Khi người dùng đã chấp nhận hoặc từ chối yêu cầu quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Phát file MP3 nếu người dùng đã cấp quyền
                playAudioFromExternalStorage();
            } else {
                Log.e(TAG, "Permission Denied!");
            }
        }
    }

    // Phương thức phát file MP3 từ bộ nhớ ngoài
    private void playAudioFromExternalStorage() {
        // Tên file trong bộ nhớ ngoài (bạn có thể thay đổi tên file tùy theo file bạn có)
        String fileName = "your_audio_file.mp3";  // Đảm bảo file này có trên bộ nhớ ngoài
        File externalStorage = Environment.getExternalStorageDirectory();
        File audioFile = new File(externalStorage, fileName);

        if (audioFile.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                // Thiết lập file MP3 từ bộ nhớ ngoài làm nguồn dữ liệu
                mediaPlayer.setDataSource(audioFile.getAbsolutePath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                Log.i(TAG, "Đang phát file MP3 từ bộ nhớ ngoài: " + audioFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Không thể phát file MP3!");
            }
        } else {
            Log.e(TAG, "File không tồn tại: " + audioFile.getAbsolutePath());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng tài nguyên MediaPlayer khi Activity bị hủy
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
