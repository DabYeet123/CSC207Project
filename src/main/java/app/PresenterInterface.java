package app;

/**
 * A generic interface for presenters in the application. This interface defines
 * the basic contract for presenters that handle the display and disposal of views.
 *
 * @param <T> the type of controller that the presenter works with
 */
public interface PresenterInterface<T> {
    /**
     * Displays the associated view. This method is responsible for showing the
     * view to the user.
     */
    void showView();

    /**
     * Disposes of the associated view. This method is responsible for cleaning
     * up and removing the view when it's no longer needed.
     */
    void disposeView();
}
