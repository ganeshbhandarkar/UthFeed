package com.ganeshbhandarkar.uthfeed.Articles;

public class ArticleModel {

    int articleImage;
    String articleHeading;
    String articleAuthor;
    String articleUrl;

    public ArticleModel(int articleImage, String articleHeading, String articleAuthor,String articleUrl) {
        this.articleImage = articleImage;
        this.articleHeading = articleHeading;
        this.articleAuthor = articleAuthor;
        this.articleUrl = articleUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public int getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(int articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleHeading() {
        return articleHeading;
    }

    public void setArticleHeading(String articleHeading) {
        this.articleHeading = articleHeading;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }
}
