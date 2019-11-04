package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.client.ClientProfileCommand;
import by.epam.fitness.command.impl.client.ModifyProfileCommand;

public enum CommandEnum {
    LOCALE(new LocaleCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    LOGOUT(new LogoutCommand()),
    ACTIVATE(new ActivationCommand()),
    RESTORE(new RestoreCommand()),
    PASSWORD_RESTORE(new PasswordRestoreCommand()),
    HOME_PAGE(new HomePageCommand()),
    CLIENT_PROFILE(new ClientProfileCommand()),
    NO_ACCESS(new NoAccessCommand()),
    MODIFY_PROFILE(new ModifyProfileCommand()),
    GYM_PHOTOS(new GymPhotosCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

    public static ActionCommand getCurrentCommand(String action) {
        return CommandEnum.valueOf(action.toUpperCase()).command;
    }
}
