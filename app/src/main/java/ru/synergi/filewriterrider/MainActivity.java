package ru.synergi.filewriterrider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
private final static String FILE_NAME = "content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveText(View view) {
        FileOutputStream fos = null;
    }

    public void openText(View view) {
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.text);
    }
}