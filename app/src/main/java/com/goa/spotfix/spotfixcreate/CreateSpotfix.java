package com.goa.spotfix.spotfixcreate;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateSpotfix extends AppCompatActivity {


    private static final String ANONYMOUS ="anonymous" ;
    private static final int RC_PHOTO_PICKER = 2;
    TextView textViewTime;
    Calendar currentTime;
    int hour,minute;
    String format;


    private static final String TAG="CreateSpotfix";


    ImageView imageView;
    Button button;
    private Button mGalleryButton;
    private Button mCreateSpotfix;
    private static final int PICK_IMAGE=100;


    public  static final int REQUEST_CAPTURE =1;
    ImageView result_photo;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spotfix);
        mUsername = ANONYMOUS;

        textViewTime = findViewById(R.id.textViewTime);
        mGalleryButton = findViewById(R.id.Gallery);
        mCreateSpotfix = findViewById(R.id.buttonCreateSpotfix);

        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);

        selectedTimeFormat(hour);
        textViewTime.setText(hour + " ; " + minute + " " + format);

        textViewTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ) {


                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateSpotfix.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay);
                        textViewTime.setText(hourOfDay + " : " + minute + " " + format);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });




        mDisplayDate = (TextView)findViewById(R.id.textViewDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dailog = new DatePickerDialog(
                        CreateSpotfix.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        mDateSetListener,
                        year,month,day);
                dailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dailog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month+"/"+ day +"/" + year;
                Date dateObject = new Date(date);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD,YYYY");
                String dateToDisplay = dateFormatter.format(dateObject);
                mDisplayDate.setText(dateToDisplay);
            }
        };







        Button buttonCamera = (Button)findViewById(R.id.buttonCamera);
        result_photo =(ImageView)findViewById(R.id.imageView);

        if(!hasCamera())
        {
            buttonCamera.setEnabled(false);
        }

        // ImagePickerButton shows an image picker to upload a image for a message
        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });


    }

    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);


    }

    public void launchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }


    public void selectedTimeFormat(int hour){
        if (hour == 0){
            hour += 12;
            format = "AM";
        } else if (hour == 12){
            format="PM";

        }else if (hour > 12){
            hour -= 12;
            format = "PM";

        }else {
            format = "AM";
        }
    }


    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras  = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            result_photo.setImageBitmap(photo);
        }
    }



}
