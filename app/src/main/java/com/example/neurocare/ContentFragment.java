package com.example.neurocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
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
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        ImageButton back_button = view.findViewById(R.id.back_button);
        ImageView image = view.findViewById(R.id.image);

//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/neurocare-6c2b7.appspot.com/o/depression.svg?alt=media&token=c76b3893-9121-4534-b13c-c244845e4e42")
//                .into(image);

        back_button.setOnClickListener(v -> {
            HomeFragment ldf = new HomeFragment();

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();
        });

        String value = null;
        try {
            assert getArguments() != null;
            value = getArguments().getString("card_number");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(), "I'm here" + value, Toast.LENGTH_SHORT).show();

        return view;
    }
}