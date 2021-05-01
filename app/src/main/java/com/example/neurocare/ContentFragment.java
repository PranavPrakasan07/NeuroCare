package com.example.neurocare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.core.FirestoreClient;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;
import java.util.Map;
import java.util.Objects;

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

    private DatabaseReference mDatabase;

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

        TextView top_heading = view.findViewById(R.id.top_header);
        TextView content_text = view.findViewById(R.id.content_text);


//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/neurocare-6c2b7.appspot.com/o/depression.svg?alt=media&token=c76b3893-9121-4534-b13c-c244845e4e42")
//                .into(image);

        back_button.setOnClickListener(v -> {
            HomeFragment ldf = new HomeFragment();

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().add(R.id.fragment, ldf).commit();
        });

        String value = "1";
        try {
            assert getArguments() != null;
            value = getArguments().getString("card_number");
            String heading = getArguments().getString("header");

            top_heading.setText(heading);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("articles").child("1");

            try {
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        Log.d("TAG", "Value is: " + map);

                        Log.d("STRINGVALUE", Objects.requireNonNull(dataSnapshot.getValue()).toString());

                        try {
                            assert map != null;
                            String s = map.toString();

                            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                    }
                };
                mDatabase.addValueEventListener(postListener);
            } catch (Exception e) {
                e.printStackTrace();
            }


//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//            Source source = Source.DEFAULT;
//            db.collection("articles")
//                    .get(source)
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot documentSnapshots) {
//                            if (documentSnapshots.isEmpty()) {
//                                return;
//                            } else {
//                                Toast.makeText(getActivity(), documentSnapshots.toString(), Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                        }
//                    });

            switch (value){
                case "1" : content_text.setText("Headache, dizziness or shaking.\n" +
                        "\n\n\n" +
                        "High blood pressure.\n" +
                        "\n\n\n" +
                        "Muscle tension or jaw clenching.\n" +
                        "\n\n\n" +
                        "Stomach or digestive problems.\n" +
                        "\n\n\n" +
                        "Aches and pains.\n" +
                        "\n\n\n" +
                        "Chest Pain or a feeling like your heart is racing.\n" +
                        "\n\n\n" +
                        "Exhaustion or trouble sleeping.\n" +
                        "\n\n");

                case "2" : content_text.setText("Headache, dizziness or shaking.\n" +
                        "\n\n\n" +
                        "High blood pressure.\n" +
                        "\n\n\n" +
                        "Muscle tension or jaw clenching.\n" +
                        "\n\n\n" +
                        "Stomach or digestive problems.\n" +
                        "\n\n\n" +
                        "Aches and pains.\n" +
                        "\n\n\n" +
                        "Chest Pain or a feeling like your heart is racing.\n" +
                        "\n\n\n" +
                        "Exhaustion or trouble sleeping.\n" +
                        "\n\n");

                case "3" : content_text.setText("\n" +
                                "Self love is about compassion for ourselves as human beings.\n" +
                                "\n" +
                                "It is a state of appreciation for oneself.\n" +
                                "\n" +
                                "It arises from conscious recognition and our worth.\n" +
                                "\n" +
                                "Self love begins with accepting small things about yourself and working your way upwards.\n" +
                                "\n" +
                                "Remember acceptance doesn’t have to be linear. If you have started accepting something about yourself that you didn’t like before but find yourself in a situation which makes you dislike it again. IT’S OKAY! you are human afterall.\n" +
                                "\n" +
                                "Self love is about being there yourself, and trusting yourself, allowing yourself to feel whatever you are at the moment.\n" +
                                "\n" +
                                "Self love is hard in a society where we are encouraged to pay attention to either’s judgements.\n" +
                                "\n" +
                                "It’s not enough to just to know about self love, because knowing is not half the battle. It’s a conscious effort. It must be practised."
                        );

                case "4" : content_text.setText("Headache, dizziness or shaking.\n" +
                        "\n\n\n" +
                        "High blood pressure.\n" +
                        "\n\n\n" +
                        "Muscle tension or jaw clenching.\n" +
                        "\n\n\n" +
                        "Stomach or digestive problems.\n" +
                        "\n\n\n" +
                        "Aches and pains.\n" +
                        "\n\n\n" +
                        "Chest Pain or a feeling like your heart is racing.\n" +
                        "\n\n\n" +
                        "Exhaustion or trouble sleeping.\n" +
                        "\n\n");

                case "5" : content_text.setText("Headache, dizziness or shaking.\n" +
                        "\n\n\n" +
                        "High blood pressure.\n" +
                        "\n\n\n" +
                        "Muscle tension or jaw clenching.\n" +
                        "\n\n\n" +
                        "Stomach or digestive problems.\n" +
                        "\n\n\n" +
                        "Aches and pains.\n" +
                        "\n\n\n" +
                        "Chest Pain or a feeling like your heart is racing.\n" +
                        "\n\n\n" +
                        "Exhaustion or trouble sleeping.\n" +
                        "\n\n");

                case "6" : content_text.setText("Headache, dizziness or shaking.\n" +
                        "\n\n\n" +
                        "High blood pressure.\n" +
                        "\n\n\n" +
                        "Muscle tension or jaw clenching.\n" +
                        "\n\n\n" +
                        "Stomach or digestive problems.\n" +
                        "\n\n\n" +
                        "Aches and pains.\n" +
                        "\n\n\n" +
                        "Chest Pain or a feeling like your heart is racing.\n" +
                        "\n\n\n" +
                        "Exhaustion or trouble sleeping.\n" +
                        "\n\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(), "I'm here" + value, Toast.LENGTH_SHORT).show();

        return view;
    }
}