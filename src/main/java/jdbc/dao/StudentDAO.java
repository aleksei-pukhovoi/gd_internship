package jdbc.dao;

import jdbc.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO<Integer, Student> {

    private static final String GET_BY_ID = "SELECT * FROM students WHERE student_id = ?";

    private static final String GET_ALL = "SELECT * FROM students";

    private static final String CREATE =
            "INSERT INTO students(student_firstname, student_lastname, student_age, student_skill, student_mentor, "
                    + "student_project) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE students SET student_firstname = ?, student_lastname = ?, student_age = ?, student_skill = ?, "
                    + "student_mentor = ?, student_project = ? WHERE student_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM students WHERE student_id = ?";

    private final SkillDAO skillDAO;

    private final MentorDAO mentorDAO;

    private final ProjectDAO projectDAO;

    public StudentDAO(Connection connection, SkillDAO skillDAO, MentorDAO mentorDAO, ProjectDAO projectDAO) {
        super(connection);
        this.skillDAO = skillDAO;
        this.mentorDAO = mentorDAO;
        this.projectDAO = projectDAO;
    }

    @Override
    public Student findById(Integer id) {
        Student student = new Student();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student.setId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("student_firstname"));
                student.setLastName(resultSet.getString("student_lastname"));
                student.setAge(resultSet.getInt("student_age"));
                student.setSkill(skillDAO.findById(resultSet.getInt("student_skill")));
                student.setMentor(mentorDAO.findById(resultSet.getInt("student_mentor")));
                student.setProject(projectDAO.findById(resultSet.getInt("student_project")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("student_firstname"));
                student.setLastName(resultSet.getString("student_lastname"));
                student.setAge(resultSet.getInt("student_age"));
                student.setSkill(skillDAO.findById(resultSet.getInt("student_skill")));
                student.setMentor(mentorDAO.findById(resultSet.getInt("student_mentor")));
                student.setProject(projectDAO.findById(resultSet.getInt("student_project")));
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public Student create(Student entity) {
        if (entity == null) {
            throw new RuntimeException("Student can't be null");
        }
        Student student = null;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, statement);
            statement.setInt(4, getSkillId(entity));
            statement.setInt(5, getMentorId(entity));
            statement.setInt(6, getProjectId(entity));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                student = this.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student update(Student entity) {
        Student student;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            skillDAO.update(entity.getSkill());
            mentorDAO.update(entity.getMentor());
            projectDAO.update(entity.getProject());
            fillPreparedStatement(entity, statement);
            statement.setInt(4, getSkillId(entity));
            statement.setInt(5, getMentorId(entity));
            statement.setInt(6, getProjectId(entity));
            statement.setInt(7, entity.getId());
            statement.executeUpdate();
            student = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

   public void fillPreparedStatement(Student entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3, entity.getAge());
    }

    private int getMentorId(Student entity) {
        Mentor mentor = entity.getMentor();
        if (mentor == null) {
            throw new RuntimeException("Mentor can't be null");
        }
        int mentorId = mentor.getId();
        if (mentorId == 0) {
            return mentorDAO.create(mentor).getId();
        }
        mentor = mentorDAO.findById(mentor.getId());
        if (mentor == null) {
            throw new RuntimeException("Mentor is not in database");
        }
        return mentor.getId();
    }

    private int getProjectId(Student entity) {
        Project project = entity.getProject();
        if (project == null) {
            throw new RuntimeException("Project can't be null");
        }
        int projectId = project.getId();
        if (projectId == 0) {
            return projectDAO.create(project).getId();
        }
        project = projectDAO.findById(project.getId());
        if (project == null) {
            throw new RuntimeException("Project is not in database");
        }
        return project.getId();
    }

    private int getSkillId(Student entity) {
        Skill skill = entity.getSkill();
        if (skill == null) {
            throw new RuntimeException("Skill can't be null");
        }
        int skillId = skill.getId();
        if (skillId == 0) {
            return skillDAO.create(skill).getId();
        }
        skill = skillDAO.findById(skill.getId());
        if (skill == null) {
            throw new RuntimeException("Skill is not in database");
        }
        return skill.getId();
    }
}
