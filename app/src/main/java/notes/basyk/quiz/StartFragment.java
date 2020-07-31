package notes.basyk.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView startFeedbackText;
    private FirebaseAuth firebaseAuth;

    private NavController navController;

    public StartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        navController = Navigation.findNavController(view);
        progressBar = view.findViewById(R.id.start_progress);
        startFeedbackText = view.findViewById(R.id.start_feedback);
        startFeedbackText.setText("Checking User Account...");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null){

            startFeedbackText.setText("Create New Account...");

            firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startFeedbackText.setText("Account Created...");
                        navController.navigate(R.id.action_startFragment_to_listFragment);
                    }else {
                        Log.d("ADS", "Start Log: " + task.getException());
                    }
                }
            });
        }else {
            startFeedbackText.setText("Log In...");
            navController.navigate(R.id.action_startFragment_to_listFragment);
        }
    }
}