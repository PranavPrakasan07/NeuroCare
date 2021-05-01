package com.example.neurocare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView profile_photo;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name = view.findViewById(R.id.user_name);
        TextView email = view.findViewById(R.id.email);

        ImageButton logout = view.findViewById(R.id.logout_button);

        profile_photo = view.findViewById(R.id.profile_photo);

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        profile_photo.setClipToOutline(true);

        FirebaseUser currentUser = LoginActivity.auth.getCurrentUser();

        if (currentUser != null) {
//            logout.setVisibility(View.VISIBLE);
            Picasso.get().load(Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getPhotoUrl())
                    .into(profile_photo);
            name.setText(Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getDisplayName());
            email.setText(Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getEmail());
        } else {
//            logout.setVisibility(View.GONE);
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/neurocare-6c2b7.appspot.com/o/profile.png?alt=media&token=4bc702ec-92c7-424b-be76-b20d49e24ee7")
                    .into(profile_photo);
        }


        return view;
    }
}