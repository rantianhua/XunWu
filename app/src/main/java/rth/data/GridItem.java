package rth.data;

/**
 * Created by Rth on 2015/3/11.
 */
public class GridItem {

    private String classify;
    private int imageId;

    public GridItem(String classify,int imageId) {
        this.classify = classify;
        this.imageId = imageId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
