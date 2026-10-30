package com.example.bluetooth_server;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView receivedEditText;
    EditText sendTextEditText;
    Button acceptConnectionButton;
    Button closeConnectionButton;
    Button sendTextButton;
    TextView BT_MAC_TextView;
    TextView BT_Name_TextView;
    TextView BT_ConnectionState_TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.receivedEditText = (TextView) findViewById(R.id.recievedEditText);
        this.sendTextEditText = this.sendTextEditText = (EditText) findViewById(R.id.sendTextEditText);
        this.acceptConnectionButton = (Button) findViewById(R.id.Btn_Accept_Connection);
        this.closeConnectionButton = (Button) findViewById(R.id.Btn_Close_Connection);
        this.sendTextButton = (Button) findViewById(R.id.Btn_Send);
        this.BT_MAC_TextView = (TextView) findViewById(R.id.TextMac);
        this.BT_Name_TextView = (TextView) findViewById(R.id.TextName);
        this.BT_ConnectionState_TextView = (TextView)
                findViewById(R.id.Text_Details_Client);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}