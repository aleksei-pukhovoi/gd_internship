package spring.utils.impl;

import org.springframework.stereotype.Component;
import spring.model.dto.*;
import spring.model.entity.*;
import spring.utils.UserConverter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto toUserDto(User user) {
        return convertToUserDto(user);
    }

    @Override
    public List<UserDto> toUserDtos(List<User> users) {
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public User toUser(UserDto userDto) {
        return convertToUser(userDto);
    }

    @Override
    public List<User> toUsers(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(this::convertToUser)
                .collect(Collectors.toList());
    }

    private User convertToUser(UserDto userDto) {
        User user = new User();
        if (Objects.nonNull(userDto.getId())) {
            user.setId(userDto.getId());
        }
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        Set<Comment> comments = userDto.getComments().stream()
                .map(this::convertToComment)
                .peek(comment -> comment.setUser(user))
                .collect(Collectors.toSet());
        user.setComments(comments);

        Set<Post> posts = userDto.getPosts().stream()
                .map(postDto -> convertToPost(postDto,comments))
                .peek(post -> post.setUser(user))
                .peek(post -> post.getComments().addAll(comments))
                .collect(Collectors.toSet());
        user.setPosts(posts);

        Set<Topic> topics = userDto.getTopics().stream()
                .map(topicDto -> convertToTopic(topicDto, posts))
                .peek(topic -> topic.setUser(user))
                .peek(topic -> topic.getPosts().addAll(posts))
                .collect(Collectors.toSet());
        user.setTopics(topics);
        return user;
    }

    private Topic convertToTopic(TopicDto topicDto, Set<Post> posts) {
        Topic topic = new Topic();
        if (Objects.nonNull(topicDto.getId())) {
            topic.setId(topicDto.getId());
        }
        topic.setName(topicDto.getName());
        topic.setMessage(topicDto.getMessage());
        Forum forum = convertToForum(topicDto.getForum());
        topic.setForum(forum);
        forum.getTopics().add(topic);
        Set<Tag> tags = topicDto.getTags().stream()
                .map(this::convertToTag)
                .peek(tag -> tag.getTopics().add(topic))
                .collect(Collectors.toSet());
        topic.setTags(tags);
        posts.forEach(post -> post.setTopic(topic));
        return topic;
    }

    private Forum convertToForum(ForumDto forumDto) {
        Forum forum = new Forum();
        if (Objects.nonNull(forumDto.getId())) {
            forum.setId(forumDto.getId());
        }
        forum.setName(forumDto.getName());
        Section section = convertToSection(forumDto.getSection());
        forum.setSection(section);
        section.getForums().add(forum);
        return forum;
    }

    private Post convertToPost(PostDto postDto, Set<Comment> comments) {
        Post post = new Post();
        if (Objects.nonNull(postDto.getId())) {
            post.setId(postDto.getId());
        }
        post.setMessage(postDto.getMessage());
        post.setDate(postDto.getDate());
        Set<Pic> pics = postDto.getPics().stream()
                .map(this::convertToPic)
                .peek(pic -> pic.setPost(post))
                .collect(Collectors.toSet());
        post.setPics(pics);
        comments.forEach(comment -> comment.setPost(post));
        return post;
    }

    private Pic convertToPic(PicDto picDto) {
        Pic pic = new Pic();
        if (Objects.nonNull(picDto.getId())) {
            pic.setId(picDto.getId());
        }
        pic.setCaption(picDto.getCaption());
        pic.setImageLink(picDto.getImageLink());
        return pic;
    }

    private Comment convertToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        if (Objects.nonNull(commentDto.getId())) {
            comment.setId(commentDto.getId());
        }
        comment.setName(commentDto.getName());
        return comment;
    }

    private Tag convertToTag(TagDto tagDto) {
        Tag tag = new Tag();
        if (Objects.nonNull(tagDto.getId())) {
            tag.setId(tagDto.getId());
        }
        tag.setName(tagDto.getName());
        return tag;
    }

    private Section convertToSection(SectionDto sectionDto) {
        Section section = new Section();
        section.setId(sectionDto.getId());
        section.setName(sectionDto.getName());
        return section;
    }

    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        Set<TopicDto> topics = user.getTopics().stream()
                .map(this::convertToTopicDto)
                .collect(Collectors.toSet());
        userDto.setTopics(topics);
        Set<PostDto> posts = user.getPosts().stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toSet());
        userDto.setPosts(posts);
        Set<CommentDto> comments = user.getComments().stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toSet());
        userDto.setComments(comments);
        return userDto;
    }

    private TopicDto convertToTopicDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setName(topic.getName());
        topicDto.setMessage(topic.getMessage());
        topicDto.setForum(convertToForumDto(topic.getForum()));
        Set<TagDto> tags = topic.getTags().stream()
                .map(this::convertToTagDto)
                .collect(Collectors.toSet());
        topicDto.setTags(tags);
        return topicDto;
    }

    private TagDto convertToTagDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }

    private ForumDto convertToForumDto(Forum forum) {
        ForumDto forumDto = new ForumDto();
        forumDto.setId(forum.getId());
        forumDto.setName(forum.getName());
        forumDto.setSection(convertToSectionDto(forum.getSection()));
        return forumDto;
    }

    private SectionDto convertToSectionDto(Section section) {
        SectionDto sectionDto = new SectionDto();
        sectionDto.setId(section.getId());
        sectionDto.setName(section.getName());
        return sectionDto;
    }

    private PostDto convertToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setMessage(post.getMessage());
        postDto.setDate(post.getDate());
        Set<PicDto> pics = post.getPics().stream()
                .map(this::convertToPicDto)
                .collect(Collectors.toSet());
        postDto.setPics(pics);
        return postDto;
    }

    private PicDto convertToPicDto(Pic pic) {
        PicDto picDto = new PicDto();
        picDto.setId(pic.getId());
        picDto.setCaption(pic.getCaption());
        picDto.setImageLink(pic.getImageLink());
        return picDto;
    }

    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        return commentDto;
    }
}
