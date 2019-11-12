package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.entity.Program;
import by.epam.fitness.entity.User;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.service.impl.ProgramServiceImpl;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.PROGRAM;

public class ShowClientExercisesCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowClientExercisesCommand.class);
    private UserService userService = new UserServiceImpl();
    private ProgramService programService = new ProgramServiceImpl();
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long userId = null;
        try {
            if (role.equals(UserRole.COACH)) {
                // FIXME: 12.11.2019 continue for coach
            } else {
                userId = (Long) session.getAttribute(SessionAttributes.ID);
                if (!membershipValidChecker.isCurrentMembershipValid(userId)) {
                    request.setAttribute(JspConst.MEMBERSHIP_VALID, false);
                    return Page.EXERCISES;
                } else {
                    request.setAttribute(JspConst.MEMBERSHIP_VALID, true);
                }
            }

            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                Long programId = user.get().getProgramId();
                Optional<Program> program = programService.findProgramById(programId);
                request.setAttribute(PROGRAM, program.get());
                List<ExerciseProgram> exercises = exerciseProgramService.findExercisesByProgramId(programId);
                request.setAttribute(JspConst.EXERCISES, exercises);
                page = Page.EXERCISES;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }
}
