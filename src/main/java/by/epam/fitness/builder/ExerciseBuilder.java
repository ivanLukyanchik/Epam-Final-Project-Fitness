package by.epam.fitness.builder;

import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ExerciseTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) throws ServiceException {
        try {
            Long id = resultSet.getLong(ExerciseTableConst.ID.getFieldName());
            String name = resultSet.getString(ExerciseTableConst.NAME.getFieldName());
            String description = resultSet.getString(ExerciseTableConst.DESCRIPTION.getFieldName());
            String image = resultSet.getString(ExerciseTableConst.IMAGE.getFieldName());
            return new Exercise(id, name, description, image);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
