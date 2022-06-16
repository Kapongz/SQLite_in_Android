package com.example.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Showdata extends AppCompatActivity {
EditText bookid,bookname,bprice,bpage;
Button ok,cancle;
Mydatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        bookid = findViewById(R.id.txtid);
        bookname = findViewById(R.id.txtbname);
        bprice = findViewById(R.id.txtprice);
        bpage = findViewById(R.id.txtpage);
        ok = findViewById(R.id.ok);
        cancle = findViewById(R.id.cancle);

        mydatabase = new Mydatabase(this);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid = bookid.getText().toString();
                String dbname = bookname.getText().toString();
                String price = bprice.getText().toString();
                String page = bpage.getText().toString();

                if(bid.isEmpty() && dbname.isEmpty() && price.isEmpty() && page.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "ກະລຸນາໃສ່ຂໍ້ມູນ", Toast.LENGTH_LONG);
                    toast.show();

                    return;
                }

                try {
                    mydatabase.InsertData(bid,dbname,price,page);
                    new SweetAlertDialog(Showdata.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("ບັນທຶກສຳເລັດແລ້ວ!")
                            .setContentText("ລາຍການຂອງທ່ານໄດ້ເພີ່ມເຂົ້າໄປໃນຖານຂໍ້ມູນແລ້ວ່່!")
                            .show();
                    bookid.setText("");
                    bookname.setText("");
                    bprice.setText("");
                    bpage.setText("");
                    //  Block of code to try
                }
                catch(Exception e) {
                    //  Block of code to handle errors
                    new SweetAlertDialog(Showdata.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("ບໍ່ສາມາດບັກທຶກຂໍ້ມູນໄດ້!")
                            .show();
                }

//                Toast toast = Toast.makeText(getApplicationContext(), "ບັນທຶກສຳເລັດແລ້ວ", Toast.LENGTH_LONG);
//                toast.show();
//                bookid.setText("");
//                bookname.setText("");
//                bprice.setText("");
//                bpage.setText("");
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookid.setText("");
                bookname.setText("");
                bprice.setText("");
                bpage.setText("");
            }
        });
    }
}