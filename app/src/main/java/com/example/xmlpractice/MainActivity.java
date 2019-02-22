package com.example.xmlpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sms> smss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smss = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Sms sms = new Sms();
            sms.setAddress("1008" + i);
            sms.setBody("nihao" + i);
            sms.setDate("201" + i);

            smss.add(sms);
        }
    }

    public void click(View view) {
        try {
            XmlSerializer serializer = Xml.newSerializer();
            File file = new File(getFilesDir().getPath(),"XmlTest.xml");
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos, "utf-8");
            serializer.startDocument("utf-8", true);
            serializer.startTag(null, "smss");

            for (Sms sms : smss) {
                serializer.startTag(null, "sms");

                serializer.startTag(null, "address");
                serializer.text(sms.getAddress());
                serializer.endTag(null, "address");

                serializer.startTag(null, "body");
                serializer.text(sms.getBody());
                serializer.endTag(null, "body");

                serializer.startTag(null, "date");
                serializer.text(sms.getDate());
                serializer.endTag(null, "date");

                serializer.endTag(null, "sms");
            }

            serializer.endTag(null,"smss");
            serializer.endDocument();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
