package com.example.mobileappdevass1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.math.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobileappdevass1.databinding.FragmentSecondBinding;

import java.math.BigDecimal;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view the ansview box
        TextView ansview = view.findViewById(R.id.textView3);

        //check if argument is null
        assert getArguments() != null;

        //get ans from bundle as a BigDecimal
        BigDecimal ans = (BigDecimal) getArguments().getSerializable("Answer");

        //make sure ans isnt null
        assert ans != null;

        //round ans to two decimal places
        ans = ans.setScale(2, RoundingMode.HALF_UP);

        //send ans to the screen
        ansview.setText("$"+ans);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}