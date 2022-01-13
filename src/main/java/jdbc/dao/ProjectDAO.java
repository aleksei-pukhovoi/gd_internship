package jdbc.dao;

import jdbc.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends BaseDAO<Integer, Project> {
    private static final String GET_BY_ID = "SELECT * FROM projects WHERE project_id = ?";

    private static final String GET_ALL = "SELECT * FROM projects";

    private static final String CREATE = "INSERT INTO projects(project_name) VALUES(?)";

    private static final String UPDATE = "UPDATE projects SET project_name = ? WHERE project_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM projects WHERE project_id = ?";

    public ProjectDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Project findById(Integer id) {
        Project project = new Project();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                project.setId(resultSet.getInt("project_id"));
                project.setName(resultSet.getString("project_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("project_id"));
                project.setName(resultSet.getString("project_name"));
                projectList.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projectList;
    }

    @Override
    public Project create(Project entity) {
        if (entity == null) {
            throw new RuntimeException("Project can't be null");
        }
        Project project = null;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                project = this.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
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
    public Project update(Project entity) {
        Project project;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
            project = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }
}
