package com.example.madassignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculation extends AppCompatActivity {
//DECLARATIONS LINKING THE IDS GIVEN IN XML FOR EDITTEXT, TEXTVIEW & BUTTONS
    EditText empname, empwdays, empbonus;
    TextView grossSal, incomeTax, travelTax, pensionTax, groceriesTax, netSal;
    Button calculateSalary, insert, delete, view, reset, update;
    //DECLARING  DATABASE
    DatabaseHelper db;

    @Override
// METHOD TO CALCULATE,HOSTING ALL FUNCTIONS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        //LINKING DATABASE
        db = new DatabaseHelper(this);
        //getting the IDs of the edittext
        empname = findViewById(R.id.empname);
        empwdays = findViewById(R.id.empwdays);
        empbonus = findViewById(R.id.empovertime);
        grossSal = findViewById(R.id.grosssal);
        incomeTax = findViewById(R.id.incometaxcal);
        travelTax = findViewById(R.id.traveltaxcal);
        pensionTax = findViewById(R.id.pensiontaxcal);
        groceriesTax = findViewById(R.id.groceriestaxcal);
        netSal = findViewById(R.id.Netssal);
        //getting the IDs of the buttons
        calculateSalary = findViewById(R.id.Calbtn);
        insert = findViewById(R.id.insbtn);
        delete = findViewById(R.id.deldata);
        view = findViewById(R.id.viewdata);
        reset = findViewById(R.id.cleardata);
        update = findViewById(R.id.updbtn);



        // calculate salary
        calculateSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = empname.getText().toString();
                //CONVERT INT VALUE TO STRING
                int workingDays = Integer.parseInt(empwdays.getText().toString());
                int overtime = Integer.parseInt(empbonus.getText().toString());
                int grossSalary = (workingDays * 20) + overtime;
                //CALCULATING THE TOTAL SALARY
                grossSal.setText("GROSS SALARY IS " + grossSalary + " OMR");
                double incomeTaxAmount = (grossSalary * 10.5) / 100;
                double travelTaxAmount = (grossSalary * 4.5) / 100;
                double pensionAmount = (grossSalary * 8.5) / 100;
                double groceriesTaxAmount  = (grossSalary * 6.0) / 100;
                incomeTax.setText("" + incomeTaxAmount + " OMR");
                travelTax.setText("" + travelTaxAmount + " OMR");
                pensionTax.setText("" + pensionAmount + " OMR");
                groceriesTax.setText("" + groceriesTaxAmount + " OMR");
                double netSalary = grossSalary - incomeTaxAmount - travelTaxAmount + pensionAmount - groceriesTaxAmount;
                //CALCULATING NET SALARY AFTER REDUCTION
                netSal.setText("NET SALARY IS " + (int) netSalary + " OMR");
            }
        });
// insert data into table
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = empname.getText().toString();
//                int workingDays = Integer.parseInt(empwdays.getText().toString());
//                int overtime = Integer.parseInt(empbonus.getText().toString());
//                int netSalary = Integer.parseInt(netSal.getText().toString());
                String workingDays = (empwdays.getText().toString());
                String overtime = (empbonus.getText().toString());
                String netSalary = (netSal.getText().toString());
                boolean b = db.insertData(name, workingDays, overtime, netSalary);
                // IF CONDITION TO CHECK THE STATEMENT IS TRUE /FALSE
                if (b == true) {
                    //IF TRUE MESSAGE SHOWS DATA INSERTED
                    Toast.makeText(calculation.this, "data inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    //IF FALSE MESSAGE SHOWS NOT INSERTED
                    Toast.makeText(calculation.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
// delete data into table
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer del=db.deleteData(empname.getText().toString());
                // IF CONDITION TO CHECK THE STATEMENT IS TRUE /FALSE
                if(del>0)
                    //IF TRUE MESSAGE SHOWS DATA DELETED
                    Toast.makeText(calculation.this,"Data deleted",Toast.LENGTH_LONG).show();
                else
                    //IF FALSE MESSAGE SHOWS NOT DELETED
                    Toast.makeText(calculation.this,"Data not deleted",Toast.LENGTH_LONG).show();

            }
        });
// view data into table
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor r=db.getAllData();
                // IF & WHILE CONDITION TO CHECK THE STATEMENT & RUN
                if(r.getCount()==0)
                {
                    // IF TURE MESSAGE SHOWS NOTHING FOUND
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer b=new StringBuffer();
                while(r.moveToNext())
                {
                    b.append("NAME: "+r.getString(0)+"\n");
                    b.append("DAYS WORKED: "+r.getString(1)+"\n");
                    b.append("BONUS: "+r.getString(2)+"\n");
                    b.append("NET SALARY: "+r.getString(3)+"\n");
                }
                // DISPLAY DETAILS OF EMPLOYEE IN DATABASE
                    showMessage("Employee Details",b.toString());
            }
        });
// clear all the entered values and output values
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empname.setText("");
                empwdays.setText("");
                empbonus.setText("");
                grossSal.setText("GROSS SALARY IS");
                incomeTax.setText("");
                travelTax.setText("");
                pensionTax.setText("");
                groceriesTax.setText("");
                netSal.setText("NET SALARY IS");
// RESET MESSAGE
                Toast.makeText(calculation.this,"Data Reset Successful",Toast.LENGTH_LONG).show();
            }

        });

// UPDATE METHOD
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int workingDays = Integer.parseInt(empwdays.getText().toString());
                int overtime = Integer.parseInt(empbonus.getText().toString());
                int grossSalary = (workingDays * 20) + overtime;
                double incomeTaxAmount = (grossSalary * 10.5) / 100;
                double travelTaxAmount = (grossSalary * 4.5) / 100;
                double pensionAmount = (grossSalary * 8.5) / 100;
                double groceriesTaxAmount  = (grossSalary * 6.0) / 100;
                double netSalary = grossSalary - incomeTaxAmount - travelTaxAmount - pensionAmount - groceriesTaxAmount;
              boolean update=db.updateData(empname.getText().toString(),empwdays.getText().toString(),empbonus.getText().toString(),""+netSalary);
              // UPDATE MESSAGE
              Toast.makeText(calculation.this, "Updated" , Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void showMessage(String title,String mes)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }

}