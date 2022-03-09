package com.krisnadwia.ipaddressfinder;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUrl;
    private TextView tvHost, tvHostUrl, tvHostIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking components:

        etUrl = findViewById(R.id.et_url);
        Button button = findViewById(R.id.btn_find_ip);
        tvHost = findViewById(R.id.tv_host);
        tvHostUrl = findViewById(R.id.tv_host_url);
        tvHostIp = findViewById(R.id.tv_host_ip);

        Toast.makeText(this, "IP Address Finder", Toast.LENGTH_LONG).show();

        button.setOnClickListener(this); // adding listener to button
    }

    @Override
    public void onClick(View view) {

        final String url = etUrl.getText().toString(); // fetch URL

        // checks if URL field is empty:

        if (url.equals("")) {
            tvHost.setText("");
            tvHostUrl.setText("");
            tvHostIp.setText(R.string.str_url_null);
            return;
        }

        // try/catch block to fetch Host & IP:

        try {

            String hostIP = new NetTask().execute(url).get(); // Gets value NetTask Class

            // Checks for error:

            if (hostIP.equals("E")) {
                tvHost.setText("");
                tvHostUrl.setText("");
                tvHostIp.setText(R.string.str_error_msg);
                return;
            }

            // Updating components:

            Toast.makeText(this, "IP Found Successfully", Toast.LENGTH_SHORT).show();
            tvHost.setText(R.string.str_tv_host);
            tvHostUrl.setPaintFlags(tvHostUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            tvHostUrl.setText(url);
            tvHostIp.setText(new StringBuilder().append("Host IP: ").append(hostIP).toString());

            // Setting hyperlink to open the URL in WebView:

            tvHostUrl.setOnClickListener(view1 -> {
                Toast.makeText(MainActivity.this, "Opening: " + etUrl.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, OpenWeb.class); // WebView Intent
                intent.putExtra("URL", etUrl.getText().toString());
                startActivity(intent);
            });

        } catch (Exception e) {
            tvHost.setText("");
            tvHostUrl.setText("");
            tvHostIp.setText(R.string.str_error_msg);
        } // end of try/catch
    } // end of onClick
} // end of class