package ru.mirea.komaristyi.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.komaristyi.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable run1 = new Runnable() {
            public void run() {
                binding.textView.setText("run1");
            }
        };
        final Runnable run2 = new Runnable() {
            public void run() {
                binding.textView.setText("run2");

            }
        };
        final Runnable run3 = new Runnable() {
            public void run() {
                binding.textView.setText("run3");
            }
        };
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(run1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.textView.postDelayed(run3, 2000);
                    binding.textView.post(run2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}