package spring.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Section {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @OneToMany(mappedBy = "section")
   private Set<Forum> forums = new HashSet<>();

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

   public Set<Forum> getForums() {
      return forums;
   }

   public void setForums(Set<Forum> forums) {
      this.forums = forums;
   }

   @Override
   public String toString() {
      return "Section{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Section section = (Section) o;
      return Objects.equals(id, section.id) &&
              name.equals(section.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name);
   }
}
