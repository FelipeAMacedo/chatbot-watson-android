package com.example.vmac.WatBot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vmac.WatBot.dao.STTDAO;
import com.example.vmac.WatBot.model.Service;

public class AddSTTActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etUsername;
    private EditText etPassword;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stt);

        initComponents();
        initListeners();
    }

    public void initComponents() {
        etNome = findViewById(R.id.etNome);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btSalvar = findViewById(R.id.btSalvar);
    }

    public void initListeners() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STTDAO sttDao = new STTDAO(AddSTTActivity.this);
                Service service = new Service();
                service.setName(etNome.getText().toString());
                service.setUsername(etUsername.getText().toString());
                service.setPassword(etPassword.getText().toString());
                service.setSelected(false);

                sttDao.insert(service);
            }
        });
    }
}
