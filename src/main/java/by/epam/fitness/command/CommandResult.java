package by.epam.fitness.command;

import java.util.Objects;

/**
 * The type Command result.
 */
public class CommandResult {
    private String page;
    private boolean isRedirect;

    /**
     * Instantiates a new Command result.
     */
    public CommandResult() {}

    /**
     * Instantiates a new Command result.
     *
     * @param page the page
     */
    public CommandResult(String page) {
        this.page = page;
        isRedirect = false;
    }

    /**
     * Instantiates a new Command result.
     *
     * @param page       the page
     * @param isRedirect the is redirect
     */
    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Is redirect boolean.
     *
     * @return the boolean
     */
    public boolean isRedirect() {
        return isRedirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return isRedirect == that.isRedirect &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, isRedirect);
    }
}
