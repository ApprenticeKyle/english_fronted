package com.xw.english.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xw.english.databinding.FragmentHomeBinding;
import com.xw.english.util.UploadUtils;

import java.io.File;
import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Context thiscontext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        thiscontext = container.getContext();
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        Button buttonUpload = binding.buttonUpload;
        buttonUpload.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(thiscontext).setMessage("测试弹框").create();
            dialog.show();
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void upload(View view) {
        new Thread(() -> {
            try {
                UploadUtils.getInstance().upload("path", new File(""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}