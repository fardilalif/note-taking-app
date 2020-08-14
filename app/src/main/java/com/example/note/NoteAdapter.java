package com.example.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    OnItemCLickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote()) &&
                    oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_cardview, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textView_title.setText(currentNote.getNote());
        holder.textView_description.setText(currentNote.getDescription());
    }

    public Note getItemAt(int position){
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textView_title;
        private TextView textView_description;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_description = itemView.findViewById(R.id.textView_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.OnItemClick(getItem(position));
                    }
                }
            });
        }

    }

    public interface OnItemCLickListener{
        void OnItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemCLickListener listener){
        this.listener = listener;
    }
}
