package ru.mirea.komaristyi.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.komaristyi.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int count;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = binding.textView;
        Button button = binding.button;

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("Мой номер группы: БСБО-06-21, Мой номер по списку: 12, Мой любимый фильм: Топ Ган: Маверик");
        textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        //- «getStackTrace» – получение стэка вызываемых методов;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = count++;
                        Log.d("ThreadProject", String.format("Запущен	поток:	№%d / Cтудентом	группы БСБО-06-21 /	Номер	по списку:	12", numberThread));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        if (String.valueOf(binding.editTextCouple.getText()).isEmpty()) {
                            throw new IllegalArgumentException("String is empty");
                        } else {
                            float couples = Integer.parseInt(String.valueOf(binding.editTextCouple.getText()));
                            float days = Integer.parseInt(String.valueOf(binding.editTextDays.getText()));
                            float num = couples / days;
                            if (days != 0 && couples != 0) {
                                Log.d("ThreadProject", "Среднее колличество пар:	" + num);
                            }
                        }
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime:	" + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток №	" + numberThread);
                        }
                    }
                }).start();

                if (String.valueOf(binding.editTextCouple.getText()).isEmpty()) {
                    throw new IllegalArgumentException("String is empty");
                } else {
                    float couples = Integer.parseInt(String.valueOf(binding.editTextCouple.getText()));
                    float days = Integer.parseInt(String.valueOf(binding.editTextDays.getText()));
                    float num = couples / days;
                    if (days != 0 && couples != 0) {

                        Log.d("ThreadProject", "Среднее колличество пар:	" + num);
                        binding.textView.setText("Среднее число пар: " + num);
                    }
                }
            }
        });
    }
}