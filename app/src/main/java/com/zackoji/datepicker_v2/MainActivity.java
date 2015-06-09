package com.zackoji.datepicker_v2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    /** Private members of the class */
    private TextView pDisplayDate1;
    private Button pPickDate1;
    private TextView pDisplayDate2;
    private Button pPickDate2;

    private int startDate_Year;
    private int startDate_Month;
    private int startDate_Day;
    private int endDate_Year;
    private int endDate_Month;
    private int endDate_Day;

    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();
    private long resultInMinutes;
    private TextView ResultdiffDate;
    private Button calDate;

    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID1 = 0;
    static final int DATE_DIALOG_ID2 = 1;

    /** Callback received when the user "picks" a date in the dialog */
    private DatePickerDialog.OnDateSetListener pDateSetListener1 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    startDate_Year = year;
                    startDate_Month = monthOfYear;
                    startDate_Day = dayOfMonth;
                    startDate.set(startDate_Year, startDate_Month, startDate_Day);
                    updateDisplay1();
                    displayToast1();
                }
            };
    private DatePickerDialog.OnDateSetListener pDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    endDate_Year = year;
                    endDate_Month = monthOfYear;
                    endDate_Day = dayOfMonth;
                    endDate.set(endDate_Year, endDate_Month, endDate_Day);
                    updateDisplay2();
                    displayToast2();
                }
            };

    /** Calculate Date To Minutes */
    private void calculateDateToMinutes(){
        resultInMinutes = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        if (resultInMinutes < 0) {
            ResultdiffDate.setText("Invalid Date, Please try again.");
        } else {
            resultInMinutes = resultInMinutes / (60 * 1000);
            ResultdiffDate.setText("Result is " + resultInMinutes + " Minutes");
        }
    }

    /** Updates the date in the TextView */
    private void updateDisplay1() {
        pDisplayDate1.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(startDate_Day).append("/")
                        .append(startDate_Month + 1).append("/")
                        .append(startDate_Year).append(" "));
    }
    private void updateDisplay2() {
        pDisplayDate2.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(endDate_Day).append("/")
                        .append(endDate_Month + 1).append("/")
                        .append(endDate_Year).append(" "));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////////////////////////////////StartDate
        /** Capture our View elements */
        pDisplayDate1 = (TextView) findViewById(R.id.displayDate1);
        pPickDate1 = (Button) findViewById(R.id.pickDate1);
        /** Listener for click event of the button */
        pPickDate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID1);
            }
        });
        /** Get the current date */
        final Calendar cal1 = Calendar.getInstance();
        startDate_Year = cal1.get(Calendar.YEAR);
        startDate_Month = cal1.get(Calendar.MONTH);
        startDate_Day = cal1.get(Calendar.DAY_OF_MONTH);
        //////////////////////////////////////////////////////////////////////EndDate
        /** Capture our View elements */
        pDisplayDate2 = (TextView) findViewById(R.id.displayDate2);
        pPickDate2 = (Button) findViewById(R.id.pickDate2);
        /** Listener for click event of the button */
        pPickDate2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID2);
            }
        });
        /** Get the current date */
        final Calendar cal2 = Calendar.getInstance();
        endDate_Year = cal2.get(Calendar.YEAR);
        endDate_Month = cal2.get(Calendar.MONTH);
        endDate_Day = cal2.get(Calendar.DAY_OF_MONTH);
        ////////////////////////////////////////////////////////////////////////////////
        /** Display the current date in the TextView */
        updateDisplay1();
        updateDisplay2();

        ResultdiffDate = (TextView) findViewById(R.id.diffDate);
        calDate = (Button) findViewById(R.id.calDate);
        calDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculateDateToMinutes();
            }
        });
    }

    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID1:
                DatePickerDialog dp1 = new DatePickerDialog(this,
                        pDateSetListener1,
                        startDate_Year, startDate_Month, startDate_Day);
                return dp1;

            case DATE_DIALOG_ID2:
                DatePickerDialog dp2 = new DatePickerDialog(this,
                        pDateSetListener2,
                        endDate_Year, endDate_Month, endDate_Day);
                return dp2;
        }
        return null;
    }

    /** Displays a notification when the date is updated */
    private void displayToast1() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate1.getText()), Toast.LENGTH_SHORT).show();
    }
    private void displayToast2() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate2.getText()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
