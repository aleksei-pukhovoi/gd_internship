package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.model.dto.UserDto;
import spring.model.entity.*;
import spring.service.UserService;
import spring.utils.UserConverter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
public class SpringExampleApplication implements CommandLineRunner {

	@Autowired
	private final UserService service;

	@Autowired
	private final UserConverter converter;

	public SpringExampleApplication(UserService service, UserConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Post post = createPost();
		Pic pic = createPic();
		post.setPics(new HashSet<>(Collections.singleton(pic)));
		pic.setPost(post);

		Topic topic = createTopic();
		Tag tag = createTag();
		tag.setTopics(new HashSet<>(Collections.singleton(topic)));
		topic.setTags(new HashSet<>(Collections.singleton(tag)));
		post.setTopic(topic);
		topic.setPosts(new HashSet<>(Collections.singleton(post)));

		Section section = createSection();
		Forum forum = createForum();
		section.setForums(new HashSet<>(Collections.singleton(forum)));
		forum.setSection(section);
		forum.setTopics(new HashSet<>(Collections.singleton(topic)));
		topic.setForum(forum);

		User user = createNewUser();
		user.setPosts(new HashSet<>(Collections.singleton(post)));
		post.setUser(user);
		topic.setUser(user);
		user.setTopics(new HashSet<>(Collections.singleton(topic)));
		UserDto userDto = converter.toUserDto(user);
		service.createUser(userDto);
	}

	private User createNewUser() {
		User user = new User();
		user.setName("Alex");
		user.setLogin("al");
		user.setPassword("asdf");
		user.setEmail("alex@gmail.com");
		user.setRole(Role.USER);
		return user;
	}

	private Section createSection() {
		Section section = new Section();
		section.setName("Cold steel and projectile weapons");
		return section;
	}

	private Forum createForum(){
		Forum forum  = new Forum();
		forum.setName("Sharpening of cutting tool");
		return forum;
	}

	private Topic createTopic() {
		Topic topic = new Topic();
		topic.setName("Identify stone");
		return topic;
	}

	private Tag createTag() {
		Tag tag = new Tag();
		tag.setName("Washita");
		return tag;
	}

	private Pic createPic() {
		Pic pic = new Pic();
		pic.setCaption("Washita oil stone");
		pic.setImageLink("http://zatochiklinok.ru/wp-content/gallery/washita/0023-lw.JPG");
		return pic;
	}

	private Post createPost(){
		Post post = new Post();
		post.setMessage("Help me to identify sharpening stone from ebay");
		post.setDate(LocalDate.now());
		return post;
	}
}
