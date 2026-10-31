package com.example.bluetooth_server;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothServerManager {

    // Variables de la clase
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothServerSocket bluetoothServerSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Handler handlerNetworkExecutorResult;

    public static boolean exitCurrentConnection = false;

    // Constructor
    public BluetoothServerManager(BluetoothAdapter _bluetoothAdapter,
                                  Handler _handlerNetworkExecutorResult) {

        this.bluetoothAdapter = _bluetoothAdapter;
        this.handlerNetworkExecutorResult = _handlerNetworkExecutorResult;

        try {
            UUID MY_UUID = UUID.fromString(
                    "00001101-0000-1000-8000-00805F9B34FB"
            );

            String NAME = "BTROBOTSERVERSIM";

            bluetoothServerSocket =
                    bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);

        } catch (IOException e) {
            Log.e("Error", "Bluetooth connection: " + e);
        }
    }

    // MÉTODOS DE COMUNICACIÓN
    public void showDisplayMessage(String displayMessage) {
        Message msg = new Message();
        msg.arg1 = 0; // mensaje de visualización
        msg.obj = displayMessage.replaceAll("_", " ");
        handlerNetworkExecutorResult.sendMessage(msg);
    }
    public void showSocketStateMessage(String displayMessage) {
        Message msg = new Message();
        msg.arg1 = 1; // estado del socket
        msg.obj = displayMessage.replaceAll("_", " ");
        handlerNetworkExecutorResult.sendMessage(msg);
    }
}