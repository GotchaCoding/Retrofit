package org.techtown.retrofit2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    ArrayList<Movie> items = new ArrayList();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item) {
        items.add(item);
    }

    public Movie getItem(int position) {
        return items.get(position);
    }

    public void addItems(List<Movie> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
        //TODO 추가된 영역만 갱신
        //notifyItemRangeInserted();
    }

    public void removeItem(Movie item) {
        //TODO 아이템 삭제
    }

    public ArrayList<Movie> getItems() {
        return items;
    }

    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        //TODO 삭제 뷰 추가

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView_adapter_movie);
            textView2 = itemView.findViewById(R.id.textView2_adapter_movie);
        }

        public void setItem(Movie item) {
            textView.setText(item.getMovieNm());
            textView2.setText(item.getGenreAlt() + " 명");
            //TODO 삭제 뷰 onClickListener 붙여줌 , adapter 에 있는 items 를 viewholder 에서 알수가 없다. 어떻게 해결할까!?

        }
    }

}
