package org.arenatest.bits.arena_test.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.api.ApiClient;
import org.arenatest.bits.arena_test.api.ApiInterface;
import org.arenatest.bits.arena_test.app.Constants;
import org.arenatest.bits.arena_test.sets.RegisterSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText name, email, phone, college;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPhone , inputLayoutCollege;
    Spinner sport_spinner;
    Button submit;
    int itemNumber;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }
    ProgressDialog dialog;
    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) view.findViewById(R.id.input_layout_phone);
        inputLayoutCollege = (TextInputLayout) view.findViewById(R.id.input_layout_college);
        submit= (Button) view.findViewById(R.id.register_submit);
        sport_spinner = (Spinner) view.findViewById(R.id.sports_spinner);

        name = (EditText) view.findViewById(R.id.input_name);
        email = (EditText) view.findViewById(R.id.input_email);
        phone = (EditText) view.findViewById(R.id.input_phone);
        college = (EditText) view.findViewById(R.id.input_college);

        name.addTextChangedListener(new MyTextWatcher(name));
        email.addTextChangedListener(new MyTextWatcher(email));
        phone.addTextChangedListener(new MyTextWatcher(phone));
        college.addTextChangedListener(new MyTextWatcher(college));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    submitForm();
                }else{
                    Toast.makeText(getActivity(),"Check your Internet Connection...",Toast.LENGTH_LONG).show();
                }
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,getActivity().getResources().getStringArray(R.array.sports_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sport_spinner.setAdapter(adapter);

        sport_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: itemNumber=0;
                        break;
                    default: itemNumber=i;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Validating form
     */
    public void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }

        if (validateCollege()){
            return;
        }

        if (itemNumber == 0){
            Toast.makeText(getActivity(),"Please Select a sport",Toast.LENGTH_SHORT).show();
            return;
        }

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Registering... Please Wait...");
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(getActivity(),"Registration Cancelled !! ",Toast.LENGTH_LONG).show();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<RegisterSet> registerSetCall = apiService.postRegister(Constants.REGISTRATION_TAG,
                name.getText().toString(),
                email.getText().toString(),
                phone.getText().toString(),
                college.getText().toString(),
                Integer.toString(itemNumber));
        registerSetCall.enqueue(new Callback<RegisterSet>() {
            @Override
            public void onResponse(Call<RegisterSet> call, Response<RegisterSet> response) {
                dialog.dismiss();
                if (response.body().getSuccess()==1){
                    getActivity().finish();
                    Toast.makeText(getActivity().getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"Registration Failed !! \n Try again Later",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterSet> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(),"Check your Internet Connection...",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateName() {
        String xname = name.getText().toString();
            if (xname.isEmpty()) {
                inputLayoutName.setError(getString(R.string.err_msg_name));
                requestFocus(this.name);
                return false;
            } else {
                inputLayoutName.setErrorEnabled(false);
            }

        return true;
    }

    private boolean validateEmail() {
        String email = this.email.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            requestFocus(this.email);
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            return true;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        if (phone.getText().toString().trim().length()==10) {
            inputLayoutPhone.setErrorEnabled(false);
            return true;
        } else {
            requestFocus(phone);
            inputLayoutPhone.setError(getString(R.string.err_msg_password));
        }

        return false;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateCollege(){
        return  (college.getText().toString().trim().isEmpty());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_college:
                    validateCollege();
                    break;
            }
        }
    }
}
