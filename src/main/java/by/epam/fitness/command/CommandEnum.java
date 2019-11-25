package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.admin.*;
import by.epam.fitness.command.impl.client.*;
import by.epam.fitness.command.impl.coach.CoachClientsCommand;
import by.epam.fitness.command.impl.coach.ShowCommentsCommand;
import by.epam.fitness.command.impl.comment.AddCommentCommand;
import by.epam.fitness.command.impl.exercise.AddExerciseCommand;
import by.epam.fitness.command.impl.exercise.RejectExerciseCommand;
import by.epam.fitness.command.impl.exercise.ShowClientExercisesCommand;
import by.epam.fitness.command.impl.exercise.UpdateExerciseCommand;
import by.epam.fitness.command.impl.nutrition.AddNutritionCommand;
import by.epam.fitness.command.impl.nutrition.RejectNutritionCommand;
import by.epam.fitness.command.impl.nutrition.ShowClientNutritionCommand;
import by.epam.fitness.command.impl.nutrition.UpdateNutritionCommand;

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
    LOGIN_USER(new LoginCommand()),
    LOGOUT_USER(new LogoutCommand()),
    SHOW_LOGIN_PAGE(new LoginPageCommand()),
    SHOW_REGISTER_PAGE(new RegisterPageCommand()),
    ADMIN_COACHES(new AdminCoachesCommand()),
    ADD_COACH(new AddCoachCommand()),
    ADMIN_CLIENTS(new AdminClientsCommand()),
    ADMIN_EXERCISES(new AdminExercisesCommand()),
    DELETE_EXERCISE(new DeleteExerciseCommand()),
    CHANGE_CLIENT_ACTIVE(new ChangeClientActiveCommand()),
    ADMIN_COMMENTS(new AdminCommentsCommand()),
    DELETE_COMMENT(new DeleteCommentCommand()),
    ADMIN_ORDERS(new AdminOrdersCommand());

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
