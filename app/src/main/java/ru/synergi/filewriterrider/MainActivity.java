package ru.synergi.filewriterrider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private  File getExternalFilePath(){
        return new File(getExternalFilesDir(null),FILE_NAME);
    }
    //сохранение файла работает в рамках онклика

    public void saveText(View view) {
        FileOutputStream fos = null;

        try {
        EditText textBox = (EditText) findViewById(R.id.editor); // находим эдит текст
            String text = textBox.getText().toString(); // достаем текст

            fos = new FileOutputStream(getExternalFilePath());//openFileOutput(FILE_NAME, MODE_PRIVATE); // находим наш файл, моде прайват - доступны только нам
            fos.write(text.getBytes()); // берем текст в виде стринги и вызываем у стринги метод гетБайтс(массив байтов по размерам нашей стринги
            Toast.makeText(this, "Файл успешно сохранен", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) { // если файла нету, нам дадут об этом знать
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) { // если мы не смогли записать файл, он найден но нет доступа на запись, будет обрабока ошибок
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {

            try {
                if (fos != null) { // не явл-ся ли наш поток записи нулем, если не ноль, то его закрываем
                    fos.close();//закрываем здесь
                }
            } catch (IOException e) {  // отлавдиваем Инпут Аутпут Инсепшен
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // открытие файла

    public void openText(View view) {
        FileInputStream fin = null;  // изначально равен 0, создаем файл как переменную
        TextView textView = (TextView) findViewById(R.id.text); // находим поле для вывода
        File file = getExternalFilePath();


        try {
            fin = new FileInputStream(file);//openFileInput(FILE_NAME);// создаем с пом. метода опенФайлИнпут(хранится в контексте), создаем файл ипунстринг для файл нейма
            byte[] bytes = new byte[fin.available()]; //чтение наших файлов,складываем новый массив, создаем массив по размеру нашего файла
            fin.read(bytes);  // прочитаем данный файл в массив байтах (нам нужно куда-то записать наши данные)
            String text = new String(bytes); // создаем стрингу из байтов, полученных из нашего входного потока
            textView.setText(text); // выставляем на экран наш текствью
        } catch (FileNotFoundException e) { // далее обрабатывем ошибки
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally { // закрываем наш поток ввода

            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}






