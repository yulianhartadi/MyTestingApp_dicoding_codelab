package com.example.rdstudio.mytestingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSetValue;
    private TextView tvText;
    private ArrayList<String> names;

    ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSetValue = findViewById(R.id.btn_set_value);

        tvText = findViewById(R.id.tv_text);
        btnSetValue.setOnClickListener(this);

        names = new ArrayList<>();
        names.add("Narenda Wicaksono");
        names.add("Kevin");
        names.add("Yoza");

        // Adding big image to simulate error of "Out Of Memory Exception
        //imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.Fronalpstock_big));

        // Solution to error above is use 3rd party library like Glide library
        // https://github.com/bumptech/glide
        Glide.with(this).load(R.drawable.Fronalpstock_big).into(imgPreview);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_set_value) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < names.size(); i++) {
                name.append(names.get(i)).append("\n");
            }
            tvText.setText(name.toString());

            // try to add thread sleep to simulate error Application Not Responding
            /*try {
                Thread.sleep(3000000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }*/


        }
    }

    private static class delayAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("Delay Async", "Done");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("Delay Async", "Cancelled");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (delayAsync != null){
            if (delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)){
                delayAsync.cancel(true);
            }
        }
    }
}
