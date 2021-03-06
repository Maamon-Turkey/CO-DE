package com.example.wifiscan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wifiscan.R;
import com.example.wifiscan.adapter.PostAdapter;
import com.example.wifiscan.databinding.FragmentPostsBinding;
import com.example.wifiscan.ui.activities.NewPostActivity;
import com.example.wifiscan.ui.viewModels.PostViewModel;

public class Posts extends Fragment {


    public Posts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPostsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false);
        View root = binding.getRoot();
        PostViewModel postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        PostAdapter adapter = new PostAdapter();
        binding.postsrecycler.setAdapter(adapter);
        binding.postsrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.postsadd.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), NewPostActivity.class);
            startActivity(intent);
        });

        postViewModel.getPosts();
        postViewModel.posts.observe(getViewLifecycleOwner(), postModels -> {
            adapter.setPosts(postModels);
            adapter.notifyDataSetChanged();
        });
        return root;
    }
}