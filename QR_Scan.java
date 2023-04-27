package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QR_Scan extends AppCompatActivity implements View.OnClickListener {
    Button scanBtn;
    TextView tvScanContent, tvScanFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = findViewById(R.id.btn1);
        tvScanContent = findViewById(R.id.tvScanContent);
        tvScanFormat = findViewById(R.id.tvScanFormat);

        scanBtn.setOnClickListener(this);

    }


    public void onClick(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this)
        integrator.setPrompt("Scan a barcode or QRcode");
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode,resultCode, data);
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                tvScanFormat.setText(result.getFormatName());
                tvScanContent.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
