package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.*;
import by.epam.fitness.service.*;
import by.epam.fitness.service.impl.*;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.COACH_CLIENT_ID;

public class ShowClientExercisesCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowClientExercisesCommand.class);
    private ClientService clientService = new ClientServiceImpl();
    private ProgramService programService = new ProgramServiceImpl();
    private CoachService coachService = new CoachServiceImpl();
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private ExerciseService exerciseService = new ExerciseServiceImpl();
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long userId = null;
        try {
            if (role.equals(UserRole.COACH)) {
                userId = getClientIdForAppropriateCoach(session,request);
            } else {
                userId = (Long) session.getAttribute(SessionAttributes.ID);
                if (!membershipValidChecker.isCurrentMembershipValid(userId)) {
                    request.setAttribute(JspConst.MEMBERSHIP_VALID, false);
                    return Page.EXERCISES;
                } else {
                    request.setAttribute(JspConst.MEMBERSHIP_VALID, true);
                }
            }
            Optional<Client> user = clientService.findById(userId);
            if (user.isPresent()) {
                if (coachService.findByClientId(userId).isEmpty()) {
                    request.setAttribute(JspConst.NO_COACH, true);
                }
                Long programId = user.get().getProgramId();
                Optional<Program> program = programService.findProgramById(programId);
                request.getSession().setAttribute(JspConst.PROGRAM, program.get());
                List<ExerciseProgram> clientExercises = exerciseProgramService.findExercisesByProgramId(programId);
                request.setAttribute(JspConst.CLIENT_EXERCISES, clientExercises);
                List<Exercise> allExercises = exerciseService.findAll();
                request.setAttribute(JspConst.All_EXERCISES, allExercises);
                page = Page.EXERCISES;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }

    private Long getClientIdForAppropriateCoach(HttpSession session, HttpServletRequest request) {
        String clientIdString = request.getParameter(COACH_CLIENT_ID);
        Long clientId;
        if (clientIdString == null) {
            clientId = (Long) session.getAttribute(COACH_CLIENT_ID);
        } else {
            clientId = Long.valueOf(request.getParameter(COACH_CLIENT_ID));
            session.setAttribute(COACH_CLIENT_ID,clientId);
        }
        return clientId;
    }
}
