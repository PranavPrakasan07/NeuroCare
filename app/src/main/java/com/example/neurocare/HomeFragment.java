package com.example.neurocare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int card_selected_number = 1;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView user_welcome = view.findViewById(R.id.top_header);

        TextView card1, card2, card3, card4, card5, card6;

        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        card5 = view.findViewById(R.id.card5);
        card6 = view.findViewById(R.id.card6);

        card1.setOnClickListener(v -> {
            card_selected_number = 1;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "1");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        card2.setOnClickListener(v -> {
            card_selected_number = 2;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "2");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        card3.setOnClickListener(v -> {
            card_selected_number = 3;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "3");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        card4.setOnClickListener(v -> {
            card_selected_number = 4;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "1");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        card5.setOnClickListener(v -> {
            card_selected_number = 5;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "5");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        card6.setOnClickListener(v -> {
            card_selected_number = 6;
            ContentFragment ldf = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("card_number", "6");
            ldf.setArguments(args);

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();

        });

        try {
            user_welcome.setText("Hello, Namaste, Bonjour, Hola, Konichiwa " + Objects.requireNonNull(LoginActivity.auth.getCurrentUser()).getDisplayName() + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView quote_image = view.findViewById(R.id.quote_image);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/neurocare-6c2b7.appspot.com/o/image.png?alt=media&token=433ecc98-e6a6-4f8e-91da-71cbb8558bb5")
                .into(quote_image);

        Fragment selectedContent = null;


        return view;
    }
}