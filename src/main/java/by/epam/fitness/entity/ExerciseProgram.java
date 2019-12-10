package by.epam.fitness.entity;

import java.util.Objects;

/**
 * The type Exercise program.
 */
public class ExerciseProgram extends Entity {
    private Long id;
    private Exercise exercise;
    private int repeatNumber;
    private int setNumber;
    private Long programId;
    private int numberTrainDay;

    /**
     * Instantiates a new Exercise program.
     */
    public ExerciseProgram() {}

    /**
     * Instantiates a new Exercise program.
     *
     * @param id             the id
     * @param exercise       the exercise
     * @param repeatNumber   the repeat number
     * @param setNumber      the set number
     * @param programId      the program id
     * @param numberTrainDay the number train day
     */
    public ExerciseProgram(Long id, Exercise exercise, int repeatNumber, int setNumber, Long programId, int numberTrainDay) {
        this.id = id;
        this.exercise = exercise;
        this.repeatNumber = repeatNumber;
        this.setNumber = setNumber;
        this.programId = programId;
        this.numberTrainDay = numberTrainDay;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets exercise.
     *
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Sets exercise.
     *
     * @param exercise the exercise
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Gets repeat number.
     *
     * @return the repeat number
     */
    public int getRepeatNumber() {
        return repeatNumber;
    }

    /**
     * Sets repeat number.
     *
     * @param repeatNumber the repeat number
     */
    public void setRepeatNumber(int repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    /**
     * Gets set number.
     *
     * @return the set number
     */
    public int getSetNumber() {
        return setNumber;
    }

    /**
     * Sets set number.
     *
     * @param setNumber the set number
     */
    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    /**
     * Gets program id.
     *
     * @return the program id
     */
    public Long getProgramId() {
        return programId;
    }

    /**
     * Sets program id.
     *
     * @param programId the program id
     */
    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    /**
     * Gets number train day.
     *
     * @return the number train day
     */
    public int getNumberTrainDay() {
        return numberTrainDay;
    }

    /**
     * Sets number train day.
     *
     * @param numberTrainDay the number train day
     */
    public void setNumberTrainDay(int numberTrainDay) {
        this.numberTrainDay = numberTrainDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseProgram exerciseProgram = (ExerciseProgram) o;
        return  Objects.equals(getId(), exerciseProgram.getId()) &&
                getExercise().equals(exerciseProgram.getExercise()) &&
                Objects.equals(getProgramId(), exerciseProgram.getProgramId()) &&
                Objects.equals(getRepeatNumber(), exerciseProgram.getRepeatNumber()) &&
                Objects.equals(getSetNumber(), exerciseProgram.getSetNumber()) &&
                Objects.equals(getNumberTrainDay(), exerciseProgram.getNumberTrainDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getExercise(),getProgramId(),getRepeatNumber(),getSetNumber(),getNumberTrainDay());
    }
}
