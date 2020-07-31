package notes.basyk.quiz.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<QuizListModel>> quizListModelData = new MutableLiveData<>();
    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public QuizListViewModel(){
        firebaseRepository.getQuizData();
    }

    @Override
    public void quizListDataAdded(List<QuizListModel> quizListModelsList) {
        quizListModelData.setValue(quizListModelsList);
    }

    @Override
    public void onError(Exception e) {

    }

    public LiveData<List<QuizListModel>> getQuizListModelData() {
        return quizListModelData;
    }


}
