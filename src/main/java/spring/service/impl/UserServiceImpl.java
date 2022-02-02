package spring.service.impl;

import static spring.exception.errorCode.UserServiceErrorCode.USER_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.exception.ServiceException;
import spring.model.dto.*;
import spring.model.entity.*;
import spring.repository.*;
import spring.service.UserService;
import spring.utils.UserConverter;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TopicRepository topicRepository;

    @Autowired
    private final TagRepository tagRepository;

    @Autowired
    private final SectionRepository sectionRepository;

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PicRepository picRepository;

    @Autowired
    private final ForumRepository forumRepository;

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserConverter converter;

    public UserServiceImpl(UserRepository userRepository, TopicRepository topicRepository, TagRepository tagRepository,
                           SectionRepository sectionRepository, PostRepository postRepository,
                           PicRepository picRepository, ForumRepository forumRepository,
                           CommentRepository commentRepository, UserConverter converter) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.tagRepository = tagRepository;
        this.sectionRepository = sectionRepository;
        this.postRepository = postRepository;
        this.picRepository = picRepository;
        this.forumRepository = forumRepository;
        this.commentRepository = commentRepository;
        this.converter = converter;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findByOrderByNameAsc();
        return converter.toUserDtos(users);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(USER_NOT_EXIST));
        return converter.toUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = converter.toUser(userDto);
        Set<Post> posts = user.getPosts();
        posts.forEach(post -> {
            picRepository.saveAll(post.getPics());
            commentRepository.saveAll(post.getComments());
        });
        postRepository.saveAll(posts);
        Set<Topic> topics = user.getTopics();
        topics.forEach(topic -> {
            Forum forum = topic.getForum();
            Section section = forum.getSection();
            sectionRepository.save(section);
            forumRepository.save(forum);
            Set<Tag> tags = topic.getTags();
            tagRepository.saveAll(tags);
        });
        topicRepository.saveAll(topics);
        User savedUser = userRepository.save(user);
        return converter.toUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User updateUser = converter.toUser(userDto);
        User loadUser = userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(USER_NOT_EXIST));
        loadUser.setName(updateUser.getName());
        loadUser.setEmail(updateUser.getEmail());
        loadUser.setLogin(updateUser.getLogin());
        loadUser.setPassword(updateUser.getPassword());
        loadUser.setRole(updateUser.getRole());
        return converter.toUserDto(loadUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
