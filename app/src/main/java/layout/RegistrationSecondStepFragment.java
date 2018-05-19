package layout;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ProvinceCitySpinnerAdapter;
import apps.sffa.com.ainaki.model.City;
import apps.sffa.com.ainaki.model.Province;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationSecondStepFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationSecondStepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationSecondStepFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  OnFragmentInteractionListener mListener;




    private Spinner spnProvinces;
    private Spinner spnCities;
    private Typeface fontIranSans;
    private TextInputEditText txtNationalCode;
    private TextInputEditText txtAddress;
    private TextInputEditText txtPostalCode;
    private TextView textView;
    private Button btnNextStep;
    private TextInputLayout inputLayoutNationalCode;
    private TextInputLayout inputLayoutAddress;
    private TextInputLayout inputLayoutPostalCode;



    public RegistrationSecondStepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFirstStepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFirstStepFragment newInstance(String param1, String param2) {
        RegistrationFirstStepFragment fragment = new RegistrationFirstStepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_second_step, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        fontIranSans = FontManager.getTypeface(getContext(), FontManager.IRANSANS_TEXTS);

        txtNationalCode = (TextInputEditText) view.findViewById(R.id.txtNationalCode);
        txtAddress = (TextInputEditText) view.findViewById(R.id.txtAddress);
        txtPostalCode = (TextInputEditText) view.findViewById(R.id.txtPostalCode);

        inputLayoutNationalCode = (TextInputLayout) view.findViewById(R.id.inputLayoutNationalCode);
        inputLayoutAddress = (TextInputLayout) view.findViewById(R.id.inputLayoutAddress);
        inputLayoutPostalCode = (TextInputLayout) view.findViewById(R.id.inputLayoutPostalCode);

        textView = (TextView) view.findViewById(R.id.textView);
        btnNextStep = (Button) view.findViewById(R.id.btnNextStep);

        spnProvinces = (Spinner) view.findViewById(R.id.spnProvinces);
        spnCities = (Spinner) view.findViewById(R.id.spnCities);


        spnProvinces.setAdapter(new ProvinceCitySpinnerAdapter(getContext(), R.layout.spinner_city_and_province_item, initProvinces()));
        spnCities.setAdapter(new ProvinceCitySpinnerAdapter(getContext(), R.layout.spinner_city_and_province_item, initCities()));
        spnProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = ((TextView) view.findViewById(R.id.txtProvinceCityName)).getText().toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = ((TextView) view.findViewById(R.id.txtProvinceCityName)).getText().toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FontManager.setFont(txtNationalCode, fontIranSans);
        FontManager.setFont(txtAddress, fontIranSans);
        FontManager.setFont(txtPostalCode, fontIranSans);

        FontManager.setFont( inputLayoutNationalCode , fontIranSans);
        FontManager.setFont( inputLayoutAddress , fontIranSans);
        FontManager.setFont( inputLayoutPostalCode , fontIranSans);

        FontManager.setFont(textView, fontIranSans);
        FontManager.setFont(btnNextStep, fontIranSans);
    }


    private ArrayList<Province> initProvinces() {
        ArrayList<Province> provinces = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            provinces.add(new Province("استان #" + (i + 1)));
        return provinces;
    }


    private ArrayList<City> initCities() {
        ArrayList<City> cities = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            cities.add(new City("شهر #" + (i + 1)));
        return cities;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onRegistrationSecondStepInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistrationFirstStepFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRegistrationSecondStepInteraction();
    }
}
