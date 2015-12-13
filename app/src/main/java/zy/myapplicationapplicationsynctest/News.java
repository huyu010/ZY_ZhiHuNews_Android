package zy.myapplicationapplicationsynctest;

import java.util.List;

/**
 * Created by ZhichaoYang on 12/13/15.
 */
public class News {
    private List<StoriesEntity> stories;
    private int color;
    private String description;
    private String name;
    private String background;
    private String image;
    private List<EditorsEntity> editors;
    private String image_source;

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<EditorsEntity> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorsEntity> editors) {
        this.editors = editors;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public static class EditorsEntity {
        /**
         * id : 70
         * bio : 微在 Wezeit 主编
         * name : 益康糯米
         * avatar : http://pic4.zhimg.com/068311926_m.jpg
         * url : http://www.zhihu.com/people/wezeit
         */
        private int id;
        private String bio;
        private String name;
        private String avatar;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
