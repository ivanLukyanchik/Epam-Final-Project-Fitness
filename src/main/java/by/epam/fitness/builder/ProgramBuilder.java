package by.epam.fitness.builder;

import by.epam.fitness.entity.Program;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ProgramTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Program builder.
 */
public class ProgramBuilder implements Builder<Program> {
    @Override
    public Program build(ResultSet resultSet) throws ServiceException {
        try {
            Long id = resultSet.getLong(ProgramTableConst.ID.getFieldName());
            Long nutritionId = resultSet.getLong(ProgramTableConst.NUTRITION_ID.getFieldName());
            int trainsPerWeek = resultSet.getInt(ProgramTableConst.TRAINS_PER_WEEK.getFieldName());
            return new Program(id,nutritionId,trainsPerWeek);
        }catch (SQLException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
