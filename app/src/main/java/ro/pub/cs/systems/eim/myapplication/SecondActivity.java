package ro.pub.cs.systems.eim.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text_view_second);
        Intent intent = getIntent();
        String instructions = intent.getStringExtra("instructions");
        textView.setText(instructions);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            Toast.makeText(this, "Register button pressed", Toast.LENGTH_SHORT).show();
            finish();
        });

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "Cancel button pressed", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}