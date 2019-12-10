package by.epam.fitness.builder;

import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ClientTableConst;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 * The type Client builder.
 */
public class ClientBuilder implements Builder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws ServiceException {
        try {
            Long id = resultSet.getLong(ClientTableConst.ID.getFieldName());
            Long coach_id = resultSet.getLong(ClientTableConst.COACH_ID.getFieldName());
            String name = resultSet.getString(ClientTableConst.NAME.getFieldName());
            String surname = resultSet.getString(ClientTableConst.SURNAME.getFieldName());
            String login = resultSet.getString(ClientTableConst.LOGIN.getFieldName());
            String password = resultSet.getString(ClientTableConst.PASSWORD.getFieldName());
            String email = resultSet.getString(ClientTableConst.EMAIL.getFieldName());
            String userHash = resultSet.getString(ClientTableConst.HASH.getFieldName());
            boolean active = resultSet.getBoolean(ClientTableConst.ACTIVE.getFieldName());
            int membershipPurchasedNumber = resultSet.getInt(ClientTableConst.MEMBERSHIP_PURCHASED_NUMBER.getFieldName());
            float personalDiscount = resultSet.getFloat(ClientTableConst.PERSONAL_DISCOUNT.getFieldName());
            Long programId = resultSet.getLong(ClientTableConst.PROGRAM_ID.getFieldName());
            String base64Image = null;
            InputStream inputStream = null;
            Blob blob = resultSet.getBlob(ClientTableConst.IMAGE.getFieldName());
            if (blob != null) {
                base64Image = getBase64Image(blob);
                inputStream = blob.getBinaryStream();
            }
            return new Client(id, coach_id, name, surname, login, password, email, userHash, active,
                    membershipPurchasedNumber, personalDiscount, programId, base64Image, inputStream);
        } catch (SQLException  e) {
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
