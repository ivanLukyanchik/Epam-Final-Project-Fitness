package by.epam.fitness.service;

import by.epam.fitness.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> checkAdminByLoginPassword(String login, String password) throws ServiceException;
}
