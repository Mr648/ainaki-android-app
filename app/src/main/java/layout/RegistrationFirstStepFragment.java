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
import android.widget.Button;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFirstStepFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFirstStepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFirstStepFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private Typeface fontIranSans;

    private TextInputEditText txtNameAndFamily;
    private TextInputEditText txtPhone;
    private TextInputEditText txtEmail;
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private TextInputEditText txtConfirmPassword;

    private TextInputLayout inputLayoutNameAndFamily;
    private TextInputLayout inputLayoutPhone;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutUsername;
    private TextInputLayout inputLayoutPassword;
    private TextInputLayout inputLayoutConfirmPassword;

    private TextView textView;

    private Button btnNextStep;


    public RegistrationFirstStepFragment() {
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
        return inflater.inflate(R.layout.fragment_registration_first_step, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        fontIranSans = FontManager.getTypeface(getContext(), FontManager.IRANSANS_TEXTS);

        txtNameAndFamily = (TextInputEditText) view.findViewById(R.id.txtNameAndFamily);
        txtPhone = (TextInputEditText) view.findViewById(R.id.txtPhone);
        txtEmail = (TextInputEditText) view.findViewById(R.id.txtEmail);
        txtUsername = (TextInputEditText) view.findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText) view.findViewById(R.id.txtPassword);
        txtConfirmPassword = (TextInputEditText) view.findViewById(R.id.txtConfirmPassword);

        inputLayoutNameAndFamily = (TextInputLayout) view.findViewById(R.id.inputLayoutNameAndFamily);
        inputLayoutPhone = (TextInputLayout) view.findViewById(R.id.inputLayoutPhone);
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.inputLayoutEmail);
        inputLayoutUsername = (TextInputLayout) view.findViewById(R.id.inputLayoutUsername);
        inputLayoutPassword = (TextInputLayout) view.findViewById(R.id.inputLayoutPassword);
        inputLayoutConfirmPassword = (TextInputLayout) view.findViewById(R.id.inputLayoutConfirmPassword);


        textView = (TextView) view.findViewById(R.id.textView);
        btnNextStep = (Button) view.findViewById(R.id.btnNextStep);

        FontManager.setFont(txtNameAndFamily, fontIranSans);
        FontManager.setFont(txtPhone, fontIranSans);
        FontManager.setFont(txtEmail, fontIranSans);
        FontManager.setFont(txtUsername, fontIranSans);
        FontManager.setFont(txtPassword, fontIranSans);
        FontManager.setFont(txtConfirmPassword, fontIranSans);

        FontManager.setFont(inputLayoutNameAndFamily, fontIranSans);
        FontManager.setFont(inputLayoutPhone, fontIranSans);
        FontManager.setFont(inputLayoutEmail, fontIranSans);
        FontManager.setFont(inputLayoutUsername, fontIranSans);
        FontManager.setFont(inputLayoutPassword, fontIranSans);
        FontManager.setFont(inputLayoutConfirmPassword, fontIranSans);


        FontManager.setFont(textView, fontIranSans);
        FontManager.setFont(btnNextStep, fontIranSans);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onRegistrationFirstStepInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
        void onRegistrationFirstStepInteraction();
    }
}
