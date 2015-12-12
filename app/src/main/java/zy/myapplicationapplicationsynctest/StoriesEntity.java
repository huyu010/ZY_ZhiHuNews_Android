package zy.myapplicationapplicationsynctest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZhichaoYang on 12/10/15.
 */
public class StoriesEntity implements Serializable {
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
//                ", ga_prefix='" + ga_prefix + '\'' +
                ", images=" + images +
                ", type=" + type +
                '}';

    }
}
