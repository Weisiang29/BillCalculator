package sg.edu.rp.c346.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount ;
    EditText pax ;
    EditText discount;
    ToggleButton svs;
    ToggleButton gst;
    ToggleButton split;
    ToggleButton reset;
    TextView totalBill;
    TextView eachPays;
    RadioGroup payment;
    RadioButton money;
    RadioButton online;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount=findViewById(R.id.amount);
        pax=findViewById(R.id.pax);
        discount=findViewById(R.id.discount);
        svs=findViewById(R.id.toggle1);
        gst=findViewById(R.id.toggle2);
        split=findViewById(R.id.toggle3);
        reset=findViewById(R.id.toggle4);
        totalBill=findViewById(R.id.totalbill);
        eachPays=findViewById(R.id.eachpays);
        payment=findViewById(R.id.rGroup);
        money=findViewById(R.id.RD1);
        online=findViewById(R.id.RD2);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String V1=amount.getText().toString();
                String V2=pax.getText().toString();
                String V3=discount.getText().toString();

                double Total = 0;
                if((V1.isEmpty())&&(V2.isEmpty())){
                    Toast.makeText(MainActivity.this,"Please fill all the input area",Toast.LENGTH_SHORT).show();
                }
                else if(!V1.isEmpty()&&V2.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill number of pax",Toast.LENGTH_SHORT).show();

                }
                else if(V1.isEmpty()&&!V2.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill number of Amount",Toast.LENGTH_SHORT).show();


                }

                else{
                    double int1=Double.parseDouble(V1);
                    int int2=Integer.parseInt(V2);
                    if(svs.isChecked()&&gst.isChecked()){
                        Total=int1*1.17;
                    }
                    else if (!svs.isChecked()&&gst.isChecked()){
                        Total=int1*1.07;
                    }
                    else if(svs.isChecked()&&!gst.isChecked()){
                        Total=int1*1.10;
                    }
                    else if(!(svs.isChecked()&&gst.isChecked())){
                        Total=int1;
                    }
                    if(!V3.isEmpty()){
                        int dis=Integer.parseInt(V3);
                        Total=Total*(100-dis)/100;
                    }
                    int checkRadioID =payment.getCheckedRadioButtonId();
                    if(checkRadioID ==R.id.RD1){
                     String newtotal   =String.format("Total Bill: $ %.2f",Total);
                     String neweachpay   = String.format("Each Pays: $ %.2f in cash",(Total/int2));
                        totalBill.setText(newtotal);
                        eachPays.setText(neweachpay);
                    }
                    else{
                        String newtotal2   =String.format("Total Bill: $ %.2f",Total);
                        String neweachpay2   = String.format("Each Pays: $ %.2f via Paynow to 90127584",(Total/int2));
                        totalBill.setText(newtotal2);
                        eachPays.setText(neweachpay2);


                    }

                }

            }

        });
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount.setText("");
                    pax.setText("");
                    discount.setText("");
                    svs.setChecked(false);
                    gst.setChecked(false);
                    totalBill.setText("Total Bill:");
                    eachPays.setText("Each Pays:");
                }
            });



    }
}