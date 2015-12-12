package zy.myapplicationapplicationsynctest;

import java.util.List;

/**
 * Created by ZhichaoYang on 12/10/15.
 */
public class Latest {

    private List<TopStoriesEntity> top_stories;
    private List<StoriesEntity> stories;
    private String date;

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Latest{" +
                "top_stories=" + top_stories +
                ", stories=" + stories +
                ", date='" + date + '\'' +
                '}';

    }

    public static class TopStoriesEntity {
        private int id;
        private String title;
        private String ga_prefix;
        private String image;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "TopStoriesEntity{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", image='" + image + '\'' +
                    ", type" + type + '}';
        }
    }

    public static class StoriesEntity {
        /**
         * id : 7047795
         * title : 央视说要干预男男性行为，具体是怎么干预法？
         * ga_prefix : 081310
         * images : ["http://pic3.zhimg.com/fe27abc8f094510f2d3b4f3706108b56.jpg"]
         * type : 0
         */
        private int id;
        private String title;
        private String ga_prefix;
        private List<String> images;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "StoriesEntity{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", images=" + images +
                    ", type=" + type +
                    '}';
        }
    }
}
