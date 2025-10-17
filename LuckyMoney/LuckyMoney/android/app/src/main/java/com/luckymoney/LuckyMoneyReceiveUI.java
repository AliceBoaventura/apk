package com.luckymoney;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LuckyMoneyReceiveUI extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnOpen;
    private int currentId = 1;
    // Generic base URL placeholder — change to your deployed API URL
    private static final String BASE_URL = "https://api.luckymoney.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_money_receive_ui);

        tvStatus = findViewById(R.id.textMessage);
        btnOpen = findViewById(R.id.btnOpenEnvelope);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStatus.setText("Abrindo..."); // quick feedback
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject env = openEnvelope(currentId);
                            final String message = env.optString("message", "Você ganhou!") + " - " + (env.optInt("cents", 0)/100.0) + "\n" + (env.optBoolean("opened")?"Aberto":"Não aberto");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvStatus.setText(message);
                                }
                            });
                        } catch (final Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvStatus.setText("Erro: " + e.getMessage());
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    private JSONObject openEnvelope(int id) throws Exception {
        URL url = new URL(BASE_URL + "/api/open/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        int code = conn.getResponseCode();
        InputStream is = (code >=200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return new JSONObject(sb.toString());
    }
}
