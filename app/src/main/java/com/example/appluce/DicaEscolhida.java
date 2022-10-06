package com.example.appluce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.appluce.ui.dicas.DicasFragment;

public class DicaEscolhida extends AppCompatActivity {
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicas);

        final WebView webView = findViewById(R.id.webView);

        webView.loadUrl(url);

        Button button = findViewById(R.id.voltarDica);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DicaEscolhida.this, DicasFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
