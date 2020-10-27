package com.example.newsflash;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsflash.model.News;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    ArrayList<News> newsList;
    ArrayList<News> newsListAll;


    class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView source;
        public TextView date;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
           title = itemView.findViewById(R.id.news_title);
           source = itemView.findViewById(R.id.news_source);
           date=itemView.findViewById(R.id.news_date);
        }
    }
    public NewsAdapter(ArrayList<News> newsArrayList){
        newsList=newsArrayList;
        newsListAll=new ArrayList<>(newsList);
     }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        final News news=newsList.get(i);
        newsViewHolder.source.setText(news.getSourceName());
        newsViewHolder.title.setText(news.getTitle());
        newsViewHolder.date.setText(news.getPublishedDate());
        final String url=news.getUrl();
        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),SingleNewsActivity.class);
                i.putExtra("url", url);
                view.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//    Filter filter=new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            List<News> filteredList = new ArrayList<>();
//            if(charSequence==null || charSequence.length()==0){
//                filteredList.addAll(newsListAll);
//            }else{
//                for(News i : newsListAll){
//                    if(i.getSourceName().toLowerCase().contains(charSequence.toString().toLowerCase())){
//                        filteredList.add(i);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            newsList.clear();
//            newsList.addAll((Collection<? extends News>) filterResults.values);
//            notifyDataSetChanged();
//        }
//    };

}


