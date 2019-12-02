package by.epam.fitness.builder;

import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ExerciseTableConst;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) throws ServiceException {
        try {
            Long id = resultSet.getLong(ExerciseTableConst.ID.getFieldName());
            String name = resultSet.getString(ExerciseTableConst.NAME.getFieldName());
            String description = resultSet.getString(ExerciseTableConst.DESCRIPTION.getFieldName());
            String base64Image = null;
            InputStream inputStream = null;
            Blob blob = resultSet.getBlob(ExerciseTableConst.IMAGE.getFieldName());
            if (blob != null) {
                base64Image = getBase64Image(blob);
                inputStream = blob.getBinaryStream();
            }
            return new Exercise(id, name, description, base64Image, inputStream);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private String getBase64Image(Blob blob) throws ServiceException {
        try (InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (SQLException | IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
