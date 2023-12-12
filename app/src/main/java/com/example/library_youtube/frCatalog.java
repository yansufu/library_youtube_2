package com.example.library_youtube;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frCatalog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frCatalog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frCatalog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frCatalog.
     */
    // TODO: Rename and change types and number of parameters
    public static frCatalog newInstance(String param1, String param2) {
        frCatalog fragment = new frCatalog();
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

        View view =  inflater.inflate(R.layout.fragment_fr_catalog, container, false);

        FirebaseApp.initializeApp(getContext());
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FloatingActionButton add = view.findViewById(R.id.addBook);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.add_book_dialog, null);
                TextInputLayout titleLayout, authorLayout, copyLayout;
                titleLayout = view1.findViewById(R.id.titleLayout);
                authorLayout = view1.findViewById(R.id.authorLayout);
                copyLayout = view1.findViewById(R.id.copyLayout);

                TextInputEditText titleET, authorET, copyET;
                titleET = view1.findViewById(R.id.titleET);
                authorET = view1.findViewById(R.id.authorET);
                copyET = view1.findViewById(R.id.copyET);

                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Add")
                        .setView(view1)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Objects.requireNonNull(titleET.getText()).toString().isEmpty()) {
                                    titleLayout.setError("This field is required!");
                                } else if (Objects.requireNonNull(authorET.getText()).toString().isEmpty()) {
                                    authorLayout.setError("This field is required!");
                                } else if (Objects.requireNonNull(authorET.getText()).toString().isEmpty()) {
                                    copyLayout.setError("This field is required!");

                                } else {
                                    ProgressDialog dialog = new ProgressDialog(getContext());
                                    dialog.setMessage("Storing in Database...");
                                    dialog.show();
                                    Book book = new Book();
                                    book.setTitle(titleET.getText().toString());
                                    book.setAuthor(authorET.getText().toString());
                                    book.setCopy(copyET.getText().toString());
                                    database.getReference().child("Books").push().setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            dialogInterface.dismiss();
                                            Toast.makeText(getContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                            Toast.makeText(getContext(), "There was an error while saving data", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        TextView empty = view.findViewById(R.id.empty);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        database.getReference().child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Book> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    Objects.requireNonNull(book).setKey(dataSnapshot.getKey());
                    arrayList.add(book);
                }

                if (arrayList.isEmpty()) {
                    empty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    empty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                BookAdapter adapter = new BookAdapter(getContext(), arrayList);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(Book book) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_book_dialog, null);
                        TextInputLayout titleLayout, authorLayout, copyLayout;
                        TextInputEditText titleET, authorET, copyET;

                        titleET = view.findViewById(R.id.titleET);
                        authorET = view.findViewById(R.id.authorET);
                        copyET = view.findViewById(R.id.copyET);
                        titleLayout = view.findViewById(R.id.titleLayout);
                        authorLayout = view.findViewById(R.id.authorLayout);
                        copyLayout = view.findViewById(R.id.copyLayout);

                        titleET.setText(book.getTitle());
                        authorET.setText(book.getAuthor());
                        copyET.setText(book.getCopy());

                        ProgressDialog progressDialog = new ProgressDialog(getContext());

                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("Edit")
                                .setView(view)
                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Objects.requireNonNull(titleET.getText()).toString().isEmpty()) {
                                            titleLayout.setError("This field is required!");
                                        } else if (Objects.requireNonNull(authorET.getText()).toString().isEmpty()) {
                                            authorLayout.setError("This field is required!");
                                        } else if (Objects.requireNonNull(copyET.getText()).toString().isEmpty()) {
                                            copyLayout.setError("This field is required!");
                                        } else {
                                            progressDialog.setMessage("Saving...");
                                            progressDialog.show();
                                            Book book = new Book();
                                            book.setTitle(titleET.getText().toString());
                                            book.setAuthor(authorET.getText().toString());
                                            book.setCopy(copyET.getText().toString());
                                            database.getReference().child("Books").push().setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    progressDialog.dismiss();
                                                    dialogInterface.dismiss();
                                                    Toast.makeText(getContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(getContext(), "There was an error while saving data", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                })
                                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        progressDialog.setTitle("Deleting...");
                                        progressDialog.show();
                                        database.getReference().child("Books").child(book.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }
                                }).create();
                        alertDialog.show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            // Inflate the layout for this fragment

    });
        return view;
    }
}