package notes.basyk.quiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import notes.basyk.quiz.viewmodel.QuizListModel;
import notes.basyk.quiz.R;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private List<QuizListModel> quizListModels;
    private OnQuizListItemClick onQuizListItemClick;

    public QuizListAdapter(OnQuizListItemClick onQuizListItemClick){
        this.onQuizListItemClick = onQuizListItemClick;
    }

    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.listTitle.setText(quizListModels.get(position).getName());
        String listDescription = quizListModels.get(position).getDesc();
        if (listDescription.length() > 150){
            listDescription = listDescription.substring(0, 150);
        }
        holder.listDesc.setText(listDescription + "...");
        holder.listLevel.setText(quizListModels.get(position).getLevel());
        String imageUrl = quizListModels.get(position).getImage();
        Glide
                .with(holder.itemView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.listImage);

    }

    @Override
    public int getItemCount() {
        if (quizListModels == null){
            return 0;
        }else {
            return quizListModels.size();
        }
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView listImage;
        private TextView listTitle;
        private TextView listDesc;
        private TextView listLevel;
        private Button listBtn;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.list_image);
            listTitle = itemView.findViewById(R.id.list_title);
            listDesc = itemView.findViewById(R.id.list_desc);
            listLevel = itemView.findViewById(R.id.list_difficulty);
            listBtn = itemView.findViewById(R.id.list_btn);

            listBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onQuizListItemClick.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnQuizListItemClick{
        void onItemClicked(int pos);
    }
}
