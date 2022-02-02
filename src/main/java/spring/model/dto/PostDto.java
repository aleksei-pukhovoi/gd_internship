package spring.model.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PostDto {

    private Long id;

    private String message;

    private LocalDate date;

    private Set<PicDto> pics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<PicDto> getPics() {
        return pics;
    }

    public void setPics(Set<PicDto> pics) {
        this.pics = pics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id) &&
                message.equals(postDto.message) &&
                Objects.equals(date, postDto.date) &&
                Objects.equals(pics, postDto.pics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, date, pics);
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", pics=" + pics +
                '}';
    }
}
