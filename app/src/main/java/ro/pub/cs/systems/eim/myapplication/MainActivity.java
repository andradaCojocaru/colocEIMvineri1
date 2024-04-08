package ro.pub.cs.systems.eim.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textView, messageTextView;
    private String instructions = "";
    private Button countButton;
    private int cardinalPointsCount = 0;
    private IntentFilter intentFilter = new IntentFilter();

    private BroadcastApp colocviu1BroadcastReceiver;
    private Intent serviceIntent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        messageTextView = (TextView)findViewById(R.id.message_text_view);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        colocviu1BroadcastReceiver = new BroadcastApp(messageTextView);
        intentFilter.addAction("ro.pub.cs.systems.eim.myapplication.action.broadcast");

        textView = findViewById(R.id.text_view);
        Button northButton = findViewById(R.id.north_button);
        northButton.setOnClickListener(v -> addInstruction("Nord"));

        countButton = findViewById(R.id.count_button);

        Button westButton = findViewById(R.id.west_button);
        westButton.setOnClickListener(v -> addInstruction("Vest"));

        Button eastButton = findViewById(R.id.east_button);
        eastButton.setOnClickListener(v -> addInstruction("Est"));

        Button southButton = findViewById(R.id.south_button);
        southButton.setOnClickListener(v -> addInstruction("Sud"));

        Button centerButton = findViewById(R.id.center_button);
        centerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("instructions", instructions);
            startActivity(intent);
            instructions = "";
            textView.setText(instructions);
            cardinalPointsCount = 0;
            countButton.setText("Numar de selectii: " + cardinalPointsCount);
        });
        if (savedInstanceState != null) {
            cardinalPointsCount = savedInstanceState.getInt("cardinalPointsCount", 0);
            countButton.setText("Numar de selectii: " + cardinalPointsCount);
        }

    }
    private void addInstruction(String instruction) {
        if (!instructions.isEmpty()) {
            instructions += ", ";
        }
        instructions += instruction;
        textView.setText(instructions);
        cardinalPointsCount++;
        countButton.setText("Numar de selectii: " + cardinalPointsCount);

        if (cardinalPointsCount == 4 && serviceIntent == null) {
            serviceIntent = new Intent(getApplicationContext(), BunService.class);
           serviceIntent.putExtra("instructions", instructions);
            startService(serviceIntent);
            //Intent intent = new Intent(MainActivity.this, ServiceColocviu.class);
            //intent.putExtra("instructions", instructions);
            //startService(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "REGISTERED", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("practical", "onRestart() method was invoked");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("practical", "onStart() method was invoked");
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(colocviu1BroadcastReceiver, intentFilter);
        Log.d("practical", "onResume() method was invoked");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(colocviu1BroadcastReceiver);
        Log.d("practical", "onPause() method was invoked");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("practical", "onStop() method was invoked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("practical", "onDestroy() method was invoked");
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cardinalPointsCount", cardinalPointsCount); // salvam numarul de selectii
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cardinalPointsCount = savedInstanceState.getInt("cardinalPointsCount", 0); // restauram numarul de selectii
        countButton.setText("Numar de selectii: " + cardinalPointsCount);
    }
}