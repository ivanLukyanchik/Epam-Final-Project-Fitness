package by.epam.fitness.command.access;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandEnum;
import by.epam.fitness.entity.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandAccess {
    public List<ActionCommand> getAvailableCommandTypesByUser(Optional<String> role) {
        List<ActionCommand> listAvailableCommands = new ArrayList<>();
        if(role.isPresent()) {
            switch (role.get()) {
                case UserRole.CLIENT: {
                    listAvailableCommands.addAll(getClientCommands());
                    listAvailableCommands.addAll(getCommonCoachUserCommands());
                    break;
                }
                case UserRole.COACH: {
                    listAvailableCommands.addAll(getCoachCommands());
                    listAvailableCommands.addAll(getCommonCoachUserCommands());
                    break;
                }
                case UserRole.ADMIN: {
                    listAvailableCommands.addAll(getAdminCommands());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown role");
                }
            }
        } else {
            listAvailableCommands.addAll(getCommandsForNotAuthorized());
        }
        return listAvailableCommands;
    }

    private List<ActionCommand> getClientCommands() {
        List<ActionCommand> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.REGISTER.getCommand());
        commandTypes.add(CommandEnum.CLIENT_PROFILE.getCommand());
        commandTypes.add(CommandEnum.UPDATE_GYM_MEMBERSHIP.getCommand());
        commandTypes.add(CommandEnum.SHOW_ORDER_PAGE.getCommand());
        commandTypes.add(CommandEnum.FIND_COACHES.getCommand());
        commandTypes.add(CommandEnum.CHOOSE_COACH.getCommand());
        commandTypes.add(CommandEnum.ADD_COMMENT.getCommand());
        commandTypes.add(CommandEnum.REJECT_COACH.getCommand());
        commandTypes.add(CommandEnum.DELETE_ACCOUNT.getCommand());
        commandTypes.add(CommandEnum.MODIFY_PROFILE_DATA.getCommand());
        commandTypes.add(CommandEnum.PASSWORD_RESTORE.getCommand());
        commandTypes.add(CommandEnum.ACTIVATE.getCommand());
        return commandTypes;
    }

    private List<ActionCommand> getCommonCoachUserCommands() {
        List<ActionCommand> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.LOCALE.getCommand());
        commandTypes.add(CommandEnum.NO_ACCESS.getCommand());
        commandTypes.add(CommandEnum.CLIENT_ORDERS.getCommand());
        commandTypes.add(CommandEnum.HOME_PAGE.getCommand());
        commandTypes.add(CommandEnum.GYM_PHOTOS.getCommand());
        commandTypes.add(CommandEnum.REJECT_EXERCISE.getCommand());
        commandTypes.add(CommandEnum.SHOW_CLIENT_EXERCISES.getCommand());
        commandTypes.add(CommandEnum.UPDATE_EXERCISE.getCommand());
        commandTypes.add(CommandEnum.SHOW_CLIENT_NUTRITION.getCommand());
        commandTypes.add(CommandEnum.UPDATE_NUTRITION.getCommand());
        commandTypes.add(CommandEnum.REJECT_NUTRITION.getCommand());
        commandTypes.add(CommandEnum.ADD_EXERCISE.getCommand());
        commandTypes.add(CommandEnum.LOGIN_USER.getCommand());
        commandTypes.add(CommandEnum.LOGOUT_USER.getCommand());
        commandTypes.add(CommandEnum.SHOW_LOGIN_PAGE.getCommand());
        return commandTypes;
    }

    private List<ActionCommand> getCoachCommands() {
        List<ActionCommand> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.ADD_EXERCISE.getCommand());
        commandTypes.add(CommandEnum.ADD_NUTRITION.getCommand());
        commandTypes.add(CommandEnum.COACH_CLIENTS.getCommand());
        commandTypes.add(CommandEnum.COACH_COMMENTS.getCommand());
        return commandTypes;
    }

    private List<ActionCommand> getAdminCommands() {
        List<ActionCommand> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.LOGIN_USER.getCommand());
        commandTypes.add(CommandEnum.LOGOUT_USER.getCommand());
        commandTypes.add(CommandEnum.LOCALE.getCommand());
        commandTypes.add(CommandEnum.NO_ACCESS.getCommand());
        commandTypes.add(CommandEnum.HOME_PAGE.getCommand());
        commandTypes.add(CommandEnum.SHOW_LOGIN_PAGE.getCommand());
        commandTypes.add(CommandEnum.ADMIN_COACHES.getCommand());
        commandTypes.add(CommandEnum.ADD_COACH.getCommand());
        commandTypes.add(CommandEnum.ADMIN_CLIENTS.getCommand());
        commandTypes.add(CommandEnum.CHANGE_CLIENT_ACTIVE.getCommand());
        commandTypes.add(CommandEnum.CLIENT_PROFILE.getCommand());
        commandTypes.add(CommandEnum.MODIFY_PROFILE_DATA.getCommand());
        return commandTypes;
    }

    private List<ActionCommand> getCommandsForNotAuthorized() {
        List<ActionCommand> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.LOCALE.getCommand());
        commandTypes.add(CommandEnum.REGISTER.getCommand());
        commandTypes.add(CommandEnum.ACTIVATE.getCommand());
        commandTypes.add(CommandEnum.PASSWORD_RESTORE.getCommand());
        commandTypes.add(CommandEnum.RESTORE.getCommand());
        commandTypes.add(CommandEnum.NO_ACCESS.getCommand());
        commandTypes.add(CommandEnum.HOME_PAGE.getCommand());
        commandTypes.add(CommandEnum.GYM_PHOTOS.getCommand());
        commandTypes.add(CommandEnum.LOGIN_USER.getCommand());
        commandTypes.add(CommandEnum.LOGOUT_USER.getCommand());
        commandTypes.add(CommandEnum.SHOW_LOGIN_PAGE.getCommand());
        return commandTypes;
    }
}
