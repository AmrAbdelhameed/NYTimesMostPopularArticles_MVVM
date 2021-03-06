package com.example.nytimesmostpopulararticles_mvvm.data.local.db;

import androidx.lifecycle.LiveData;

import com.example.nytimesmostpopulararticles_mvvm.data.model.db.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DbRepository implements DbDataSource {
    private final AppDatabase mAppDatabase;

    @Inject
    public DbRepository(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> insertArticle(Article article) {
        return Observable.fromCallable(() -> {
            mAppDatabase.articleDao().insert(article);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteArticle(Article article) {
        return Observable.fromCallable(() -> {
            mAppDatabase.articleDao().delete(article);
            return true;
        });
    }

    @Override
    public Observable<Article> findById(long id) {
        return Observable.fromCallable(() -> mAppDatabase.articleDao().findById(id));
    }

    @Override
    public LiveData<List<Article>> getArticles() {
        return mAppDatabase.articleDao().loadAll();
    }
}
