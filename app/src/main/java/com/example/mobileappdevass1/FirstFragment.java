package com.example.mobileappdevass1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mobileappdevass1.databinding.FragmentFirstBinding;

import java.math.BigDecimal;

public class FirstFragment extends Fragment {

    //Declare variables
    private EditText mpaview;
    private EditText interestview;
    private EditText periodview;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view values in each text box
        mpaview = view.findViewById(R.id.editTextNumberDecimal);
        interestview = view.findViewById(R.id.editTextNumberDecimal2);
        periodview = view.findViewById(R.id.editTextNumberDecimal5);

        //when button is clicked
        binding.buttonFirst.setOnClickListener(view1 -> {

            //receive values inputted as BigDecimals for less error in calculations
            BigDecimal mpa = BigDecimal.valueOf(Double.parseDouble(mpaview.getText().toString()));
            BigDecimal interest = BigDecimal.valueOf((Double.parseDouble(interestview.getText().toString()))/12/100);
            BigDecimal period = BigDecimal.valueOf(Double.parseDouble(periodview.getText().toString()));

            //Create a value for power. Use double and int values in this case.
            double pow = Math.pow(1 + interest.doubleValue(), period.intValue());

            //do math for numerator and denominator
            BigDecimal numer = interest.multiply(BigDecimal.valueOf(pow));
            BigDecimal denom = BigDecimal.valueOf(pow - 1);

            //divide numerator by the denominator, then multiply by mpa.
            BigDecimal ans = mpa.multiply(numer.divide(denom));

            //bundle the answer
            Bundle bundle = new Bundle();
            bundle.putSerializable("Answer", ans);

            //go to the next fragment along with the answer
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}