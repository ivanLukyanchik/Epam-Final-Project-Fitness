package by.epam.fitness.builder;

import by.epam.fitness.entity.Exercise;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ExerciseProgramTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseProgramBuilder implements Builder<ExerciseProgram> {
    @Override
    public ExerciseProgram build(ResultSet resultSet) throws ServiceException {
        Exercise exercise = new ExerciseBuilder().build(resultSet);
        try {
            long id = resultSet.getLong(ExerciseProgramTableConst.ID.getFieldName());
            int repeatNumber = resultSet.getInt(ExerciseProgramTableConst.REPEAT_NUMBER.getFieldName());
            int setNumber = resultSet.getInt(ExerciseProgramTableConst.SET_NUMBER.getFieldName());
            int numberOfTrainDay = resultSet.getInt(ExerciseProgramTableConst.NUMBER_TRAIN_DAY.getFieldName());
            long programId = resultSet.getLong(ExerciseProgramTableConst.PROGRAM_ID.getFieldName());
            return new ExerciseProgram(id, exercise, repeatNumber, setNumber, programId, numberOfTrainDay);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
