package spring.model.dto;

import java.util.Objects;
import java.util.Set;

public class TopicDto {
    private Long id;

    private String name;

    private String message;

    private ForumDto forum;

    private Set<TagDto> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForumDto getForum() {
        return forum;
    }

    public void setForum(ForumDto forum) {
        this.forum = forum;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDto topicDto = (TopicDto) o;
        return Objects.equals(id, topicDto.id) &&
                name.equals(topicDto.name) &&
                Objects.equals(message, topicDto.message) &&
                Objects.equals(forum, topicDto.forum) &&
                Objects.equals(tags, topicDto.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, message, forum, tags);
    }

    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", forum=" + forum +
                ", tags=" + tags +
                '}';
    }
}
