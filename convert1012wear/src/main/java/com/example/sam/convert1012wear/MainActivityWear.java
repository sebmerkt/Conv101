package com.example.sam.convert1012wear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityWear extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        Button lengthButton = findViewById(R.id.button_length);
        lengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mAreaIntent = new Intent(MainActivityWear.this, ConvertUnitsLength.class);
                MainActivityWear.this.startActivity(mAreaIntent);
            }
        });

    }
}
