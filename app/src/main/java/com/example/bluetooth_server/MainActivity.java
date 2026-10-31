package com.example.bluetooth_server;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    TextView receivedEditText;
    EditText sendTextEditText;
    Button acceptConnectionButton;
    Button closeConnectionButton;
    Button sendTextButton;
    TextView BT_MAC_TextView;
    TextView BT_Name_TextView;
    TextView BT_ConnectionState_TextView;
    BluetoothAdapter mBluetoothAdapter; // objeto bluetooth adapter

    private ActivityResultLauncher<Intent> enableBtLauncher;

    int REQUEST_ENABLE_BT = 1;

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
        this.BT_ConnectionState_TextView = (TextView) findViewById(R.id.Text_Details_Client);

        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        obtainAdapaterBLuetooth(); // Comprobación de que existe adaotador bluetooth en el dispositivo
        bt_activated_check(); // Comprobación de que el bluetooth está activado

        // CONDICIONES INICIALES DE LOS BOTONES
        this.acceptConnectionButton.setEnabled(true);
        this.closeConnectionButton.setEnabled(false);
        this.sendTextButton.setEnabled(false);
        this.sendTextEditText.setEnabled(false);
        this.BT_ConnectionState_TextView.setText("NOT CONNECTED");

        // Mostrar la dirección MAC y NAME de nuestro dispositivo
        showBT_MAC_NAME();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // FUNCIONES BLUETOOTH
    private boolean obtainAdapaterBLuetooth() {
        if (mBluetoothAdapter == null) {
            Toast.makeText(
                    getApplicationContext(),
                    "There is not bluetooth",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }
        return true;
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){
        // RESPUESTA DEL INTENT
        if (requestCode == REQUEST_ENABLE_BT){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Bluetooth enabled",
                        Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Bluetooth NOT enabled",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void bt_activated_check(){
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT); // sale en rojo porque es una función deprecated
        }
    }
    private void showBT_MAC_NAME(){
        String BT_name = this.mBluetoothAdapter.getName();
        String BT_MAC = this.mBluetoothAdapter.getAddress();
        this.BT_MAC_TextView.setText("BT MAC:"+BT_MAC);
        this.BT_Name_TextView.setText("BT Name:"+BT_name);
    }


}