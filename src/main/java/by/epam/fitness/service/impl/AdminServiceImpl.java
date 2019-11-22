package by.epam.fitness.service.impl;

import by.epam.fitness.dao.AdminDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.AdminDaoImpl;
import by.epam.fitness.entity.Admin;
import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Optional<Admin> checkAdminByLoginPassword(String login, String password) throws ServiceException {
        try {
            return adminDao.checkAdminByLoginPassword(login, DigestUtils.sha512Hex(password));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
