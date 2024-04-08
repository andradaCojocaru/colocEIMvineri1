package ro.pub.cs.systems.eim.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String instructions = "";
    private Button countButton;
    private int cardinalPointsCount = 0;
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
            //Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
            //startActivity(intent);
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