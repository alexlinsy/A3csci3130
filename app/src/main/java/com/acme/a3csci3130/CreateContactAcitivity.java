package com.acme.a3csci3130;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, businessNumberField, addressField;
    private Spinner primaryBusinessSpinner, provinceSpinner;
    private MyApplicationData appState;
    private String selectedPrimaryBusiness;
    private String selectedProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        //Assign value to other text field
        businessNumberField = (EditText) findViewById(R.id.businessNumberText);
        addressField = (EditText) findViewById(R.id.addressText);
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


        //Set values to spinners
        primaryBusinessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        break;

                    case 1:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        break;

                    case 2:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        break;

                    case 3:
                        selectedPrimaryBusiness = primaryBusinessSelector[position];
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 1:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 2:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 3:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 4:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 5:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 6:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 7:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 8:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 9:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 10:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 11:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 12:
                        selectedProvince = provinceSelector[position];
                        break;

                    case 13:
                        selectedProvince = provinceSelector[position];
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    /**
     * Method that creates user contact info to firebase database
     * Firebase databse.
     *
     * @value personID the unique id of the contact person
     * @value name the name value of user
     * @value email the email value of user
     * @value businessNumber the business number of user
     * @value address the address value of user
     * @return null
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String address = addressField.getText().toString();

        //Create validator to check if input method is valid
        Validator functionValidate = new Validator();
        //Create Alert Dialog box
        AlertDialog.Builder Alertbuilder = new AlertDialog.Builder(this);
        Alertbuilder.setCancelable(true);

        Alertbuilder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        //Check if contact info is validate
        if(functionValidate.nameValidate(name) == false) {
            Alertbuilder.setMessage("Name should be 2-48 characters");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        } else if(functionValidate.addressValidate(address) == false) {
            Alertbuilder.setMessage("Address should be less than 50 characters");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        } else if(functionValidate.businessNumberValidate(businessNumber) == false) {
            Alertbuilder.setMessage("Business number should be 9 digit number");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        } else if(functionValidate.emailValidate(email) == false) {
            Alertbuilder.setMessage("Please enter an valid email");
            AlertDialog alertBox = Alertbuilder.create();
            alertBox.show();
        } else {

            Contact person = new Contact(personID, name, email, businessNumber, selectedPrimaryBusiness, address, selectedProvince);

            appState.firebaseReference.child(personID).setValue(person);

            finish();
        }


    }
}
