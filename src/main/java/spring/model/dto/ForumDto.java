package spring.model.dto;

import java.util.Objects;

public class ForumDto {
    private Long id;

    private String name;

    private SectionDto section;

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

    public SectionDto getSection() {
        return section;
    }

    public void setSection(SectionDto section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumDto forumDto = (ForumDto) o;
        return Objects.equals(id, forumDto.id) &&
                name.equals(forumDto.name) &&
                Objects.equals(section, forumDto.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, section);
    }

    @Override
    public String toString() {
        return "ForumDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section=" + section +
                '}';
    }
}
