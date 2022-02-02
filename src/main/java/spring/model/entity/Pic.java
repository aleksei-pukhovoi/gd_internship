package spring.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;

    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Pic{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pic pic = (Pic) o;
        return Objects.equals(id, pic.id) &&
                Objects.equals(caption, pic.caption) &&
                Objects.equals(imageLink, pic.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, imageLink);
    }
}
