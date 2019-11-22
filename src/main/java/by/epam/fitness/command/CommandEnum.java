package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.admin.AddCoachCommand;
import by.epam.fitness.command.impl.admin.AdminClientsCommand;
import by.epam.fitness.command.impl.admin.AdminCoachesCommand;
import by.epam.fitness.command.impl.admin.ChangeClientActiveCommand;
import by.epam.fitness.command.impl.client.*;
import by.epam.fitness.command.impl.coach.CoachClientsCommand;
import by.epam.fitness.command.impl.comment.AddCommentCommand;
import by.epam.fitness.command.impl.coach.ShowCommentsCommand;
import by.epam.fitness.command.impl.exercise.*;
import by.epam.fitness.command.impl.nutrition.AddNutritionCommand;
import by.epam.fitness.command.impl.nutrition.RejectNutritionCommand;
import by.epam.fitness.command.impl.nutrition.ShowClientNutritionCommand;
import by.epam.fitness.command.impl.nutrition.UpdateNutritionCommand;
import by.epam.fitness.command.impl.servlet.Login;
import by.epam.fitness.command.impl.servlet.Logout;
import by.epam.fitness.command.impl.servlet.ModifyProfile;

public enum CommandEnum {
    LOCALE(new LocaleCommand()),
    REGISTER(new RegisterCommand()),
    ACTIVATE(new ActivationCommand()),
    RESTORE(new RestoreCommand()),
    PASSWORD_RESTORE(new PasswordRestoreCommand()),
    HOME_PAGE(new HomePageCommand()),
    CLIENT_PROFILE(new ClientProfileCommand()),
    NO_ACCESS(new NoAccessCommand()),
    GYM_PHOTOS(new GymPhotosCommand()),
    UPDATE_GYM_MEMBERSHIP(new UpdateMembershipCommand()),
    SHOW_ORDER_PAGE(new ShowOrderPageCommand()),
    CLIENT_ORDERS(new ClientOrdersCommand()),
    FIND_COACHES(new FindCoachesCommand()),
    CHOOSE_COACH(new ChooseCoachCommand()),
    ADD_COMMENT(new AddCommentCommand()),
    REJECT_COACH(new RejectCoachCommand()),
    ADD_EXERCISE(new AddExerciseCommand()),
    REJECT_EXERCISE(new RejectExerciseCommand()),
    SHOW_CLIENT_EXERCISES(new ShowClientExercisesCommand()),
    UPDATE_EXERCISE(new UpdateExerciseCommand()),
    SHOW_CLIENT_NUTRITION(new ShowClientNutritionCommand()),
    UPDATE_NUTRITION(new UpdateNutritionCommand()),
    ADD_NUTRITION(new AddNutritionCommand()),
    REJECT_NUTRITION(new RejectNutritionCommand()),
    COACH_CLIENTS(new CoachClientsCommand()),
    COACH_COMMENTS(new ShowCommentsCommand()),
    DELETE_ACCOUNT(new DeleteAccountCommand()),
    MODIFY_PROFILE_DATA(new ModifyProfile()),
    LOGIN_USER(new Login()),
    LOGOUT_USER(new Logout()),
    SHOW_LOGIN_PAGE(new LoginPageCommand()),
    ADMIN_COACHES(new AdminCoachesCommand()),
    ADD_COACH(new AddCoachCommand()),
    ADMIN_CLIENTS(new AdminClientsCommand()),
    CHANGE_CLIENT_ACTIVE(new ChangeClientActiveCommand());

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
