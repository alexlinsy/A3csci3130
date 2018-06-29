package com.acme.a3csci3130;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, businessNumberField, addressField;
    Contact receivedPersonInfo;
    private TextView currentPrimaryBusinessView, currentProvinceView;
    private Spinner primaryBusinessSpinner, provinceSpinner;
    private String selectedPrimaryBusiness;
    private String selectedProvince;
    private int counterBusiness;
    private int counterProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        businessNumberField = (EditText) findViewById(R.id.businessNumberText);
        addressField = (EditText) findViewById(R.id.addressText);
        currentPrimaryBusinessView = (TextView) findViewById(R.id.currentPrimaryBusiness);
        currentProvinceView = (TextView) findViewById(R.id.currentProvince);
        primaryBusinessSpinner = (Spinner) findViewById(R.id.primaryBusinessSpinner);
        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);

        //Spinner Selector Function
        Spinners spinnerSelector = new Spinners();
        final String[] primaryBusinessSelector = spinnerSelector.getPrimaryBusiness().clone();
        final String[] provinceSelector = spinnerSelector.getProvince().clone();
        ArrayAdapter<String> businessAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, primaryBusinessSelector);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceSelector);

        primaryBusinessSpinner.setAdapter(businessAdapter);
        provinceSpinner.setAdapter(provinceAdapter);


        //Choose Selected spinner content
        primaryBusinessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //User counter to check if the spinner is used, if not, the value of primary business will not change.
                counterBusiness = 0;
                switch (position) {
                    case 0:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        break;

                    case 1:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        counterBusiness ++;
                        break;

                    case 2:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        counterBusiness ++;
                        break;

                    case 3:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        counterBusiness ++;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //Choose Selected spinner content
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //User counter to check if the spinner is used, if not, the value of province will not change.
                counterProvince = 0;
                switch (position) {
                    case 0:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 1:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 2:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 3:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 4:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 5:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 6:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 7:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 8:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 9:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 10:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 11:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 12:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;

                    case 13:
                        selectedProvince = provinceSelector[position];
                        counterProvince ++;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            //Set other contact info
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            addressField.setText(receivedPersonInfo.address);
            currentPrimaryBusinessView.setText(receivedPersonInfo.primaryBusiness);
            currentProvinceView.setText(receivedPersonInfo.province);
        }

    }
    /**
     * Method that update user contact info to firebase database
     * Firebase databse.
     *
     * @value personID the unique id of the contact person
     * @value name the name value of user
     * @value email the email value of user
     * @value businessNumber the business number of user
     * @value address the address value of user
     * @return null
     */
    public void updateContact(View v){
        //each entry needs a unique ID
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String address = addressField.getText().toString();
        MyApplicationData appState = ((MyApplicationData) getApplicationContext());

        //Create validator to check if input method is valid
        Validator functionValidate = new Validator();

        //Create Alert Dialog box
        AlertDialog.Builder Alertbuilder = new AlertDialog.Builder(this);
        Alertbuilder.setMessage("Name size should be 2-48 characters");
        Alertbuilder.setCancelable(true);

        Alertbuilder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        //Check if update contact info is valid and update Data
        if(functionValidate.nameValidate(name) == true) {
            appState.firebaseReference.child(personID).child("name").setValue(name);
        } else {
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        }
        if(functionValidate.emailValidate(email) == true) {
            appState.firebaseReference.child(personID).child("email").setValue(email);
        } else {
            Alertbuilder.setMessage("Please enter an valid email");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        }

        if(functionValidate.businessNumberValidate(businessNumber) == true) {
            appState.firebaseReference.child(personID).child("businessNumber").setValue(businessNumber);
        } else {
            Alertbuilder.setMessage("Business number should be 9 digit number");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        }

        if(functionValidate.addressValidate(address) == true) {
            appState.firebaseReference.child(personID).child("address").setValue(address);
        } else {
            Alertbuilder.setMessage("Address should be less than 50 characters");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        }

        if(counterBusiness != 0) {
            appState.firebaseReference.child(personID).child("primaryBusiness").setValue(selectedPrimaryBusiness);
        } else {
            appState.firebaseReference.child(personID).child("primaryBusiness").setValue(currentPrimaryBusinessView.getText().toString());
        }

        if(counterProvince != 0) {
            appState.firebaseReference.child(personID).child("province").setValue(selectedProvince);
        } else {
            appState.firebaseReference.child(personID).child("province").setValue(currentProvinceView.getText().toString());
        }

        finish();
    }

    public void eraseContact(View v)
    {
        String personID = receivedPersonInfo.uid;
        MyApplicationData appState = ((MyApplicationData) getApplicationContext());
        appState.firebaseReference.child(personID).removeValue();
        finish();
    }
}
