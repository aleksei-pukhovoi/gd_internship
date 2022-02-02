package spring.model.dto;

import java.util.Objects;

public class PicDto {
    private Long id;

    private String caption;

    private String imageLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PicDto picDto = (PicDto) o;
        return Objects.equals(id, picDto.id) &&
                Objects.equals(caption, picDto.caption) &&
                Objects.equals(imageLink, picDto.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, imageLink);
    }

    @Override
    public String toString() {
        return "PicDto{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
