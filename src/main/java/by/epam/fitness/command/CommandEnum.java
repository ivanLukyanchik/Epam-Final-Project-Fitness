package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.client.*;
import by.epam.fitness.command.impl.comment.AddCommentCommand;

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
    GYM_PHOTOS(new GymPhotosCommand()),
    UPDATE_GYM_MEMBERSHIP(new UpdateMembershipCommand()),
    SHOW_ORDER_PAGE(new ShowOrderPageCommand()),
    CLIENT_ORDERS(new ClientOrdersCommand()),
    FIND_COACHES(new FindCoachesCommand()),
    CHOOSE_COACH(new ChooseCoachCommand()),
    ADD_COMMENT(new AddCommentCommand()),
    REJECT_COACH(new RejectCoachCommand());

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
